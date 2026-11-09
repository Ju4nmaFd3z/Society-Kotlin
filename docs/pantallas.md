# Pantallas

Implementación de cada pantalla en la app Android. El diseño, el contenido y los estados de cada una están en la [documentación general](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/pantallas/README.md).

## Navegación

Todas las rutas están en `navigation/Rutas.kt`, una por pantalla, con los datos que recibe cada una según el diagrama de flujo de la app. El grafo está en `navigation/SocietyNavHost.kt` y se monta desde `MainActivity`. La app arranca en 01 Bienvenida.

| Ruta | Pantalla | Carpeta | Recibe | Botones que llevan a otra pantalla |
| --- | --- | --- | --- | --- |
| `BienvenidaRoute` | 01 Bienvenida | `bienvenida` | Nada | Descubre cómo funciona → 01A |
| `AccesoRoute` | 01A Acceso | `acceso` | Nada | Entrar → 02 |
| `EmpezamosRoute` | 02 Busca tu local | `empezamos` | Nada | Analizar → 03 · Añádelo a mano → 02M |
| `AltaManualRoute` | 02M Alta manual | `altamanual` | Nada | Guardar y analizar → 03 |
| `AnalisisRoute` | 03 Análisis | `analisis` | `analisisId` | Ver resultado → 04 · Cancelar → vuelve a 02 |
| `AsiTeVeInternetRoute` | 04 Así te ve internet | `asiteveinternet` | `restauranteId`, `modoEdicion` | Todo correcto, seguir → 05 |
| `HomeRoute` | 05 Home | `home` | Nada | 06, 11, 09, 12, 15 y 13 |
| `CrearContenidoGraph` | Grafo de 06 a 08 | `crearcontenidograph` | `campanaId`, opcional | Empieza en 06 |
| `CrearContenidoRoute` | 06 Crear contenido | `crearcontenidograph` | Nada | Siguiente → 07 |
| `EleccionEstiloRoute` | 07 Elección de estilo | `crearcontenidograph` | Nada | Siguiente → 08 |
| `FotosLocalRoute` | 08 Fotos del local | `crearcontenidograph` | Nada | Generar contenido → 09 |
| `ContenidoCreadoRoute` | 09 Contenido creado | `contenidocreado` | `piezaId` | Descripción y hashtags → 09P |
| `PublicarContenidoRoute` | 09P Publicar contenido | `publicarcontenido` | `piezaId` | Publicar en Instagram → 05 |
| `CampanaPropuestaRoute` | 11 Campaña propuesta | `campanapropuesta` | `oportunidadId` | Crear campaña → 06 |
| `CampanaMedidaRoute` | 12 Campaña a medida (P1) | `campanamedida` | Nada | Crear plan de campaña → 11 |
| `PerfilRoute` | 13 Perfil | `perfil` | Nada | Datos del local → 04 en modo edición · Cerrar sesión → 01 |
| `CalendarioRoute` | 15 Calendario (P1) | `calendario` | Nada | Ver pieza → 09 |

10 Notificaciones y 14 Estadísticas quedan fuera del MVP y no tienen ruta.

Cada pantalla sigue el mismo patrón, dentro de su carpeta de `feature/`:

- `XNavigation.kt`: `navigateToX()`, que navega a la ruta con sus datos, y `xDestination()`, que saca los datos de la ruta con `toRoute()` y muestra la pantalla. La pantalla no conoce el `navController`: solo avisa de que se ha pulsado un botón y es `SocietyNavHost` quien decide a dónde va.
- `ui/XScreen.kt`: la pantalla. Salvo 02, de momento es provisional: el título, los datos que recibe y los botones que llevan a la siguiente.

Los pasos de 06 a 08 van dentro del grafo anidado `CrearContenidoGraph` (`crearcontenidograph/CrearContenidoNavigation.kt`), para que más adelante compartan un ViewModel con el borrador de la pieza.

Lo simulado está marcado con un TODO en el código. Los IDs que todavía no devuelve la API se pasan inventados (`analisis_demo`, `restaurante_demo`, `pieza_demo`, `oportunidad_demo` y `campana_demo`). También falta elegir el destino inicial según la sesión y el estado del alta, y vaciar la pila al terminar el alta y al cerrar sesión.

## 02. Empezamos

Especificación: [02. Empezamos](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/pantallas/02-empezamos.md).

Carpeta: `feature/empezamos/`, dentro de `app/src/main/java/com/alanturing/society/`.

| Archivo | Función |
| --- | --- |
| `EmpezamosNavigation.kt` | El destino de la ruta `EmpezamosRoute` en el grafo de navegación. |
| `ui/EmpezamosScreen.kt` | La pantalla en Compose: `BuscadorLocales` y enlace «Añádelo a mano» arriba y, debajo, según el estado, el mensaje, la carga, el error con Reintentar o la lista de locales, con una `LocalCard` por local. |
| `ui/EmpezamosViewModel.kt` | Lanza la búsqueda en `PlacesRepository`, guarda el estado actual y permite reintentar la última búsqueda tras un error. |
| `ui/EmpezamosUiState.kt` | Los estados de la pantalla: inicial, cargando, vacío, error y con datos. |
| `ui/component/BuscadorLocales.kt` | El campo de búsqueda con su botón Buscar. |
| `ui/component/LocalCard.kt` | La tarjeta de un local, con el botón Analizar. |
| `data/PlacesApi.kt` | Las llamadas a la búsqueda de locales de la API de Society, que consulta Google Places desde el backend. |
| `data/PlacesRepository.kt` | Punto único de acceso a los datos para el ViewModel. De momento lee `assets/locales.json` y filtra por nombre o dirección. |
| `data/LocalesPagingSource.kt` | El origen de datos paginado de la lista. |
| `data/dto/PlacesDto.kt` | La respuesta tal y como llega en el JSON, con la misma forma que la de Google Places. |
| `model/Local.kt` | El local tal como lo usa la interfaz. |

Los datos de ejemplo están en `app/src/main/assets/locales.json`.

Estado: funcional con los datos de ejemplo. La búsqueda filtra los locales de `locales.json` por nombre o dirección, con una espera simulada de medio segundo para que se vea la carga, y la pantalla muestra sus cinco estados. Al pulsar Analizar en una tarjeta se navega a Análisis con el ID del local, provisional hasta que la API devuelva el `analisisId`. Pendiente: buscar contra la API de Society (`PlacesApi`, con Retrofit) y cargar por páginas (`LocalesPagingSource`, con Paging 3); al hacerlo solo cambia `PlacesRepository`.

## 03. Análisis

Especificación: [03. Análisis](https://github.com/Ju4nmaFd3z/Society/blob/main/docs/diseno/pantallas/03-analisis.md).

Carpeta: `feature/analisis/`, dentro de `app/src/main/java/com/alanturing/society/`.

| Archivo | Función |
| --- | --- |
| `AnalisisNavigation.kt` | El destino de la ruta `AnalisisRoute(analisisId)` en el grafo de navegación. |
| `ui/AnalisisScreen.kt` | La pantalla en Compose. Es el destino de la ruta desde 02 y 02M. |

Estado: provisional. `AnalisisScreen` recibe el ID del análisis y lo muestra como texto, con el botón que lleva a la pantalla 04 y el de cancelar, que vuelve a 02. Quedan por definir el contenido y los estados de la pantalla, y con ellos el resto de archivos.