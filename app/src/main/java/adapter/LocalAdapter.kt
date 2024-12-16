package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import api.Local
import com.example.trabalhopratico.R

/**
 * Adaptador responsável por apresentar a lista de locais num RecyclerView.
 * Mostra informações detalhadas como distrito, cidade, rua e código postal.
 */
class LocalAdapter(private val locais: List<Local>) : RecyclerView.Adapter<LocalAdapter.LocalViewHolder>() {

    /**
     * Criação de um novo ViewHolder para cada item do RecyclerView.
     * Este metodo é chamado quando é necessário criar a interface para um item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        // Inflação do layout item_local.xml para cada item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_local, parent, false)
        return LocalViewHolder(view)
    }

    /**
     * Associa os dados do local atual ao ViewHolder.
     * Este metodo é chamado quando o RecyclerView precisa atualizar os itens visíveis.
     */
    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        holder.bind(locais[position]) // Associa os dados do local ao ViewHolder
    }

    /**
     * Retorna o número total de locais na lista.
     */
    override fun getItemCount() = locais.size

    /**
     * ViewHolder responsável por apresentar os elementos visuais de um item individual da lista.
     */
    class LocalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Elementos visuais do layout item_local.xml
        private val textViewDistrito: TextView = itemView.findViewById(R.id.textViewDistrito)
        private val textViewCidade: TextView = itemView.findViewById(R.id.textViewCidade)
        private val textViewRua: TextView = itemView.findViewById(R.id.textViewRua)
        private val textViewCodigoPostal: TextView = itemView.findViewById(R.id.textViewCodigoPostal)

        /**
         * Metodo responsável por associar os dados do local aos elementos visuais.
         */
        fun bind(local: Local) {
            // Preenche os TextViews com as informações do local
            textViewDistrito.text = "Distrito: ${local.distrito}"
            textViewCidade.text = "Cidade: ${local.cidade}"
            textViewRua.text = "Rua: ${local.rua}"
            textViewCodigoPostal.text = "Código Postal: ${local.codigo_postal}"
        }
    }
}