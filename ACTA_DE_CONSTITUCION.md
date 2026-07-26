# Acta de Constitución del Proyecto: AudioRecorderPlayer

## 1. Información del Proyecto
* **Nombre del Proyecto:** AudioRecorderPlayer
* **Fecha:** 25 de julio de 2026
* **Descripción:** Aplicación Android nativa para la grabación y reproducción de audio con interfaz moderna basada en Material Design 3.

## 2. Participantes y Roles
El desarrollo de esta aplicación fue realizado de manera colaborativa, donde cada integrante se encargó de una parte específica y fundamental del ciclo de vida del software:

### Ronald Vasquez Maldonado (Matrícula: 214-4287)
* **Responsabilidad:** Diseño e implementación de la Interfaz de Usuario (UI).
* **Actividades:** Elaboración de los layouts en XML utilizando `LinearLayout` y `MaterialButton`, configuración de estilos y temas (Material 3), e integración de iconos y recursos visuales para una experiencia de usuario fluida.

### Eduardo Del Carmen Rodriguez (Matrícula: 122-4217)
* **Responsabilidad:** Lógica de Grabación de Audio y Gestión del Ciclo de Vida.
* **Actividades:** Implementación de la API `MediaRecorder`, configuración de formatos de salida (3GP) y encoders (AMR_NB), gestión del cronómetro en tiempo real durante la grabación y manejo de estados del hardware.

### Jose Manuel Reyes (Matrícula: 121-5110)
* **Responsabilidad:** Lógica de Reproducción de Audio y Gestión de Permisos.
* **Actividades:** Implementación de la API `MediaPlayer`, manejo de excepciones de entrada/salida de archivos, y configuración del sistema de permisos en tiempo de ejecución para el acceso al micrófono (`RECORD_AUDIO`).

## 3. Objetivos del Proyecto
* Proveer una herramienta sencilla y eficaz para grabar notas de voz.
* Garantizar la compatibilidad con dispositivos Android modernos (API 24+).
* Implementar buenas prácticas de desarrollo como ViewBinding y Edge-to-Edge.

## 4. Firma de los Participantes
Al concluir el desarrollo, los integrantes dan por aceptada la distribución de tareas y el resultado final de la aplicación.
