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
- Parte visual de la pantalla Inicio (buscador, estados y lista de tarjetas), sin funcionalidad real.
- Esqueleto de la capa de datos de Inicio (`InicioViewModel`, `PlacesRepository`, `PlacesApi` y `LocalesPagingSource`), con cada archivo descrito pero sin implementar.
- Primera ruta definida (Inicio → Análisis) y esqueleto de la pantalla Análisis.

**Pendiente**

- Añadir el permiso de acceso a Internet en el manifiesto y las dependencias previstas que faltan (Retrofit, kotlinx.serialization y Paging 3). Coil ya está añadida.
- Adaptar el tema a la identidad de marca. Hoy mantiene los colores y el modo oscuro por defecto de la plantilla, y el diseño pide modo claro.
- Sustituir la pantalla de ejemplo de `MainActivity` por la pantalla Inicio.
- Dar funcionalidad a la pantalla Inicio: buscador, lectura de los datos y carga por páginas.
- Añadir Navigation Compose y montar el grafo de navegación en `MainActivity`.
- Definir el contenido de la pantalla Análisis y completar su esqueleto.

## Claves de API

Las claves, como la de Google Maps, no se suben al repositorio. `local.properties` está excluido por `.gitignore`.

## Convenciones

Commits, ramas y estilo de documentación siguen la [guía de estilo](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/guia-de-estilo.md) del proyecto.

## Licencia

Pendiente de definir. Hasta entonces, todos los derechos reservados.