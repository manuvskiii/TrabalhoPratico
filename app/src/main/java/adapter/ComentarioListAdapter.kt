package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pm_project.entities.Comentario
import com.example.trabalhopratico.R

class ComentarioListAdapater : ListAdapter<Comentario, ComentarioListAdapater.ComentarioViewHolder>(
    WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioViewHolder {
        return ComentarioViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ComentarioViewHolder, position: Int) {
        val currentComentario = getItem(position)
        holder.bind(currentComentario)
    }

    class ComentarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val comentarioItemView: TextView = itemView.findViewById(R.id.room_id)

        fun bind(item: Comentario?) {
            comentarioItemView.text = item!!.nome.toString()
        }

        companion object {
            fun create(parent: ViewGroup): ComentarioViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.roomcomentario, parent, false)
                return ComentarioViewHolder(view)
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Comentario>() {
        override fun areItemsTheSame(oldItem: Comentario, newItem: Comentario): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: Comentario, newItem: Comentario): Boolean {
            return oldItem.nome == newItem.nome
        }
    }
}
