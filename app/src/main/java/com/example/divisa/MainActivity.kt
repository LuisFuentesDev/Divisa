package com.example.divisa

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.divisa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val divisas = listOf("USD", "CLP", "EUR")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.divisaIngresada.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)
        binding.divisaRetornada.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)

        initListener()
    }

    private fun initListener() {
        binding.buttonConvert.setOnClickListener {
            val importe = binding.valorIngresado.text.toString().toDouble()
            val divisaIngresada = binding.divisaIngresada.selectedItem.toString()
            val divisaRetornada = binding.divisaRetornada.selectedItem.toString()
            Log.d("Estamos en el initlistener", "$importe $divisaIngresada $divisaRetornada")
            val resultado = conversorDivisa(importe, divisaIngresada, divisaRetornada)
            binding.divisaResultado.text = resultado.toString()
        }
        binding.buttonReset.setOnClickListener {
            limpiar()
        }
    }

    fun conversorDivisa(importe: Double, divisaIngresada: String, divisaRetornada: String): Double {
        var resultado = importe
        when (divisaIngresada) {
            "USD" -> if (divisaRetornada == "CLP") {
                resultado = importe * 817
            } else if (divisaRetornada == "USD") {
                resultado = importe * 1
            } else if (divisaRetornada == "EUR") {
                resultado = importe * 0.89
            }

            "CLP" -> if (divisaRetornada == "CLP") {
                resultado = importe * 1
            } else if (divisaRetornada == "USD") {
                resultado = importe * 0.001
            } else if (divisaRetornada == "EUR") {
                resultado = importe * 0.01
            }

            "EUR" -> if (divisaRetornada == "CLP") {
                resultado = importe * 910
            } else if (divisaRetornada == "USD") {
                resultado = importe * 1.11
            } else if (divisaRetornada == "EUR") {
                resultado = importe * 1
            }

            else -> {
                resultado = importe
            }
        }
        return resultado
    }

    fun limpiar() {
        binding.divisaResultado.text = ""
        binding.valorIngresado.setText("")
    }
}