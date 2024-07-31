
# Biblioteca Divergente 📚

## Índice

- [Proyecto 📝](#proyecto-)
    - [Requisitos previos](#requisitos-previos-)
- [Diagramas](#diagrama-)
    - [Diagrama de flujo](#diagrama-de-flujo-)
    - [Diagrama de datos](#diagrama-de-datos-)
- [Instalación 🛠️](#installation-)
    - [Requerimientos](#requerimientos-)
- [Estructura del proyecto](#estructura-del-proyecto-)
- [Tecnologías](#tecnologias-)
- [Uso](#uso-)
- [Contribución 🤝](#contribution-)
- [Coders👩‍💻](#coders-)
- [Demo](#demo-)


## Proyecto 

Desarrollo de un gestor de libros para una biblioteca ficticia, biblioteca Divergente, donde se puede acceder a la base de datos de la biblioteca para visualizar todo el catálogo disponible, buscar por autor, título o género, modificar o eliminar un libro, o añadir un libro nuevo a la base de datos.

Se ha trabajado con metodologías ágiles, usando Trello para la organización y distribución de tareas a través de historias de usuario, que a su vez han sido referenciadas en los nombres de las ramas del proyecto. 


### Requisitos previos

**Funcionalidades**
- Poder ver una lista de todos los libros de la BBDD
- Poder añadir un libro a la BBDD 
- Poder editar un libro de la BBDD
- Poder eliminar un libro de la BBDD
- Poder buscar un libro por título
- Poder buscar libros por autor
- Poder buscar libros por género literario
- La base de datos debe estar normalizada
- Los libros deben tener título, autor o autores, una descripción de máximo 200 caracteres, un código ISBN y uno o varios géneros literarios; en este proyecto hemos añadido stock y lenguaje. 


## Diagramas

### Diagrama de flujo

[Ver diagrama de flujo]()

### Diagrama de datos

En el proceso de normalizar la base de datos a través de tres formas normales, llegamos a cinco tablas: la principal de libros, una tabla de géneros, una de autores, dos asociativas que conectan los libros con sus respectivos género(s) y autor(es) y una de lenguajes.

[Ver diagrama de datos](https://drive.google.com/file/d/1Npe71pMS-Zj1ldXu07YRYapa7U5RKJG9/view)



## Instalación 🛠️

### Requerimientos

- [Java](https://www.w3schools.com/java/java_intro.asp)
    + [Extensión Java Pack VSC](vscjava.vscode-java-pack) o [IntelliJ](https://www.jetbrains.com/es-es/idea/)
- [Junit](https://junit.org/junit5/)
- Base de datos


1. Clona el repositorio:
```bash
 git clone https://github.com/Mercedes-Celedon/biblioteca-las-normalizadas
```

2. Instala Junit

3. Crea tu base de datos y conectála al repo:
```
pon tu usuario y contraseña de pgAdmin o de tu BBDD en el archivo .env 

```
```
cambia el link de acceso a tu BBDD en config > DBManager
```


## Estructura del proyecto

El desarrollo de este proyecto ha tenido dos procesos de construcción destacables: en el primer sprint creamos la base de datos, así como todas las funcionalidades del gestor. 

En la segunda parte del desarrollo nos centramos en refactorizar el código para que el proyecto se adhiriese a los principios de MVC: model - view - controller. 

La estructura final del proyecto es la siguiente:

```plaintext
/
├── biblioteca
│   ├── src/
│   │    ├── main/normalizadas
│   │    │     ├── config
│   │    │     │      └── DBManager
│   │    │     ├── controller
│   │    │     │      ├── AuthorsController
│   │    │     │      ├──BooksController
│   │    │     │      └──GenresController
│   │    │     ├── model
│   │    │     │      ├── Author
│   │    │     │      ├── AuthorDAO
│   │    │     │      ├── AuthorDAOInterface
│   │    │     │      ├── Book
│   │    │     │      ├── BookDAO
│   │    │     │      ├── BookDAOInterface
│   │    │     │      ├── Genre
│   │    │     │      ├── GenreDAO
│   │    │     │      └── enreDAOInterface
│   │    │     ├── view
│   │    │     │      └── BookView
│   │    │     └── App.java
│   │    └── main/test
│   ├── target
│   └── pom.xml
├── .env
├── .gitignore
└── README.md

```

## Tecnologías

- [Java](https://www.java.com)
- [PgAdmin](https://www.pgadmin.org/) con [SQL](https://www.w3schools.com/sql/default.asp) para la creación de la base de datos



## Uso
El programa se inicia en la terminal una vez compilado.

Para compilar:

En terminal:
```
1. javac App.java
2. java App
```

O dar al play para compilar automáticamente.

El programa se iniciará imprimiendo el menú principal:

```
    Bienvenid@ a la Biblioteca Divergente.
    ¿Qué quieres hacer?
    1. Ver catálogo entero.
    2. Buscar un libro.
    3. Añadir un libro.
    4. Modificar un libro.
    5. Eliminar un libro.
    6. Salir.
```

Una vez se inicialice el menú, podemos seleccionar cada opción por su número de índice, momento en el que el programa conectará con la base de datos y podremos realizar la consulta pertinente.


## Contribución 🤝

1. Haz un fork al repositorio.
2. Crea una nueva rama: `git checkout -b feature/name`.
3. Haz tus cambios.
4. Haz push de tu rama: `git push origin feature/name`.
5. Crea un pull request.


 ## Coders👩‍💻
Las coders que hemos trabajado en este proyecto somos:

- [Rossemary Castellanos](https://github.com/castellanorn)
- [Mercedes Celedón](https://github.com/Mercedes-Celedon)
- [Laura Gil](https://github.com/LauraGDev)
- [Betty Lopez](https://github.com/BettyLopo)
- [Cris Pérez](https://github.com/CrisZDE)

## Demo

