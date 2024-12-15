package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import api.Estagio
import com.example.trabalhopratico.R

class EstagioAdapter(
    private val estagios: List<Estagio>,
    private val onItemClick: (Estagio) -> Unit // Função de clique
) : RecyclerView.Adapter<EstagioAdapter.EstagioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstagioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_estagio, parent, false)
        return EstagioViewHolder(view)
    }

    override fun onBindViewHolder(holder: EstagioViewHolder, position: Int) {
        holder.bind(estagios[position], onItemClick)
    }

    override fun getItemCount() = estagios.size

    class EstagioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val empresaTextView: TextView = itemView.findViewById(R.id.empresaTextView)
        private val descricaoTextView: TextView = itemView.findViewById(R.id.descricaoTextView)

        fun bind(estagio: Estagio, onItemClick: (Estagio) -> Unit) {
            empresaTextView.text = estagio.empresa
            descricaoTextView.text = estagio.descricao
            itemView.setOnClickListener { onItemClick(estagio) } // Configuração do click
        }
    }
}