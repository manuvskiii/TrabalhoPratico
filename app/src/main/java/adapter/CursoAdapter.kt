package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import api.Curso
import com.example.trabalhopratico.R

class CursoAdapter(
    private val cursos: List<Curso>,
    private val onCursoClick: (Curso) -> Unit // Callback para clique
) : RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cursos, parent, false)
        return CursoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        holder.bind(cursos[position], onCursoClick)
    }

    override fun getItemCount() = cursos.size

    class CursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomeTextView: TextView = itemView.findViewById(R.id.nomeCursoTextView)

        fun bind(curso: Curso, onCursoClick: (Curso) -> Unit) {
            nomeTextView.text = curso.nome
            itemView.setOnClickListener { onCursoClick(curso) }
        }
    }
}