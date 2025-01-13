package adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import api.Curso
import com.example.trabalhopratico.NovoComentario
import com.example.trabalhopratico.R
import com.example.trabalhopratico.RoomTest

class CursoAdapter(
    private val cursos: List<Curso>,
    private val onCursoClick: (Curso) -> Unit
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
        private val comentariosButton: Button = itemView.findViewById(R.id.buttonVerComentarios)
        private val adicionarComentarioButton: Button = itemView.findViewById(R.id.buttonAdicionarComentario)

        fun bind(curso: Curso, onCursoClick: (Curso) -> Unit) {
            nomeTextView.text = curso.nome

            comentariosButton.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, RoomTest::class.java)
                intent.putExtra("CURSO_ID", curso.id_curso)
                context.startActivity(intent)
            }

            adicionarComentarioButton.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, NovoComentario::class.java)
                intent.putExtra("CURSO_ID", curso.id_curso)
                context.startActivity(intent)
            }
        }
    }
}
