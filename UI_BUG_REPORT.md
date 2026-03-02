# UI Bug Report  
## STE Assessment – Benefits Dashboard Application  

**Environment:** Production  
**Base URL (UI):** https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/Benefits  
**Automation Framework Used:** Java (Selenium + TestNG)  
**Tester:** Preethi  

---

# 🟠 UI DEFECTS IDENTIFIED

---

## UI-001 – First Name and Last Name Displayed in Reverse

### Severity: High  
### Priority: High  

### Description  
When viewing employee records in the Benefits table, the First Name and Last Name appear reversed.

### Steps to Reproduce
1. Login to the application.
2. Navigate to the Benefits dashboard.
3. Click **Add Employee**.
4. Enter:
   - First Name: John  
   - Last Name: Doe  
   - Dependants: 1  
5. Click **Add Employee**.
6. Observe the newly added row in the employee table.

### Expected Result
```
John Doe
```

### Actual Result
```
Doe John
```

### Impact
- Incorrect data presentation  
- Confusing to end users  
- Indicates UI column binding/mapping issue  

---

## UI-002 – Edit Modal Title Incorrect

### Severity: Low  
### Priority: Low  

### Description  
When editing an existing employee, the modal title displays "Add Employee" instead of "Edit Employee".

### Steps to Reproduce
1. Login to the Benefits page.
2. Click the edit icon for any employee.
3. Observe the modal title.

### Expected Result
```
Edit Employee
```

### Actual Result
```
Add Employee
```

### Impact
- UX inconsistency  
- May cause user confusion  

---

## UI-003 – No Inline Validation Messages

### Severity: Medium  
### Priority: Medium  

### Description  
The Add/Edit Employee form does not provide field-level validation messages for invalid inputs.

### Steps to Reproduce
1. Click **Add Employee**.
2. Leave First Name empty.
3. Enter invalid dependants (e.g., -1 or text).
4. Click **Add Employee**.

### Expected Result
Clear inline validation messages such as:
- "First Name is required"
- "Dependants must be a positive number"

### Actual Result
- No clear field-level validation
- Silent failure or generic error behavior

### Impact
- Poor user experience  
- Invalid input attempts not clearly explained  
- Inconsistent with expected UI validation standards  

---

## UI-004 – No Success Confirmation After Update

### Severity: Low  
### Priority: Low  

### Description  
After editing and updating an employee, no confirmation message or success toast is displayed.

### Steps to Reproduce
1. Click edit icon for an employee.
2. Modify dependants.
3. Click **Update Employee**.
4. Observe UI behavior.

### Expected Result
A success message such as:
```
Employee updated successfully
```

### Actual Result
No confirmation feedback is shown.

### Impact
- User uncertainty  
- Reduced confidence in system behavior  

---

## UI-005 – Delete Confirmation Lacks Contextual Information

### Severity: Low  
### Priority: Low  

### Description  
The delete confirmation modal only shows limited employee information.

It does not display:
- Dependants
- Salary (derived)
- Financial impact

### Steps to Reproduce
1. Click delete icon for any employee.
2. Observe confirmation modal.

### Expected Result
Confirmation modal should display additional contextual details before deletion.

### Actual Result
Minimal information displayed.

### Impact
- Risk of accidental deletion  
- Insufficient confirmation clarity  

---

## UI-006 – No Loading Indicator During Operations

### Severity: Medium  
### Priority: Medium  

### Description  
During Add / Update / Delete operations:
- No spinner is displayed
- Buttons remain clickable
- No loading state indicator

### Steps to Reproduce
1. Add or update an employee.
2. Observe UI while request is processing.

### Expected Result
- Loading spinner OR
- Disabled action button until completion

### Actual Result
No visible loading state.

### Impact
- Risk of duplicate submissions  
- Poor perceived responsiveness  
- Unprofessional UX behavior  

---

# 📌 NOTES ON BUSINESS RULE ALIGNMENT

Based on system assumptions:

- All employees are paid $2000 per paycheck  
- 26 paychecks per year  
- $1000/year per employee  
- $500/year per dependent  

Salary is system-derived and not user-editable.

Therefore:
✔ UI correctly does not expose salary input  
✔ No defect recorded for missing salary field  

---

# 📊 SUMMARY

Through structured UI automation using:

- Java (Selenium WebDriver)
- TestNG
- Explicit waits (WebDriverWait)
- Page Object Model (POM)

The UI demonstrates core functional capability but contains:

- Data presentation defects  
- Validation gaps  
- UX inconsistencies  
- Feedback deficiencies  

The identified issues primarily impact usability, clarity, and user confidence rather than core functionality.
