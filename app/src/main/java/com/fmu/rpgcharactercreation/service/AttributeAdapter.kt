package com.fmu.rpgcharactercreation.service

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fmu.rpgcharactercreation.model.Attribute

class AttributeAdapter(private val attributes: List<Attribute>) : RecyclerView.Adapter<AttributeAdapter.AttributeViewHolder>() {

    inner class AttributeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvAttributeName)
        val value: EditText = view.findViewById(R.id.etAttributeValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttributeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_attribute, parent, false)
        return AttributeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AttributeViewHolder, position: Int) {
        val attribute = attributes[position]
        holder.name.text = attribute.name
        holder.value.setText(attribute.value.toString())
    }

    override fun getItemCount(): Int = attributes.size
}
