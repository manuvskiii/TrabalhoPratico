package db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.pm_project.entities.Comentario
import dao.ComentarioDao
import kotlinx.coroutines.flow.Flow

/**
 * Repositório responsável por abstrair a lógica de acesso aos dados
 * e fornecer uma interface limpa para o resto da aplicação.
 *
 * Ele interage diretamente com o DAO (`ComentarioDao`) e expõe os métodos
 * necessários para o acesso e manipulação de dados na tabela `Comentarios`.
 */
class WordRepository(private val wordDao: ComentarioDao) {

    /**
     * LiveData contendo todos os comentários armazenados no banco de dados.
     * Este LiveData permite observar os dados de forma reativa, atualizando
     * automaticamente a interface do utilizador quando houver mudanças.
     */
    val allWords: LiveData<List<Comentario>> = wordDao.getALLComentarios()

    /**
     * Insere um comentário no banco de dados.
     * Esta função é marcada como `suspend` porque deve ser executada
     * num contexto de corrotina para evitar operações na thread principal.
     *
     * A anotação `@WorkerThread` indica que esta função é executada numa thread
     * separada, ideal para operações intensivas como manipulação de dados.
     *
     * @param word O comentário a ser inserido.
     */
    @Suppress("RedundantSuspendModifier") // Supressão de aviso para o uso do modificador suspend
    @WorkerThread
    suspend fun insert(word: Comentario) {
        wordDao.insert(word)
    }

    /**
     * Obtém um único comentário com base no ID fornecido.
     * Retorna os dados como um fluxo (Flow), permitindo que mudanças sejam
     * observadas de forma reativa em tempo real.
     *
     * @param id O ID do comentário a ser consultado.
     * @return Um fluxo contendo o comentário correspondente.
     */
    fun getComentarioById(id: Int): Flow<Comentario> {
        return wordDao.getComentarioById(id)
    }
}
