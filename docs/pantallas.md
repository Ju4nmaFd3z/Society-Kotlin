# Pantallas

Implementación de cada pantalla en la app Android. El diseño, el contenido y los estados de cada una están en la [documentación general](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/pantallas/README.md).

## Navegación

Las rutas están en `navigation/Rutas.kt`, una por pantalla, con los nombres del [flujo de navegación](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/flujo-de-navegacion/README.md). El grafo está en `navigation/SocietyNavHost.kt` y se monta desde `MainActivity`. La numeración y los nombres son los del diseño en Figma (pestaña v3 · Cristal líquido), y la app arranca en 01 · Inicio.

| Ruta | Pantalla | Estado | Botones que llevan a otra pantalla |
| --- | --- | --- | --- |
| `Inicio` | 01. Inicio | Cartón piedra | Descubre cómo funciona → 02 |
| `Empezamos` | 02. Empezamos | **Real** (carpeta `feature/inicio`) | Analizar, en la tarjeta de un local → 03 |
| `Analisis(localId)` | 03. Análisis | Cartón piedra, con el ID del local | Avisarme cuando termine → 04 |
| `AsiTeVeInternet` | 04. Así te ve Internet | Cartón piedra | Todo correcto, seguir → 05 |
| `Home` | 05. Home | Cartón piedra | 10, 11, 12, 15, 06, 14 y 13 |
| `CrearContenido` | 06. Crear contenido | Cartón piedra | Siguiente → 07 |
| `EleccionEstilo` | 07. Elección de estilo | Cartón piedra | Siguiente → 08 |
| `FotosLocal` | 08. Fotos del local | Cartón piedra | Siguiente → 09 |
| `ContenidoCreado` | 09. Contenido creado | Cartón piedra | — |
| `Notificaciones` | 10. Notificaciones | Cartón piedra | — |
| `CampanaPropuesta` | 11. Campaña propuesta | Cartón piedra | — |
| `CampanaMedida` | 12. Campaña a medida | Cartón piedra | — |
| `Perfil` | 13. Perfil | Cartón piedra | — |
| `Estadisticas` | 14. Estadísticas | Cartón piedra | — |
| `Calendario` | 15. Calendario | Cartón piedra | — |

Las pantallas de cartón piedra usan `ui/component/PantallaProvisional.kt`, que muestra su número, su nombre y un botón por cada pantalla a la que lleva. Los enlaces de 06 a 09 siguen el orden del selector de pasos del diseño (Idea, Estética, Local). En 02, la lupa del buscador lleva a Análisis con el texto escrito como ID: es provisional.

## 02. Empezamos

Especificación: [02. Empezamos](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/pantallas/02-empezamos.md).

Carpeta: `feature/inicio/`, dentro de `app/src/main/java/com/alanturing/society/`.

La carpeta se llama `feature/inicio` porque, al empezar, se pensó como la pantalla de inicio. En el Figma es la 02, y la 01 · Inicio es la bienvenida. Pendiente de renombrar a `feature/empezamos`.

| Archivo | Función |
| --- | --- |
| `ui/InicioScreen.kt` | La pantalla en Compose: buscador arriba y, debajo, el mensaje, la carga, el error o la lista de locales, según el estado. |
| `ui/InicioViewModel.kt` | Guarda el texto del buscador y el estado actual, lanza la búsqueda y pide la página siguiente al llegar al final de la lista. |
| `ui/InicioUiState.kt` | Los estados de la pantalla: inicial, cargando, vacío, error y con datos. |
| `ui/component/LocalCard.kt` | La tarjeta de un local, con el botón Analizar. |
| `data/PlacesApi.kt` | Las llamadas a la API de Google Maps (Places). |
| `data/PlacesRepository.kt` | Punto único de acceso a los datos para el ViewModel. |
| `data/LocalesPagingSource.kt` | El origen de datos paginado de la lista. |
| `data/dto/` | Las respuestas de la API tal y como llegan. Prevista, aún no creada. |
| `model/Local.kt` | El local tal como lo usa la interfaz. |

Los datos de ejemplo están en `app/src/main/assets/locales.json`.

Estado: la parte visual está hecha (`InicioScreen`, `InicioUiState` y `LocalCard`), sin funcionalidad real todavía. `InicioViewModel` solo guarda el estado inicial, y `PlacesRepository`, `PlacesApi` y `LocalesPagingSource` siguen siendo esqueleto. Al pulsar Analizar en una tarjeta se navega a Análisis.

## 03. Análisis

Especificación: [03. Análisis](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/pantallas/03-analisis.md).

Carpeta: `feature/analisis/`, dentro de `app/src/main/java/com/alanturing/society/`.

| Archivo | Función |
| --- | --- |
| `ui/AnalisisScreen.kt` | La pantalla en Compose. Es el destino de la ruta desde Empezamos. |

Estado: cartón piedra. `AnalisisScreen` recibe el ID del local y lo muestra como texto, con el botón que lleva a la pantalla 04. Quedan por definir el contenido y los estados de la pantalla, y con ellos el resto de archivos.