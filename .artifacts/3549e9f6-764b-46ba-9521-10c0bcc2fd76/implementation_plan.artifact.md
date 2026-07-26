# Plan para solucionar alertas amarillas en AndroidManifest.xml

El objetivo es resolver las 3 advertencias de inspección (alertas amarillas) detectadas en el archivo `AndroidManifest.xml`, las cuales están relacionadas con permisos obsoletos y la configuración de la aplicación para versiones recientes de Android (Target SDK 36).

## User Review Required

> [!IMPORTANT]
> Se eliminarán los permisos `WRITE_EXTERNAL_STORAGE` y `READ_EXTERNAL_STORAGE` por estar obsoletos en API 33+. Se añadirá `READ_MEDIA_AUDIO` para mantener la funcionalidad de lectura de audio en dispositivos modernos.

## Proposed Changes

### [app](file:///C:/Users/pc/AndroidStudioProjects/MiSegundaApp/app)

#### [MODIFY] [AndroidManifest.xml](file:///C:/Users/pc/AndroidStudioProjects/MiSegundaApp/app/src/main/AndroidManifest.xml)

1.  **Actualizar Permisos**:
    *   Eliminar `android.permission.WRITE_EXTERNAL_STORAGE`.
    *   Reemplazar `android.permission.READ_EXTERNAL_STORAGE` con `android.permission.READ_MEDIA_AUDIO` para compatibilidad con Android 13+.
2.  **Configurar Iconos y Etiquetas**:
    *   Añadir `android:icon="@mipmap/ic_launcher"`.
    *   Añadir `android:roundIcon="@mipmap/ic_launcher_round"`.
    *   Cambiar `android:label="AudioRecorderPlayer"` por `android:label="@string/app_name"`.
3.  **Reglas de Respaldo**:
    *   Añadir `android:dataExtractionRules="@xml/data_extraction_rules"` y `android:fullBackupContent="@xml/backup_rules"` para cumplir con los requisitos de Android 12+.

## Verification Plan

### Manual Verification
1.  Ejecutar `analyze_file` nuevamente sobre `AndroidManifest.xml` para confirmar que las advertencias han desaparecido.
2.  Realizar una sincronización de Gradle para asegurar que no hay conflictos con los nuevos atributos.
