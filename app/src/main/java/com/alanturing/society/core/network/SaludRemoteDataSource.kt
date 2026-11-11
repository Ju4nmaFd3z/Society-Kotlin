package com.alanturing.society.core.network

import com.google.gson.JsonParseException
import java.io.IOException
import javax.inject.Inject

class SaludRemoteDataSource @Inject constructor(
    private val api: SocietyApi
) : SaludDataSource {

    override suspend fun comprobarSalud(): Salud {
        return try {
            val respuesta = api.salud()
            val cuerpo = respuesta.body()
            if (respuesta.isSuccessful && cuerpo != null) {
                cuerpo.toModel()
            } else {
                Salud(ok = false)
            }
        } catch (e: IOException) {
            // Sin conexión o sin llegar al servidor
            Salud(ok = false)
        } catch (e: JsonParseException) {
            // La API respondió con algo que no se puede leer
            Salud(ok = false)
        }
    }
}
