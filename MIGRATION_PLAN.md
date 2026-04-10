# Struts 1.3 + Hibernate + Ant to Spring Boot + React Migration Plan

## Objectives and Guardrails

- Preserve all existing business logic and runtime behavior.
- Keep configuration sourcing exactly as it is today (same repository files and structure).
- Use `db/Schema_Table_Details.xlsx` as the schema validation baseline.
- Migrate incrementally with rollback points (no big-bang cutover).

## Current to Target Mapping

| Layer | Current (Legacy) | Target (Modern) |
|---|---|---|
| Controller | Struts `Action` + `struts-config.xml` | Spring Boot `@Controller` / `@RestController` + explicit request mappings |
| Model | Manual POJOs/DTOs | Lombok-based models/DTOs (no business rule change) |
| ORM / Data Access | Hibernate (XML/annotations, DAO style) | `JdbcTemplate` and/or Spring Data JDBC repositories |
| Validation | Struts validation framework | Spring Validation (JSR-380 annotations + custom validators) |
| Build | Ant (`scripts/build.xml`) | Maven (`pom.xml`) |
| UI | JSP (`WebContent/**/*.jsp`) | React frontend consuming Spring APIs |
| Java Version | Java 7 | Java 17 |
| Properties | `properties/*.properties` + existing XML config | Same files remain source-of-truth (loaded by Boot bridge) |

## Repository Anchors (Verified)

- Struts config: `WebContent/struts-config.xml`
- Ant build: `scripts/build.xml`
- Properties: `properties/ApplicationResources.properties`, `properties/hibernate.db.properties`, `properties/Registration.properties`, `properties/Claims.properties`, `properties/SGVConstants.properties`, `properties/FollowUp.properties`
- DDL workbook: `db/Schema_Table_Details.xlsx`

## Domain Business Logic Summary

### System Context
**OperationsNABH** is a comprehensive health insurance claim and patient management system for the **Aarogyasri** health scheme in Telengana. The system manages patient enrollment, pre-authorization, claim processing, payment, and post-claim follow-up workflows. It integrates biometric authentication, Aadhaar verification, and multi-role audit trails.

### Core Business Domains and Workflows

#### 1. **Login & Authentication** (`src/com/ahct/login/`)
- **Purpose**: User authentication with OpenAM/LDAP integration, password management, and role-based access.
- **Key flows**:
  - OpenAM single-sign-on with fallback to local login.
  - Password change with complexity validation (numeric, special chars, length enforcement).
  - Digital certificate and OTP authentication for sensitive operations.
  - Session management with timeout configuration.
- **Critical DAO patterns**: Simple user lookup; no complex queries.
- **Recommended migration**: **Spring Security + JDBC lookups** (low complexity).

#### 2. **Patient Registration & Enrollment** (`src/com/ahct/patient/`)
- **Purpose**: Register patients (employee, pensioner, new-born, journalist, AIS card holders) with Aadhaar/biometric verification and OTP validation.
- **Key flows**:
  - Card number lookup → retrieve enrollment details.
  - OTP-based patient validation; exemption and approval workflows.
  - Biometric enrollment and photo capture.
  - Patient history and exemption list tracking.
- **Critical DAO patterns**: 
  - Complex lookup queries with joins (card + enrollment + family head).
  - Temporal data (issue date, validity periods).
  - Photo/attachment path storage and retrieval.
- **Recommended migration**: **JdbcTemplate** (multiple joins, path handling).

#### 3. **Card Search & Photo Retrieval** (`src/com/ahct/cardSearch/`)
- **Purpose**: Search and retrieve card details (employee, journalist, dependent) and convert photo to Base64 for display.
- **Key flows**:
  - Search by card number or enrollment ID.
  - Retrieve card holder info (name, role, district, relation).
  - Read photo file from filesystem, encode Base64, embed in response.
  - Print view for journalist and employee cards.
- **Critical DAO patterns**: 
  - Simple ID-based lookups (low risk).
  - **File I/O logic** (critical—must preserve exact path handling and encoding).
- **Recommended migration**: **Spring Data JDBC** (simple reads) + custom Base64 encoder (preserve exact file I/O behavior).

#### 4. **Pre-Authorization (Pre-Auth)** (`src/com/ahct/preauth/`)
- **Purpose**: Medical pre-approval before treatment. Capture patient clinical data, treating doctor details, surgery/investigation lists, and mandatory attachments.
- **Key flows**:
  - Retrieve patient + case common details.
  - Fetch specialty, surgery, investigation, and drug lists (master data).
  - Save therapy/clinical notes (discharge summary, medication details, photos, on-bed photos).
  - Mandatory attachment checks (case files, investigation reports).
  - Comorbidity and enhancement request handling.
  - Audit trail for all clinical notes.
  - Send for panel verification and final authorization.
- **Critical DAO patterns**:
  - Complex master data lookups (specialty → surgery sub-lists, drug hierarchies).
  - Temporal tracking (telephonic dates, phase status checks).
  - Multi-attachment validation logic.
  - Approval workflow state transitions.
- **Recommended migration**: **JdbcTemplate** (complex hierarchical lookups, attachment logic).

#### 5. **Claims Processing** (`src/com/ahct/claims/`)
- **Purpose**: Process and validate submitted claims against pre-auth, calculate deductions, and manage payment readiness.
- **Key flows**:
  - Retrieve case details and approved amounts.
  - Non-technical verification (beneficiary photo matching, form signatures, document authenticity).
  - Trust doctor verification (case management protocol adherence).
  - Technical specialist review (diagnosis evidence, treatment evidence, deduction recommendations).
  - Claim head and accounts final review.
  - Generate payment files (regular, erroneous, TDS).
  - Audit trail of all verifications.
  - Status transitions (received → technical → approval → ready → payment).
- **Critical DAO patterns**:
  - Complex checklist scoring and status tracking.
  - Multi-actor workflow with role-based transitions.
  - Amount calculations and payment slabs (rules-driven).
  - File generation (payment batches, error reports).
- **Recommended migration**: **JdbcTemplate** (complex business rules, file generation, payment workflows).

#### 6. **Follow-Up Claims** (`src/com/ahct/followup/`)
- **Purpose**: Manage post-discharge/follow-up treatments within same enrollment cycle. Reduce readmission burden.
- **Key flows**:
  - Retrieve original case and available balance.
  - Capture follow-up admission/treatment details and revised amounts.
  - Comparative document/photo verification against discharge records.
  - Medication matching and batch verification.
  - Non-tech, trust doctor, technical, and claim head verification (same as regular claims).
  - Payable amount calculation (balance deduction).
- **Critical DAO patterns**: 
  - Similar to claims but with balance carry-forward calculations.
  - Comparative analysis logic (discharge vs follow-up).
- **Recommended migration**: **JdbcTemplate** (claims-like complexity; comparative logic).

#### 7. **Case Search** (`src/com/ahct/caseSearch/`)
- **Purpose**: Unified case lookup by multiple criteria (case no, claim no, patient name, card no, surgery type, status).
- **Key flows**:
  - Search across multiple statuses (pre-auth, claims, erroneous, death cases).
  - Pagination and export (CSV download).
  - Case details retrieval for downstream workflows.
- **Critical DAO patterns**: 
  - Dynamic WHERE clause construction (search filters).
  - Large result set handling with pagination.
- **Recommended migration**: **JdbcTemplate** (dynamic SQL, pagination).

#### 8. **Medical Audit & Flagging** (`src/com/ahct/medicalAudit/`, `src/com/ahct/flagging/`)
- **Purpose**: Flag suspicious cases for secondary review; audit case details against protocols.
- **Key flows**:
  - Flag criteria (patient frequency, high amount, doctor patterns).
  - Save flag with audit comments and re-open case.
  - Verify flag resolution before releasing payment.
- **Critical DAO patterns**: 
  - Audit trail insertion; status flag updates.
  - Secondary workflow re-entry logic.
- **Recommended migration**: **Spring Data JDBC** (simple inserts + lookups) or **JdbcTemplate** (if complex re-entry rules exist).

#### 9. **Panel Doctor Payment Management** (`src/com/ahct/panelDoctor/`)
- **Purpose**: Track and pay panel doctors for claims and consultations (professional fee, investigation fee).
- **Key flows**:
  - Retrieve claims assigned to panel doctors.
  - Calculate professional fee and investigation fee by role/specialty.
  - Payment gateway integration (TDS, payment status).
  - Payment report generation.
- **Critical DAO patterns**: 
  - Join claims + doctors + fees + payment rules.
  - Amount aggregation and payment slab lookups.
- **Recommended migration**: **JdbcTemplate** (fee aggregation, payment rules).

#### 10. **Annual Health Checkup (AHC)** (`src/com/ahct/annualCheckUp/`)
- **Purpose**: Manage annual preventive health check-ups for covered members.
- **Key flows**:
  - Check-up package assignment.
  - Investigation and consultation scheduling.
  - Claim entry against check-ups.
- **Critical DAO patterns**: 
  - Package lookup; investigation master data.
- **Recommended migration**: **Spring Data JDBC** (simple master data reads).

#### 11. **Biometric Registration** (`src/com/ahct/bioMetric/`)
- **Purpose**: Enroll biometric data (fingerprint) for patient identity verification during registration/claims.
- **Key flows**:
  - Capture and store biometric templates.
  - Biometric attendance tracking during visits.
  - Biometric validation reports.
- **Critical DAO patterns**: 
  - Binary data storage (templates).
  - Temporal attendance records.
- **Recommended migration**: **Spring Data JDBC** (simple template + attendance inserts).

#### 12. **Chronic OP (Outpatient) Cases** (`src/com/ahct/chronicOp/`)
- **Purpose**: Handle chronic disease management with installment-based claims.
- **Key flows**:
  - Track installment eligibility and claims per chronic condition.
  - Balance carry-forward between installments.
  - Multi-visit support within same cycle.
- **Critical DAO patterns**: 
  - Installment state machine (due → claimed → balance adj).
  - Balance arithmetic.
- **Recommended migration**: **JdbcTemplate** (stateful balance logic).

#### 13. **Telephonic Registration** (`src/com/ahct/telephonic/`)
- **Purpose**: Support registration via phone for remote/unreachable patients.
- **Key flows**:
  - Telephonic OTP verification.
  - Data entry by call center staff.
  - Follow-up confirmation.
- **Critical DAO patterns**: 
  - OTP validation; caller history.
- **Recommended migration**: **Spring Data JDBC** (simple OTP + insert).

#### 14. **Executive/CEO Approvals** (`src/com/ahct/CEO/`)
- **Purpose**: Final approval authority for high-value/escalated cases.
- **Key flows**:
  - CEO work list of pending approvals.
  - Approve/reject with remarks.
  - Sanction authority and audit trails.
- **Critical DAO patterns**: 
  - Status transitions; approval history.
  - Delegation logic (if applicable).
- **Recommended migration**: **Spring Data JDBC** or **JdbcTemplate** (depends on delegation rules).

#### 15. **Attachments Management** (`src/com/ahct/attachments/`)
- **Purpose**: Upload, retrieve, and manage case attachments (discharge summary, investigation reports, photos).
- **Key flows**:
  - Upload file to filesystem; store path in DB.
  - Link attachments to case/pre-auth/follow-up.
  - Retrieve and serve attachments (inline or download).
  - Attachment type validation (file extension checking).
- **Critical DAO patterns**:
  - Path storage and retrieval.
  - **File I/O and MIME type handling** (critical—must preserve exact behavior).
  - Attachment type enum validation.
- **Recommended migration**: **JdbcTemplate** (file I/O dependencies; exact path logic preservation).

#### 16. **Workflow & Auditing** (`src/com/ahct/workflow/`, `src/com/ahct/common/`)
- **Purpose**: Workflow state transitions, audit logging, and error handling.
- **Key flows**:
  - Case state machine (received → in-review → approved → ready → paid).
  - Audit trail on every transition (who, when, why).
  - Error flagging and re-submission workflows.
- **Critical DAO patterns**:
  - Temporal audit records.
  - Status enum tracking.
- **Recommended migration**: **Spring Data JDBC** (insert-heavy; simple state tracking).

#### 17. **Master Data & Common Functions** (`src/com/ahct/common/`, properties)
- **Purpose**: Configuration, master data lookups, and utility functions.
- **Key configurations** (from properties files):
  - `ApplicationResources.properties`: UI labels, messages, password policies.
  - `Claims.properties`: Role names, checklist items, field labels for claims review.
  - `FollowUp.properties`: Follow-up-specific labels and checklist definitions.
  - `Registration.properties`: Patient registration fields, card types, ward types.
  - `SGVConstants.properties`: System constants (timeouts, upload sizes, etc.).
  - `hibernate.db.properties`: Database connection, pool settings.
- **Key integrations**:
  - Aadhaar verification service (SOAP web service).
  - SMS/OTP service.
  - OpenAM authentication.
- **Recommended migration**: **Load all properties via Spring Boot `@PropertySource`** without restructuring (preserve exact keys and precedence).

---

### Data Model Complexity Assessment

#### **Simple (Low-Risk) Modules** → Use Spring Data JDBC
- `cardSearch`: Single/dual table lookups; file I/O wrapper.
- `bioMetric` (reads): Template + attendance retrieval.
- `telephonic`: OTP lookups; call entry.
- `annualCheckUp`: Master data reads.

#### **Moderate (Medium-Risk) Modules** → Use JdbcTemplate or Spring Data JDBC (with caution)
- `patient`: Multi-join enrollment lookups; OTP management.
- `preauth` (single-phase): Fetch clinical data; save notes.
- `CEO/approvals`: Status transitions; simple queries.

#### **Complex (High-Risk) Modules** → Use JdbcTemplate exclusively
- `claims`: Multi-actor workflow; amount calculations; file generation.
- `followup`: Comparative logic + balance carry-forward.
- `caseSearch`: Dynamic WHERE clause; pagination.
- `attachments`: File I/O + DB consistency.
- `panelDoctor`: Fee aggregation; payment slab rules.
- `preauth` (full): Multi-step state machine; mandatory attachment scoring.
- `chronicOp`: Installment state machine; balance arithmetic.
- `medicalAudit`: Audit trail + re-open logic.

---

### Critical Business Rules to Preserve

1. **Amount Calculations**:
   - Claim amount deduction based on payment slabs and checklist findings (non-tech, trust doc, tech).
   - Follow-up balance carry-forward (actual package - claimed paid = balance available).
   - Panel doctor fee by role/specialty.

2. **Workflow State Machines**:
   - Case status transitions (received → verified → approved → ready → paid → settled).
   - Erroneous case re-submission workflow.
   - Flag resolution before payment release.

3. **Mandatory Validations**:
   - Photo matching (beneficiary photo ≈ discharge photo ≈ on-bed photo).
   - Signature verification and consistency checks.
   - Mandatory attachment checklist (reports signed, dates correct).

4. **Temporal Rules**:
   - Admission/discharge/death date consistency.
   - Telephonic intimation within X days.
   - Follow-up within Y days of discharge.

5. **Audit Trail**:
   - Every case status change logged with user, role, timestamp, remarks.
   - Claim verification step history (who verified, when, outcome).

6. **Multi-Role Workflows**:
   - Role-based claim verification (MEDCO → Trust Doctor → Specialist → Claim Head → Accounts).
   - Separate workflows for journalist, employee, pensioner, AIS card holders.

7. **File I/O Semantics**:
   - Photo Base64 encoding for UI display (must preserve exact encoding).
   - Payment file generation with precise formatting.
   - Attachment serve with MIME type and inline/download handling.

---

### Database Characteristics

- **Schema**: Oracle (or similar RDBMS); relatively stable, schema is documented in `db/Schema_Table_Details.xlsx`.
- **Key tables** (inferred from code):
  - `EHF_PATIENT`: Patient master (enrollment, card details, OTP).
  - `EHF_CASE`: Case master (case number, patient, status, admission/discharge dates).
  - `EHF_PREAUTH`: Pre-authorization details (approved amount, clinical notes).
  - `EHF_CLAIM`: Claim submission (claim amount, submitted date, status).
  - `EHF_CLAIM_CHECKLIST`: Checklist item results (non-tech, trust doc, tech scores).
  - `EHF_FOLLOWUP`: Follow-up claim details (balance, follow-up date).
  - `EHF_ATTACHMENT`: Case attachments (path, type, link to case/preauth/followup).
  - `EHF_AUDIT_LOG`: Audit trail (case ID, action, user, timestamp, remarks).
  - `EHF_BIOMET_ENROLL`: Biometric templates and templates.
  - `EHF_PAYMENT_GATEWAY`: Payment status (claim ID, payment date, reference).
  - Master tables: `EHF_M_SURGERY`, `EHF_M_INVESTIGATION`, `EHF_M_DRUGS`, `EHF_M_HOSPITALS`, `EHF_M_PAYMENT_SLAB`, etc.

---

### Configuration & Property Usage

All runtime configuration is driven by `properties/*.properties` files. **No restructuring permitted**; Boot must read these exactly as-is:

| File | Purpose | Key Examples |
|---|---|---|
| `ApplicationResources.properties` | UI labels, messages, session timeout | `MAX_INACTIVE_INTERVAL`, password policy messages |
| `Claims.properties` | Claims workflow labels and role-to-code mappings | `EHF.Claims.Role.MEDCO=GP2`, checklist item labels |
| `FollowUp.properties` | Follow-up workflow labels | `EHF.Title.FollowUpClaimForm`, verification checklist |
| `Registration.properties` | Patient registration field labels, card types | `EHF.Cardtype`, ward types, identification proof |
| `SGVConstants.properties` | System constants | (need to read to confirm) |
| `hibernate.db.properties` | DB connection, Hibernate dialect | JDBC URL, datasource JNDI name |
| XML configs (if any) | Spring bean wiring, DAO config | (preserved; loaded unchanged by Boot bridge) |

---

### Phase-by-Phase Execution Plan

### Phase 0: Inventory and Baseline

- Inventory all Struts actions/forms/forwards from `WebContent/struts-config.xml`.
- Map JSP pages and action flags to user workflows.
- Inventory DAO/query usage from `src/com/ahct/**`.
- Build schema cross-reference from `db/Schema_Table_Details.xlsx`.
- Capture baseline behavior for critical flows (request/response + DB impact).

**Exit criteria**
- Endpoint inventory complete.
- Module dependency map complete.
- Baseline regression scenarios approved.

### Phase 1: Platform Foundation (Parallel Run)

- Create new Spring Boot backend (Java 17 + Maven) alongside existing app.
- Introduce config bridge to load existing property/XML files without restructuring.
- Keep legacy Ant build active while Boot foundation stabilizes.
- Stand up React shell app with no functional cutover yet.

**Exit criteria**
- Boot starts with legacy config values.
- Shared database connectivity validated.
- Legacy deployment remains unaffected.

### Phase 2: Strangler Routing and Compatibility

- Keep legacy ownership for existing `.do` + JSP routes.
- Introduce Boot APIs under `/api/**` and React under `/app/**` (or equivalent dedicated path).
- Add feature toggles per module/flow to switch routing.
- Define rollback playbook: route flip back to legacy.

**Exit criteria**
- Parallel runtime with zero route collision.
- Route-based rollback tested.

### Phase 3: Pilot Vertical Slice

- Pick a low-risk module first (recommended: read-heavy `cardSearch` / `attachments`).
- Migrate action handling to Boot controller while preserving input/output semantics.
- Replace Hibernate DAO path with `JdbcTemplate` or Spring Data JDBC based on DAO complexity.
- Build corresponding React screen while retaining JSP fallback.

**Exit criteria**
- Functional parity passed for pilot scenarios.
- SQL and config parity validated.
- Rollback toggle verified in staging.

### Phase 4: Domain-Wise Migration Waves

- Migrate module-by-module in waves from low to high coupling.
- For each module:
  1. Controller route migration
  2. Service adapter (logic unchanged)
  3. DAO migration (Hibernate -> JDBC)
  4. React UI replacement
  5. Parity validation and sign-off

**Suggested wave order**
1. `cardSearch`, `attachments`, selected read-only flows
2. `preauth`, `followup`
3. `claimsFlow`, `panelDoctor`, `medicalAudit`
4. `patient` and other high-complexity modules

### Phase 5: Scheduler and Background Flow Migration

- Migrate scheduler/background jobs with same timing, retries, and side effects.
- Validate idempotency and operational behavior against baseline.

**Exit criteria**
- Job outputs and timing windows match legacy expectations.

### Phase 6: Cutover and Decommission

- Shift default traffic to Spring Boot + React after all parity gates pass.
- Keep rollback window for at least one release cycle.
- Decommission Struts/Hibernate/Ant components in controlled manner.

**Exit criteria**
- Full regression and parity sign-off completed.
- Rollback drill executed successfully.

## Data Access Migration Decision Framework

### Prefer `JdbcTemplate` when

- Query logic is complex/dynamic.
- Legacy DAO has heavy SQL/HQL manipulation.
- Exact SQL control is required for parity.
- Module is high-risk and needs deterministic behavior.

### Prefer Spring Data JDBC when

- Aggregate/table mapping is straightforward.
- CRUD-style operations dominate.
- Query complexity is low to moderate.

### Recommended practical policy

- Start with `JdbcTemplate` for complex legacy DAOs.
- Introduce Spring Data JDBC for stable/simple domains after parity confidence is established.
- Allow mixed strategy by module; optimize later after full parity.

## Configuration Preservation Strategy

- Keep all existing files in `properties/` as-is.
- Continue reading existing XML-based configuration sources where currently used.
- Do not rename keys or change precedence/order.
- Add automated config parity checks to compare resolved values between legacy and Boot paths.

## DDL (XLSX) Validation Strategy

Use `db/Schema_Table_Details.xlsx` to validate:

- Table and column usage for each migrated DAO.
- Data type and nullability expectations.
- Key and relationship assumptions in query logic.
- Regression checks for inserts/updates/select projections.

## Testing and Validation Gates (Per Module)

1. **Behavioral parity**: same outcomes for same inputs.
2. **SQL parity**: same row count/order/transaction behavior.
3. **Configuration parity**: same effective values and resolution source.
4. **Schema parity**: DAO-to-DDL consistency using xlsx baseline.
5. **Rollback readiness**: feature toggle can restore legacy path immediately.

## Risks and Anti-Patterns to Avoid

- Big-bang migration across all modules at once.
- Refactoring business logic during framework replacement.
- Moving or restructuring configuration sources prematurely.
- Replacing all DAOs with one data style without complexity analysis.
- Disabling legacy routes before proving parity and rollback.

## Deliverables

- Endpoint/action inventory and migration backlog.
- DAO classification sheet (`JdbcTemplate` vs Spring Data JDBC).
- DDL validation matrix from `db/Schema_Table_Details.xlsx`.
- Module-wise parity reports (behavior + SQL + config).
- Rollback runbook and route-toggle matrix.
- Final handover documentation.

## 30 / 60 / 90 Day Roadmap

### Day 0-30

- Complete inventory, baseline capture, and migration backlog.
- Set up Boot + Maven + config bridge and React shell.
- Finalize pilot module scope and parity test pack.

### Day 31-60

- Execute pilot migration and canary rollout.
- Produce first parity report.
- Start second low-risk module wave.

### Day 61-90

- Begin high-value core module migration wave.
- Migrate scheduler paths with parity checks.
- Finalize cutover criteria and decommission readiness checklist.

---

## Property-Oriented Business Logic (Configuration-Driven Rules)

All service behavior is configurable via properties files. These govern business rules, workflow behavior, and system integrations. During migration, these exact property keys and resolution order **must be preserved**.

### Login & Authentication Configuration
**Source**: `ApplicationResources.properties` + LDAP/OpenAM config

| Property | Purpose | Example Value | Business Rule |
|---|---|---|---|
| `LDAPlogin` | Enable/disable LDAP authentication | `true` or `false` | If true, validate user via LDAP; else fall back to local DB |
| `MAX_INACTIVE_INTERVAL` | Session timeout in seconds | `1800` | Session expires after 30 mins of inactivity; user logged out |
| `label.PWD.kmsMsgNewPwdLength` | Password length requirement | `Password length should exactly 8` | Enforce 8-character password length |
| `label.PWD.kmsMsgNewPwdNumeric` | Numeric requirement message | `New password should contain atleast one numeric digit` | Password **must** contain ≥1 numeric character |
| `label.PWD.kmsMsgNewPwdSplChrs` | Special character requirement | `New password should contain atleast one special character` | Password **must** contain ≥1 special character |
| `label.PWD.kmsMsgNewPwdChrs` | Alphabet requirement | `New password should contain atleast one alphabet` | Password **must** contain ≥1 alpha character |
| `sendPasswordSMS` | Send SMS notification on password change | `Y` or `N` | If `Y`, send SMS to registered mobile; critical for OTP workflows |
| `changePwdEmailFlag` | Send email notification on password change | `Y` | If `Y`, send email via template `EHFPasswordTemplateName` |
| `EHFPasswordTemplateName` | Email template name | (varies) | Used for password reset/change notifications |

**Service Logic Preserved**
- Password validation during change (numeric + special + alphabet + length checks).
- SMS/Email notification triggers.
- LDAP fallback logic if primary auth fails.

---

### Patient Registration & Enrollment Configuration
**Source**: `Registration.properties` + `ApplicationResources.properties`

| Property | Purpose | Example Value | Business Rule |
|---|---|---|---|
| `EHF.Cardtype` | Card type enumeration | Employee, Pensioner, Journalist, AIS, New Born | Enrollment flow differs by card type |
| `EHF.EmployeeCardNo` | Label for employee card lookup | Employee Card No | UI field label; also determines search field in DB |
| `EHF.DateOfBirth` | Date of birth field label | Date Of Birth | Required field for enrollment; used in age calculations |
| `EHF.MaritalStatus` | Marital status enumeration | (varies by property file) | Impacts family head linkage and coverage rules |
| `EHF.GeneralWard`, `EHF.SemiPrivateWard` | Ward type selections | Ward type names | Determines pre-auth/claim verification scope |
| OTP Exemption Workflow | (implicit in Registration.properties) | (varies) | Some users exempt from OTP; exemption approved via workflow |

**Service Logic Preserved**
- Card number → enrollment details lookup (join card + family + active benefits).
- OTP generation and validation (random 6-digit).
- Exemption approval chain (manager → HR → system admin).
- Photo capture and biometric binding during enrollment.

---

### Claims Processing & Verification Configuration
**Source**: `Claims.properties`

| Property | Purpose | Example Value | Business Rule |
|---|---|---|---|
| `EHF.Claims.Role.MEDCO` | Role code for medical coordinators | `GP2` | Claims flow: MEDCO → Trust Doctor → Specialist → Claim Head → Accounts. Each role has different verification checklist. |
| `EHF.Claims.Role.Mithra` | Role code for trust doctors | `GP1` | Trust doctor verifies: treatment protocol adherence, signature authenticity. |
| `EHF.Claims.Role.CEX` | Role code for claim heads | `GP6` | Claim head final approval on deduction recommended; ready for payment. |
| `EHF.Claims.Role.COCCEX` | Role code for accounts (Co-CCEx) | `GP72` | Accounts role: approves payment, TDS calculation, file generation. |
| `EHF.Claims.Role.CPD` | Role code for panel doctor coordinator | `GP7` | Panel doctor fee calculation and payment. |
| `EHF.Claim.PackAmt` | Label: Pre-Auth Approved Amount | PreAuth Approved Amount | Anchor amount for deduction calculation. |
| `EHF.Claim.TotalClaimable` | Label: Total Claimable Amount | Total Claimable Amount | After all deductions, the payable amount to hospital. |
| `EHF.Claim.DateVerif` | Document verification checklist label | Date Verification | MEDCO verifies dates consistency (admission ≈ discharge ≈ surgery dates). |
| `EHF.Claim.BenPhoto` | Photo matching checklist label | Is beneficiary card photo matching? | **Non-tech checklist item**: visual match of card photo + discharge photo + on-bed photo. |
| `EHF.Claim.NameCaseSheet` | Name verification checklist | Name in case sheet and consent forms is correct | MEDCO validates patient name consistency across documents. |
| `EHF.Claim.GenderCaseSheet` | Gender verification checklist | Gender in case sheet is correct | MEDCO validates patient gender consistency. |
| `EHF.Claim.DocVer1` through `DocVer6` | Document verification items (Trust Doctor) | (specific checklist texts) | Trust doctor validates: signatures match, dates correct, mandatory reports attached. |
| `EHF.Claim.trustDoc1` through `trustDoc3` | Trust doctor verification (protocol/evidence) | (specific texts) | Trust doctor validates: case mgmt protocol adherence, therapy evidence exists. |
| `EHF.Claim.techCheck1` through `techCheck9` | Technical specialist checklist | (amount breakdowns, diagnosis evidence, treatment evidence) | Specialist validates claim against medical protocols; recommends deduction. |

**Service Logic Preserved**
- **Multi-role workflow**: status transitions only allowed for assigned role + valid state.
- **Checklist scoring**: each verification (non-tech, trust doc, tech) produces pass/fail or partial score.
- **Deduction calculation**: `Total Claim - Deduction Recommended = Payable Amount`.
- **Amount tracking**: pre-auth amount, claimed amount, deduction, payable amount, paid amount (audit trail).
- **File generation**: payment file with TDS calculation, error report (erroneous claims), remittance advice.

---

### Follow-Up Claims Configuration
**Source**: `FollowUp.properties`

| Property | Purpose | Example Value | Business Rule |
|---|---|---|---|
| `EHF.Title.FollowUpClaimForm` | Form title | FOLLOWUP CLAIM FORM | UI label; indicates follow-up (not initial) claim. |
| `EHF.FollowUpId` | Follow-up ID label | FollowUp ID | Unique identifier linking to original case + balance. |
| `EHF.FollowUpDt` | Follow-up date label | Followup Date | Must be within X days of discharge; later follows-up rejected. |
| `EHF.BalanceAvailb` | Available balance label | Balance Available | Calculated as: (Original Package + Enhancements - Original Claim Paid). |
| `EHF.CarryFwd` | Carry-forward amount | Carry Forward | Balance carried to follow-up; can be used for new treatment. |
| `EHF.NonTechChkListPhoto1` | Follow-up photo matching | Discharge Photo & Follow-up Photo Matching | **Comparative rule**: discharge photo must match follow-up treatment photo. |
| `EHF.NonTechChkListDoc2` | Medication matching | Medication Period Matching | Follow-up medication dates must match treatment dates. |
| `EHF.NonTechChkListDoc3` | Medication batch matching | Quantity Matching & Batch no & Expiry Check | Verify batch numbers and expiry dates for drugs used. |
| `EHF.Claims.Role.NAM`, `FCX`, `FTD`, `CH` | Follow-up verification roles | `GP1` (NAM), `GP10` (FCX), `GP11` (FTD), `GP9` (CH) | Follow-up has same multi-role verification as claims but with comparative logic. |

**Service Logic Preserved**
- **Balance carry-forward rule**: `Follow-up Payable = MIN(Balance Available, Follow-up Claimed Amount)`.
- **Temporal rule**: follow-up must be submitted within Y days of discharge; later submissions rejected.
- **Comparative verification**: photo and document matching against original discharge records.
- **Reuse of checklist**: non-tech, trust doc, technical verification applied; amounts deducted from available balance (not original package).

---

### Case Search & Workflow Configuration
**Source**: `SGVConstants.properties` + Claims.properties

| Property | Purpose | Example Value | Business Rule |
|---|---|---|---|
| `CASE.CaseRegistered` | Case status code for "Registered" | `CD73` | Initial case state after patient registration. |
| `CASE.OnBed` | Case status code for "On Bed" | `CD483` | Case state during admission; used in preauth flow. |
| `case.RowsPerPage` | Pagination rows per page | `10` | Case search results paginated; default 10 rows/page. |
| `EHF.Claims.DISCHARGE` | Case status for discharge | (varies) | Triggers day-count calculation: days elapsed since discharge. |
| `IPOP.APCode` | IP/OP code for case type | `S17` | Determines approval workflow (IP claims vs OP claims). |
| `IPOP.CmbhdrIDS` | Combo header IDs (state, district, etc.) | `CH4~CH11~CH6` | Master data filter criteria; determines location hierarchy. |
| `Role.Aarogyamithra` | Aarogyamithra role code | `CD10` | Community health worker role; limited permissions. |
| `Role.Ramco` | RAMCO (field staff) role code | `CD9` | Field staff role; enroll patients, capture photos. |

**Service Logic Preserved**
- **Case status workflow**: registered → on-bed → discharged → (preauth/claims) → (verified/approved) → ready → paid.
- **Dynamic search**: case search filters by status, date range, role; results paginated and exported to CSV.
- **Role-based visibility**: only cases assigned to user's role/group visible in work lists.

---

### Attachment & File I/O Configuration
**Source**: `Claims.properties` + `Registration.properties` (implicit file handling)

| Property | Purpose | Example Value | Business Rule |
|---|---|---|---|
| `preauth_onbed_cmbDtlId` | Combo detail ID for on-bed photos | (code reference) | Identifies on-bed photos in attachment system; used for attachment type filtering. |
| `Signedprf_docs` | Attachment type for signed professional docs | (code reference) | Case documents that must be signed; verified in trust doctor phase. |
| `preauth_partail_save` | Attachment action for partial save | (code reference) | Allows saving attachments during preauth without final submission. |
| `EHF_CASE_DOC_ATTCH_seq` | Database sequence for attachment IDs | (sequence name) | Auto-increment ID generation for case attachments. |
| `ENROLLMENT_*_REJECTION` | Enrollment rejection status codes | `ENROLLMENT_DDO_REJECTION`, `ENROLLMENT_TL_REJECTION`, etc. | Filter rejected enrollments in query. |

**Service Logic Preserved**
- **File upload**: received file uploaded to filesystem; path stored in DB; attachment linked to case/preauth/followup.
- **File I/O**: read file from path, detect MIME type, serve inline (images/PDFs) or download (archives).
- **Base64 encoding**: for UI display (cardSearch photo rendering); must preserve exact encoding (UTF-8).
- **Path validation**: check file exists before serving; fallback to 404.jpg if missing.
- **Attachment type validation**: file extension checked against allowed types; only specific types allowed per attachment use.

---

## Service-Based Business Logic (Code-Level Rules)

Service implementations (`*ServiceImpl.java`) enforce core business rules that cannot be expressed in properties alone. These orchestrate DAOs, apply calculations, manage state transitions, and log audit trails.

### 1. **Claims Processing Service** (`ClaimsFlowServiceImpl`)
**File**: `src/com/ahct/claims/service/ClaimsFlowServiceImpl.java` (2199 lines)

#### Key Methods & Rules

**`getCaseDtls(ClaimsFlowVO)`** - Retrieve and calculate claim details
```
Rule 1: Calculate total approved amount
  totalPack = packageApprovedAmount + enhancementApprovedAmount + comorbidApprovedAmount
  
Rule 2: Calculate days elapsed since discharge/death
  daysDiff = (today - discharge_date) / (24 * 3600 * 1000) ms
  Used to enforce claim submission deadline.
  
Rule 3: Status-specific logic
  IF case_status == "DISCHARGE":
    calculate daysDiff (triggers validation)
  ELSE IF case_status == "ON_BED":
    case still in hospital; cannot claim yet.
```

**`saveClaim(ClaimsFlowVO)`** - Save claim and update case status
```
Rule 4: Claim state transition
  BEFORE: case_status = "DISCHARGE" OR "ERR_CLAIM_RESUBMIT"
  ACTION: Insert into EHF_CLAIM with claim_amount, submitted_date
  AFTER: Update case_status = "CLAIM_RECEIVED"
  
Rule 5: Audit trail insertion
  Log: user_id, role, timestamp, action ("CLAIM_SAVED"), remarks
  Used for compliance and dispute resolution.
```

**`updateClaimStatus(HashMap)`** - Role-based status transition
```
Rule 6: Multi-role workflow enforcement
  IF role == "MEDCO" (GP2):
    Allowed transitions: CLAIM_RECEIVED → CLAIM_MEDCO_VERIFIED
    Validates: non-tech checklist completion
    
  IF role == "TRUST_DOC" (GP1):
    Allowed transitions: CLAIM_MEDCO_VERIFIED → CLAIM_TRUST_DOC_VERIFIED
    Validates: trust doctor checklist completion
    
  IF role == "TECHNICAL" (GP3):
    Allowed transitions: CLAIM_TRUST_DOC_VERIFIED → CLAIM_TECH_VERIFIED
    Validates: technical specialist checklist completion
    
  IF role == "CLAIM_HEAD" (GP6):
    Allowed transitions: CLAIM_TECH_VERIFIED → CLAIM_HEAD_APPROVED
    Validates: final deduction approved (amount ≥ 0)
    
  IF role == "ACCOUNTS" (GP72):
    Allowed transitions: CLAIM_HEAD_APPROVED → READY_FOR_PAYMENT
    Calculates: TDS deduction, final payable, generates payment file
```

**`generateFile()` / `generateFollowUpFile()` / `generateERRFile()`** - Payment file generation
```
Rule 7: Payment file format and content
  File format: Fixed-width or CSV (role-specific)
  Contents: claim_id, case_id, payable_amount, TDS_deduction, net_amount, hospital_code, status
  Triggers: when status = "READY_FOR_PAYMENT"
  Frequency: Daily/on-demand batch generation
  
Rule 8: Erroneous claim file
  Trigger: IF original_claim_status = "ERRONEOUS"
  File contents: claim_id, error_reason, resubmitted_amount, previous_rejections
  Used by hospital to resubmit corrected claim.
  
Rule 9: TDS calculation
  TDS = payable_amount * 0.02 (2% standard; can be role/hospital-specific)
  Net payment = payable_amount - TDS
```

**`getAuditTrail(ClaimsFlowVO)`** - Retrieve claim verification history
```
Rule 10: Temporal audit trail
  Query: SELECT * FROM EHF_AUDIT WHERE case_id = ? ORDER BY timestamp DESC
  Includes: who (user_id, role), when (timestamp), what (action), why (remarks)
  Used for compliance reporting and dispute resolution.
```

### 2. **Login Service** (`LoginServiceImpl`)
**File**: `src/com/ahct/login/service/LoginServiceImpl.java` (1093 lines)

#### Key Business Rules

**Password Change Flow**
```
Rule 11: Password complexity validation
  IF new_password.contains(numeric) && new_password.contains(special_char) 
     && new_password.contains(alpha) && new_password.length() == 8:
    password_valid = true
  ELSE:
    Reject with specific message (missing numeric, special char, length, etc.)
    
Rule 12: Password history check
  IF new_password IN last_10_passwords:
    Reject with "This password has been set before. Please enter a new password."
  
Rule 13: Notification on password change
  IF config.getString("sendPasswordSMS") == "Y":
    Generate OTP (6 digits, random)
    Send SMS: "Your new password is set. Please use OTP: {otp} to verify."
  IF config.getString("changePwdEmailFlag") == "Y":
    Send email via template (EHFPasswordTemplateName)
    Subject: config.getString("passwordResetEmailSubject")
    From: config.getString("emailFrom")
```

**Session Management**
```
Rule 14: Session timeout
  session.invalidate() after MAX_INACTIVE_INTERVAL seconds
  User logged out; must re-authenticate.
  
Rule 15: OpenAM integration
  IF config.getString("LDAPlogin") == "true":
    Authenticate against OpenAM/LDAP
    If success: populate user roles from OpenAM
    If fail: attempt local DB auth (fallback)
  ELSE:
    Authenticate against local EHF_M_USERS table (password hash comparison)
```

### 3. **Attachment Service** (`AttachmentServiceImpl`)
**File**: `src/com/ahct/attachments/service/AttachmentServiceImpl.java`

#### Key Business Rules

**`savePotoAttach(AttachmentVO)`** - Save attachment (photo/document)
```
Rule 16: File upload to filesystem
  Generated_path = base_upload_dir + "/" + case_id + "/" + attachment_type + "/" + timestamp + "." + file_ext
  Save file to filesystem at generated_path
  Check if base_upload_dir exists; create if not.
  
Rule 17: Database record insertion
  INSERT INTO EHF_CASE_DOC_ATTCH:
    attach_id = getNextVal(config.getString("EHF_CASE_DOC_ATTCH_seq"))
    case_id, attach_type, file_path, upload_user, upload_date, remarks
  
Rule 18: Attachment type validation
  IF attachment_type == config.getString("preauth_onbed_cmbDtlId"):
    only image files allowed (.jpg, .jpeg, .gif, .png)
  ELSE IF attachment_type == config.getString("Signedprf_docs"):
    PDF or signed document required
  Reject unsupported file types.
```

**`getcaseAttachments(AttachmentVO)`** - Retrieve attachments
```
Rule 19: Filtered attachment query
  IF attachment_type == config.getString("Signedprf_docs"):
    Query: SELECT * FROM EHF_CASE_DOC_ATTCH 
           WHERE case_id = ? AND attach_type = ? AND act_id IN ('partail_save_action')
  (Filters based on attachment use-case)
  
Rule 20: File existence check
  FOR each attachment:
    IF file_path exists on filesystem:
      return file_path
    ELSE:
      log warning, set placeholder path (or fallback image)
```

### 4. **Pre-Authorization Service** (`PreauthServiceImpl`)
**File**: `src/com/ahct/preauth/service/` (multiple files)

#### Key Business Rules

**`getPatientCommonDtls(String caseId)`** - Fetch case-related master data
```
Rule 21: Master data hierarchies
  Query: get case + patient details
  Then: fetch disease_list (by patient condition)
  Then: fetch surgery_list (filtered by disease)
  Then: fetch investigation_list (filtered by surgery + disease)
  Then: fetch drug_list (filtered by disease + investigation)
  (Hierarchical filtering; not flat lists)
  
Rule 22: Temporal availability checks
  FOR each surgery/investigation:
    IF effective_date <= today <= end_date:
      Include in list (active)
    ELSE:
      Exclude from list (inactive)
```

**`saveClinicalNotes(PreauthClinicalNotesVO, userId)`** - Save therapy/clinical data
```
Rule 23: Mandatory attachment check
  IF case_phase == "PREAUTH_SUBMISSION":
    Required attachments: case_file, investigation_reports (at least 1)
  IF attachment_count < required_count:
    Reject with "Mandatory attachments missing"
  
Rule 24: Phase state machine
  BEFORE: phase_id = X
  ACTION: Insert clinical notes + audit trail
  AFTER: phase_id = X+1 (advance phase)
  
Rule 25: Concurrent edit prevention
  IF last_modified_by != current_user:
    Check last_modified_timestamp
    IF (now - last_modified_timestamp) < 5 minutes:
      Reject with "Case being edited by another user"
    ELSE:
      Allow edit (session expired; safe to proceed)
```

**`sentForPreauth(PreauthVO)`** - Submit for pre-auth review
```
Rule 26: Pre-submission validation
  Verify: patient_enrolled && case_registered && hospital_active && clinical_notes_saved
  Verify: mandatory attachments present
  Verify: no pending flags on case
  
Rule 27: Status transition
  BEFORE: case_status = "PREAUTH_DRAFT"
  ACTION: Update case_status = "PREAUTH_SUBMITTED", submission_date = now
  AFTER: Send notification to pre-auth team (email/SMS based on config)
  
Rule 28: Audit trail
  Log: user_id, role, action ("PREAUTH_SUBMITTED"), submitted_amount, remarks
```

### 5. **Patient Registration Service** (`PatientServiceImpl`)
**File**: `src/com/ahct/patient/service/PatientService.java` (670 methods)

#### Key Business Rules

**`retrieveCardDetails(PatientVO)`** - Card number → enrollment lookup
```
Rule 29: Card type-specific lookup
  IF card_type == "EMPLOYEE":
    Query: EHF_EMPLOYEE_ENROLL (employee card master)
    Validate: card_issue_date <= today <= card_expiry_date (active)
  ELSE IF card_type == "PENSIONER":
    Query: EHF_PENSIONER_ENROLL (pensioner card master)
    Validate: pension_active_yn = "Y"
  ELSE IF card_type == "JOURNALIST":
    Query: EHF_JOURNALIST_ENROLL
    Validate: special_status = "ACTIVE"
  
Rule 30: Family head linkage
  IF enrolled_member == "DEPENDENT":
    Query: EHF_FAMILY_HEAD WHERE family_head_id = enrolled_family_head_id
    Retrieve: family_head_name, family_head_card_details
    Store in patient VO for later reference.
```

**`registerPatient(PatientVO)`** - Register new patient
```
Rule 31: Enrollment duplicate check
  IF card_number + sno (serial number) already in EHF_PATIENT:
    Reject with "Patient already enrolled"
  
Rule 32: Aadhaar verification (if applicable)
  IF card_type IN ("EMPLOYEE", "PENSIONER"):
    Call AadhaarVerificationService.verify(aadhaar_number)
    IF verification_success:
      enrollment_aadhaar_verified = "Y"
    ELSE:
      Warn but allow conditional enrollment (verify_pending)
  
Rule 33: OTP verification (mandatory for certain card types)
  IF card_type == "JOURNALIST":
    Generate OTP, send SMS to phone
    User must verify OTP before registration completes
  ELSE:
    Enrollment completes after photo capture + biometric.
    
Rule 34: Biometric enrollment (optional enhancement)
  IF biometric_mode_enabled:
    Capture fingerprint, store template in EHF_BIOMET_ENROLL
    Link to patient_id for future identification.
```

**`sendMobileOtp(String patientId, String mobileNo)` / `verifyMobileOTP(String patientId, String otp)`**
```
Rule 35: OTP generation and storage
  otp = random 6-digit number (100000-999999)
  Store in EHF_PATIENT with otp_value, otp_generated_timestamp
  Set otp_expiry = now + 10 minutes (configurable)
  
Rule 36: OTP validation on verify
  Query: EHF_PATIENT WHERE patient_id = ? AND otp_value = ?
  IF otp_expiry < now:
    Reject with "OTP expired"
  IF otp_match:
    Update patient_otp_verified = "Y"
    Return success
  ELSE:
    Increment otp_attempt_count
    IF otp_attempt_count > 3:
      Lock patient account temporarily
```

**`viewExemptOtpList(String userId, String patientId)` / `exemptOtpApproval(...)`**
```
Rule 37: OTP exemption workflow
  Manager can request exemption for specific patients (elderly, disabled, etc.)
  Request logged in EHF_OTP_EXEMPTION_REQ with status = "PENDING"
  
Rule 38: Approval chain
  Manager requests -> HR reviews -> System admin approves
  Once approved: patient_otp_exemption = "Y"
  Patient no longer required to enter OTP during registration.
```

---

### 6. **Card Search Service** (`cardSearchServiceImpl`)
**File**: `src/com/ahct/cardSearch/service/cardSearchServiceImpl.java`

#### Key Business Rules

**`getCardStatusList(String empRadio, String cardNo)`** - Search by radio selection + card number
```
Rule 39: Radio-based search filtering
  IF empRadio == "EMPLOYEE":
    Query: employee_card_master WHERE card_number LIKE cardNo%
  ELSE IF empRadio == "JOURNALIST":
    Query: journalist_card_master WHERE card_number LIKE cardNo%
  ELSE IF empRadio == "DEPENDENT":
    Query: dependent_card WHERE card_number LIKE cardNo%
  
Rule 40: Result set transformation
  FOR each card record:
    Fetch: card_holder_name, role, district, relation_to_head
    Fetch: photo_path FROM attachment table
    Return as cardSearchVO list
```

**`getUsrId(String cardNo)` / `getEmpUsrId(String cardNo)`** - Resolve user ID from card
```
Rule 41: User resolution
  Query: EHF_M_USERS WHERE linked_card_number = ?
  Return: user_id (internal system user, not card holder)
  Used for audit trails and authorization checks.
```

**File I/O for Photo Base64 Encoding**
```
Rule 42: Photo file I/O (CRITICAL - must preserve exact behavior)
  1. Read file from file_path (from DB)
  2. Check file exists; if not, fallback to 404.jpg
  3. Read entire file into byte array:
     FileInputStream fis = new FileInputStream(file_path)
     DataInputStream dis = new DataInputStream(fis)
     byte[] imageData = new byte[dis.available()]
     dis.readFully(imageData)
     dis.close()
  4. Base64 encode:
     byte[] encoded = org.apache.commons.codec.binary.Base64.encodeBase64(imageData)
  5. Convert to string:
     String photo = new String(encoded, "UTF8")
  6. Set request attribute:
     request.setAttribute("photo" + index, photo)
  7. JSP renders: <img src="data:image/jpeg;base64,{photo}" />
  
PARITY REQUIREMENT: exact UTF-8 encoding, exact Base64 algorithm, exact request attr naming
```

---

### 7. **Follow-Up Claims Service** (implied from Claims service)
**File**: `src/com/ahct/followup/` (inferred structure)

#### Key Business Rules

**Balance Carry-Forward Logic**
```
Rule 43: Calculate available balance
  available_balance = original_package_amount - original_claim_paid
  + enhancements - enhancement_claim_paid
  + comorbid - comorbid_claim_paid
  
Rule 44: Follow-up deduction from balance
  follow_up_payable = MIN(available_balance, follow_up_claimed_amount - follow_up_deductions)
  Example: available_balance=100K, follow_up_claim=80K, deduction=5K
           -> follow_up_payable = MIN(100K, 75K) = 75K
  
Rule 45: Temporal constraint
  IF follow_up_submission_date > (discharge_date + 90 days):
    Reject with "Follow-up claim submission window expired"
  (90 days is configurable per policy)
```

**Comparative Verification Logic**
```
Rule 46: Discharge vs follow-up photo matching
  Require: both discharge_photo AND follow_up_treatment_photo
  Manual verification step: MEDCO compares images
  Result: "MATCH" or "MISMATCH" or "UNDECIDED"
  If MISMATCH: raise flag, escalate to supervisor.
  
Rule 47: Medication consistency check
  Fetch: original_discharge_medications (from preauth/discharge summary)
  Fetch: follow_up_medications (from follow-up submission)
  Verify: follow_up_meds are continuation/appropriate follow-on to discharge meds
  Reject if: conflicting medications or unsafe combinations.
```

---

### Service-Based Rules Summary Table

| Module | Service | Key Method | Rule Type | Preservation Requirement |
|---|---|---|---|---|
| Claims | ClaimsFlowServiceImpl | getCaseDtls | Amount calculation | Preserve date arithmetic (discharge days calc) |
| Claims | ClaimsFlowServiceImpl | updateClaimStatus | Workflow state machine | Preserve role-to-status mapping (MEDCO → TRUST_DOC → ...) |
| Claims | ClaimsFlowServiceImpl | generateFile | File generation | Preserve exact file format, TDS calc (2%), field order |
| Login | LoginServiceImpl | passwordChange | Validation logic | Preserve 8-char, numeric, special char, alpha requirements |
| Login | LoginServiceImpl | sessionTimeout | Temporal logic | Preserve MAX_INACTIVE_INTERVAL config reading |
| Attachments | AttachmentServiceImpl | savePotoAttach | File I/O | Preserve file path generation, upload dir creation |
| Attachments | AttachmentServiceImpl | (implicit) | Base64 encoding | **CRITICAL**: exact UTF-8, exact Base64, exact attr naming |
| Preauth | PreauthServiceImpl | saveClinicalNotes | State machine | Preserve phase transitions, audit trail insertion |
| Preauth | PreauthServiceImpl | getPatientCommonDtls | Master data hierarchy | Preserve disease → surgery → investigation → drug filtering |
| Patient | PatientServiceImpl | retrieveCardDetails | Card type dispatch | Preserve card_type-to-table mapping (EMPLOYEE, PENSIONER, ...) |
| Patient | PatientServiceImpl | registerPatient | Aadhaar + OTP flow | Preserve Aadhaar verification call, OTP generation (6-digit, 10 min expiry) |
| Patient | PatientServiceImpl | verifyMobileOTP | Temporal + attempt limiting | Preserve 10-min expiry, 3-attempt lock logic |
| CardSearch | cardSearchServiceImpl | (implicit) | Base64 encoding | **CRITICAL**: preserve exact file I/O, UTF-8 encoding, fallback to 404.jpg |
| FollowUp | (implied) | (implied) | Balance carry-forward | Preserve balance arithmetic, 90-day window enforcement |
| FollowUp | (implied) | (implied) | Comparative verification | Preserve photo matching logic (manual), medication cross-check |

---

## Integration Points & External Dependencies

### Configuration Service
```
Pattern: ConfigurationService accessed as singleton
  CompositeConfiguration config = configurationService.getConfiguration()
  String value = config.getString("property_key")
  
MIGRATION NOTE: Spring Boot replaces with @Value or @ConfigurationProperties
  @Value("${property.key:default_value}")
  private String propertyValue;
```

### DAO Layer
```
Pattern: Service delegates to DAO for persistence
  DAO retrieves/updates entities; service applies business logic
  
MIGRATION: JdbcTemplate DAOs should NOT embed business logic
  Business logic stays in service; DAO focuses on SQL execution
  Service calls DAO, applies rules, logs audit trail
```

### Email & SMS Services
```
Pattern: Notification on key events (password change, OTP, claim status)
  config.getString("sendPasswordSMS") triggers SMS service
  config.getString("changePwdEmailFlag") triggers email service
  
MIGRATION: Spring Cloud Config for notification toggles
  Spring Cloud Stream or Spring Integration for async notification
  Preserve exact template names and parameter passing
```

### Aadhaar Verification Service (SOAP)
```
Pattern: External web service call for identity verification
  AadhaarVerificationService.verify(aadhaar_number)
  
MIGRATION: Keep SOAP client or convert to REST wrapper (if available)
  Preserve exact service endpoint URL and request/response format
  Handle timeouts and fallback logic
```

---

## Summary: What Must Be Preserved at Service Level

1. **Amount calculations**: every formula (package + enhancements, balance carry-forward, TDS, deduction).
2. **State machines**: case status transitions, phase progression, role-based allowed states.
3. **Temporal rules**: date comparisons, expiry windows (OTP 10 min, follow-up 90 days), discharge day calculations.
4. **File I/O**: exact path generation, directory creation, Base64 encoding (UTF-8), MIME type detection, fallback behavior.
5. **Validation logic**: password complexity, OTP length, attachment types, mandatory fields.
6. **Audit trail**: every state change logged with user, role, timestamp, remarks.
7. **Multi-role workflows**: allowed role transitions, checklist completion requirements, delegation logic.
8. **Configuration resolution**: property key names, precedence, fallback defaults, config service injection.
9. **External integrations**: Aadhaar verification, SMS/email notification, OpenAM/LDAP auth.
10. **Notification triggers**: SMS/email sending conditions, template name resolution.

