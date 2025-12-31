# AI Context: Hans Backend Portfolio Project

이 파일은 AI 에이전트가 프로젝트의 컨텍스트를 빠르게 파악하고 작업을 이어갈 수 있도록 작성된 기술 명세서 및 히스토리 기록입니다.

## 1. 프로젝트 개요
- **목적**: 백엔드 개발자 포트폴리오 웹사이트 구축
- **기술 스택**: Java 17, Spring Boot 3.x, Thymeleaf, H2 Database (In-memory)
- **배포 환경**: Render (Docker 기반 배포), AWS 등 확장 고려
- **주요 기능**: 
    - 경력 기술서(Resume) 웹 페이지 렌더링 (원페이지 스타일)
    - Contact Me 이메일 발송 기능 (SMTP)
    - PDF 저장 기능 (html2pdf.js 활용 시도 중 -> 브라우저 인쇄 모드 권장)

## 2. 프로젝트 구조 (핵심 파일)
```text
/src/main/java/com/hans/portfolio/
├── PortfolioApplication.java  # 메인 실행 파일
├── controller/
│   ├── HomeController.java    # 메인 페이지(/) 라우팅
│   └── ContactController.java # 이메일 발송 처리 (/contact/send)
├── service/
│   └── MailService.java       # JavaMailSender 기반 메일 발송 로직
└── dto/
    └── ContactDto.java        # 메일 폼 데이터 전송 객체

/src/main/resources/
├── application.yml            # 설정 파일 (SMTP, H2, 로컬/서버 환경 분리 필요)
└── templates/
    └── index.html             # 메인 HTML (Bootstrap 5 + Thymeleaf)
```

## 3. 주요 구현 내용 상세

### A. 이메일 발송 (SMTP)
- **설정**: `application.yml`에서 `spring.mail.host` (smtp.naver.com 권장) 및 계정 정보 설정.
- **보안**: 계정 정보는 코드에 하드코딩하지 않고 환경변수(`${MAIL_USERNAME}`, `${MAIL_PASSWORD}`)로 주입받음.
- **로직**: `MailService`에서 `SimpleMailMessage`를 생성하여 본인 계정으로 발송.

### B. PDF 저장 기능 (진행 중 이슈)
- **시도**: `html2pdf.js` 라이브러리를 사용하여 프론트엔드에서 DOM을 캡처해 PDF 생성.
- **이슈**: 레이아웃 깨짐(여백, 잘림 등) 발생. 
- **현재 상태**: `html2pdf.js` 코드가 `index.html` 하단에 포함되어 있으며, `generatePDF()` 함수로 동작함.
- **권장 방향**: 라이브러리 제거 후 브라우저 네이티브 인쇄(`window.print()`) + `@media print` CSS 최적화 방식으로 전환 추천.

### C. 배포 (Render)
- **방식**: GitHub 연동 -> `Dockerfile` 감지 -> 자동 빌드 및 배포.
- **Dockerfile**: JDK 17 (Eclipse Temurin) 기반.
- **환경변수**: Render 대시보드에서 `MAIL_USERNAME`, `MAIL_PASSWORD` 반드시 설정 필요.

## 4. 트러블슈팅 히스토리 (AI 참고용)

### Q1. 이메일 발송 실패 (SMTP Connect Error)
- **원인**: Render 무료 서버 대역이 구글 SMTP(smtp.gmail.com)에 의해 차단됨.
- **해결**: 네이버 SMTP(smtp.naver.com, 포트 465 SSL)로 변경하거나, 로컬 테스트 시에만 구글 사용 권장.

### Q2. PDF 레이아웃 깨짐
- **원인**: `html2pdf.js`가 Flexbox 및 복잡한 CSS를 완벽히 렌더링하지 못함.
- **시도**: `padding` 강제 제거, `page-break` 속성 추가, 복제본(clone) 생성 등 다양한 시도 했으나 완벽하지 않음.
- **결론**: 복잡한 DOM 캡처 방식보다는 인쇄 모드 CSS 최적화가 정답에 가까움.

## 5. 다음 작업 가이드
이 프로젝트를 이어받는 AI는 아래 순서로 작업을 검토하세요:
1.  **PDF 기능 정리**: `index.html`에서 불완전한 `html2pdf.js` 스크립트를 제거하고, 깔끔한 `@media print` 스타일로 대체하여 `window.print()` 버튼으로 변경하는 것을 사용자에게 제안할 것.
2.  **이메일 테스트**: 배포된 환경(Render)에서 네이버 SMTP가 정상 동작하는지 로그 확인할 것.
3.  **내용 업데이트**: `index.html` 내의 경력 기술 텍스트가 최신 PDF 내용과 일치하는지 최종 점검할 것.
