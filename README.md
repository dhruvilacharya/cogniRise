# CogniRise

CogniRise indexes GitHub repositories and lets you ask questions about their code.
The project has a Next.js client, a Spring Boot API, PostgreSQL with pgvector, and
Google Gemini for chat and embeddings.

## Requirements

- Java 21 or newer
- Node.js 22 or newer and npm
- Docker with Docker Compose
- A GitHub OAuth app configured with callback URL
  `http://localhost:8080/login/oauth2/code/github`
- A Gemini Developer API key from [Google AI Studio](https://aistudio.google.com/app/apikey)

Gemini's Developer API has a free tier with usage limits; it is not unlimited.
Check the current [Gemini API pricing and limits](https://aistudio.google.com/docs/pricing)
before using it. The defaults are `gemini-2.5-flash` for chat and
`gemini-embedding-001` for embeddings.

## Local setup

1. Copy `.env.example` to `.env` and
   `backend/src/main/resources/application.properties.example` to
   `backend/src/main/resources/application.properties`. Set the Gemini key and
   GitHub OAuth credentials. The token-encryption password must be a long random
   secret (for example, generate one with `openssl rand -base64 32`). Generate
   the salt with `openssl rand -hex 8`. Never commit `.env` or
   `application.properties`.
2. Start PostgreSQL with pgvector:

   ```sh
   docker compose up -d postgres
   ```

3. In a terminal, start the backend from its directory. Spring Boot reads the
   optional root `.env` file when launched from `backend/`:

   ```sh
   cd backend
   ./mvnw spring-boot:run
   ```

4. In another terminal, start the frontend:

   ```sh
   cd client
   cp .env.local.example .env.local
   npm ci
   npm run dev
   ```

5. Open [http://localhost:3000](http://localhost:3000) and sign in with GitHub.

The backend defaults to port `8080`, the frontend to `3000`, and PostgreSQL is
published on host port `5433`. You can override these settings in `.env`.
Set `GEMINI_CHAT_MODEL` or `GEMINI_EMBEDDING_MODEL` to use other compatible models.

The Gemini embedding table is `vector_store_gemini` with 768 dimensions. The
previous `vector_store` table is left untouched. Reindex repositories after
switching models so their code chunks receive compatible embeddings.

## Checks

From the repository root:

```sh
(cd backend && ./mvnw test)
(cd client && npm run lint && npm run typecheck && npm run build)
```

Backend tests use an in-memory H2 database and do not require a running PostgreSQL
container or live Gemini/GitHub credentials.
