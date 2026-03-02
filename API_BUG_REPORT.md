# API Bug Report  
## STE Assessment – Benefits Dashboard Application  

**Environment:** Production  
**Base URL:** https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod  
**Automation Framework:** Java (TestNG + RestAssured)  
**Tester:** Preethi  

---

# 🔴 CONFIRMED API DEFECTS

The following defects were identified through automated validation using TestNG + RestAssured.

---

## API BUG 1 – Negative Salary Accepted

### Severity: Critical

### Description
The API accepts negative salary values and processes the request successfully.

### Steps to Reproduce
1. Send POST request to:
   ```
   /api/Employees
   ```
2. Use request body:
   ```json
   {
     "username": "bad_user2",
     "firstName": "John",
     "lastName": "Doe",
     "dependants": 2,
     "salary": -100
   }
   ```

### Expected Result
HTTP 400 Bad Request with validation error indicating salary must be positive.

### Actual Result
HTTP 200 OK returned.

### Impact
- Financial data integrity risk  
- Payroll corruption potential  
- Violates business rules  

---

## API BUG 2 – Negative Salary Silently Mutated

### Severity: Critical

### Description
When negative salary is submitted:
- API returns 200 OK
- Salary value is silently modified internally

### Steps to Reproduce
1. Submit salary = -100.
2. Inspect response body.

### Expected Result
Request rejected with validation error.

### Actual Result
- Response status: 200 OK  
- Salary returned is NOT -100  
- Salary is auto-corrected (mutated)

### Impact
- Silent data mutation  
- Audit compliance risk  
- Extremely dangerous for payroll systems  

---

## API BUG 3 – Username Overwritten by Authenticated User

### Severity: High

### Description
The username provided in the request payload is ignored and replaced with the authenticated username.

### Steps to Reproduce
1. Send POST request:
   ```json
   {
     "username": "random_user",
     "firstName": "John",
     "lastName": "Doe",
     "dependants": 2,
     "salary": 52000
   }
   ```
2. Inspect response.

### Expected Result
```
"username": "random_user"
```

### Actual Result
```
"username": "TestUser905"
```

### Impact
- API contract mismatch  
- Breaks multi-user creation logic  
- Swagger documentation misleading  

---

## API BUG 4 – Dependants Upper Bound Not Enforced Properly

### Severity: Medium

### Steps to Reproduce
1. Send POST request:
   ```json
   {
     "username": "bad_user",
     "firstName": "John",
     "lastName": "Doe",
     "dependants": 40,
     "salary": 50000
   }
   ```

### Expected Result
HTTP 400 with validation error (maximum allowed: 32).

### Actual Result
Inconsistent behavior observed.

### Impact
- Business rule enforcement unclear  
- Risk of invalid data entry  

---

## API BUG 5 – Generic Validation Error Response

### Severity: Medium

### Steps to Reproduce
1. Send POST request with missing required fields:
   ```json
   {
     "firstName": "John"
   }
   ```

### Actual Response
```json
{
  "title": "An error occurred while processing your request."
}
```

### Expected Response
Field-level validation message:
```json
{
  "errors": {
    "salary": ["Salary must be greater than 0"]
  }
}
```

### Impact
- Poor API usability  
- Difficult client integration  
- No actionable validation feedback  

---

# 🟢 VERIFIED WORKING FUNCTIONALITY

The following behaviors were validated and confirmed working correctly:

✔ Authentication enforcement (401/403 without auth)  
✔ Successful login using Basic Auth  
✔ Employee creation with valid data  
✔ Employee retrieval by ID  
✔ Employee update  
✔ Employee deletion  
✔ Full lifecycle test  
✔ Business logic calculation (gross, benefitsCost, net)  
✔ Floating precision tolerance  
✔ UUID-based employee IDs  

---

# 📊 BUSINESS RULE VALIDATION

The following business assumptions were validated through automation:

- $2000 per paycheck  
- 26 paychecks per year  
- $1000 per year per employee  
- $500 per year per dependent  

Calculation validation:

```
gross = 2000
benefitsCost = (1000/26) + (dependents × 500/26)
net = gross - benefitsCost
```

All validated records matched expected calculations within floating tolerance.

---

# 🔐 SECURITY OBSERVATION

## Basic Authentication Used

```
Authorization: Basic <token>
```

### Risk
- No token expiration  
- Potential replay attack risk  

### Recommendation
- OAuth 2.0
- JWT with expiration

---

# 📌 SUMMARY

Through structured automated testing using:

- Java (TestNG)
- RestAssured

The API demonstrates correct core functionality but contains:

- Critical payroll validation gaps
- Silent data mutation behavior
- API contract inconsistencies
- Weak validation messaging

The defects identified are primarily related to financial validation and input enforcement, which are high-risk areas for payroll systems.