# School Management System – Selenium and TestNG Test Automation

<p>
<picture>
  <img width="1280" height="720" alt="School_Management_Selenium_Tests" src="https://github.com/user-attachments/assets/4f6f0846-f72b-45ee-afb0-91413ed850d4" />
</picture> 
</p>

## Short Description of the Project

This project is a Java-based test automation framework for the School Management System. It was converted from the original Playwright/TypeScript project to use Selenium WebDriver and TestNG for UI testing, and REST Assured for API testing.

The framework automates essential application checks, including page loading, login functionality, and role-based dashboards for administrators, teachers, and students. It also supports automated execution through GitHub Actions.

## Technologies

The project uses the following technologies and tools:

- **Java 17** – Programming language.
- **Selenium WebDriver** – Browser automation for UI testing.
- **Maven** – Dependency management and test execution.
- **TestNG** – Test organization, execution, assertions, and reporting.
- **REST Assured** – API testing.
- **WebDriverManager** – Automatic browser driver setup.
- **GitHub Actions** – Continuous Integration (CI).
- **Google Chrome / Mozilla Firefox** – Supported browsers.

## Features

### UI Automation Testing
- Verify that the application homepage loads successfully.
- Verify that the login page displays the username, password, and Sign In controls.
- Test authenticated login when admin credentials are provided.
- Verify that the admin dashboard displays the expected profile and account management sections.
- Test admin account creation and deletion.
- Verify that the student dashboard displays profile information and assigned teachers.
- Verify that the teacher dashboard displays profile information and assigned students.

### API Automation Testing
- Verify that the application root responds successfully without a server error.
- Verify that unauthenticated requests to `GET api/me` redirect to the login page.

### Additional Features
- Support for Chrome and Firefox browsers.
- Headless and headed browser execution.
- Configurable application URL and test credentials.
- Separate execution of UI and API test groups.
- Screenshots captured when UI tests fail.
- TestNG reports generated after test execution.
- Automated test execution through GitHub Actions.

## The Process

The automation framework follows the Page Object Model (POM) design pattern to organize browser interactions and make the tests easier to maintain.

### 1. Configuration
The framework reads application settings, browser preferences, and test credentials from environment variables or Maven system properties.

### 2. WebDriver Setup
The DriverFactory initializes the selected browser using Selenium WebDriver and WebDriverManager. It also configures browser options, including headless execution.

### 3. Page Object Model
Page classes contain the locators and methods used to interact with the application.

These include:
- BasePage
- HomePage
- LoginPage
- DashboardPage
- AdminDashboardPage
- StudentDashboardPage
- TeacherDashboardPage

### 4. UI Test Execution
TestNG executes the UI test classes, which use the page objects to interact with the application and verify expected behavior.

### 5. API Test Execution
REST Assured sends HTTP requests to the application and validates the returned status codes and response headers.

### 6. Reporting
TestNG generates test execution reports. Screenshots of failed UI tests are saved in the screenshots directory.

### 7. Continuous Integration
GitHub Actions automatically executes the Maven test suite when changes are pushed or pull requests are created for the configured branches. The workflow can also be triggered manually.

## Running The Project

### Prerequisites

Before running the tests, make sure you have installed:

- JDK 17 or higher
- Apache Maven 3.9 or higher
- Google Chrome or Mozilla Firefox
- A deployed and accessible School Management System application
- An IDE that can run Java projects (I recommend VS Code)

The default application URL is:

```text
http://localhost:8080/school-management-system/
```

### 1. Clone the Repository

```bash
git clone https://github.com/cyqatester/School-Management-Selenium-Tests.git
cd School-Management-Selenium-Tests
```

### 2. Configure Test Credentials

You can provide credentials through environment variables or Maven system properties.

| Setting | Environment Variable | Maven Property |
|---|---|---|
| Application URL | `BASE_URL` | `base.url` |
| Browser | `BROWSER` | `browser` |
| Headless mode | `HEADLESS` | `headless` |
| Admin username | `E2E_USERNAME` | `e2e.username` |
| Admin password | `E2E_PASSWORD` | `e2e.password` |
| Student username | `STUDENT_USERNAME` | `student.username` |
| Student password | `STUDENT_PASSWORD` | `student.password` |
| Teacher username | `TEACHER_USERNAME` | `teacher.username` |
| Teacher password | `TEACHER_PASSWORD` | `teacher.password` |

Maven system properties take precedence over environment variables.

Do not commit real credentials to the repository. The `.env.example` file is provided for documentation and is not loaded automatically.

### 3. Run All Tests

Execute the complete TestNG test suite:

```bash
mvn clean test
```

### 4. Run UI Tests Only

```bash
mvn test -Dgroups=web
```

### 5. Run API Tests Only

```bash
mvn test -Dgroups=api
```

### 6. Run Tests in Headed Mode

To open the browser while tests are running:

```bash
mvn clean test -Dheadless=false
```

To use Firefox:

```bash
mvn clean test -Dbrowser=firefox
```

### 7. Run Tests with a Specific Application URL

```bash
mvn clean test -Dbase.url=http://localhost:8080/school-management-system/
```

### 8. Run Tests with Admin Credentials

Admin dashboard tests and the optional authenticated login test require admin credentials:

```bash
mvn clean test \
  -De2e.username=your_admin_username \
  -De2e.password=your_admin_password
```

### 9. Configure Student and Teacher Accounts

If your application uses different seeded test accounts, provide the appropriate credentials:

```bash
mvn clean test \
  -Dstudent.username=student \
  -Dstudent.password=student1234 \
  -Dteacher.username=teacher \
  -Dteacher.password=teacher1234
```

### 10. View Test Reports

After execution, TestNG reports are available in:

```text
target/surefire-reports/
```

Screenshots captured from failed UI tests are saved in:

```text
target/screenshots/
```

### 11. Run Tests Through GitHub Actions

The CI workflow is located at:

```text
.github/workflows/maven-tests.yml
```

It runs automatically on pushes and pull requests to the `main`, `master`, and `develop` branches. You can also trigger it manually through the GitHub Actions tab.

Configure the following repository secrets under **Settings → Secrets and variables → Actions**:

- `BASE_URL`
- `E2E_USERNAME`
- `E2E_PASSWORD`
- `STUDENT_USERNAME`
- `STUDENT_PASSWORD`
- `TEACHER_USERNAME`
- `TEACHER_PASSWORD`

**Important:** The GitHub-hosted runner must be able to access the deployed application URL. It cannot access `localhost` on your personal computer. Use a reachable test deployment or a self-hosted runner.

Use dedicated test accounts and avoid running destructive tests against production data.
