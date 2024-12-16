package api

/**
 * Modelo para a tabela "escolas".
 * Representa uma escola no sistema.
 *
 * @property id_escola Identificador único da escola.
 * @property nome Nome da escola.
 */
data class Escola(
    val id_escola: Int,
    val nome: String
)

/**
 * Modelo para a tabela "cursos".
 * Representa um curso associado a uma escola.
 *
 * @property id_curso Identificador único do curso.
 * @property nome Nome do curso.
 * @property id_escola Identificador da escola à qual o curso está associado.
 */
data class Curso(
    val id_curso: Int,
    val nome: String,
    val id_escola: Int
)

/**
 * Modelo para a tabela "locais".
 * Representa um local onde pode estar localizado um estágio.
 *
 * @property id_local Identificador único do local.
 * @property distrito Distrito onde o local se encontra.
 * @property cidade Cidade onde o local se encontra.
 * @property rua Rua do local.
 * @property codigo_postal Código postal do local.
 */
data class Local(
    val id_local: Int,
    val distrito: String,
    val cidade: String,
    val rua: String,
    val codigo_postal: String
)

/**
 * Modelo para a tabela "estágios".
 * Representa um estágio oferecido por uma empresa e associado a um curso.
 *
 * @property id_estagio Identificador único do estágio.
 * @property empresa Nome da empresa que oferece o estágio.
 * @property curso Nome do curso associado ao estágio.
 * @property distrito Distrito onde o estágio está localizado.
 * @property rua Rua onde o estágio está localizado.
 * @property codigo_postal Código postal do local do estágio.
 * @property cidade Cidade onde o estágio está localizado.
 * @property descricao Descrição do estágio (ex.: atividades ou requisitos).
 * @property id_curso Identificador do curso relacionado ao estágio.
 * @property id_local Identificador do local onde o estágio está localizado.
 */
data class Estagio(
    val id_estagio: Int,
    val empresa: String,
    val curso: String,
    val distrito: String,
    val rua: String,
    val codigo_postal: String,
    val cidade: String,
    val descricao: String,
    val id_curso: Int,
    val id_local: Int
)