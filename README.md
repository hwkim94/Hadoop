# Hadoop-Java
- hadoop을 공부하며 수행했던 실습파일들 입니다.

### 환경
- intelliJ IDEA
- Java 8
- Maven 2.3
- Hadoop 2.9.0
- 자세한 사항은 pom.xml 참조

### 파일 설명
- dependency-reduced-pom.xml, hadoop-study.iml, pom.xml
    - 설정파일
- target 
    - maven build한 jar파일이 생성되는 디렉토리
- src/main/java/com/ybigta
    - source code가 존재하는 디렉토리
- .idea

### 어플리케이션 빌드 방법
- intelliJ에서 maven build
- clean -> install -> package 차례대로 수행
- 위의 target 디렉토리에서 {app name}-{version}.jar파일 복사

### 어플리케이션 실행 방법
- hadoop이 설치되어있는 aws 접속
- 복사한 jar파일 전송
```bash
$ hadoop jar {jar파일 명}.jar {package명}.{Driver명} {input 디렉토리} {output }
```

