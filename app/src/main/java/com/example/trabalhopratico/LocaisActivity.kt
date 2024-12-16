package com.example.trabalhopratico

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalhopratico.R

class LocaisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locais)

        // Receber os valores de distrito e cidade
        val distrito = intent.getStringExtra("DISTRITO") ?: "Desconhecido"
        val cidade = intent.getStringExtra("CIDADE") ?: "Desconhecido"

        // Referenciar os TextViews no layout
        val textViewDistrito = findViewById<TextView>(R.id.textViewDistrito)
        val textViewCidade = findViewById<TextView>(R.id.textViewCidade)

        // Exibir os valores recebidos
        textViewDistrito.text = "Distrito: $distrito"
        textViewCidade.text = "Cidade: $cidade"
    }
}