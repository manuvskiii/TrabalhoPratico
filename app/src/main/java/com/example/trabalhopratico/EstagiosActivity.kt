package com.example.trabalhopratico

import adapter.EstagioAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.EndPoints
import api.Estagio
import api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EstagiosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estagios)

        // Inicializar o RecyclerView para exibir os estágios
        val recyclerViewEstagios = findViewById<RecyclerView>(R.id.recyclerViewEstagios)

        // Configurar o RecyclerView com layout linear (lista vertical)
        recyclerViewEstagios.layoutManager = LinearLayoutManager(this)

        // Nome do curso para filtrar os estágios
        val cursoNome = "Engenharia de Redes e Sistemas de Computadores"

        // Criar uma instância da API para obter os dados
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getEstagios()

        // Fazer a chamada à API para obter a lista de estágios
        call.enqueue(object : Callback<List<Estagio>> {

            override fun onResponse(call: Call<List<Estagio>>, response: Response<List<Estagio>>) {
                // Verificar se a resposta foi bem-sucedida
                if (response.isSuccessful) {
                    // Obter a lista de estágios da resposta
                    val todosOsEstagios = response.body() ?: emptyList()

                    // Filtrar os estágios pelo curso especificado
                    val estagiosFiltrados = todosOsEstagios.filter { it.curso == cursoNome }

                    // Configurar o Adapter para o RecyclerView com os estágios filtrados
                    recyclerViewEstagios.adapter = EstagioAdapter(estagiosFiltrados) { estagio ->
                        // Quando um item é clicado, abrir o MapsActivity com o distrito selecionado
                        val intent = Intent(this@EstagiosActivity, MapsActivity::class.java)
                        intent.putExtra("DISTRITO", estagio.distrito) // Passar o distrito como extra
                        startActivity(intent)
                    }
                } else {
                    // Mostrar mensagem de erro se a resposta não for bem-sucedida
                    Toast.makeText(this@EstagiosActivity, "Erro ao carregar estágios", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Estagio>>, t: Throwable) {
                // Tratar erros, como problemas de conexão ou falhas na API
                Log.e("API_ERROR", t.message ?: "Erro desconhecido")
                Toast.makeText(this@EstagiosActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}