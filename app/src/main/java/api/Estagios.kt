package api

// Modelo para a tabela "escolas"
data class Escola(
    val id_escola: Int,
    val nome: String
)

// Modelo para a tabela "cursos"
data class Curso(
    val id_curso: Int,
    val nome: String,
    val id_escola: Int
)

// Modelo para a tabela "locais"
data class Local(
    val id_local: Int,
    val distrito: String,
    val cidade: String
)

// Modelo para a tabela "estagios"
data class Estagio(
    val id_estagio: Int,
    val empresa: String,
    val curso:String,
    val distrito: String,
    val descricao: String,
    val id_curso: Int,
    val id_local: Int
)