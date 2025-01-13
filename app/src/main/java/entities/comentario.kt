package com.example.pm_project.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Classe de entidade que representa a tabela "Comentarios" no banco de dados.
 * Cada instância desta classe será um registro (linha) na tabela.
 *
 * @param id O identificador único (Primary Key) do comentário, gerado automaticamente.
 * @param nome O texto do comentário.
 * @param rating A classificação (Rating) associada ao comentário.
 */
@Entity(tableName = "Comentarios") // Define esta classe como uma tabela chamada "Comentarios".
class Comentario(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null, // Chave primária gerada automaticamente pelo banco de dados.

    @ColumnInfo(name = "Comentario")
    val nome: String, // Coluna chamada "Comentario", que armazena o texto do comentário.

    @ColumnInfo(name = "Rating")
    val rating: Int // Coluna chamada "Rating", que armazena a classificação do comentário.
)
