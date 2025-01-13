package com.example.trabalhopratico

import adapter.CursoAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.Curso
import api.EndPoints
import api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CursosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cursos)

        val recyclerViewCursos = findViewById<RecyclerView>(R.id.recyclerViewCursos)
        recyclerViewCursos.layoutManager = LinearLayoutManager(this)

        val escolaId = intent.getIntExtra("ESCOLA_ID", -1)

        if (escolaId == -1) {
            Toast.makeText(this, "Erro: ID da escola não encontrado", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getCursos()

        call.enqueue(object : Callback<List<Curso>> {
            override fun onResponse(call: Call<List<Curso>>, response: Response<List<Curso>>) {
                if (response.isSuccessful) {
                    val todosOsCursos = response.body() ?: emptyList()
                    val cursosFiltrados = todosOsCursos.filter { it.id_escola == escolaId }

                    val adapter = CursoAdapter(cursosFiltrados) { curso ->
                        val intent = Intent(this@CursosActivity, RoomTest::class.java)
                        intent.putExtra("CURSO_ID", curso.id_curso)
                        startActivity(intent)
                    }

                    recyclerViewCursos.adapter = adapter
                } else {
                    Toast.makeText(this@CursosActivity, "Erro ao carregar cursos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Curso>>, t: Throwable) {
                Log.e("API_ERROR", t.message ?: "Erro desconhecido")
                Toast.makeText(this@CursosActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
