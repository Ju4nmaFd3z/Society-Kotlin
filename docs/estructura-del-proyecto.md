# Estructura del proyecto

Cómo está organizado el código de la app móvil y qué va en cada carpeta.

## Criterio

El código se organiza por pantalla o funcionalidad, no por tipo de archivo. Cada pantalla vive en `feature/<nombre>`.

## Carpetas y archivos generales

Las rutas parten de `app/src/main/java/com/alanturing/society/`.

- `MainActivity.kt`: único punto de entrada de la app. Monta la navegación.
- `core/SocietyApplication.kt`: clase que arranca Hilt, necesaria para inyectar los ViewModel.
- `core/network/`: código de red compartido por varias pantallas. Prevista, aún no creada.
- `navigation/Rutas.kt`: un nombre de ruta por pantalla, para no escribirlas a mano.
- `navigation/SocietyNavHost.kt`: el grafo de navegación, que enlaza cada ruta con su pantalla y los botones que llevan a la siguiente.
- `ui/component/`: piezas visuales compartidas por varias pantallas, como `PantallaProvisional`.
- `ui/theme/`: tema visual de la app: colores, tema y tipografía.
- `feature/`: una carpeta por pantalla.

## Estructura de cada feature

- `ui/`: lo que se ve en pantalla y su ViewModel. Las partes reutilizables van en `ui/component/`.
- `data/`: de dónde salen los datos: API, repositorio y paginación. Los objetos que devuelve la API van en `data/dto/`, prevista y aún no creada.
- `model/`: las clases con las que trabaja la interfaz.

## Dependencias previstas

Pendientes de añadir:

- Retrofit, para llamar a Google Maps.
- Paging 3, para la lista perezosa.

Ya añadidas: Coil, para cargar imágenes; Navigation Compose y kotlinx.serialization, para la navegación; e Hilt, para inyectar los ViewModel.