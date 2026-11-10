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
- Permiso de Internet en el manifiesto. Dependencias añadidas: Coil, Navigation Compose, kotlinx.serialization, Hilt y Retrofit con Gson.
- Pantalla 02 · Empezamos (`feature/empezamos`) funcional con datos de ejemplo: busca en `assets/locales.json`, muestra sus estados y lleva a 03 · Análisis.
- Capa de red con Retrofit, Gson y Hilt: `SocietyApi`, un DataSource y un repositorio que llaman a `GET /salud` al arrancar y escriben el resultado en el log. La URL de la API sale de `API_BASE`.
- Navegación de todas las pantallas del MVP montada desde `MainActivity`: las rutas, con los datos que recibe cada pantalla, están en `navigation/Rutas.kt`, y los enlaces siguen el diagrama de flujo de la app.
- El resto de pantallas, provisionales: cada una muestra su título y los botones que llevan a la siguiente.

**Pendiente**

- Pasar la búsqueda de 02 · Empezamos a la API de Society, que hoy lee `assets/locales.json`.
- Cambiar los IDs inventados de la navegación por los que devuelva la API, elegir el destino inicial según la sesión y vaciar la pila al terminar el alta y al cerrar sesión.
- Aplicar las fuentes de la marca en `Type.kt`, que hoy mantiene la tipografía de la plantilla.
- Implementar las pantallas provisionales según el diseño de Figma.

## Claves de API

La app no guarda claves de servicios externos. La de Google Maps vive solo en el backend, que es quien consulta Google Places (decisión D-05 de la arquitectura).

La configuración local, como la URL de la API (`API_BASE`), va en `local.properties`, que está excluido por `.gitignore` y no se sube al repositorio. Los detalles están en [Estructura del proyecto](docs/estructura-del-proyecto.md).

## Convenciones

Commits, ramas y estilo de documentación siguen la [guía de estilo](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/guia-de-estilo.md) del proyecto.

## Licencia

Pendiente de definir. Hasta entonces, todos los derechos reservados.