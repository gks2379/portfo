# PDF 다운로드 기능 구현 및 에러 해결 로그

## 1. 기능 구현 요약

### 1.1 라이브러리 추가 (`build.gradle`)
- **Flying Saucer (`flying-saucer-pdf-openpdf`)**: HTML을 PDF로 렌더링하기 위해 추가.
- 버전: `9.1.22`

### 1.2 한글 폰트 적용
- **원인**: 서버 사이드 렌더링 시 시스템 폰트가 없으면 한글이 깨짐.
- **해결**:
  - `src/main/resources/static/fonts/NanumGothic.ttf` 다운로드 및 배치.
  - `index.html` 내 CSS `@font-face` 설정 추가.
  - `PdfService`에서 `renderer.getFontResolver().addFont(...)`로 폰트 등록.

### 1.3 서비스 및 컨트롤러 구현
- **`PdfService.java`**:
  - Thymeleaf `TemplateEngine`을 사용하여 HTML을 문자열로 렌더링.
  - `ITextRenderer`를 사용하여 HTML 문자열을 PDF 바이트 배열로 변환.
- **`PdfController.java`**:
  - `/download-pdf` 엔드포인트 생성.
  - `isPdf=true` 변수를 모델에 담아 템플릿에 전달.
  - PDF 파일 다운로드 응답 반환.

### 1.4 UI 수정 (`index.html`)
- **다운로드 버튼**: 메인 화면 Hero 섹션에 추가.
- **조건부 렌더링**: PDF 생성 시 불필요한 요소(상단 메뉴, 하단 Contact, 버튼들)를 `th:unless="${isPdf}"`를 사용하여 숨김 처리.

---

## 2. 발생한 에러 및 해결 (XHTML 호환성 문제)

Flying Saucer 라이브러리는 엄격한 **XHTML** 문법을 요구하기 때문에, 일반적인 HTML5 문법에서 허용되는 생략된 태그들이 에러를 유발함.

### 2.1 앰퍼샌드(&) 에러
- **에러 메시지**: `org.xml.sax.SAXParseException: "display" 엔티티에 대한 참조는 ';' 구분자로 끝나야 합니다.`
- **원인**: Google Fonts URL 파라미터 연결자 `&`가 XML 엔티티로 인식됨.
- **해결**: `&`를 `&amp;`로 변경.
  - 변경 전: `...family=Noto+Sans+KR...&display=swap`
  - 변경 후: `...family=Noto+Sans+KR...&amp;display=swap`

### 2.2 닫히지 않은 태그 에러
- **에러 메시지**: `org.xml.sax.SAXParseException: 요소 유형 "link"은(는) 짝이 맞는 종료 태그 "</link>"(으)로 종료되어야 합니다.`
- **원인**: HTML5에서는 `<meta>`, `<link>`, `<br>`, `<img`> 등의 태그를 닫지 않아도 되지만, XHTML에서는 반드시 닫아야 함.
- **해결**: 모든 `<meta>`와 `<link>` 태그에 Self-closing 슬래시(`/>`) 추가.
  - 예: `<meta charset="UTF-8">` → `<meta charset="UTF-8" />`
