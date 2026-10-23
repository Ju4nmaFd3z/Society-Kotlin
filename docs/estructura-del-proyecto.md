# Estructura del proyecto

Cómo está organizado el código de la app móvil y qué va en cada carpeta.

## Criterio

El código se organiza por pantalla o funcionalidad, no por tipo de archivo. Cada pantalla vive en `feature/<nombre>`.

## Carpetas y archivos generales

Las rutas parten de `app/src/main/java/com/alanturing/society/`.

- `MainActivity.kt`: único punto de entrada de la app.
- `core/network/`: código de red compartido por varias pantallas. Prevista, aún no creada.
- `navigation/Rutas.kt`: un nombre de ruta por pantalla, para no escribirlas a mano.
- `ui/theme/`: tema visual de la app: colores, tema y tipografía.
- `feature/`: una carpeta por pantalla.

## Estructura de cada feature

- `ui/`: lo que se ve en pantalla y su ViewModel. Las partes reutilizables van en `ui/component/`.
- `data/`: de dónde salen los datos: API, repositorio y paginación. Los objetos que devuelve la API van en `data/dto/`, prevista y aún no creada.
- `model/`: las clases con las que trabaja la interfaz.

## Dependencias previstas

Aún no están añadidas a `gradle/libs.versions.toml`. Coil sí está añadida, declarada directamente en `app/build.gradle.kts`.

- Retrofit con kotlinx.serialization, para llamar a Google Maps.
- Paging 3, para la lista perezosa.
- Coil, para cargar imágenes.