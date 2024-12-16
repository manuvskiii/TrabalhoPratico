package com.example.trabalhopratico

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LocaisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locais)

        // Receber os valores passados pela Intent:
        // "DISTRITO", "CIDADE", "RUA" e "CODIGO_POSTAL".
        // Caso não existam valores na Intent, o texto "Desconhecido" será usado como valor padrão.
        val distrito = intent.getStringExtra("DISTRITO") ?: "Desconhecido"
        val cidade = intent.getStringExtra("CIDADE") ?: "Desconhecido"
        val rua = intent.getStringExtra("RUA") ?: "Desconhecida"
        val codigoPostal = intent.getStringExtra("CODIGO_POSTAL") ?: "Desconhecido"

        // Associar os elementos do layout (TextViews) às variáveis correspondentes.
        val textViewDistrito = findViewById<TextView>(R.id.textViewDistrito)
        val textViewCidade = findViewById<TextView>(R.id.textViewCidade)
        val textViewRua = findViewById<TextView>(R.id.textViewRua)
        val textViewCodigoPostal = findViewById<TextView>(R.id.textViewCodigoPostal)

        // Definir o texto dos TextViews com os valores recebidos.
        textViewDistrito.text = "Distrito: $distrito"
        textViewCidade.text = "Cidade: $cidade"
        textViewRua.text = "Rua: $rua"
        textViewCodigoPostal.text = "Código Postal: $codigoPostal"
    }

}