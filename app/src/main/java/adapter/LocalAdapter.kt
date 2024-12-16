package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import api.Local
import com.example.trabalhopratico.R

class LocalAdapter(private val locais: List<Local>) : RecyclerView.Adapter<LocalAdapter.LocalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_local, parent, false)
        return LocalViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        holder.bind(locais[position])
    }

    override fun getItemCount() = locais.size

    class LocalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewDistrito: TextView = itemView.findViewById(R.id.textViewDistrito)
        private val textViewCidade: TextView = itemView.findViewById(R.id.textViewCidade)

        fun bind(local: Local) {
            textViewDistrito.text = "Distrito: ${local.distrito}"
            textViewCidade.text = "Cidade: ${local.cidade}"
        }
    }
}