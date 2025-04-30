# Alianza-Back
Alianza backend 


Manejo de Logs con SLF4J
Pruebas unitarias con JUnit 5 y Mockito
Servicios RESTFUL


# API de Alianza (Spring Boot + Docker + MySQL + FlyWay)

Para la prueba se utilio BD MySQL, Java y SprintBoot, Flyway para la creacion de las tablas
en la BD y las migraciones de la informacion , servicios rest y FlyWay para importar tablas y registros.

#Se necesita tener instalado estas herramientas antes de correrlo
Docker
Docker Compose
Java 17+
Maven
Git
Intellij
Lombok

##  Cómo ejecutar en local
Para poder correr en local y habiendo instalado estas herramientas se deben ejecutar estos comandos

# 1 Clonar el repositorio

git clone https://github.com/oscar930/Alianza-Back.git
cd Alianza-Back


# 2 Levantar Docker
Se debe levantar el docker dentro de la carpeta del proyecto desde una terminal de windows
ejecutando el siguiente comando  "docker-compose up -d"

# 3 Comprobar si docker levanto o levantarlo
Ejecutar comando "docker start mysql_prueba"

# 3 Ejecutar el siguiente comando para limpiar e instalar dependencias
mvn clean install o dentro de intellij solo "clean install" dentro del Run de Maven
y ejecutar el siguiente comando:
"spring-boot:run" dentro de comando de maven o "mvn spring-boot:run"

# 4 Correr proyecto para instalar y migraciones
Build and run o desde  el Run de configuraicon en Intellij