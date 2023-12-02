package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import icesi.edu.co.mercatero_app.databinding.ListitemCatgsBinding
import models.CatgsModel


class CatgsAdapter(private val mClickListener: OnClickListener, private val items: List<CatgsModel>) :
    RecyclerView.Adapter<CatgsAdapter.ItemHolder>() {


    inner class ItemHolder(binding: ListitemCatgsBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ListitemCatgsBinding
        init {
            this.binding = binding
            itemView.setOnClickListener { mClickListener.onClick(adapterPosition) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ListitemCatgsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n", "MissingPermission")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item=items[position]
        holder.binding.title.text = item.name.toString()
        holder.binding.icon.setImageResource(item.icon)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    interface OnClickListener{
        fun onClick(position: Int)
    }
}