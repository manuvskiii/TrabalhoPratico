package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import api.Curso
import com.example.trabalhopratico.R

/**
 * Adaptador responsável por apresentar a lista de cursos num RecyclerView.
 * Utiliza um callback para detetar cliques em itens individuais da lista.
 */
class CursoAdapter(
    private val cursos: List<Curso>, // Lista de cursos a ser apresentada
    private val onCursoClick: (Curso) -> Unit // Callback para ação ao clicar num item da lista
) : RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {

    /**
     * Criação de uma nova ViewHolder (elemento visual individual da lista).
     * É chamado sempre que o RecyclerView precisa de criar um novo item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        // Inflate do layout item_cursos.xml para cada item da lista
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cursos, parent, false)
        return CursoViewHolder(view)
    }

    /**
     * Metodo responsável por ligar (bind) os dados do curso à interface visual (ViewHolder).
     * É chamado sempre que o RecyclerView precisa de mostrar um item na posição especificada.
     */
    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        // Chama a função bind para associar os dados ao ViewHolder
        holder.bind(cursos[position], onCursoClick)
    }

    /**
     * Retorna o tamanho total da lista de cursos.
     */
    override fun getItemCount() = cursos.size

    /**
     * ViewHolder responsável por representar cada item da lista (layout individual).
     */
    class CursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referência ao TextView no layout item_cursos.xml
        private val nomeTextView: TextView = itemView.findViewById(R.id.nomeCursoTextView)

        /**
         * Função responsável por associar os dados do curso ao elemento visual.
         * Configura ainda um clique no item para executar o callback fornecido.
         */
        fun bind(curso: Curso, onCursoClick: (Curso) -> Unit) {
            // Define o nome do curso no TextView
            nomeTextView.text = curso.nome

            // Configura o evento de clique no item da lista
            itemView.setOnClickListener { onCursoClick(curso) }
        }
    }
}