package com.example.trabalhopratico

import android.app.Application
import db.WordRepository
import db.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * Classe `RoomAPP` que estende a classe `Application`.
 * Responsável por inicializar e fornecer dependências globais para o app, como banco de dados e repositório.
 */
class RoomAPP : Application() {

    // CoroutineScope que supervisiona todas as coroutines do banco de dados.
    // `SupervisorJob` permite que uma falha numa coroutine não afete outras coroutines do mesmo escopo.
    val applicationScope = CoroutineScope(SupervisorJob())

    // Propriedade que inicializa o banco de dados apenas quando necessário (lazy initialization).
    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }

    // Propriedade que inicializa o repositório apenas quando necessário.
    // O repositório utiliza o DAO fornecido pelo banco de dados.
    val repository by lazy { WordRepository(database.comentarioDao()) }
}
