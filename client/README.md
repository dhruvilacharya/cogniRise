# CogniRise client

The client is a Next.js app. From this directory, install dependencies and start
the development server:

```sh
npm ci
cp .env.local.example .env.local
npm run dev
```

By default, the client calls the backend at `http://localhost:8080`. Set
`NEXT_PUBLIC_API_BASE_URL` in `.env.local` if the backend runs elsewhere.
See the repository [setup guide](../README.md) for backend, PostgreSQL, and
GitHub/Gemini configuration.
