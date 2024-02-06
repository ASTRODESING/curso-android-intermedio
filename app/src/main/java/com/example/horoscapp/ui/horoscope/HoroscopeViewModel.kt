package com.example.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor():ViewModel() {

    // Se crea una lista mutable la cual solo puede modificarse desde este view model y asegurar
    //  que el usuario no pueda modificar informacion
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    // -------------------------------------------------------------------------


    // Se crea una lista no mutable la cual sera la que se despliegue en el fragment
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope
    // -------------------------------------------------------------------------
    init {
        _horoscope.value = listOf(
            Aries,Tauro,Gemini
        )
    }
}