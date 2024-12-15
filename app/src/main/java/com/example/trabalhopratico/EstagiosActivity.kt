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

        val recyclerViewEstagios = findViewById<RecyclerView>(R.id.recyclerViewEstagios)
        recyclerViewEstagios.layoutManager = LinearLayoutManager(this)

        // Nome do curso a ser filtrado
        val cursoNome = "Engenharia de Redes e Sistemas de Computadores"

        // Chamada para obter todos os estágios
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getEstagios()

        call.enqueue(object : Callback<List<Estagio>> {
            override fun onResponse(call: Call<List<Estagio>>, response: Response<List<Estagio>>) {
                if (response.isSuccessful) {
                    val todosOsEstagios = response.body() ?: emptyList()

                    // Filtrar estágios para o curso de Engenharia de Redes e Sistemas de Computadores
                    val estagiosFiltrados = todosOsEstagios.filter { it.curso == cursoNome }

                    // Configurar o Adapter com os estágios filtrados e o click listener
                    recyclerViewEstagios.adapter = EstagioAdapter(estagiosFiltrados) { estagio ->
                        val intent = Intent(this@EstagiosActivity, MapsActivity::class.java)
                        intent.putExtra("DISTRITO", estagio.distrito) // Passar o distrito
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this@EstagiosActivity, "Erro ao carregar estágios", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Estagio>>, t: Throwable) {
                Log.e("API_ERROR", t.message ?: "Erro desconhecido")
                Toast.makeText(this@EstagiosActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}