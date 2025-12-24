# 이제 빌더(gradle) 이미지는 필요 없습니다. 가벼운 실행용 이미지만 씁니다.
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# 우리가 로컬에서 만들어서 올린 JAR 파일을 복사합니다.
# (Git에 build/libs/*.jar가 올라갈 것이기 때문입니다.)
COPY build/libs/*.jar app.jar

# 실행
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]