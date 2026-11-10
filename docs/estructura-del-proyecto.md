# Estructura del proyecto

Cómo está organizado el código de la app móvil y qué va en cada carpeta.

## Criterio

El código se organiza por pantalla o funcionalidad, no por tipo de archivo. Cada pantalla vive en `feature/<nombre>`.

## Carpetas y archivos generales

Las rutas parten de `app/src/main/java/com/alanturing/society/`.

- `MainActivity.kt`: único punto de entrada de la app. Monta la navegación.
- `core/SocietyApplication.kt`: clase que arranca Hilt, necesaria para inyectar los ViewModel.
- `core/network/`: la capa de red compartida por todas las pantallas. `SocietyApi` reúne las llamadas a la API con Retrofit; hoy solo `GET /salud`. Junto a ella están los modelos de la respuesta (`SaludApiModels.kt`), el `SaludDataSource` con su `SaludRemoteDataSource` y el `SaludRepository` con su implementación.
- `core/di/`: los módulos de Hilt. `NetworkModule` crea Retrofit una sola vez, con la URL de `API_BASE`, y `AppModule` une cada interfaz con su implementación.
- `navigation/Rutas.kt`: una ruta por pantalla, con los datos que recibe.
- `navigation/SocietyNavHost.kt`: el grafo de navegación, que enlaza cada ruta con su pantalla y los botones que llevan a la siguiente.
- `ui/component/`: piezas visuales compartidas por varias pantallas.
- `ui/theme/`: tema visual de la app: colores, tema y tipografía.
- `feature/`: una carpeta por pantalla.

## Estructura de cada feature

- `XNavigation.kt`: cómo se llega a la pantalla y qué se muestra cuando su ruta está activa.
- `ui/`: lo que se ve en pantalla y su ViewModel. Las partes reutilizables van en `ui/component/`.
- `data/`: de dónde salen los datos: el DataSource que llama a la API y el repositorio que usa el ViewModel. Los objetos que devuelve la API van en `data/dto/`.
- `model/`: las clases con las que trabaja la interfaz.

## Dependencias

- Coil, para cargar imágenes.
- Navigation Compose y kotlinx.serialization, para la navegación.
- Hilt, para inyectar los ViewModel y las clases de la capa de red.
- Retrofit con el conversor Gson, para llamar a la API de Society. Gson convierte el JSON de la API en clases de Kotlin.

No se usa Paging 3: la paginación de la búsqueda es manual, con `nextPageToken` (decisión D-15). La app tampoco guarda ninguna clave de Google Places (decisión D-05): las búsquedas pasan por la API.

## URL de la API

La app lee la URL base de `API_BASE`, que se puede definir en `local.properties`:

```
API_BASE=https://society-api-xi.vercel.app/api/v1/
```

Tiene que terminar en barra. Si `local.properties` no la define, la app usa la API desplegada en Vercel, la misma de este ejemplo. Al arrancar, `MainActivity` llama a `GET /salud` y escribe el resultado en el log, con la etiqueta `SocietyApi`.
