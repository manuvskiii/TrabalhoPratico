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

        //mostra a imagem de fundo
        val backgroundImage: ImageView = findViewById(R.id.backgroundImage)

        // Referência para o botão "Começar"
        val startButton: Button = findViewById(R.id.startButton)

        startButton.setOnClickListener {
            val intent = Intent(this, EscolasActivity::class.java)
            startActivity(intent) // Inicia a segunda atividade
        }
    }
}
