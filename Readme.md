# Lost and Found API 

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- MySQL

## Run the Project
1. Open `src/main/resources/application.properties` and set your MySQL username/password.
2. Start MySQL server.
3. Run from project root


Base URL: `http://localhost:8080`

---

## Enums Used
- `Role`: `USER`, `ADMIN`
- `ItemStatus`: `FOUND`, `CLAIMED`, `RETURNED`
- `ClaimStatus`: `PENDING`, `APPROVED`, `REJECTED`

---

## 1) Auth APIs

### Register User
**POST** `/api/auth/register`

Request body:
```json
{
  "name": "test",
  "email": "test@mail.com",
  "password": "1234",
  "role": "USER"
}
```

Success response (`201 Created`):
```json
{
  "id": 1,
  "name": "test",
  "email": "test@mail.com",
  "role": "USER"
}
```

### Login User
**POST** `/api/auth/login`

Request body:
```json
{
  "email": "test@mail.com",
  "password": "1234"
}
```

Success response (`200 OK`):
```json
{
  "id": 1,
  "name": "test",
  "email": "test@mail.com",
  "role": "USER"
}
```

---

## 2) Item APIs

### Create Item
**POST** `/api/items`

Request body:
```json
{
  "name": "Black Wallet",
  "description": "Found near library gate",
  "location": "Central Library",
  "uploadedByUserId": 1
}
```

Success response (`201 Created`):
```json
{
  "id": 1,
  "name": "Black Wallet",
  "description": "Found near library gate",
  "location": "Central Library",
  "foundDate": "2026-03-16T12:10:20.100",
  "status": "FOUND",
  "uploadedByUserId": 1,
  "uploadedByName": "test"
}
```

### Get All Items
**GET** `/api/items`

Success response (`200 OK`):
```json
[
  {
    "id": 1,
    "name": "Black Wallet",
    "description": "Found near library gate",
    "location": "Central Library",
    "foundDate": "2026-03-16T12:10:20.100",
    "status": "FOUND",
    "uploadedByUserId": 1,
    "uploadedByName": "test"
  }
]
```

### Get Item By ID
**GET** `/api/items/{itemId}`

Example: `/api/items/1`

### Get Items By Status
**GET** `/api/items/status/{status}`

Example: `/api/items/status/FOUND`

### Update Item Status
**PUT** `/api/items/{itemId}/status`

Request body:
```json
{
  "status": "RETURNED"
}
```

Success response (`200 OK`): ItemResponse JSON

---

## 3) Claim APIs

### Create Claim
**POST** `/api/claims`

Request body:
```json
{
  "itemId": 1,
  "userId": 2
}
```

Success response (`201 Created`):
```json
{
  "id": 1,
  "itemId": 1,
  "itemName": "Black Wallet",
  "userId": 2,
  "userName": "Rahul",
  "proofDocument": null,
  "status": "PENDING"
}
```

### Get All Claims
**GET** `/api/claims`

### Get Claims By Status
**GET** `/api/claims/status/{status}`

Example: `/api/claims/status/PENDING`

### Get Claims By Item
**GET** `/api/claims/item/{itemId}`

Example: `/api/claims/item/1`

### Update Claim Status (Admin flow demo)
**PUT** `/api/claims/{claimId}/status`

Request body:
```json
{
  "status": "APPROVED"
}
```

Success response (`200 OK`): ClaimResponse JSON

---

## Common Error Response Format
```json
{
  "timestamp": "2026-03-16T12:15:01.300",
  "status": 400,
  "error": "Bad Request",
  "message": "Email is required"
}
```
