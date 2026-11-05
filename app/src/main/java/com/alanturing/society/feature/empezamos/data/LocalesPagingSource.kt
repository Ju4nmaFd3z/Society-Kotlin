package com.alanturing.society.feature.empezamos.data

/**
 * Origen de datos paginado para la lista perezosa. Pide a PlacesRepository cada página
 * usando el token de la anterior, de modo que la lista solo carga más locales a medida
 * que el usuario hace scroll.
 */