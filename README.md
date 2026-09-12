# Práctica 03: Construcción automática con Maven

**Autor:** Gino Sebastian Diaz Neyra  
**Curso:** Ingeniería de Software II  
**Docente:** DSc. Edgar Sarmiento Calisaya

Proyecto Java de gestión de tareas en memoria: agrega tareas, marca tareas completadas y exporta JSON con Gson. Incluye 8 pruebas unitarias con JUnit 5.

## Requisitos y configuración

- JDK 17 o superior con `java` y `javac` disponibles. Validado en Windows 11 con JDK 21.0.12.1.
- Git e Internet para descargar Maven y dependencias la primera vez.
- El Maven Wrapper incluido descarga Maven 3.9.16. No requiere una instalación global de Maven.

En PowerShell:

```powershell
git clone https://github.com/GinoSebastianD/IS2-practica3-.git
cd IS2-practica3-
java -version
javac -version
.\mvnw.cmd --version
.\mvnw.cmd -B -ntp clean install
java -jar target\gestor-tareas-1.0.0.jar
```

En Linux/macOS: usar `sh mvnw` en lugar de `.\mvnw.cmd` y `/` en las rutas.

## Modelo de construcción

`pom.xml` declara nombre, coordenadas `pe.edu.practica03:gestor-tareas:1.0.0`, tipo `jar`, codificación UTF-8 y compilación mediante `javac --release 17`. Maven Compiler 3.13.0 compila; Surefire 3.5.2 ejecuta las pruebas; Shade 3.6.0 incluye las dependencias y la clase principal en el JAR; Install 3.1.3 instala el artefacto en el repositorio local.

Gson 2.11.0 es una dependencia de ejecución (ámbito predeterminado `compile`). JUnit Jupiter 5.11.0 usa ámbito `test` y no se incorpora al ejecutable.

```powershell
.\mvnw.cmd dependency:tree
Get-Content target\surefire-reports\pe.edu.practica03.GestorTareasTest.txt
```

`clean install` limpia `target`, compila producción y pruebas, ejecuta las pruebas, empaqueta e instala localmente. `install` no publica en GitHub ni despliega una aplicación en Internet.

## Resultados y entregables

- `target/gestor-tareas-1.0.0.jar`: ejecutable generado al construir.
- `target/surefire-reports/`: resultados de las pruebas.
- `pom.xml`: modelo de construcción solicitado.
- `docs/`: informe, tutorial y evidencias de la ejecución.
- Ramas `main` y `codex/construccion-automatica`: historial de desarrollo y construcción.

El programa de demostración imprime tres tareas: dos completadas y una pendiente. Los datos se mantienen solo en memoria.

## Pruebas automatizadas

Se comprueba el estado inicial, creación y normalización de títulos, rechazo de títulos vacíos/nulos, finalización de tareas, rechazo de identificadores inexistentes, protección de la lista interna y exportación JSON con comillas y caracteres acentuados.

## Referencias

- [Guía oficial Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
- [Instalación Maven](https://maven.apache.org/install.html)
- [Maven Wrapper](https://maven.apache.org/wrapper/)
- [Maven Shade Plugin](https://maven.apache.org/plugins/maven-shade-plugin/)

El enunciado Pr_3_Automatic_Build.pdf permite escoger el proyecto y el lenguaje; este repositorio desarrolla un proyecto Java propio para practicar la construcción automática.
