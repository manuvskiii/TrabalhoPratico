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

        // Obter a referência do RecyclerView no layout
        val recyclerViewCursos = findViewById<RecyclerView>(R.id.recyclerViewCursos)

        // Configurar o layout do RecyclerView como Linear
        recyclerViewCursos.layoutManager = LinearLayoutManager(this)

        // Obter o ID da escola passado a partir da atividade anterior
        val escolaId = intent.getIntExtra("ESCOLA_ID", -1)

        // Validar se o ID da escola foi passado corretamente
        if (escolaId == -1) {
            Toast.makeText(this, "Erro: ID da escola não encontrado", Toast.LENGTH_SHORT).show()
            finish() // Fechar a atividade caso o ID não seja válido
            return
        }

        // Criar uma instância da API para obter a lista de cursos
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getCursos()

        // Fazer a chamada à API de forma assíncrona
        call.enqueue(object : Callback<List<Curso>> {
            override fun onResponse(call: Call<List<Curso>>, response: Response<List<Curso>>) {
                if (response.isSuccessful) {
                    // Obter a lista de todos os cursos
                    val todosOsCursos = response.body() ?: emptyList()

                    // Filtrar os cursos pelo ID da escola
                    val cursosFiltrados = todosOsCursos.filter { it.id_escola == escolaId }

                    // Configurar o adapter do RecyclerView
                    val adapter = CursoAdapter(cursosFiltrados) { curso ->
                        // Definir ação ao clicar num curso
                        val intent = Intent(this@CursosActivity, EstagiosActivity::class.java)

                        // Passar o ID do curso selecionado para a próxima atividade
                        intent.putExtra("CURSO_ID", curso.id_curso)
                        startActivity(intent) // Iniciar a atividade EstagiosActivity
                    }

                    // Associar o adapter ao RecyclerView
                    recyclerViewCursos.adapter = adapter
                } else {
                    // Exibir mensagem de erro caso a resposta da API não seja bem-sucedida
                    Toast.makeText(this@CursosActivity, "Erro ao carregar cursos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Curso>>, t: Throwable) {
                // Registar o erro no log e mostrar uma mensagem ao utilizador
                Log.e("API_ERROR", t.message ?: "Erro desconhecido")
                Toast.makeText(this@CursosActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}