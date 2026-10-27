# Society-Kotlin

App móvil Android de Society, escrita en Kotlin con Jetpack Compose.

Forma parte del proyecto Society. La documentación de producto, diseño y marca está en el repositorio general: [Society](https://github.com/Ju4nmaFd3z/Society).

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
- Parte visual de la pantalla 02 · Empezamos (buscador, estados y lista de tarjetas), sin funcionalidad real. Su carpeta se llama `feature/inicio`.
- Esqueleto de la capa de datos de esa pantalla (`PlacesRepository`, `PlacesApi` y `LocalesPagingSource`), con cada archivo descrito pero sin implementar.
- Navegación montada desde `MainActivity`, con una ruta por pantalla del diseño (15 en total) y los enlaces del flujo de Figma.
- El resto de pantallas, de cartón piedra (`PantallaProvisional`).

**Pendiente**

- Dar funcionalidad a la pantalla 02 · Empezamos: buscador, lectura de los datos y carga por páginas. Faltan las dependencias Retrofit y Paging 3.
- Renombrar la carpeta `feature/inicio` a `feature/empezamos`, para que coincida con el Figma, y crear la pantalla 01 · Inicio de verdad.
- Aplicar las fuentes de la marca en `Type.kt`, que hoy mantiene la tipografía de la plantilla.
- Implementar las pantallas de cartón piedra según el diseño de Figma.

## Claves de API

Las claves, como la de Google Maps, no se suben al repositorio. `local.properties` está excluido por `.gitignore`.

## Convenciones

Commits, ramas y estilo de documentación siguen la [guía de estilo](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/guia-de-estilo.md) del proyecto.

## Licencia

Pendiente de definir. Hasta entonces, todos los derechos reservados.