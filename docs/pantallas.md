# Pantallas

Implementación de cada pantalla en la app Android. El diseño, el contenido y los estados de cada una están en la [documentación general](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/pantallas/README.md).

## 01. Inicio

Especificación: [01. Inicio](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/pantallas/01-inicio.md).

Carpeta: `feature/inicio/`, dentro de `app/src/main/java/com/alanturing/society/`.

| Archivo | Función |
| --- | --- |
| `ui/InicioScreen.kt` | La pantalla en Compose: buscador y lista de locales. |
| `ui/InicioViewModel.kt` | Guarda el texto del buscador y el estado actual, lanza la búsqueda y pide la página siguiente al llegar al final de la lista. |
| `ui/InicioUiState.kt` | Los estados de la pantalla: inicial, cargando, vacío, error y con datos. |
| `ui/component/LocalCard.kt` | La tarjeta de un local. |
| `data/PlacesApi.kt` | Las llamadas a la API de Google Maps (Places). |
| `data/PlacesRepository.kt` | Punto único de acceso a los datos para el ViewModel. |
| `data/LocalesPagingSource.kt` | El origen de datos paginado de la lista. |
| `data/dto/` | Las respuestas de la API tal y como llegan. Prevista, aún no creada. |
| `model/Local.kt` | El local tal como lo usa la interfaz. |

Estado: esqueleto. Cada archivo contiene solo su descripción y la lógica está sin implementar.
