package api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto singleton para construir o cliente Retrofit.
 * Este cliente será utilizado para efetuar chamadas à API.
 */
object ServiceBuilder {

    // URL base da API. O "10.0.2.2" é utilizado para redirecionar para o localhost no emulador Android.
    private const val BASE_URL = "http://10.0.2.2:5000/"

    // Instância do Retrofit configurada com a URL base e o conversor Gson.
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // Define a URL base para as chamadas da API.
        .addConverterFactory(GsonConverterFactory.create()) // Adiciona suporte para conversão JSON usando Gson.
        .build() // Constrói a instância Retrofit.

    /**
     * Função genérica para construir serviços API.
     *
     * @param service A interface que define os endpoints da API.
     * @return Uma instância da interface fornecida, pronta para efetuar chamadas à API.
     */
    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service) // Cria uma implementação da interface especificada.
    }
}