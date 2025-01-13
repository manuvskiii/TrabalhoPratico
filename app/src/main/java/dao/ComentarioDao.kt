package dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pm_project.entities.Comentario
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) responsável por fornecer métodos para
 * interagir com a tabela "Comentarios" no banco de dados Room.
 */
@Dao
interface ComentarioDao {

    /**
     * Consulta um único comentário com base no ID fornecido.
     * Retorna os dados como um fluxo (Flow), permitindo observar mudanças
     * nos dados de forma reativa.
     *
     * @param id O ID do comentário a ser consultado.
     * @return Um fluxo reativo contendo o comentário correspondente.
     */
    @Query("SELECT * FROM Comentarios WHERE id = :id")
    fun getComentarioById(id: Int): Flow<Comentario>

    /**
     * Consulta todos os comentários da tabela "Comentarios".
     * Retorna os dados como um LiveData, permitindo que sejam observados
     * de forma reativa na interface do utilizador.
     *
     * @return Uma lista reativa de todos os comentários.
     */
    @Query("SELECT * FROM comentarios")
    fun getALLComentarios(): LiveData<List<Comentario>>

    /**
     * Apaga todos os dados da tabela "Comentarios".
     * Esta operação é realizada de forma assíncrona devido ao uso de `suspend`.
     */
    @Query("DELETE FROM Comentarios")
    suspend fun deleteAll()

    /**
     * Insere um novo comentário na tabela "Comentarios".
     * Se já existir um comentário com o mesmo ID, ele será substituído
     * devido à estratégia `OnConflictStrategy.REPLACE`.
     *
     * @param comentario O comentário a ser inserido ou substituído.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comentario: Comentario)
}
