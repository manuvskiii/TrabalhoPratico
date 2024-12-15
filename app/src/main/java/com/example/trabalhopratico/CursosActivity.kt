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

        // Obter o ID da escola passado da atividade anterior
        val escolaId = intent.getIntExtra("ESCOLA_ID", -1)

        if (escolaId == -1) {
            Toast.makeText(this, "Erro: ID da escola não encontrado", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Chamar a API para obter todos os cursos
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getCursos()

        call.enqueue(object : Callback<List<Curso>> {
            override fun onResponse(call: Call<List<Curso>>, response: Response<List<Curso>>) {
                if (response.isSuccessful) {
                    val todosOsCursos = response.body() ?: emptyList()

                    // Filtrar cursos pelo id_escola
                    val cursosFiltrados = todosOsCursos.filter { it.id_escola == escolaId }

                    // Configurar o Adapter com os cursos filtrados e click listener
                    val adapter = CursoAdapter(cursosFiltrados) { cursos ->
                        // Navegar para EstagiosActivity
                        val intent = Intent(this@CursosActivity, EstagiosActivity::class.java)
                        intent.putExtra("CURSO_ID", cursos.id_curso) // Passar o ID do curso
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