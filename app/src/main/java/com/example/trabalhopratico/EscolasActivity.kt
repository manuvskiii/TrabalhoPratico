package com.example.trabalhopratico

import adapter.EscolaAdapter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.EndPoints
import api.Escola
import api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EscolasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.escolas_activity)

        // Inicializar o RecyclerView a partir do layout
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewEscolas)

        // Definir o layout do RecyclerView como uma lista linear
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Chamar a função para obter a lista de escolas da API
        getEscolas(recyclerView)
    }

    /**
     * Função responsável por buscar a lista de escolas da API e configurar o RecyclerView.
     *
     * @param recyclerView RecyclerView onde as escolas serão exibidas.
     */
    private fun getEscolas(recyclerView: RecyclerView) {
        // Criar uma instância da API utilizando o ServiceBuilder
        val request = ServiceBuilder.buildService(EndPoints::class.java)

        // Fazer a chamada ao endpoint "getEscolas" para obter a lista de escolas
        val call = request.getEscolas()

        // Executar a chamada de forma assíncrona
        call.enqueue(object : Callback<List<Escola>> {

            override fun onResponse(call: Call<List<Escola>>, response: Response<List<Escola>>) {
                if (response.isSuccessful) {
                    // Obter a lista de escolas do corpo da resposta
                    val escolas = response.body()

                    // Configurar o RecyclerView com o adapter e a lista de escolas
                    recyclerView.adapter = EscolaAdapter(escolas ?: emptyList())
                } else {
                    // Caso a resposta não seja bem-sucedida, mostrar uma mensagem ao utilizador
                    Toast.makeText(this@EscolasActivity, "Erro ao carregar escolas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Escola>>, t: Throwable) {
                // Caso haja um erro na chamada, mostrar mensagem e registar no log
                Toast.makeText(this@EscolasActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("EscolasActivity", "Erro: ${t.message}")
            }
        })
    }
}