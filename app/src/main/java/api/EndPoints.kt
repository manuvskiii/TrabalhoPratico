package api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

    interface EndPoints {
        // Página inicial (opcional)
        @GET("/")
        fun getApiStatus(): Call<Map<String, String>>

        // Listar todos os estágios
        @GET("api/estagios")
        fun getEstagios(): Call<List<Estagio>>

        // Listar todos os cursos
        @GET("api/cursos")
        fun getCursos(): Call<List<Curso>>

        // Listar todos os locais
        @GET("api/locais")
        fun getLocais(): Call<List<Local>>

        // Listar todas as escolas
        @GET("api/escolas")
        fun getEscolas(): Call<List<Escola>>

        // Detalhes de um estágio específico
        @GET("api/estagios/{id}")
        fun getEstagioById(@Path("id") id: Int): Call<Estagio>

        // Detalhes de um curso específico
        @GET("api/cursos/{id}")
        fun getCursoById(@Path("id") id: Int): Call<Curso>

        // Detalhes de um local específico
        @GET("api/locais/{id}")
        fun getLocalById(@Path("id") id: Int): Call<Local>

        // Detalhes de uma escola específica
        @GET("api/escolas/{id}")
        fun getEscolaById(@Path("id") id: Int): Call<Escola>

        @GET("api/cursos/{id_escola}")
        fun getCursosByEscola(@Path("id_escola") escolaId: Int): Call<List<Curso>>
    }
