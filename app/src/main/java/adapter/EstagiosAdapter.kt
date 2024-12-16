package adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import api.Estagio
import com.example.trabalhopratico.MapsActivity
import com.example.trabalhopratico.LocaisActivity
import com.example.trabalhopratico.R

/**
 * Adaptador responsável por apresentar a lista de estágios num RecyclerView.
 * Permite abrir um mapa com a cidade ou visualizar detalhes do local através de duas ações distintas.
 */
class EstagioAdapter(
    private val estagios: List<Estagio>,            // Lista de estágios a apresentar
    private val onItemClick: (Estagio) -> Unit      // Função callback para eventos de clique
) : RecyclerView.Adapter<EstagioAdapter.EstagioViewHolder>() {

    /**
     * Criação de um novo ViewHolder para cada item do RecyclerView.
     * Este metodo é chamado sempre que é necessário criar a interface para um item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstagioViewHolder {
        // Inflação do layout item_estagio.xml para cada item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_estagio, parent, false)
        return EstagioViewHolder(view)
    }

    /**
     * Associa os dados do estágio atual ao ViewHolder.
     * Este metodo é chamado quando o RecyclerView precisa atualizar os itens visíveis.
     */
    override fun onBindViewHolder(holder: EstagioViewHolder, position: Int) {
        holder.bind(estagios[position], onItemClick)
    }

    /**
     * Retorna o número total de estágios na lista.
     */
    override fun getItemCount() = estagios.size

    /**
     * ViewHolder responsável por apresentar os elementos visuais de um item individual da lista.
     */
    class EstagioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Elementos visuais do layout item_estagio.xml
        private val empresaTextView: TextView = itemView.findViewById(R.id.empresaTextView)
        private val descricaoTextView: TextView = itemView.findViewById(R.id.descricaoTextView)
        private val buttonVerLocais: Button = itemView.findViewById(R.id.buttonVerLocais)

        /**
         * Metodo responsável por associar os dados do estágio aos elementos visuais.
         * Define também os eventos de clique.
         */
        fun bind(estagio: Estagio, onItemClick: (Estagio) -> Unit) {
            // Define o nome da empresa e a descrição no layout
            empresaTextView.text = estagio.empresa
            descricaoTextView.text = estagio.descricao

            /**
             * Evento de clique no nome da empresa:
             * - Abre a atividade MapsActivity com a cidade associada ao estágio.
             * - Permite mostrar a localização no mapa.
             */
            empresaTextView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, MapsActivity::class.java)
                intent.putExtra("CIDADE", estagio.cidade) // Passa a cidade como parâmetro
                context.startActivity(intent)            // Inicia a MapsActivity
            }

            /**
             * Evento de clique no botão "Ver Locais":
             * - Abre a atividade LocaisActivity com os detalhes do local (distrito, cidade, rua e código postal).
             */
            buttonVerLocais.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, LocaisActivity::class.java)
                intent.putExtra("DISTRITO", estagio.distrito)
                intent.putExtra("CIDADE", estagio.cidade)
                intent.putExtra("RUA", estagio.rua)
                intent.putExtra("CODIGO_POSTAL", estagio.codigo_postal)
                context.startActivity(intent) // Inicia a LocaisActivity
            }

            /**
             * Evento de clique normal no item:
             * - Permite usar o callback definido pelo utilizador.
             * - Pode ser utilizado para outras ações adicionais.
             */
            itemView.setOnClickListener { onItemClick(estagio) }
        }
    }
}