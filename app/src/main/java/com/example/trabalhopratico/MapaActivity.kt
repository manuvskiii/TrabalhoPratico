package com.example.trabalhopratico

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import api.EndPoints
import api.Estagio
import api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var distrito: String // Distrito recebido via Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        // Receber o distrito passado da EstagiosActivity
        distrito = intent.getStringExtra("DISTRITO") ?: ""

        // Carregar o mapa
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (distrito.isNotEmpty()) {
            // Carregar os estágios filtrados pelo distrito
            fetchEstagiosByDistrito(distrito)
        } else {
            Toast.makeText(this, "Erro: Distrito não recebido!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchEstagiosByDistrito(distrito: String) {
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getEstagios()

        call.enqueue(object : Callback<List<Estagio>> {
            override fun onResponse(call: Call<List<Estagio>>, response: Response<List<Estagio>>) {
                if (response.isSuccessful) {
                    val estagios = response.body() ?: emptyList()

                    // Filtrar estágios pelo distrito
                    val estagiosFiltrados = estagios.filter { it.distrito == distrito }

                    if (estagiosFiltrados.isNotEmpty()) {
                        for (estagio in estagiosFiltrados) {
                            // Simulando coordenadas baseado na cidade (substituir por dados reais)
                            val position = getCoordinatesFromCity(estagio.distrito)
                            mMap.addMarker(
                                MarkerOptions()
                                    .position(position)
                                    .title("${estagio.empresa}: ${estagio.descricao}")
                            )
                        }

                        // Centralizar o mapa no primeiro estágio
                        val firstPosition = getCoordinatesFromCity(estagiosFiltrados[0].distrito)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstPosition, 10f))
                    } else {
                        Toast.makeText(
                            this@MapsActivity,
                            "Nenhum estágio encontrado para o distrito: $distrito",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<Estagio>>, t: Throwable) {
                Log.e("MAPS_ERROR", t.message ?: "Erro desconhecido")
                Toast.makeText(this@MapsActivity, "Erro ao carregar estágios", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    // Metodo de exemplo para retornar coordenadas fictícias baseadas em cidades
    private fun getCoordinatesFromCity(city: String): LatLng {
        return when (city) {
            "Porto" -> LatLng(41.1579, -8.6291)
            "Braga" -> LatLng(41.5454, -8.4265)
            "Guimarães" -> LatLng(41.4427, -8.2919)
            "Viana do Castelo" -> LatLng(41.6932, -8.8329)
            "Ponte de Lima" -> LatLng(41.7676, -8.5836)
            "Maia" -> LatLng(41.2357, -8.6199)
            "Vila Nova de Gaia" -> LatLng(41.1244, -8.6110)
            "Barcelos" -> LatLng(41.5380, -8.6157)
            else -> LatLng(41.1579, -8.6291) // Default: Porto
        }
    }
}