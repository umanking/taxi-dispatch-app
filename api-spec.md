

### POST 회원가입 

```
https://example.com/api/v1/user
```

**[Request]**

```json
{
  "email": "umanking@gmail.com",
  "password": "1234",
  "userStatus":"CUSTOMER"
}
```

| 키         | 설명                               | 필수 |
| ---------- | ---------------------------------- | ---- |
| email      | 이메일                             | O    |
| password   | 비밀번호                           | O    |
| userStatus | 접속 유저의 상태(CUSTOMER, DRIVER) | O    |



**[Response]**

```json
{
    "email": " umanking@gmail.com",
    "userStatus": "CUSTOMER"
}
```



------



### POST 로그인 

```
https://example.com/api/v1/user/login
```

**[Request]**

```json
{
  "email": "umanking@gmail.com",
  "password": "1234",
}
```

| 키       | 설명     | 필수 |
| -------- | -------- | ---- |
| email    | 이메일   | O    |
| password | 비밀번호 | O    |



**[Response]**

```json
{
    "email": " umanking@gmail.com",
    "userStatus": "CUSTOMER"
}
```





------



### POST 택시배차 요청

```
https://example.com/api/v1/dispatch
```

**[Request]**

```json
{
	"address": "서울시,마곡동 마곡중앙로76",
}
```

| 키      | 설명                                          | 필수 |
| ------- | --------------------------------------------- | ---- |
| address | 현재의 위치(주소) 100자리를 넘지 않아야 한다. | O    |



**[Response]**

```json
{
    "address": "{\n\t\"address\": \"서울시,마곡동 마곡중앙로76\"\n}",
    "assignDriver": null,
    "driverStatus": "WAITING",
    "requestAt": "2019-08-18T07:59:35.081925",
    "completedAt": null
}
```





------

### GET 목록조회

**[Request]**

```
https://example.com/api/v1/dispatch
```



**[Response]**

```json
[
    {
        "address": "{\n\t\"address\": \"서울시 마곡동 마곡중앙로76\"\n}",
        "assignDriver": null,
        "driverStatus": "COMPLETED",
        "requestAt": "2019-08-18T08:05:14.091732",
        "completedAt": "2019-08-18T08:05:47.142441"
    },
    {
        "address": "{\n\t\"address\": \"서울시 마곡동 마곡중앙로123\"\n}",
        "assignDriver": null,
        "driverStatus": "WAITING",
        "requestAt": "2019-08-18T08:05:21.632101",
        "completedAt": null
    }
]
```

배차가 완료(`COMPLETED`)되거나 대기(`WAITING`) 상태인 목록 전부를 표시

------

### PUT 기사 배차

**[Request]**

```
https://example.com/api/v1/dispatch?id=2
```

| 키   | 설명          | 필수여부 |
| ---- | ------------- | -------- |
| id   | dispatch의 id | O        |

**[Response]**

```json
{
    "address": "{\n\t\"address\": \"서울시 마곡동 마곡중앙로76\"\n}",
    "assignDriver": null,
    "driverStatus": "COMPLETED",
    "requestAt": "2019-08-18T08:05:14.091732",
    "completedAt": "2019-08-18T08:05:47.142441"
}
```

- 기사 배치가 되었으면, `driverStatus` 를 `COMPLETED` 로  변경, `assignDriver` 에 자기 자신 할당