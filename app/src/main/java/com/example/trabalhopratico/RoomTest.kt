package com.example.trabalhopratico

import adapter.ComentarioListAdapater
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import viewModel.WordViewModel
import viewModel.WordViewModelFactory

class RoomTest : AppCompatActivity() {

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as RoomAPP).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.roomtest)

        val cursoId = intent.getIntExtra("CURSO_ID", -1)
        if (cursoId == -1) {
            Toast.makeText(this, "Erro: ID do curso não encontrado", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val recyclerView = findViewById<RecyclerView>(R.id.RoomView)
        val adapter = ComentarioListAdapater()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel.allComentarios.observe(this) { comentarios ->
            val comentariosFiltrados = comentarios.filter { it.id == cursoId }
            adapter.submitList(comentariosFiltrados)
        }

        val adicionarComentarioButton = findViewById<Button>(R.id.buttonAdicionarNovoComentario)
        adicionarComentarioButton.setOnClickListener {
            val intent = Intent(this, NovoComentario::class.java)
            intent.putExtra("CURSO_ID", cursoId)
            startActivity(intent)
        }
    }
}
