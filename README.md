# Hadoop
- hadoop을 공부하며 수행했던 실습파일들입니다.
- 연세대학교 빅데이터 학회 와이빅타 엔지니어링팀의 실습/과제를 위한 코드입니다.
- https://github.com/YBIGTA/EngineeringTeam

## 1. 예제 설명
### skeleton
- Driver, Mapper, Reducer, Partioner, Writable의 skeleton code

### example
- wordcount 예제

### hw3/wordlength
- 각 알파벳으로 시작하는 단어들의 평균 길이

### hw3_2/bamboo
- 각 날짜별 가장 많은 좋아요를 받은 제보
- 연세대학교 대나무숲 제보 데이터 사용(날짜, 시간, 좋아요, 내용 등)
- Writable을 사용하지 않음

### hw5/fine
- 각 월별 사람들 각각의 벌금합
- Writable : Date와 Name을 값으로 가짐
- Partitioner : 날짜 중 월(12달)을 기준으로 총 12개의 reducer로 분배 
- 와이빅타 엔지니어링팀 인원들의 벌금 데이터 사용

### hw6/secondarySort
- 와이빅타 각 기수별 학번/이름 데이터를 사용하여 원하는 기준별로 sorting하는 예제

- **bad version**
    - composite-key별로 연산해야하는데, group-key로 연산해버리는 예제
    
- **geed version**
    - 위의 문제점을 해결하는 예제

- **minimized version**
    - secodary sort 예제
    
- **only_sort version**
    - secondary sort가 아닌 그냥 sort해주는 예제

- **test version**, **test2 version**
    - secondary sort를 했을 때, reduce 함수에서 어떤 값을 기준으로 Iterable class를 만드는지 확인하기 위한 예제

### project
- 게임 플레이 데이터로 플레이시간 예측

- **pubg_small**
    - 작은 데이터가 들어왔을 때, 행렬 계산을 분산처리하지 않고 회귀계수를 바로 계산
    
- **pubg_large**
    - 큰 데이터가 들어왔을 때, 행렬 계산을 분산처리하며 회귀계수를 계산
    - random sampling으로 sample을 뽑아 회귀계수를 계산하여, 총 5번 시행한 후 회귀 계수들을 평균내어 계산

- https://www.kaggle.com/skihikingkevin/pubg-match-deaths 사용



## 2. 환경
- intelliJ IDEA
- Java 8
- Maven 2.3
- Hadoop 2.9.0
- 자세한 사항은 pom.xml 참조

### 파일 설명
- maven build한 jar파일이 생성되는 디렉토리
    - target 
    
- source code가 존재하는 디렉토리
    - src/main/java/com/ybigta

- 기타 설정 파일
    - .idea, dependency-reduced-pom.xml, hadoop-study.iml, pom.xml

### 어플리케이션 빌드 방법
1. intelliJ에서 maven build
2. clean -> install -> package 차례대로 수행
3. 위의 target 디렉토리에서 ${app-version.jar}파일 복사


### 어플리케이션 실행 방법
- hadoop이 설치되어있는 aws 접속
- 복사한 jar파일 전송
```bash
$ hadoop jar ${jar파일 명.jar} ${package명.Driver명} ${input 디렉토리} ${output 디렉토리}
```
