# 1. 빌드 환경 (Build Stage)
FROM gradle:7.6.4-jdk17 AS builder
WORKDIR /app

# Gradle 캐싱을 위해 필요한 파일만 먼저 복사
COPY build.gradle settings.gradle ./
COPY src ./src

# 빌드 실행 (테스트 제외하여 속도 향상)
RUN gradle build -x test --no-daemon

# 2. 실행 환경 (Run Stage)
FROM openjdk:17-jdk-slim
WORKDIR /app

# 빌드된 JAR 파일만 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 실행
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
