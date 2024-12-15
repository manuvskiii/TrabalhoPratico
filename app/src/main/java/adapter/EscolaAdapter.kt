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
class EscolaAdapter(private val escolas: List<Escola>) : RecyclerView.Adapter<EscolaAdapter.EscolaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EscolaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_escola, parent, false)
        return EscolaViewHolder(view)
    }

    override fun onBindViewHolder(holder: EscolaViewHolder, position: Int) {
        val escola = escolas[position]
        holder.bind(escola)

        // Configurar o click listener para abrir CursosActivity
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CursosActivity::class.java)
            intent.putExtra("ESCOLA_ID", escola.id_escola) // Passa o ID da escola
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = escolas.size

    class EscolaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomeTextView: TextView = itemView.findViewById(R.id.nomeTextView)

        fun bind(escola: Escola) {
            nomeTextView.text = escola.nome
        }
    }
}
