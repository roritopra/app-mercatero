package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import icesi.edu.co.mercatero_app.databinding.ListitemStoreOrderBinding
import models.StoreOrderModel


class StoreOrdersAdapter(private val mClickListener: OnClickListener, private val items: List<StoreOrderModel>) :
    RecyclerView.Adapter<StoreOrdersAdapter.ItemHolder>() {


    inner class ItemHolder(binding: ListitemStoreOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ListitemStoreOrderBinding
        init {
            this.binding = binding
            itemView.setOnClickListener { mClickListener.onOrderItemClick(adapterPosition) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ListitemStoreOrderBinding.inflate(
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
        fun onOrderItemClick(position: Int)
    }
}