package com.example.trabalhopratico

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap // Referência para o mapa do Google
    private lateinit var cidade: String // Nome da cidade recebida por Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        // Receber a cidade passada pela Intent da atividade anterior
        cidade = intent.getStringExtra("CIDADE") ?: ""

        // Inicializar o fragmento de mapa e configurar o callback
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this) // Definir a atividade como callback quando o mapa estiver pronto
    }

    // Metodo chamado quando o mapa está pronto para ser utilizado
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap // Inicializa o mapa

        if (cidade.isNotEmpty()) {
            // Obter as coordenadas da cidade
            val position = getCoordinatesFromCity(cidade)

            // Adicionar um marcador na posição da cidade
            mMap.addMarker(
                MarkerOptions()
                    .position(position) // Coordenadas da cidade
                    .title("Local: $cidade") // Texto exibido ao clicar no marcador
            )

            // Mover a câmara para a cidade com um zoom de 12
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 12f))
        } else {
            // Mostrar uma mensagem de erro se a cidade não foi passada
            Toast.makeText(this, "Erro: Cidade não recebida", Toast.LENGTH_SHORT).show()
        }
    }

    // Metodo para obter as coordenadas fixas de uma cidade
    private fun getCoordinatesFromCity(city: String): LatLng {
        return when (city) {
            "Porto" -> LatLng(41.14961, -8.61099) // Coordenadas do Porto
            "Lisboa" -> LatLng(38.71689, -9.13989) // Coordenadas de Lisboa
            "Braga" -> LatLng(41.55032, -8.42005) // Coordenadas de Braga
            "Guimarães" -> LatLng(41.44444, -8.29278) // Coordenadas de Guimarães
            "Viana do Castelo" -> LatLng(41.6946, -8.8297) // Coordenadas de Viana do Castelo
            "Aveiro" -> LatLng(40.64427, -8.64554) // Coordenadas de Aveiro
            "Maia" -> LatLng(41.2357, -8.6199) // Coordenadas da Maia
            "Vila Nova de Gaia" -> LatLng(41.1244, -8.6110) // Coordenadas de Vila Nova de Gaia
            else -> LatLng(39.3999, -8.2245) // Coordenadas padrão para Portugal
        }
    }
}