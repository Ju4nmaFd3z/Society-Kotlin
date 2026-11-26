package com.alanturing.society.feature.altamanual.ui

import com.alanturing.society.feature.arranque.model.PlaceType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ManualFormTest {

    // El local de la demo (D-37), bien rellenado
    private val boqueron = ManualForm(
        name = "Bar El Boquerón Dorado",
        type = PlaceType.BAR,
        address = "Calle Larios 12",
        town = "Málaga",
        isManager = true
    )

    @Test
    fun `el local de la demo no tiene errores`() {
        assertTrue(boqueron.validate().isEmpty)
    }

    @Test
    fun `un formulario vacío marca todos los obligatorios`() {
        val errores = ManualForm().validate()

        assertEquals(FieldError.Required, errores.name)
        assertEquals(FieldError.Required, errores.type)
        assertEquals(FieldError.Required, errores.address)
        assertEquals(FieldError.Required, errores.town)
        assertEquals(FieldError.Required, errores.isManager)
        // Web e Instagram son opcionales: vacíos no son un error
        assertNull(errores.web)
        assertNull(errores.instagram)
    }

    @Test
    fun `los espacios no cuentan como texto`() {
        val errores = boqueron.copy(name = "   ", address = " a ").validate()

        assertEquals(FieldError.Required, errores.name)
        assertEquals(FieldError.TooShort(ADDRESS_MIN), errores.address)
    }

    @Test
    fun `la web tiene que empezar por https y tener dominio`() {
        assertEquals(FieldError.InvalidWeb, boqueron.copy(web = "boqueron.es").validate().web)
        assertEquals(FieldError.InvalidWeb, boqueron.copy(web = "http://boqueron.es").validate().web)
        assertEquals(FieldError.InvalidWeb, boqueron.copy(web = "https://boqueron").validate().web)
        assertNull(boqueron.copy(web = "https://boqueron.es").validate().web)
    }

    @Test
    fun `instagram acepta la arroba y las mayúsculas pero no otros símbolos`() {
        assertNull(boqueron.copy(instagram = "@Boqueron.Dorado").validate().instagram)
        assertEquals(FieldError.InvalidInstagram, boqueron.copy(instagram = "boquerón dorado").validate().instagram)
        assertEquals(FieldError.InvalidInstagram, boqueron.copy(instagram = "a".repeat(31)).validate().instagram)
    }

    @Test
    fun `lo que se envía va limpio`() {
        val enviado = boqueron.copy(
            name = "  Bar El Boquerón Dorado ",
            web = " ",
            instagram = " @Boqueron_Dorado"
        ).toManualRestaurant()

        assertEquals("Bar El Boquerón Dorado", enviado.name)
        assertNull(enviado.web) // en blanco no se manda
        assertEquals("boqueron_dorado", enviado.instagram)
    }
}
