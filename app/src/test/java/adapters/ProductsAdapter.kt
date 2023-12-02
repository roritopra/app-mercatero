package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import icesi.edu.co.mercatero_app.databinding.ListitemProductBinding
import models.ProductModel


class ProductsAdapter(private val mClickListener: OnClickListener, private val items: List<ProductModel>) :
    RecyclerView.Adapter<ProductsAdapter.ItemHolder>() {


    inner class ItemHolder(binding: ListitemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ListitemProductBinding
        init {
            this.binding = binding
            itemView.setOnClickListener { mClickListener.onProductClick(adapterPosition) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ListitemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n", "MissingPermission")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item=items[position]
        //holder.binding.title.text = item.name.toString()
        //holder.binding.icon.setImageResource(item.icon)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    interface OnClickListener{
        fun onProductClick(position: Int)
    }
}