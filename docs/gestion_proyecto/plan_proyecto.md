# PROYECTO GESTIÓN DE TURNOS

## 2024

**PROGRAMACIÓN JAVA**  
**EQUIPO:** Felipe Marín, Juan José Romero, Sergio Dominguez y Antonela Díaz  

**DIGITECH | MA-DAM 1º AÑO**

---

## Índice
1. [Resumen Ejecutivo](#resumen-ejecutivo)
   - [Funcionalidades Principales](#funcionalidades-principales)
2. [Descripción del Proyecto](#descripción-del-proyecto)
   - [Antecedentes](#antecedentes)
   - [Objetivos](#objetivos)
   - [Alcance](#alcance)
   - [Entregables](#entregables)
3. [Estructura de Desglose del Trabajo (EDT)](#estructura-de-desglose-del-trabajo-edt)
   - [Desglose Jerárquico](#desglose-jerárquico)
   - [Desglose del trabajo en tareas y sub-tareas](#desglose-del-trabajo-en-tareas-y-sub-tareas)
4. [Cronograma del Proyecto](#cronograma-del-proyecto)
   - [Diagrama de Gantt](#diagrama-de-gantt)
5. [Organización del Proyecto](#organización-del-proyecto)
   - [Equipo](#equipo)
   - [Recursos Materiales](#recursos-materiales)
   - [Recursos Tecnológicos](#recursos-tecnológicos)
6. [Plan de Implementación y Cierre](#plan-de-implementación-y-cierre)
   - [Estrategia de Implementación](#estrategia-de-implementación)
   - [Cierre del proyecto](#cierre-del-proyecto)
7. [Apéndices](#apéndices)
   - [Documentación Adicional](#documentación-adicional)
     - [Cartas de Aprobación](#cartas-de-aprobación)
     - [Diagramas Adicionales](#diagramas-adicionales)
     - [Captura de la Pantalla de Gestión de Turnos](#captura-de-la-pantalla-de-gestión-de-turnos)
     - [Informes Finales](#informes-finales)

---

## Resumen Ejecutivo

**Descripción breve del proyecto:**  
El proyecto consiste en el desarrollo de una aplicación de gestión de turnos para empresas. Esta aplicación permite a las empresas organizar y administrar los horarios de sus trabajadores de manera eficiente, optimizando el uso de recursos humanos y mejorando la productividad general. La aplicación permitirá gestionar turnos, administrar ausencias, establecer restricciones generales como la limitación de horas de trabajo, asignar automáticamente los turnos y generar alertas cuando no se puedan ocupar puestos.

### Funcionalidades Principales

- **Gestión de Turnos:** Permitir al administrador gestionar los turnos de los trabajadores de cada departamento.
- **Administración de Ausencias:** Considerar las ausencias por vacaciones o enfermedades al asignar los turnos.
- **Restricciones Generales:** Establecer restricciones como un máximo de x horas semanales por trabajador.
- **Asignación Automática:** Asignar turnos automáticamente respetando las ausencias y restricciones.
- **Generación de Alertas:** Notificar problemas como la falta de trabajadores para cubrir los puestos necesarios.

**Objetivos principales:**

- **Mejora en la comunicación interna:** Facilitar la comunicación entre trabajadores y gestores en relación con cambios de turno y ausencias.
- **Reducción de conflictos de programación:** Minimizar errores y conflictos en la asignación de turnos.
- **Logro:** Se implementó un sistema de validación que verifica la disponibilidad y evita la superposición de turnos.
- **Gestión eficiente de ausencias:** Crear un registro centralizado de ausencias y preferencias de turnos.
- **Logro:** Se desarrollaron módulos específicos para registrar y gestionar ausencias y preferencias, mejorando la visibilidad y el control.

**Beneficios obtenidos:**

- **Aumento de la eficiencia operativa:** La automatización de la programación de turnos ha permitido una asignación más rápida y precisa de los horarios de trabajo.
- **Mejora en la satisfacción de los empleados:** Al considerar las preferencias individuales de los trabajadores, se ha incrementado la satisfacción y motivación del personal.
- **Reducción de costos operativos:** La optimización de los turnos y la mejor gestión de ausencias han contribuido a una reducción significativa de los costos laborales.

**Impacto del proyecto:**

- **Dimensión económica:** Reducción de horas extras no planificadas y mejor uso del tiempo de los empleados, lo que se traduce en un ahorro de costos para la empresa.
- **Dimensión social:** Mejora en la comunicación interna y la transparencia en la gestión de turnos.
- **Futuro emprendimiento:** Posibilidad de comercializar la aplicación a empresas y sectores, ampliando el mercado objetivo y generando nuevas oportunidades de negocio. Creación de módulos adicionales para integrar otras funciones como la evaluación de desempeño y la gestión de nóminas.

**Resumen del cronograma y presupuesto:**

**Cronograma:**

- Inicio del proyecto: 23 de abril de 2024
- Fase de diseño y planificación: semana 2, 3 y 4
- Desarrollo y pruebas: semana 3, 4 y 5
- Implementación piloto: semana 6
- Lanzamiento oficial: 4 de junio de 2024

**Presupuesto:**

- Presupuesto inicial estimado: 100,000 Euros
- Costos reales al finalizar el proyecto: 90,000 Euros (incluyendo desarrollo, pruebas y capacitación)
- Ahorro respecto al presupuesto inicial: 10,000 Euros

---

## Descripción del Proyecto

### Antecedentes

**Contexto y justificación del proyecto:**  
En un entorno empresarial cada vez más dinámico y competitivo, la gestión eficiente de los recursos humanos es crucial. Muchas empresas enfrentan desafíos significativos en la planificación de turnos de trabajo, lo que puede llevar a conflictos, insatisfacción de los empleados y pérdida de productividad. La necesidad de una solución automatizada y efectiva para gestionar los turnos de trabajo se vuelve esencial para optimizar el rendimiento organizacional. La aplicación de gestión de turnos se justifica como una herramienta para mejorar la asignación de turnos, reducir conflictos, y aumentar la satisfacción y retención de empleados.

### Objetivos

**Objetivos generales:**

- Automatizar la asignación de turnos para mejorar la eficiencia operativa.
- Aumentar la satisfacción de los empleados mediante la consideración de sus preferencias de turnos.
- Reducir los conflictos de programación y mejorar la gestión de ausencias.

**Objetivos específicos:**

- Desarrollar algoritmos que permitan la generación automática de horarios de trabajo equilibrados.
- Implementar funcionalidades de comunicación interna para facilitar cambios y notificaciones de turnos.
- Crear un sistema de registro y gestión de ausencias que permita un seguimiento preciso y eficiente.
- Diseñar interfaces de usuario intuitivas y fáciles de usar para administradores y empleados.

### Alcance

**Definición del alcance del proyecto:**  
El proyecto abarca el desarrollo de una aplicación de gestión de turnos que incluye la planificación, programación y gestión de ausencias de los empleados. El alcance se limita a empresas de tamaño mediano que requieren una solución eficiente para la asignación de turnos. Las limitaciones incluyen la integración con sistemas existentes de recursos humanos, que no se cubrió en esta fase del proyecto. Excluye también la gestión de otros aspectos de recursos humanos como la evaluación de desempeño o la gestión de nóminas.

### Entregables

**Productos, servicios o resultados entregados:**

- **Diagramas de entidad-relación (ER):** Se entregaron diagramas detallados que muestran la estructura de la base de datos, incluyendo entidades como Departamento, Posición, Trabajador, Turno, Asignación, Preferencia y Ausencia.
- **Capturas de interfaces:** Se desarrollaron y se entregaron capturas de pantalla que muestran las interfaces de usuario para la administración de turnos, gestión de preferencias y registro de ausencias.
- **Aplicación final:** Se entregó la aplicación completa en fase inicial de gestión de turnos, incluyendo funcionalidades de generación manual de turnos, y gestión de ausencias.
- **Documentación técnica:** Documentación completa del proyecto, incluyendo manuales de usuario, para facilitar la implementación y el uso de la aplicación en las empresas.

**Ficha Técnica de la Aplicación de Gestión de Turnos:**

- **Nombre de la Aplicación:** Gestión de Turnos Empresariales
- **Descripción General:** Una aplicación diseñada para la gestión automatizada y eficiente de turnos laborales en empresas de tamaño mediano, optimizando la asignación de horarios, gestionando ausencias y mejorando la comunicación interna.

### Funcionalidades Clave:

**A. Asignación Manual de Turnos:**

- Asignación equitativa y balanceada de turnos.

**B. Gestión de Ausencias:**

- Registro y seguimiento de ausencias con motivos y fechas específicas.
- Notificaciones automáticas a administradores y empleados sobre ausencias registradas.

**C. Preferencias de Turnos:**

- Sistema para que los empleados registren sus preferencias de turnos.
- Consideración de preferencias en la asignación automática de turnos.

**D. Interfaces de Usuario:**

- Interfaces intuitivas y amigables para administradores y empleados.
- Paneles de control con visualización de turnos, ausencias y preferencias.

### Componentes Técnicos:

**A. Base de Datos:**

- Sistema de gestión de bases de datos relacional (MySQL).
- Entidades principales: Departamento, Posición, Trabajador, Turno, Asignación, Preferencia, Ausencia.
- Relaciones bien definidas entre entidades para asegurar la integridad y consistencia de los datos.

**B. Backend:**

- Proyecto Maven en Java
- Gestión de lógica de negocios y procesamiento de datos.

**C. Frontend:**

- Interfaz de usuario desarrollada con tecnología Swing.
- Formularios y paneles interactivos para la gestión de turnos y ausencias.

### Requisitos del Sistema:

**A. Servidor:**

- Sistema operativo: Linux/Windows Server.
- Procesador: Intel Xeon o equivalente.
- Memoria: 8 GB RAM mínimo.
- Almacenamiento: 100 GB SSD mínimo.

**B. Cliente:**

- Aplicación de escritorio

---

## Estructura de Desglose del Trabajo (EDT)

### Desglose Jerárquico

- Análisis y diseño del sistema.
- Desarrollo de la interfaz gráfica.
- Implementación de la lógica de negocio.
- Integración y pruebas.
- Documentación.

### Desglose del trabajo en tareas y sub-tareas:

1. **Planificación del Proyecto**
   - **1.1 Análisis de Requerimientos**
     - Definición de requisitos
   - **1.2 Diseño del Proyecto**
     - Creación de diagramas
     - Diseño de interfaces
     - Diseño de base de datos

2. **Desarrollo del Proyecto**
   - **2.1 Desarrollo del Backend**
     - Configuración del servidor
     - Desarrollo de módulos
   - **2.2 Desarrollo del Frontend**
     - Creación de componentes UI Swing
     - Implementación de formularios
     - Integración backend-frontend

3. **Desarrollo de Funcionalidades Clave**
   - Asignación de turnos
   - Gestión de ausencias
   - Preferencias de turnos
   - Comunicación y notificaciones

4. **Pruebas y Validación**
   - **3.1 Pruebas de Usuario Final**
     - Pruebas con usuarios
     - Recopilación de feedback

5. **Documentación Técnica**
   - Manual de usuario

---

## Cronograma del Proyecto

### Diagrama de Gantt

A continuación, se presenta el diagrama de Gantt actualizado con las fechas reales de inicio y finalización de cada tarea, considerando un plazo total de 6 semanas.

| TAREA                    | RESPONSABLE | SEMANA 1 | SEMANA 2 | SEMANA 3 | SEMANA 4 | SEMANA 5 | SEMANA 6 |
|--------------------------|-------------|----------|----------|----------|----------|----------|----------|
| Análisis de Requerimientos | Felipe      | X        | X        |          |          |          |          |
| Diseño del Proyecto      | Juanjo       |          | X        | X        |          |          |          |
| Desarrollo del Backend   | Antonela     |          |          | X        | X        |          |          |
| Desarrollo del Frontend  | Sergio       |          |          | X        | X        |          |          |
| Desarrollo de Funcionalidades | Todo el equipo |          |          |          | X        | X        | X        |
| Pruebas de Usuario Final | Todo el equipo |          |          |          |          | X        | X        |
| Documentación Técnica    | Juanjo       |          |          |          |          | X        | X        |

---

## Organización del Proyecto

### Equipo

**Equipo del Proyecto (Recursos Humanos):**

- **Felipe (Desarrollador):** Coordinación general del proyecto. Supervisión de todas las fases del proyecto.
- **Juanjo (Diseñador y Desarrollador Frontend):** Diseño de la aplicación. Creación de diagramas ER y de flujo. Diseño y desarrollo de la interfaz de usuario. Documentación técnica.
- **Antonela (Desarrolladora Backend):** Configuración del servidor y base de datos. Realización de pruebas de integración.
- **Sergio (Desarrollador Frontend):** Implementación de formularios y paneles de control. Integración del frontend con el backend. Colaboración en el desarrollo de funcionalidades clave.

### Recursos Materiales

- Equipo informático: Ordenadores personales de alta performance para cada miembro del equipo.
- Espacio de Trabajo: Oficina equipada con acceso a internet de alta velocidad.

### Recursos Tecnológicos

- **Software de Desarrollo:**
  - IDEs: Visual Studio Code
  - Herramientas de diseño: Librería Java Swing
  - Gestión de versiones: Git | GitHub
- **Bases de Datos:** MySQL para almacenamiento de datos.
- **Herramientas de Comunicación:**
  - GitHub para comunicación interna.
  - Discord para reuniones de equipo y presentaciones.
- **Herramientas de Gestión de Proyectos:**
  - Teams para seguimiento de tareas y gestión del proyecto.
  - GitHub para la organización de tareas y subtareas.

---

## Plan de Implementación y Cierre

### Estrategia de Implementación

**Descripción de la estrategia para el proceso de Implementación del Proyecto:**

**Preparación y Configuración:**

**Semana 1:**

- Configuración inicial del entorno de desarrollo y servidores.
- Instalación y configuración de herramientas y software necesario.
- Revisión y finalización de la documentación de requisitos.

**Desarrollo e Integración:**

**Semanas 2 a 4:**

**Backend:**

- Configuración del servidor y base de datos.
- Desarrollo APIs RESTful, incluyendo endpoints para gestión de turnos, usuarios, ausencias y preferencias.
- Implementación de módulos de autenticación y autorización.

**Frontend:**

- Desarrollo de la interfaz de usuario con componentes interactivos.
- Implementación de formularios para registro y gestión de turnos, ausencias y preferencias.
- Integración del frontend con la API backend.

**Funcionalidades Clave:**

- Desarrollo del algoritmo para la asignación automática de turnos.
- Implementación del sistema de gestión de ausencias y preferencias.
- Integración de la comunicación interna y notificaciones en tiempo real.

**Pruebas y Ajustes:**

**Semanas 4 a 5:**

**Pruebas Unitarias:**

- Pruebas exhaustivas de cada módulo y componente individual.
- Verificación de que cada unidad funcional cumple con los requisitos especificados.

**Pruebas de Integración:**

- Pruebas para asegurar la correcta integración entre backend y frontend.
- Verificación de los flujos completos de usuario desde el inicio de sesión hasta la gestión de turnos.

**Pruebas de Usuario Final:**

- Realización de pruebas con un grupo selecto de usuarios finales.
- Recopilación de feedback y realización de ajustes y mejoras según las recomendaciones de los usuarios.

**Despliegue y Capacitación:**

**Semana 6:**

**Implementación Piloto:**

- Despliegue en un entorno de prueba para detectar y solucionar cualquier problema potencial.
- Capacitación inicial para usuarios clave, incluyendo tutoriales y sesiones de preguntas y respuestas.

**Despliegue Oficial:**

- Despliegue completo de la aplicación en el entorno de producción.
- Capacitación para todos los usuarios, asegurando que todos los empleados comprendan cómo utilizar la aplicación.
- Monitoreo inicial del rendimiento y la estabilidad de la aplicación, ofreciendo soporte técnico inmediato para resolver cualquier incidencia.

### Cierre del proyecto

**Descripción del Proceso de Cierre del Proyecto:**

**1. Entrega Final:**

- **Documentación Completa:**
  - Finalización y entrega de toda la documentación técnica, incluyendo manuales de usuario, guías de administración y documentación de la API.
  - Entrega de un informe final del proyecto que incluye el alcance, cronograma, presupuesto y resultados obtenidos.

**2. Evaluación del Proyecto:**

- **Revisión Post-Implementación:**
  - Evaluación del rendimiento de la aplicación después del despliegue oficial.
  - Recopilación de feedback de los usuarios finales sobre la funcionalidad y usabilidad de la aplicación.
  - Realización de un análisis de cualquier problema o mejora identificada durante la fase de monitoreo.
- **Análisis de Desempeño del Equipo:**
  - Evaluación del desempeño del equipo de proyecto.
  - Revisión de la eficiencia del proceso de desarrollo, identificando áreas de mejora para futuros proyectos.
  - Documentación de lecciones aprendidas y mejores prácticas para futuros proyectos.

**3. Cierre Formal:**

- **Informe de Cierre:** Preparación y presentación del informe de cierre del proyecto, que incluye un resumen ejecutivo, análisis de resultados, lecciones aprendidas y recomendaciones para el futuro.
- **Archivar Documentación:** Archivar toda la documentación del proyecto en un repositorio accesible para referencia futura.

---

## Apéndices

### Documentación Adicional

En esta sección se incluyen documentos adicionales relevantes que complementan el desarrollo y la implementación del proyecto de la aplicación de gestión de turnos. Estos documentos proporcionan información detallada y apoyo visual al proyecto, asegurando una comprensión completa de los procesos y resultados.

**1. Cartas de Aprobación**  
**Carta de Aprobación del Proyecto**

- **Fecha:** [Fecha de aprobación]
- **Firmado por:** Rodolfo García
- **Descripción:** Aprobación formal del proyecto de desarrollo de la aplicación de gestión de turnos, incluyendo los objetivos, alcance y cronograma acordados.

**2. Diagramas Adicionales**  
**Diagrama Entidad-Relación (ER)**

- **Descripción:** Representación gráfica de las entidades involucradas en la aplicación de gestión de turnos y sus relaciones. Incluye entidades como Departamento, Posición, Trabajador, Turno, Asignación, Preferencia y Ausencia.

**Diagrama de Flujo**

- **Descripción:** Diagramas que muestran el flujo de procesos dentro de la aplicación, desde el inicio de sesión de los usuarios hasta la asignación y gestión de turnos. Ayuda a visualizar los pasos y decisiones involucradas en cada proceso.

**3. Capturas de Interfaces**

**Captura de la Pantalla de Inicio de Sesión**

- **Descripción:** Pantalla donde los usuarios pueden ingresar sus credenciales para acceder a la aplicación.

**Captura de la Pantalla de Gestión de Turnos**

- **Descripción:** Pantalla principal donde los usuarios pueden ver y gestionar los turnos asignados, incluyendo opciones para crear, modificar o eliminar turnos.

**Captura de la Pantalla de porcentajes de trabajadores por departamento**

- **Descripción:** Pantalla donde se ve la cantidad de personal por departamento en porcentual.

**4. Informes Finales**  
**Informe Final del Proyecto**

- **Descripción:** Documento que resume todo el proyecto, incluyendo los objetivos, alcance, cronograma, presupuesto, resultados obtenidos, y una evaluación final del proyecto. Incluye lecciones aprendidas y recomendaciones para futuros proyectos.
