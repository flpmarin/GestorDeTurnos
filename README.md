
# Proyecto Turnos

## Descripción

>>***Aplicación en Java utilizando [Swing](https://docs.oracle.com/javase/tutorial/uiswing/) para organizar los turnos y posiciones de los trabajadores.*** La aplicación permitirá gestionar turnos, administrar ausencias, establecer restricciones generales como la limitación de horas de trabajo, asignar automáticamente los turnos y generar alertas cuando no se puedan ocupar puestos.

>### Funcionalidades Principales
>- **Gestión de Turnos**: Permitir al administrador gestionar los turnos de los trabajadores de cada departamento.
>- **Administración de Ausencias**: Considerar las ausencias por vacaciones o enfermedades al asignar los turnos.
>- **Restricciones Generales**: Establecer restricciones como un máximo de x horas semanales por trabajador.
>- **Asignación Automática**: Asignar turnos automáticamente respetando las ausencias y restricciones.
>- **Generación de Alertas**: Notificar problemas como la falta de trabajadores para cubrir los puestos necesarios.


## Configuración inicial y desarrollo
### Clonar el Repositorio

Para obtener una copia local del repositorio:

```bash
git clone https://github.com/flpmarin/turnos.git
```

### Comandos Maven para Proyectos Java
Para compilar y ejecutar el proyecto:
```bash
mvn clean install  # Compila el proyecto y descarga las dependencias
mvn exec:java -Dexec.mainClass="com.turnos.TurnosMain.java"  # Ejecuta la aplicación principal
```

# Flujo de trabajo 

| Paso | Descripción                                                        | Comandos                                                                                                                                                                   |
|------|--------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1    | **Actualizar `main`**<br>Antes de comenzar, asegúrate de que tu rama `main` esté actualizada. | `git checkout main`<br>`git pull`                                                                                                                                          |
| 2    | **Crear una nueva rama**<br>Deriva una rama para cada nueva tarea o mejora. | `git checkout main`<br>`git pull`<br>`git checkout -b feature/<nombre-rama>`                                                                                               |
| 3    | **Trabajar en tu rama**<br>Realiza cambios y guarda tu progreso regularmente. | `git add .`<br>`git commit -m "Descripción de los cambios"`                                                                                                                |
| 4    | **Mantener la rama actualizada**<br>Mantén tu rama sincronizada con `main` para evitar conflictos. | `git checkout main`<br>`git pull`<br>`git checkout feature/<nombre-rama>`<br>`git merge main`<br>o<br>`git rebase main`                                                     |
| 5    | **Resolver conflictos**<br>Si ocurren conflictos después de un merge o rebase, resuélvelos antes de continuar. | `git add <archivos_resueltos>`<br>`git rebase --continue`<br>o<br>`git commit -m "Resolver conflictos"`                                                                     |
| 6    | **Empujar cambios a tu rama remota**<br>Una vez que tu rama está lista y actualizada, empuja los cambios al remoto. | `git push origin feature/<nombre-rama>`                                                                                                                                   |
| 7    | **Crear un Pull Request**<br>Abre un pull request en GitHub hacia `main` y solicita revisiones de tus compañeros. | No aplica, se hace a través de la [interfaz web de GitHub](https://github.com/flpmarin/turnos/pulls)                                                                       |
| 8    | **Revisión y fusión del Pull Request**<br>Una vez revisado y aprobado, el pull request puede ser fusionado. | No aplica, se hace a través de la [interfaz web de GitHub](https://github.com/flpmarin/turnos/pulls)                                                                       |
| 9    | **Limpiar post-fusión**<br>Después de la fusión, elimina las ramas de funcionalidad obsoletas tanto localmente como en el remoto. | `git branch -d feature/<nombre-rama>`<br>`git push origin --delete feature/<nombre-rama>`                                                                                  |
| 10   | **Mantener tu `main` actualizado**<br>Finalmente, asegúrate de que tu rama `main` local esté siempre actualizada. | `git checkout main`<br>`git pull`                                                                                                                                          |


# Buenas Prácticas para Mensajes de Commit en Git

- **Claridad**: Describe claramente qué cambios realiza el commit y por qué.
- **Evitar Caracteres Especiales**
- **Uso del Imperativo**: Redacta mensajes en infinitivo.
- **Referencia de Problemas**: Incluye referencias a tickets o IDs de problemas cuando sea relevante.

### Ejemplo de Mensaje de Commit
Si se necesita dar más detalles del mensaje:

>Añadir validación de email al formulario de contacto

>Este commit introduce una verificación para asegurar que el campo de email contenga una dirección válida. Se implementó usando expresiones regulares y se añadieron pruebas unitarias correspondientes.

La línea en blanco después del título es necesaria para separar el título del cuerpo del commit:
```bash
git commit -m "Añadir validación de email al formulario de contacto

Este commit introduce una verificación para asegurar que el campo de email contenga una dirección válida. Se implementó usando expresiones regulares y se añadieron pruebas unitarias correspondientes."

```



## Recursos Adicionales

- **Maven con Java**
  - [Maven Official Documentation](https://maven.apache.org/guides/index.html) - Guías oficiales y documentación de Apache Maven.
- **Interfaz Swing**
  - [Swing (Java Tutorials)](https://docs.oracle.com/javase/tutorial/uiswing/) - Tutorial oficial de Oracle sobre la creación de interfaces gráficas con Swing.
- **Conexión a Base de Datos con JDBC**
  - [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html) - Tutorial oficial de Oracle para aprender a utilizar JDBC.
- **Documentación de Git**
  - [Pro Git Book](https://git-scm.com/book/en/v2) - Libro completo y detallado sobre Git, disponible de forma gratuita.
  - [Atlassian Git Tutorials](https://www.atlassian.com/git/tutorials) - Tutoriales de Git por Atlassian, creadores de Bitbucket.
  


## Descargas y Configuración de Herramientas
- **Java Development Kit (JDK) 21**
  - [Download JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) - Descarga el JDK 21 desde el sitio oficial de Oracle.
- **MySQL**
  - [Download MySQL](https://dev.mysql.com/downloads/mysql/) - Descarga MySQL para gestionar la base de datos del proyecto.
  - [MySQL JDBC Connector](https://dev.mysql.com/downloads/connector/j/) - Descarga del conector JDBC de MySQL, necesario para conectar aplicaciones Java con bases de datos MySQL.
- **Visual Studio Code (VSCode)**
  - [Download VSCode](https://code.visualstudio.com/Download) - Descarga Visual Studio Code, un editor de código ligero y potente.
- **Extensiones para VSCode**
  - [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) - Paquete de extensiones para desarrollo en Java, incluye soporte para Java, Maven, y más.
  - [Maven for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-maven) - Extensión para trabajar con proyectos Maven directamente desde VSCode.





