# STE ASSESSMENT – AUTOMATION CHALLENGE  
## Benefits Dashboard Application  

**Automation Framework:** Java (TestNG + RestAssured + Selenium)  
**Tester:** Preethi  
**Environment:** Production  
**Base URL:** https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod  

---

# 📌 OVERVIEW

This project provides comprehensive automated test coverage for the **Benefits Dashboard Application**, covering both:

1. **API Automation (RestAssured)**
2. **UI Automation (Selenium WebDriver)**

The framework was fully implemented in Java and follows professional automation architecture standards.

All test scripts were written manually (no record-and-playback tools used), as required in the assessment.

---

# 🛠 TECH STACK

### Language
- Java 17 (Compatible with Java 8+)

### Frameworks & Libraries
- TestNG (Test Runner)
- RestAssured (API Automation)
- Selenium WebDriver (UI Automation)
- WebDriverManager (Driver Management)
- Maven (Build Tool)

### Design Patterns & Architecture
- Page Object Model (POM) for UI tests
- Static API client utility class
- Reusable configuration class
- Explicit wait synchronization
- Modular layered structure
- Separation of API and UI test suites


---

# 🔎 API AUTOMATION COVERAGE

## Covered Scenarios

✔ Authentication validation  
✔ Create Employee  
✔ Get Employee  
✔ Update Employee  
✔ Delete Employee  
✔ Full lifecycle test  
✔ Negative validation scenarios  
✔ Business logic validation  
✔ Floating-point precision validation  
✔ Data integrity validation  

---
<img width="1067" height="738" alt="image" src="https://github.com/user-attachments/assets/a407de40-b959-4365-9758-e06b6228f4be" />

<img width="1917" height="996" alt="image" src="https://github.com/user-attachments/assets/649da12f-0452-4f82-984f-c354c162cdd0" />

## Key API Validations

- Correct HTTP status codes
- Authentication enforcement (Basic Auth)
- UUID-based employee ID validation
- Business calculations:
  - Gross pay
  - Benefits cost
  - Net pay
- Floating precision validation
- Negative input validation
- Salary mutation detection
- Username override validation

---

# 🖥 UI AUTOMATION COVERAGE

## Covered Scenarios

✔ Login  
✔ Add Employee  
✔ Update Employee  
✔ Delete Employee  
✔ Modal handling  
✔ Table row validation  
✔ Asynchronous table load handling  
✔ Explicit wait synchronization  
✔ Row count validation  

---

## UI Framework Highlights

- No Thread.sleep() usage
- Explicit waits used throughout
- Modal visibility/invisibility verification
- Dynamic table validation
- UUID-based employee creation
- Reusable login helper method
- Page Object Model implementation

---

# 🔐 AUTHENTICATION

API requires Basic Authentication header:

```
Authorization: Basic <authToken>
```

Credentials configured in:

```
Config.java
```

UI login is automated using Selenium before navigating to the Benefits dashboard.

---

# 📊 BUSINESS LOGIC VALIDATION

The following business rules are validated:

- $2000 per paycheck
- 26 paychecks per year
- $1000 per employee annually
- $500 per dependent annually

Validated calculations:

✔ Gross Pay  
✔ Benefits Cost  
✔ Net Pay  
✔ Floating precision tolerance  

---

# 🧪 HOW TO SETUP

### 1️⃣ Clone the repository

```
git clone https://github.com/preethi-samala244/paylocity-ste-assessment-automation/tree/main
cd automation-framework
```

### 2️⃣ Ensure Java & Maven are installed

```
java -version
mvn -version
```

### 3️⃣ Install dependencies

```
mvn clean install
```

---

# ▶ HOW TO RUN TESTS

### Run all tests:

```
mvn clean test
```

### Run only API tests:

```
mvn -Dtest=api.* test
```

### Run only UI tests:

```
mvn -Dtest=ui.* test
```

### Run specific test class:

```
mvn -Dtest=EmployeeLifecycleTest test
```

---

# 🐞 DEFECTS IDENTIFIED DURING AUTOMATION

The following issues were discovered during automation development:

- API overrides username field
- Negative salary accepted
- Silent salary mutation
- UI table flickering due to async load
- Missing UI salary field
- No client-side validation

Detailed information available in:

```
API_BUG_REPORT.md
UI_BUG_REPORT.md
```

---

# 🏗 KEY AUTOMATION DESIGN DECISIONS

- Used explicit waits instead of static waits
- Built reusable API utility class
- Separated API and UI test layers
- Implemented Page Object Model
- Added full lifecycle test for realistic coverage
- Used UUID for dynamic test data
- Applied floating-point tolerance validation
- Stabilized AJAX table loading

---

# ✅ CONCLUSION

This automation framework provides:

- End-to-end API validation
- Stable UI automation
- Business logic verification
- Negative testing coverage
- Professional test architecture
- Production-ready automation structure

The solution demonstrates:

- Strong test design capability
- Framework architecture understanding
- API & UI automation expertise
- Debugging and defect identification skills
- Real-world synchronization handling

All test scripts were written manually as required by the assessment.
