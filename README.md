# 👨‍💻 한정우 - Backend Developer Portfolio

이 프로젝트는 Spring Boot와 Thymeleaf로 제작된 **웹 기반 인터랙티브 경력기술서(Portfolio)** 입니다.
기존의 정적인 문서 형식을 벗어나, 웹 환경에서 경력과 프로젝트 성과를 효과적으로 전달하고 필요 시 PDF로 변환하여 소장할 수 있도록 설계되었습니다.

## 🚀 Key Features

*   **Responsive Design**: Bootstrap 5를 활용하여 데스크탑, 태블릿, 모바일 등 모든 기기에서 최적화된 화면을 제공합니다.
*   **Interactive Timeline**: 경력 및 프로젝트 진행 순서를 시각적인 타임라인 형태로 제공합니다.
*   **PDF Export**: 브라우저의 인쇄 기능을 커스터마이징하여, 웹페이지를 깔끔한 A4 규격의 PDF 문서로 즉시 저장할 수 있습니다.
*   **Project Metrics**: 각 프로젝트의 참여 인원, 기여도, 정량적 성과를 아이콘과 배지 형태로 직관적으로 표현합니다.

## 🛠 Tech Stack

### Backend
*   **Java 17**
*   **Spring Boot 3.x**
*   **Gradle** (Build Tool)

### Frontend
*   **Thymeleaf** (Template Engine)
*   **Bootstrap 5.3**
*   **FontAwesome** (Icons)
*   **HTML5 / CSS3**

### Infra / DevOps
*   **Docker**
*   (배포 환경에 따라 AWS, NCP 등 추가 가능)

## 🏃 How to Run

### Prerequisites
*   Java 17 이상 설치 필요

### 1. Clone & Build
```bash
# Repository Clone
git clone <repository-url>

# Build
./gradlew build
```

### 2. Run Application
```bash
# Run
./gradlew bootRun
```
*   브라우저에서 `http://localhost:8080` 접속

### 3. Run with Docker
```bash
# Build Image
docker build -t my-portfolio .

# Run Container
docker run -p 8080:8080 my-portfolio
```

## 📝 License
This project is for personal portfolio use.
Copyright © 2025 Han Jeong-Woo. All Rights Reserved.
