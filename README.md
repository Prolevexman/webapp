# Web Application - Process Management

Spring Boot приложение для управления процессами с двумя типами обработки: TRANSLATE и MODIFY.

## Требования

- Java 21+
- Maven 3.9+ (или используйте встроенный `mvnw`)

## Быстрый старт

### Запуск с InMemory хранилищем (по умолчанию)

```bash
./mvnw spring-boot:run
```

или на Windows:

```bash
mvnw.cmd spring-boot:run
```

### Запуск с PostgreSQL

1. Убедитесь, что PostgreSQL запущен и создана база данных:

```sql
CREATE DATABASE webapp;
```

2. Настройте подключение в `src/main/resources/application-postgres.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/webapp
spring.datasource.username=postgres
spring.datasource.password=yourpassword
```

3. Запустите с профилем postgres:

```bash
./mvnw spring-boot:run "-Dspring-boot.run.profiles=postgres"
```

## API Endpoints

Базовый URL: `http://localhost:8080/api/v1`

| Метод | Endpoint | Описание |
|-------|----------|----------|
| POST | `/process/start` | Запуск нового процесса |
| GET | `/process/{id}/result` | Получение результата процесса |
| GET | `/process` | Список всех процессов |
| DELETE | `/process/{id}` | Удаление процесса |

### Примеры запросов

**Запуск процесса MODIFY:**

```bash
curl -X POST "http://localhost:8080/api/v1/process/start" \
  -H "Content-Type: application/json" \
  -d '{"type": "MODIFY", "inputData": "hello world"}'
```

**Запуск процесса TRANSLATE:**

```bash
curl -X POST "http://localhost:8080/api/v1/process/start" \
  -H "Content-Type: application/json" \
  -d '{"type": "TRANSLATE", "inputData": "hello"}'
```

**Получение результата:**

```bash
curl "http://localhost:8080/api/v1/process/1/result"
```

**Список всех процессов:**

```bash
curl "http://localhost:8080/api/v1/process"
```

**Удаление процесса:**

```bash
curl -X DELETE "http://localhost:8080/api/v1/process/1"
```

## Swagger UI

После запуска приложения Swagger доступен по адресу:

- **Swagger UI:** http://localhost:8080/api/v1/swagger-ui/index.html
- **OpenAPI JSON:** http://localhost:8080/api/v1/v3/api-docs

## Типы процессов

### MODIFY

Переворачивает строку и заменяет гласные буквы на следующие по алфавиту.

Пример: `hello world` → `dlrpw pllfh`

### TRANSLATE

Выполняет перевод текста с английского на русский через MyMemory API (асинхронно).

Пример: `hello` → `привет`

## Запуск тестов

### Все тесты

```bash
./mvnw test
```

### Конкретный тестовый класс

```bash
./mvnw test -Dtest=ModifyProcessTest
```

### Конкретный метод теста

```bash
./mvnw test "-Dtest=ModifyProcessTest#getTypeShouldReturnModify"
```

### Тесты с подробным выводом

```bash
./mvnw test -Dsurefire.useFile=false
```

## Структура проекта

```
src/main/java/prolevexman/webapp/
├── config/                 # Конфигурации (Async, OpenAPI)
├── controller/             # REST контроллеры
├── dao/                    # Data Access Objects
│   ├── inmemory/          # InMemory реализация
│   └── postgres/          # PostgreSQL реализация
├── dto/                    # Data Transfer Objects
├── mapper/                 # Маперы Entity ↔ DTO
├── model/
│   ├── entity/            # Сущности
│   └── enums/             # Перечисления
├── service/
│   ├── external/          # Внешние API клиенты
│   ├── factory/           # Фабрики
│   └── strategy/          # Стратегии выполнения
└── WebappApplication.java  # Точка входа
```

## Профили

| Профиль | Описание | Хранилище |
|---------|----------|-----------|
| `inmemory` | По умолчанию, данные в памяти | ConcurrentHashMap |
| `postgres` | PostgreSQL база данных | PostgreSQL + JDBC |

## Конфигурация

### application.properties

```properties
spring.application.name=webapp
mymemory.api.url=https://api.mymemory.translated.net/get
server.servlet.context-path=/api/v1
spring.profiles.active=inmemory
```

### application-postgres.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/webapp
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=org.postgresql.Driver
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:schema.sql
```

## Технологии

- Spring Boot 4.0.2
- Spring Web MVC
- Spring JDBC
- PostgreSQL Driver
- SpringDoc OpenAPI (Swagger)
- JUnit 5
- Mockito
- WireMock (интеграционные тесты)
