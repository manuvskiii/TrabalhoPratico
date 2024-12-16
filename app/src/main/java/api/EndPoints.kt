package api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface EndPoints:
 * Contém todas as definições de endpoints para comunicação com a API REST.
 * Utiliza Retrofit para fazer as requisições HTTP.
 */
interface EndPoints {

    /**
     * Endpoint para verificar o estado da API.
     * Este endpoint retorna um mapa com o status da API.
     *
     * Metodo HTTP: GET
     * URL: "/"
     */
    @GET("/")
    fun getApiStatus(): Call<Map<String, String>>

    /**
     * Endpoint para obter todos os estágios disponíveis.
     * Retorna uma lista de objetos do tipo Estagio.
     *
     * Metodo HTTP: GET
     * URL: "api/estagios"
     */
    @GET("api/estagios")
    fun getEstagios(): Call<List<Estagio>>

    /**
     * Endpoint para obter todos os cursos disponíveis.
     * Retorna uma lista de objetos do tipo Curso.
     *
     * Metodo HTTP: GET
     * URL: "api/cursos"
     */
    @GET("api/cursos")
    fun getCursos(): Call<List<Curso>>

    /**
     * Endpoint para obter todos os locais.
     * Retorna uma lista de objetos do tipo Local.
     *
     * Metodo HTTP: GET
     * URL: "api/locais"
     */
    @GET("api/locais")
    fun getLocais(): Call<List<Local>>

    /**
     * Endpoint para obter todas as escolas.
     * Retorna uma lista de objetos do tipo Escola.
     *
     * Metodo HTTP: GET
     * URL: "api/escolas"
     */
    @GET("api/escolas")
    fun getEscolas(): Call<List<Escola>>

    /**
     * Endpoint para obter os detalhes de um estágio específico.
     * Recebe o ID do estágio como parâmetro.
     *
     * Metodo HTTP: GET
     * URL: "api/estagios/{id}"
     * Parâmetro: id - Identificador do estágio.
     */
    @GET("api/estagios/{id}")
    fun getEstagioById(@Path("id") id: Int): Call<Estagio>

    /**
     * Endpoint para obter os detalhes de um curso específico.
     * Recebe o ID do curso como parâmetro.
     *
     * Metodo HTTP: GET
     * URL: "api/cursos/{id}"
     * Parâmetro: id - Identificador do curso.
     */
    @GET("api/cursos/{id}")
    fun getCursoById(@Path("id") id: Int): Call<Curso>

    /**
     * Endpoint para obter os detalhes de um local específico.
     * Recebe o ID do local como parâmetro.
     *
     * Metodo HTTP: GET
     * URL: "api/locais/{id}"
     * Parâmetro: id - Identificador do local.
     */
    @GET("api/locais/{id}")
    fun getLocalById(@Path("id") id: Int): Call<Local>

    /**
     * Endpoint para obter os detalhes de uma escola específica.
     * Recebe o ID da escola como parâmetro.
     *
     * Metodo HTTP: GET
     * URL: "api/escolas/{id}"
     * Parâmetro: id - Identificador da escola.
     */
    @GET("api/escolas/{id}")
    fun getEscolaById(@Path("id") id: Int): Call<Escola>

    /**
     * Endpoint para obter os cursos associados a uma escola específica.
     * Recebe o ID da escola como parâmetro.
     *
     * Metodo HTTP: GET
     * URL: "api/cursos/{id_escola}"
     * Parâmetro: id_escola - Identificador da escola.
     */
    @GET("api/cursos/{id_escola}")
    fun getCursosByEscola(@Path("id_escola") escolaId: Int): Call<List<Curso>>
}