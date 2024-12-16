package adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import api.Estagio
import com.example.trabalhopratico.LocaisActivity
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
        private val buttonVerLocais: Button = itemView.findViewById(R.id.buttonVerLocais)

        fun bind(estagio: Estagio, onItemClick: (Estagio) -> Unit) {
            empresaTextView.text = estagio.empresa
            descricaoTextView.text = estagio.descricao

            buttonVerLocais.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, LocaisActivity::class.java)
                intent.putExtra("DISTRITO", estagio.distrito) // Passar o distrito
                intent.putExtra("CIDADE", estagio.cidade)     // Passar a cidade
                context.startActivity(intent)
            }

            // Clique normal no item
            itemView.setOnClickListener { onItemClick(estagio) }
        }
    }
}