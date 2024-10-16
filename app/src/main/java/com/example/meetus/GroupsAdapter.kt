package com.example.meetus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroupsAdapter(private val groups: List<String>) : RecyclerView.Adapter<GroupsAdapter.GroupViewHolder>() {

    // ViewHolder do trzymania widoku pojedynczego kafelka
    class GroupViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val groupName: TextView = view.findViewById(R.id.group_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        // Inflatujemy layout kafelka grupy
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return GroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        // Ustawiamy nazwę grupy w kafelku
        holder.groupName.text = groups[position]
    }

    override fun getItemCount(): Int {
        // Zwracamy liczbę grup
        return groups.size
    }
}
