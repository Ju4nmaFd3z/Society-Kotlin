# Pantallas

Implementación de cada pantalla en la app Android. El diseño, el contenido y los estados de cada una están en la [documentación general](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/pantallas/README.md).

## Navegación

Las rutas están en `navigation/Rutas.kt`, una por pantalla. Sus nombres coinciden con los del [flujo de navegación](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/flujo-de-navegacion/README.md).

| Ruta | Pantalla | Se llega desde |
| --- | --- | --- |
| `Rutas.INICIO` | 01. Inicio | Punto de partida de la app |
| `Rutas.ANALISIS` | 02. Análisis | Inicio, al pulsar Continuar en un local o desde el buscador de página web |

El grafo de navegación aún no está montado. Falta añadir la dependencia de Navigation Compose.

## 01. Inicio

Especificación: [01. Inicio](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/pantallas/01-inicio.md).

Carpeta: `feature/inicio/`, dentro de `app/src/main/java/com/alanturing/society/`.

| Archivo | Función |
| --- | --- |
| `ui/InicioScreen.kt` | La pantalla en Compose: buscador arriba y, debajo, el mensaje, la carga, el error o la lista de locales, según el estado. |
| `ui/InicioViewModel.kt` | Guarda el texto del buscador y el estado actual, lanza la búsqueda y pide la página siguiente al llegar al final de la lista. |
| `ui/InicioUiState.kt` | Los estados de la pantalla: inicial, cargando, vacío, error y con datos. |
| `ui/component/LocalCard.kt` | La tarjeta de un local. |
| `data/PlacesApi.kt` | Las llamadas a la API de Google Maps (Places). |
| `data/PlacesRepository.kt` | Punto único de acceso a los datos para el ViewModel. |
| `data/LocalesPagingSource.kt` | El origen de datos paginado de la lista. |
| `data/dto/` | Las respuestas de la API tal y como llegan. Prevista, aún no creada. |
| `model/Local.kt` | El local tal como lo usa la interfaz. |

Los datos de ejemplo están en `app/src/main/assets/locales.json`.

Estado: la parte visual está hecha (`InicioScreen`, `InicioUiState` y `LocalCard`), sin funcionalidad real todavía. `InicioViewModel`, `PlacesRepository`, `PlacesApi` y `LocalesPagingSource` siguen siendo esqueleto. La tarjeta no tiene botón Continuar hasta que se vea el enrutamiento.

## 02. Análisis

Especificación: [02. Análisis](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/pantallas/02-analisis.md).

Carpeta: `feature/analisis/`, dentro de `app/src/main/java/com/alanturing/society/`.

| Archivo | Función |
| --- | --- |
| `ui/AnalisisScreen.kt` | La pantalla en Compose. Es el destino de la ruta desde Inicio. |

Estado: esqueleto. El archivo contiene solo su descripción. Quedan por definir el contenido y los estados de la pantalla, y con ellos el resto de archivos.