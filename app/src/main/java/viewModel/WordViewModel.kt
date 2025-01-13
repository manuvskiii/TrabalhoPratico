package viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pm_project.entities.Comentario
import db.WordRepository
import kotlinx.coroutines.launch

/**
 * ViewModel responsável por gerir a lógica de dados e interagir com o repositório.
 * Ele fornece dados ao UI (interface do utilizador) e mantém o estado durante mudanças de configuração.
 */
class WordViewModel(private val repository: WordRepository) : ViewModel() {

    // Lista de todos os comentários, observável pela UI através de LiveData.
    val allComentarios: LiveData<List<Comentario>> = repository.allWords

    /**
     * Insere um novo comentário no banco de dados usando uma coroutine.
     * O uso de `viewModelScope` assegura que a operação seja realizada no contexto de uma coroutine.
     */
    fun insert(comentario: Comentario) = viewModelScope.launch {
        repository.insert(comentario)
    }

    /**
     * Obtém um comentário específico pelo seu ID.
     * Retorna um objeto LiveData que pode ser observado pela UI.
     *
     * @param id ID do comentário a ser obtido.
     */
    fun getComentarioById(id: Int): LiveData<Comentario> {
        return repository.getComentarioById(id).asLiveData()
    }
}

/**
 * Fábrica de ViewModels personalizada para criar instâncias de WordViewModel.
 * Necessária porque WordViewModel possui um construtor com parâmetros.
 */
class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {

    /**
     * Método responsável por criar a instância de ViewModel.
     *
     * @param modelClass A classe da ViewModel solicitada.
     * @return Uma instância de WordViewModel.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Verifica se a classe solicitada é do tipo WordViewModel.
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }

        // Lança uma exceção caso a classe solicitada não seja WordViewModel.
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
