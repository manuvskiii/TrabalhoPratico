package db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pm_project.entities.Comentario
import dao.ComentarioDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Representa a base de dados Room para armazenar os dados dos comentários.
 * Esta classe define as entidades associadas e fornece métodos para acessar o DAO.
 */
@Database(entities = [Comentario::class], version = 2, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    /**
     * Método abstrato que retorna o DAO para acessar os comentários.
     */
    abstract fun comentarioDao(): ComentarioDao

    /**
     * Classe interna para implementar callbacks de criação e abertura do banco de dados.
     * É utilizada para realizar operações adicionais, como pré-popular a base de dados.
     */
    private class WordDatabaseCallback(
        private val scope: CoroutineScope // Escopo para executar corrotinas
    ) : RoomDatabase.Callback() {

        /**
         * Callback chamado quando o banco de dados é criado pela primeira vez.
         * Aqui, dados iniciais podem ser inseridos na base de dados.
         */
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    // Exemplo de pré-população do banco de dados com comentários iniciais
                    var comentarioDao = Comentario(1, "Comentario", 1)
                    database.comentarioDao().insert(comentarioDao)

                    comentarioDao = Comentario(null, "Comentario2", 2)
                    database.comentarioDao().insert(comentarioDao)
                }
            }
        }

        /**
         * Callback chamado sempre que o banco de dados é aberto.
         * Pode ser usado para realizar operações adicionais, como validações ou sincronizações.
         */
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    // Exemplo de inserção de comentários ao abrir o banco de dados
                    var comentarioDao = Comentario(1, "Comentario", 1)
                    database.comentarioDao().insert(comentarioDao)

                    comentarioDao = Comentario(null, "Comentario2", 2)
                    database.comentarioDao().insert(comentarioDao)
                }
            }
        }
    }

    companion object {
        // Variável para armazenar a instância do banco de dados, garantindo que seja única
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        /**
         * Método para obter a instância do banco de dados.
         * Utiliza o padrão Singleton para garantir que apenas uma instância do banco de dados seja criada.
         *
         * @param context O contexto da aplicação.
         * @param scope O escopo para executar corrotinas.
         * @return A instância da base de dados.
         */
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "Comentarios" // Nome do banco de dados
                )
                    .addCallback(WordDatabaseCallback(scope)) // Adiciona callbacks
                    .fallbackToDestructiveMigration() // Define estratégia de migração destrutiva
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
