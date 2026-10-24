# Platzi Play

Platzi Play is a Spring Boot REST API for managing a movie catalog and generating movie suggestions from user preferences with LangChain4j and OpenAI.

## Requirements

- Java 21
- Docker Compose
- An OpenAI API key to use movie suggestions

## Run locally

Start the PostgreSQL database:

```powershell
docker compose up -d postgres
```

The `dev` profile is active by default and configures PostgreSQL at `localhost:5432`, using the database and credentials from `compose.yaml`. Its JDBC URL currently contains a typo (`postgressql`); supply the corrected URL when starting the app:

```powershell
.\gradlew.bat bootRun --args="--spring.datasource.url=jdbc:postgresql://localhost:5432/platzi_play_db"
```

To use the AI suggestions endpoint, also provide a valid OpenAI API key:

```powershell
.\gradlew.bat bootRun --args="--spring.datasource.url=jdbc:postgresql://localhost:5432/platzi_play_db --langchain4j.open-ai.chat-model.api-key=YOUR_OPENAI_API_KEY"
```

On macOS or Linux, use `./gradlew` in place of `.[0mgradlew.bat`.
On macOS or Linux, use `./gradlew` instead of `./gradlew.bat`.

The API listens on port `8090` and uses the context path `/platzi-play/api`. OpenAPI documentation is available at [http://localhost:8090/platzi-play/api/swagger-ui/index.html](http://localhost:8090/platzi-play/api/swagger-ui/index.html).

## API

All endpoints are relative to `http://localhost:8090/platzi-play/api`.

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/movies` | List movies |
| `GET` | `/movies/{id}` | Get a movie by ID |
| `POST` | `/movies` | Create a movie |
| `PUT` | `/movies/{id}` | Update a movie's title, release date, and rating |
| `DELETE` | `/movies/{id}` | Delete a movie |
| `POST` | `/movies/suggest` | Generate suggestions from preferences |

### Movie fields

Movie responses and create requests use these fields:

```json
{
  "id": 1,
  "title": "Inception",
  "duration": 148,
  "genre": "SCI_FI",
  "releaseDate": "2010-07-16",
  "rating": 4.8
}
```

For creation, `id` can be omitted. Supported genres are `ACTION`, `COMEDY`, `DRAMA`, `ANIMATED`, `HORROR`, and `SCI_FI`.

Example update request:

```json
{
  "title": "Inception",
  "releaseDate": "2010-07-16",
  "rating": 4.8
}
```

Update validation requires a non-blank title, a release date that is not in the future, and a rating between 0 and 5.

Example suggestion request:

```json
{
  "userPreferences": "Thought-provoking science fiction movies"
}
```

## Tests

Run the test suite with:

```powershell
.\gradlew.bat test
```

## Configuration notes

- PostgreSQL connection settings are in `src/main/resources/application-dev.properties`; the local profile currently has the JDBC URL typo described above.
- The checked-in production profile contains environment-specific database settings. Replace these with externally managed configuration before using the production profile; do not use those credentials for a deployment.
- The API key must be supplied securely for AI suggestions. Do not commit real keys to the repository.
