package com.alanturing.society.feature.acceso.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.alanturing.society.feature.acceso.model.Sesion
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SesionDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SesionLocalDataSource {

    // Claves en dataStore
    private val accessToken = stringPreferencesKey("access_token")
    private val refreshToken = stringPreferencesKey("refresh_token")
    private val caducaEn = longPreferencesKey("caduca_en_ms")

    // Lectura:     // Emite al cambiar la sesión. Si falta algún dato no hay sesión (null)
    override val sesion: Flow<Sesion?> = dataStore.data.map { prefs ->
        val access = prefs[accessToken]
        val refresh = prefs[refreshToken]
        val caduca = prefs[caducaEn]
        if (access == null || refresh == null || caduca == null) {
            null
        } else {
            // Segundos que le quedan al token (nunca negativo)
            val quedan = ((caduca - System.currentTimeMillis()) / 1000).coerceAtLeast(0)

            Sesion(accessToken = access, refreshToken = refresh, expiraEn = quedan)
        }
    }

    // Escritura:Guarda los valores. convierte a caducaEn, se guarada el momento exacto en ms
    override suspend fun guardar(sesion: Sesion) {
        dataStore.updateData {
            it.toMutablePreferences().also { prefs ->
                prefs[accessToken] = sesion.accessToken
                prefs[refreshToken] = sesion.refreshToken
                prefs[caducaEn] = System.currentTimeMillis() + sesion.expiraEn * 1000
            }
        }
    }
    //Elimina los tres valores
    override suspend fun borrar() {
        dataStore.updateData {
            it.toMutablePreferences().also { prefs -> prefs.clear() }
        }
    }
}