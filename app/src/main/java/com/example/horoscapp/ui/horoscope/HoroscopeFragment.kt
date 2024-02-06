package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    //Se crea una instancia del view model para utilizar posteriormente y extraer los datos
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()
    // ---------------------------------------------------------------------------------

    // Se crea la variable _binding y se inicializa en nulo debido a que android studio
    // por defecto dudara si el fragment contiene algun contenido, para evitar errores
    // _binding se iniciara en nulo y luego se negara con _binding!!
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!
    // ---------------------------------------------------------------------------------

    // CUANDO LA VISTA YA ESTA CREADA SE LLAMA AL HOROSCOPE VIEW MODEL PARA EXTRAER LOS DATOS
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    // ---------------------------------------------------------------------------------
    private fun initUi() {
        initUiState()
    }

    private fun initUiState() {
        // Se crea una corrutina que al momento de que el fragment muera (muy comun teniendo en
        // cuenta que los fragments tienen un ciclo de vida hasta que el usuario cambie de
        // fragment) la corrutina en cuestion se va a eliminar con el fragment a la vez
        lifecycleScope.launch {
            // repeatOnLifecycle significa que cuando empieze el ciclo de vida se ejecutara algo
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //finalmente se recolectan los datos del view model
                horoscopeViewModel.horoscope.collect(){
                    Log.i("Prueba View Model", it.toString())
                }
                // ------------------------------------------------------------------------

            }
        }
        // ---------------------------------------------------------------------------------
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}