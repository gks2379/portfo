# 프로젝트 트러블슈팅 로그 (Troubleshooting Log)

## 1. 빌드 에러: 패키지 찾을 수 없음 (`org.xhtmlrenderer.pdf does not exist`)
- **발생 일시**: 2026-02-21
- **문제 현상**: `./gradlew build` 또는 애플리케이션 컴파일 시 `PdfService.java`에서 `org.xhtmlrenderer.pdf.ITextRenderer` 패키지를 찾지 못하여 `compileJava` 단계에서 빌드 실패. (`error: package org.xhtmlrenderer.pdf does not exist`)
- **원인**: 
  - 문서(`PDF_FEATURE_LOG.md`)에는 `flying-saucer-pdf-openpdf` 라이브러리가 추가되었다고 기록되어 있었으나, 실제 `build.gradle` 내 `dependencies` 블록에서는 해당 의존성 코드가 누락되어 발생한 문제.
- **해결 방법**:
  - `build.gradle` 파일의 `dependencies` 블록에 아래와 같이 누락된 라이브러리 의존성을 추가함.
  ```gradle
  dependencies {
      // ... 기존 의존성들 ...
      implementation 'org.xhtmlrenderer:flying-saucer-pdf-openpdf:9.1.22'
      // ...
  }
  ```
- **결과**: 의존성 추가 후 재빌드 진행 시 문제없이 빌드 성공 (`BUILD SUCCESSFUL`).
