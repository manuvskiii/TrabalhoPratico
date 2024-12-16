package com.example.trabalhopratico

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Associar a imagem de fundo no layout à variável backgroundImage.
        val backgroundImage: ImageView = findViewById(R.id.backgroundImage)

        // Associar o botão "Começar" à variável startButton.
        val startButton: Button = findViewById(R.id.startButton)

        // Configurar um listener de clique no botão.
        // Quando o botão é pressionado, a aplicação navega para a EscolasActivity.
        startButton.setOnClickListener {
            // Criar uma intenção para iniciar a atividade EscolasActivity.
            val intent = Intent(this, EscolasActivity::class.java)
            startActivity(intent) // Iniciar a nova atividade.
        }
    }
}