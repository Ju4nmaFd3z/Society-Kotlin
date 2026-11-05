# Society-Kotlin

App móvil Android de Society, escrita en Kotlin con Jetpack Compose.

Forma parte del proyecto Society. La documentación de producto, diseño y marca está en el repositorio general: [Society](https://github.com/Ju4nmaFd3z/Society).

La app no llama directamente a servicios externos: consume la API única de Society, que vive en [Society-TypeScript](https://github.com/Ju4nmaFd3z/Society-TypeScript). Ver la [arquitectura](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/arquitectura/README.md).

## Requisitos

- Android Studio compatible con la versión del plugin de Android indicada en `gradle/libs.versions.toml`.
- Dispositivo o emulador con Android 12 (API 31) o superior.

## Cómo ejecutarla

1. Abre la carpeta del repositorio en Android Studio.
2. Espera a que Gradle sincronice el proyecto.
3. Ejecuta la configuración `app` en un emulador o dispositivo.

## Estructura

El código se organiza por pantalla o funcionalidad, no por tipo de archivo. La documentación está en [`docs/`](docs/README.md):

- [Estructura del proyecto](docs/estructura-del-proyecto.md): qué va en cada carpeta.
- [Pantallas](docs/pantallas.md): cómo se implementa cada pantalla.

## Estado

**Hecho**

- Proyecto Android base con Compose y Material 3.
- Estructura de carpetas por funcionalidad.
- Tema de la identidad de marca: colores y modo claro, sin color dinámico.
- Permiso de Internet en el manifiesto. Dependencias añadidas: Coil, Navigation Compose, kotlinx.serialization e Hilt.
- Pantalla 02 · Empezamos (`feature/empezamos`) funcional con datos de ejemplo: busca en `assets/locales.json`, muestra sus estados y lleva a 03 · Análisis.
- Esqueleto de `PlacesApi` y `LocalesPagingSource`, para cuando la búsqueda use la API de Society.
- Navegación montada desde `MainActivity`, con una ruta por pantalla del diseño (15 en total) y los enlaces del flujo de Figma.
- El resto de pantallas, de cartón piedra (`PantallaProvisional`).

**Pendiente**

- Pasar la búsqueda de 02 · Empezamos a la API de Society, con carga por páginas. Faltan las dependencias Retrofit y Paging 3.
- Crear la pantalla 01 · Inicio (bienvenida) de verdad.
- Aplicar las fuentes de la marca en `Type.kt`, que hoy mantiene la tipografía de la plantilla.
- Implementar las pantallas de cartón piedra según el diseño de Figma.

## Claves de API

La app no guarda claves de servicios externos. La de Google Maps vive solo en el backend, que es quien consulta Google Places (decisión D-05 de la arquitectura).

La configuración local, como la URL de la API, va en `local.properties`, que está excluido por `.gitignore` y no se sube al repositorio.

## Convenciones

Commits, ramas y estilo de documentación siguen la [guía de estilo](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/guia-de-estilo.md) del proyecto.

## Licencia

Pendiente de definir. Hasta entonces, todos los derechos reservados.