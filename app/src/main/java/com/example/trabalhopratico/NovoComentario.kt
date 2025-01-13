package com.example.trabalhopratico

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.pm_project.entities.Comentario
import viewModel.WordViewModel
import viewModel.WordViewModelFactory

class NovoComentario : AppCompatActivity() {

    private lateinit var adicionarComentario: EditText
    private lateinit var ratingBar: RatingBar

    private val wordviewmodel: WordViewModel by viewModels {
        WordViewModelFactory((application as RoomAPP).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adicionarcomentario)

        val cursoId = intent.getIntExtra("CURSO_ID", -1)

        adicionarComentario = findViewById(R.id.AddComentario)
        ratingBar = findViewById(R.id.room_ratingBar)

        val button = findViewById<Button>(R.id.SaveComentario)
        button.setOnClickListener {
            if (TextUtils.isEmpty(adicionarComentario.text)) {
                Toast.makeText(this, "Campo vazio", Toast.LENGTH_SHORT).show()
            } else {
                val comentario = Comentario(
                    id = cursoId,
                    nome = adicionarComentario.text.toString(),
                    rating = ratingBar.rating.toInt()
                )
                wordviewmodel.insert(comentario)
                finish()
            }
        }
    }
}
