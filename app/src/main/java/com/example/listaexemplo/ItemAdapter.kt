package com.example.listaexemplo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val listaItem: List<DadosLista>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // associar as variaveis aos campos da vista
        val primeiraTextView: TextView = itemView.findViewById(R.id.primeiraTextView)
        val segundaTextView: TextView = itemView.findViewById(R.id.segundaTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //cria nova vista com o layout personalizado
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    // Substitui o conteúdo da vista com os dados inseridos
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaItem[position]
        // define os valores das TextViews com os dados da posicao atual da lista
        holder.primeiraTextView.text = item.nome
        holder.segundaTextView.text = item.idade.toString()
    }

    // retorna o tamanho da lista
    override fun getItemCount(): Int {
        return listaItem.size
    }
}