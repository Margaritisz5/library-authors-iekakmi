# Σύστημα Διαχείρισης Βιβλίων και Συγγραφέων

Πλήρης εργασία εξαμήνου με Spring Boot, MySQL, JPA/Hibernate, Swagger και React.

Η Δομη που ζητηθηκε:
```text
src/main/java/com/iekakmi/eshop_api/
├── apiLayer/
│   ├── configurations/
│   └── controllers/
├── dataAccessLayer/
│   ├── models/
│   └── services/
├── domainLayer/
│   ├── models/
│   └── repositories/
└── EshopApiApplication.java
```

## Περιεχόμενα

- `backend/`: Spring Boot REST API
- `frontend/`: React/Vite UI
- `database/schema.sql`: SQL δημιουργίας βάσης
- `docs/ERD.md`: ERD σε Mermaid
- `docker-compose.yml`: MySQL container

## Εκκίνηση βάσης

```bash
docker compose up -d
```

Αν δεν έχεις Docker, δημιούργησε MySQL βάση `library_db` με username `root` και password `root`, ή άλλαξε το `application.properties`.

## Εκκίνηση backend

Άνοιξε στο IntelliJ τον φάκελο:

```text
library-authors-iekakmi/backend
```

Μετά τρέξε την κλάση:

```text
com.iekakmi.eshop_api.EshopApiApplication
```

ή από terminal:

```bash
cd backend
mvn spring-boot:run
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

## Εκκίνηση frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend URL:

```text
http://localhost:5173
```

## REST Endpoints

### Books

- `GET /books`: λίστα βιβλίων
- `GET /books/{id}`: βιβλίο με id
- `POST /books`: προσθήκη βιβλίου
- `PUT /books/{id}`: ενημέρωση βιβλίου
- `DELETE /books/{id}`: διαγραφή βιβλίου
- `PUT /books/{id}/authors`: ανάθεση συγγραφέων σε βιβλίο

### Authors

- `GET /authors`: λίστα συγγραφέων
- `GET /authors/{id}`: συγγραφέας με id
- `POST /authors`: προσθήκη συγγραφέα
- `PUT /authors/{id}`: ενημέρωση συγγραφέα
- `DELETE /authors/{id}`: διαγραφή συγγραφέα
- `GET /authors/{id}/books`: βιβλία συγγραφέα

## Παράδειγμα JSON για συγγραφέα

```json
{
  "name": "George Orwell",
  "nationality": "British",
  "dateOfBirth": "1903-06-25"
}
```

## Παράδειγμα JSON για βιβλίο

```json
{
  "isbn": "9780451524935",
  "title": "1984",
  "category": "Dystopian Fiction",
  "publicationYear": 1949,
  "authorIds": [1]
}
```
201 Created

Το GET /books επέστρεψε δεδομένα
✅ Το frontend άνοιξε στον browser μέσω:npm run dev
