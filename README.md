# Taxi Dispatch Service API 
택시 배차 API

## DataBase Model
![](https://user-images.githubusercontent.com/28615416/63218212-72aefc80-c191-11e9-879d-6caf50cacab9.png)


## Prerequisite
- Java8 (openjdk 1.8)
- maven 
- Your preferred IDE
    - intellij IDEA 


openjdk 1.8 install  
```shell script
brew tap AdoptOpenJDK/openjdk
brew cask install adoptopenjdk8

```

maven install 
```shell script
brew install maven
```


## Running Application locally 
[http://localhost:8080](http://localhost:8080)

```shell script
git clone https://github.com/umanking/taxi-dispatch-app.git
cd taxi-dispatch-app
./mvnw package
java -jar target/*.jar
```


## API Spec
- [API 스펙](api-spec.md)
