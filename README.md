# School Management System – Selenium, Maven, TestNG & REST Assured

Java automation framework converted from the supplied Playwright/TypeScript project.

## Technology stack

- Java 17
- Selenium WebDriver for browser automation
- Maven for dependency management and build execution
- TestNG for test lifecycle, assertions, suite execution and reporting
- REST Assured for HTTP API tests
- WebDriverManager for local browser-driver setup
- GitHub Actions for CI

## Project structure

```text
School-Management-Selenium-TestNG-RestAssured/
├── .github/workflows/maven-tests.yml
├── src/test/java/com/schoolmanagement/
│   ├── config/Config.java
│   ├── driver/DriverFactory.java
│   ├── pages/
│   │   ├── BasePage.java
│   │   ├── HomePage.java
│   │   ├── LoginPage.java
│   │   ├── DashboardPage.java
│   │   ├── AdminDashboardPage.java
│   │   ├── StudentDashboardPage.java
│   │   └── TeacherDashboardPage.java
│   └── tests/
│       ├── BaseTest.java
│       ├── web/
│       │   ├── HomeTest.java
│       │   ├── LoginTest.java
│       │   ├── AdminDashboardTest.java
│       │   ├── StudentDashboardTest.java
│       │   └── TeacherDashboardTest.java
│       └── api/
│           ├── ApiBaseTest.java
│           ├── HealthApiTest.java
│           └── AuthApiTest.java
├── .env.example
├── .gitignore
├── pom.xml
├── testng.xml
└── README.md
```

## Prerequisites

- JDK 17+
- Apache Maven 3.9+
- Google Chrome (default) or Firefox
- The School Management System application deployed and reachable

The default application URL is `http://localhost:8080/school-management-system/`.

## Configuration

Configuration can be supplied as environment variables or Maven system properties. Maven properties take precedence over environment variables.

| Setting | Environment variable | Maven property | Default |
|---|---|---|---|
| Target environment label | `TEST_ENV` | `test.env` | `dev` |
| Application URL | `BASE_URL` | `base.url` | `http://localhost:8080/school-management-system/` |
| Browser | `BROWSER` | `browser` | `chrome` |
| Headless mode | `HEADLESS` | `headless` | `true` |
| Login path | `LOGIN_PATH` | `login.path` | `login` |
| Admin test username | `E2E_USERNAME` | `e2e.username` | blank |
| Admin test password | `E2E_PASSWORD` | `e2e.password` | blank |
| Student username | `STUDENT_USERNAME` | `student.username` | `student` |
| Student password | `STUDENT_PASSWORD` | `student.password` | `student1234` |
| Teacher username | `TEACHER_USERNAME` | `teacher.username` | `teacher` |
| Teacher password | `TEACHER_PASSWORD` | `teacher.password` | `teacher1234` |

Do not commit real credentials. Configure secrets in GitHub Actions for CI. The `.env.example` file is documentation only; this project deliberately does not load `.env` files automatically.

## Run tests

Run the complete TestNG suite:

```bash
mvn clean test
```

Set the application URL:

```bash
mvn clean test -Dbase.url=http://localhost:8080/school-management-system/
```

Run in a visible Chrome browser:

```bash
mvn clean test -Dheadless=false
```

Use Firefox:

```bash
mvn clean test -Dbrowser=firefox
```

Provide admin credentials to enable the admin and optional login tests:

```bash
mvn clean test \
  -De2e.username=your_admin_username \
  -De2e.password=your_admin_password
```

Set the student and teacher accounts if the application uses different test users:

```bash
mvn clean test \
  -Dstudent.username=student \
  -Dstudent.password=student1234 \
  -Dteacher.username=teacher \
  -Dteacher.password=teacher1234
```

Run only API tests:

```bash
mvn test -Dgroups=api
```

Run only UI tests:

```bash
mvn test -Dgroups=web
```

TestNG results are written to `target/surefire-reports/`. Screenshots for failed UI tests are saved to `target/screenshots/`.

## Converted test coverage

### UI tests (Selenium + TestNG)

- Home page loads and has a non-empty body.
- Login page displays username, password and Sign in controls.
- Optional authenticated login test when admin credentials are supplied.
- Admin dashboard displays profile, account management and account creation controls.
- Admin can create and delete a generated admin account.
- Student dashboard displays profile and assigned-teachers sections.
- Teacher dashboard displays profile, Add student and assigned-students sections.

### API tests (REST Assured + TestNG)

- Application root responds with an HTTP status below 500.
- Unauthenticated `GET api/me` returns HTTP 302 and a `Location` header containing `/login`.

## GitHub Actions

The workflow is `.github/workflows/maven-tests.yml`. It runs on pushes and pull requests to `main`, `master` and `develop`, and can be started manually.

Configure these repository secrets as needed:

- `BASE_URL` (required for tests to execute in CI)
- `E2E_USERNAME`, `E2E_PASSWORD`
- `STUDENT_USERNAME`, `STUDENT_PASSWORD`
- `TEACHER_USERNAME`, `TEACHER_PASSWORD`

A GitHub-hosted runner cannot access `localhost` on your computer. Use a reachable test deployment or a self-hosted runner. Use dedicated test accounts and avoid destructive tests against Production.

## Notes about this conversion

- The existing Playwright page objects and scenarios were mapped to Selenium page objects and TestNG tests.
- Playwright's label locators were replaced with Selenium XPath locators targeting the actual nested `<input>` elements where form interaction is required.
- The student and teacher test credentials are configurable; change the defaults if your seeded application accounts differ.
- The current API tests preserve the two API behaviors documented in the original project. They do not assume additional endpoints.
- Application-specific selectors and route paths are based on the uploaded test code. If the web application markup or context path changes, update the page objects/configuration accordingly.
- This is a test automation project; it does not contain or modify the School Management web application itself.
