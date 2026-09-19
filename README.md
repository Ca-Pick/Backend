# 🤝 Ca-pick Backend
<p align="center">
  <a href="https://mingling.kr">
    <img src="https://github.com/user-attachments/assets/7d41bd0e-2ad4-4e54-9910-86e5363429fb" width="600"/>
  </a>
</p>

<p align="center">
  <a href="https://www.ca-pick.com/">🚀 Capick 바로가기</a>
</p>

**Ca-pick**은 취향 태그로 원하는 디자인의 케이크를 쉽게 찾는 맞춤형 큐레이션 서비스입니다.

---

## ✨ Features
| 기능 | 화면 |
|:---:|-----|

<img src="https://github.com/user-attachments/assets/6df12fb8-49fc-4692-b45f-cf7ddedee8cb" width="600"/> |
<img src="https://github.com/user-attachments/assets/e85cf45e-4539-4cb7-9d22-29973b0e27e8" width="600"/> |

---

## 🛠 Tech Stack

### Language

![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white)
### Framework

![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-brightgreen?logo=spring&logoColor=white)

### Database

![MariaDB](https://img.shields.io/badge/MySQL-black?&logo=mariadb&logoColor=white)

### Documentation
![Swagger](https://img.shields.io/badge/Swagger-UI)

---

## 🖥 Architecture

<img width="3200" height="2260" alt="image" src="https://github.com/user-attachments/assets/0b8a1104-5e70-491f-a975-0df31c761f95" />


### 📊 Entity Relationship Diagram (ERD)

<img width="688" height="531" alt="Image" src="https://github.com/user-attachments/assets/7fe10a00-b2b7-4bc3-9d9a-e5d1971a9554" />

## 🚀 API Specification

### 📦 Common Response Format

모든 API 응답은 아래의 공통 규격을 따릅니다.

**✅ Success Response**

```json
{
  "success": true,
  "data": {
    "items": [],
    "page": 1
  },
  "timestamp": "2026-03-06T14:00:00Z"
}
```

**❌ Failure Response**

```json
{
  "success": false,
  "code": "INVALID_TOKEN",
  "message": "토큰이 유효하지 않습니다.",
  "data": null,
  "timestamp": "2026-03-06T14:00:00Z"
}
```

---

## 🌿 Branch Strategy

브랜치 생성 시 아래 규칙을 준수하며, 작업 단위별로 명확히 분리합니다.

> **Format**: `<type>/<jira-issue-number>-<short-description>`

| Type         | Description             | Example                           |
| ------------ | ----------------------- | --------------------------------- |
| **feature**  | 신규 기능 개발          | `feature/SW-10-jwt-refresh-token` |
| **fix**      | 버그 수정               | `fix/SW-21-user-profile-error`    |
| **refactor** | 코드 리팩토링           | `refactor/SW-35-order-validation` |
| **chore**    | 설정, 환경 및 기타 작업 | `chore/SW-01-setup-gradle`        |

---

## 💬 Commit Message Convention

**AngularJS Git Commit Convention**을 따르며, `subject`는 **한국어**로 명확하게 작성합니다.

> **Format**: `[issue-number] <type>: <subject>`

- **feat**: 새로운 기능 추가
- **fix**: 버그 수정
- **docs**: 문서 수정 (README, Swagger 등)
- **style**: 코드 포맷팅 (로직 변경 없음)
- **refactor**: 코드 리팩토링
- **test**: 테스트 코드 추가 및 수정
- **chore**: 빌드 업무, 패키지 설정 등

**💡 Example:**
`[SW-94] fix: 장소 추천 API 런타임 오류 수정`

---

## 🌐 Deployment

### 🏗 Service Architecture
전체적인 시스템 구조는 **Nginx**를 리버스 프록시로 활용하며, 애플리케이션 서버와 데이터베이스 모두 **Docker 컨테이너**로 독립 운영됩니다. 서비스의 안정적인 운영을 위해 로그 데이터는 호스트 서버와 볼륨 마운트를 통해 영구 보관됩니다.

* **Web Server**: Nginx (Reverse Proxy)
* **Application Server**: Docker Containers (Spring Boot 3.x)
* **Database**: Docker Container (Mysql 8.0)

---

### 💻 Infrastructure Detail
| Infrastructure | Detail |
| :--- | :--- |
| **Cloud** | **AWS ** |
| **Instance** | Micro Server (Ubuntu 26.04 LTS) |
| **Database** | MySQL 8.0 |
| **Container** | Docker, Docker-compose |

---

### 🚀 CI/CD Pipeline

1. `main` 브랜치에 push되면 GitHub Actions가 실행됩니다.
2. Actions runner에서 Docker 멀티 스테이지 빌드를 수행합니다. 빌드 스테이지에서 Java 21과 Gradle로 애플리케이션 JAR를 만들고, 런타임 이미지에 포함합니다.
3. 완성된 Docker 이미지를 Amazon ECR에 `latest` 태그로 push합니다.
4. SCP로 운영 Docker Compose 및 Nginx 설정을 EC2에 동기화합니다.
5. SSH로 EC2에 접속하여 ECR 로그인과 이미지 경로 설정을 수행합니다.
6. EC2가 ECR에서 앱 이미지를 pull합니다.
7. `docker compose -f docker-compose.prod.yml up -d`로 컨테이너를 실행·갱신합니다.
8. Nginx 설정을 reload합니다.



---

## 📄 API Documentation

상세한 API 명세 및 테스트는 아래 Swagger UI 링크를 참조하세요.
👉 **[ca-pick Swagger UI 바로가기](https://api.ca-pick.com/swagger-ui/index.html)**
