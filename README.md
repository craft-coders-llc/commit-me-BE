# commit-me - Back End
### Una aplicación de gestión de eventos
![image](https://github.com/user-attachments/assets/91bbf268-c037-41fa-8d8e-972e0bb4888c)

## 📖 Descripción
**Commit-Me** es una aplicación inclusiva para promocionar eventos y gestionar la reserva de entradas de los usuarios, pensada para el sector tecnológico.
Este repositorio contiene el **backend** del proyecto, desarrollado con **Spring Boot** y **PostgreSQL**.  

Esta API sigue una arquitectura MVC de 3 capas, tiene un estilo cliente-servidor (tipo API Rest) y se conecta a una base de datos PostgreSQL.

## 🛠️ Tecnologías utilizadas  
- **Java 21**  
- **Spring Boot 3.4.5** (Web, Data JPA)  
- **Maven** (Gestión de dependencias)
- **Configuración de la DB** dotenv-java 3.0

## 📌 Herramientas utilizadas
- **VSCode**
- **PostgreSQL 42.7.5** (Base de datos)
- **Postman 11.41** (para testear los endpoints)
- **Gestión del equipo:** Trello
- **Control de Versiones:** Git - GitHub

## 📡 Endpoints de la API: métodos implementados
### GET
### POST
### PATCH
### DELETE

## 📊 Conexión con la base de datos

### Estructura de la base de datos
El proyecto utiliza JPA/Hibernate para la creación automática de las tablas en la base de datos. Las principales entidades son:

- **Usuario**: Almacena información de los usuarios que crean eventos y que se apuntan a ellos
- **Categorías**: Contiene las categorías de los eventos - presencial u online
- **Eventos**: Almacena la información de los eventos: dónde será, la fecha y la hora, etc.

### Diagrama de la base de datos
![image](https://github.com/user-attachments/assets/fbc4ff2a-262f-4ea9-af02-66f035a5469c)


## ✅ Funcionalidades
La API permite:
- **Crear un evento:** Permite la creación de un nuevo artículo especificando su título, contenido, fecha de publicación, categoría y el usuario que lo crea.
- **Listar todos los eventos:** Muestra una lista de todos los artículos disponibles en el periódico.
- **Obtener un evento por ID:** Recupera un artículo específico utilizando su identificador único.
- **Actualizar un evento:** Modifica el contenido de un artículo existente.
- **Eliminar un evento:** Elimina un artículo del sistema.
- **Crear un usuario:** Permite la creación de un nuevo usuario con nombre de usuario y correo electrónico (sin necesidad de autenticación o autorización en esta fase).
- **Actualizar un usuario**
- **Eliminar un usuario**

### Login
En la primera etapa del desarrollo, hemos implementado un login sencillo y sin encriptación, que se pretende implementar con el BCRYPT más adelante.
Además, para próximos sprints, deseamos implementar el Json Web-Token (JWT) para proteger eficazmente los datos sensibles de los usuarios.

### ✅ Validaciones
Los manejos de excepciones y validaciones se están haciendo de las siguientes maneras:
- **en las entidades**: a través de las etiquetas de Spring Boot como @Size, @NotNull, @NotBlank, entre otros.
- **en el exception handler**: hay un archivo de control de excepciones llamado GlobalExceptionHandler que hace el manejo de excepciones con control de datos presentes en la DB.

### ⚠️ Importante!
>**Este frontend se conecta con un frontend en ReactJS que debe estar activo para la funcionalidad completa.**

## 🔗 Información adicional
🎨 Código fuente: [Commit-Me Frontend](https://github.com/craft-coders-llc/commit-me-FE) <br>
📂 Código fuente Backend: [Commit-Me Backend](https://github.com/craft-coders-llc/commit-me-BE)

### 👥 **Equipo de Desarrollo**  
#### 📂 Backend (BE)  
- **Priscila Guillen** - https://github.com/pgoliv-code
- **Maria Bongoll** - https://github.com/Femcom-Mari

#### 🎨 Frontend (FE)  
- **Marta Ibarra** - https://github.com/Marpro24
- **Karisha Melendez** - https://github.com/karisssha
- **Carolina Mas** - https://github.com/Carocitta
