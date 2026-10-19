package com.alanturing.society.feature.inicio.data

/**
 * Llamadas a la API de Google Maps (Places) para buscar locales por texto.
 * Solo se ocupa de la comunicación: envía la búsqueda y devuelve la respuesta
 * en JSON, sin interpretarla. Soporta paginación con un token, que es lo que
 * permite que la lista de la pantalla cargue por partes.
 */