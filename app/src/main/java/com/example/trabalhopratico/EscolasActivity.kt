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

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewEscolas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Chamar o endpoint para buscar as escolas
        getEscolas(recyclerView)
    }
    private fun getEscolas(recyclerView: RecyclerView) {
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getEscolas()

        call.enqueue(object : Callback<List<Escola>> {
            override fun onResponse(call: Call<List<Escola>>, response: Response<List<Escola>>) {
                if (response.isSuccessful) {
                    val escolas = response.body()
                    recyclerView.adapter = EscolaAdapter(escolas ?: emptyList())
                } else {
                    Toast.makeText(this@EscolasActivity, "Erro ao carregar escolas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Escola>>, t: Throwable) {
                Toast.makeText(this@EscolasActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("EscolasActivity", "Erro: ${t.message}")
                Toast.makeText(this@EscolasActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
