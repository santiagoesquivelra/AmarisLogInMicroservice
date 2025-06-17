# AmarisLogInMicroservice

Microservicio desarrollado en **Java Spring Boot** con conexión a **PostgreSQL**. Permite autenticación de usuarios contra la API externa DummyJSON, registro de logins y consulta de logs, siguiendo buenas prácticas y principios SOLID.

---

## 🚀 Requisitos

- Java 21+
- Maven 3.3+
- PostgreSQL 12+

---

## ⚙️ Configuración

1. **Clona el repositorio y entra al proyecto:**
   ```sh
   git clone <repo-url>
   cd AmarisLogInMicroservice/authapi
   ```
2. **Configura la base de datos PostgreSQL:**
   ```sql
   CREATE DATABASE amaris_auth;
   CREATE USER amaris_user WITH ENCRYPTED PASSWORD 'amaris_pass';
   GRANT ALL ON SCHEMA public TO amaris_user;
   GRANT ALL PRIVILEGES ON DATABASE amaris_auth TO amaris_user;
   ```
3. **Edita `src/main/resources/application.properties`:**
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/amaris_auth
   spring.datasource.username=amaris_user
   spring.datasource.password=amaris_pass
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   dummyjson.api.url=https://dummyjson.com
   ```

---

## 🏁 Ejecución

```sh
./mvnw spring-boot:run
```
La API estará disponible en `http://localhost:8080`.

---

## 📚 Endpoints principales

- `POST   /api/auth/login`         → Login de usuario (DummyJSON) y registro en base de datos
- `POST   /api/auth/bearer-token` → Devuelve el Bearer token listo para usar
- `GET    /api/auth/me`           → Consulta usuario autenticado (requiere header Authorization)
- `GET    /api/auth/users`        → Lista usuarios de prueba
- `GET    /api/auth/logs`         → Visualiza todos los logs de autenticación

---

## 🧪 Pruebas con Postman

1. Importa la colección `PostmanCollectionLogInLogs.json` incluida en el correo electrónico.
2. Realiza login y ejecuta el /api/auth/me la colección ya tiene automatizado el uso del token de acceso de consumo.

---

## 📝 Notas

- El microservicio usa Feign Client para consumir DummyJSON.
- Los logs de login se almacenan en la tabla `login_log`.
- Puedes consultar los logs desde la API o directamente en la base de datos.

---

## 👨‍💻 Autor

Santiago Esquivel
