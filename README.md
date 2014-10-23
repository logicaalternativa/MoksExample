#MoksExample


Este proyecto está relacionado con la entrada en el blog:
**[﻿Mocks… o el TDD y yo. Segunda parte] (http://www.logicaalternativa.com/?p=739)**


Demo de utilización de 'mock' propios para realizar test y:

 - Simular la capa de persistencia a base de datos.
 - Para comprobar que se ha llamado al interfaz que representa
 - Para poder pasar las pruebas si interesarno su comportamiento.

## Breve descripción

La demo es una aplicación web que mediante un formulario html sencillo llamará con ajax a un servicio REST. Consiste en dar de alta códigos de promoción de una web.

Consiste en dar de alta códigos de promoción de una web. Los datos que se piden son la promoción, el nombre, el correo electrónico y el código de promoción. Se validará que el correo electrónico no esté ya utilizado y si todo es correcto se enviará el correo a la persona y se mostrarán los datos de los códigos ya dados de alta. 

Es una aplicación JAVA montada sobre Spring. Características: 
 - La capa de persistencia se utiliza Spring-Data. 
 - Se usa **Maven** como herramienta de compilación, ejecución de test y construcción del proyecto.
 - Como motor de Base de datos se usa Hsqldb.

## Prerrequisitos

Es necesario tener instalado:
- java
- Maven

## Ejecutar la aplicación

Descargar la aplicación y configurar los datos del servidor de correo electrónico en el fichero *application.properties* (directoriosrc/ main/resources/). Este paso no es necesario y la demo funciona correctamente sin enviar correos

En el directorio de trabajo, ejecutar

```
$ mvn tomcat:run
```

Con un navegador,abrir la siguiente URL 

```
http://localhost:8080/EjemploMock/
```

## Directorios de interés:

 - Controladores: [src/main/java/com/logicaalternativa/ejemplomock/controller/]
(https://github.com/logicaalternativa/MoksExample/tree/master/src/main/java/com/logicaalternativa/ejemplomock/controller)
 - Mocks Persitencia BBDD: [src/test/java/com/logicaalternativa/ejemplomock/repository/mock/](https://github.com/logicaalternativa/MoksExample/tree/master/src/test/java/com/logicaalternativa/ejemplomock/repository/mock)

- Moks de comprobación de llamada: [src/test/java/org/springframework/mail/javamail/mock/](https://github.com/logicaalternativa/MoksExample/tree/master/src/test/java/org/springframework/mail/javamail/mock)

- Mocks sólo para pasar pruebas: [src/test/java/org/springframework/context/mock/](https://github.com/logicaalternativa/MoksExample/tree/master/src/test/java/org/springframework/context/mock)


[M.E.](http://www.logicaalternativa.com/mi-cv/)

**[LogicaAlternativa.com] (http://www.logicaalternativa.com)**


