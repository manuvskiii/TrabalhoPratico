package adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import api.Escola
import com.example.trabalhopratico.CursosActivity
import com.example.trabalhopratico.R

/**
 * Adaptador responsável por apresentar a lista de escolas num RecyclerView.
 * Permite a navegação para a atividade CursosActivity ao clicar numa escola específica.
 */
class EscolaAdapter(private val escolas: List<Escola>) : RecyclerView.Adapter<EscolaAdapter.EscolaViewHolder>() {

    /**
     * Criação de uma nova ViewHolder para cada item do RecyclerView.
     * É chamado sempre que é necessário criar uma nova instância do layout do item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EscolaViewHolder {
        // Inflação do layout item_escola.xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_escola, parent, false)
        return EscolaViewHolder(view)
    }

    /**
     * Metodo que associa (bind) os dados da escola ao ViewHolder.
     * Configura também o evento de clique no item.
     */
    override fun onBindViewHolder(holder: EscolaViewHolder, position: Int) {
        // Obtém a escola atual com base na posição
        val escola = escolas[position]

        // Associa os dados ao ViewHolder
        holder.bind(escola)

        // Configura o evento de clique para abrir a CursosActivity
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CursosActivity::class.java)
            intent.putExtra("ESCOLA_ID", escola.id_escola) // Passa o ID da escola para a próxima atividade
            context.startActivity(intent) // Inicia a CursosActivity
        }
    }

    /**
     * Retorna o tamanho da lista de escolas.
     */
    override fun getItemCount() = escolas.size

    /**
     * ViewHolder responsável por apresentar os elementos visuais de um item individual da lista.
     */
    class EscolaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referência ao TextView definido no layout item_escola.xml
        private val nomeTextView: TextView = itemView.findViewById(R.id.nomeTextView)

        /**
         * Metodo que define o nome da escola no TextView.
         */
        fun bind(escola: Escola) {
            nomeTextView.text = escola.nome // Mostra o nome da escola no layout
        }
    }
}