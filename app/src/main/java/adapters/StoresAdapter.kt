package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import icesi.edu.co.mercatero_app.databinding.ListitemStoreBinding
import models.StoreModel


class StoresAdapter(private val mClickListener: OnClickListener, private val items: List<StoreModel>) :
    RecyclerView.Adapter<StoresAdapter.ItemHolder>() {


    inner class ItemHolder(binding: ListitemStoreBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ListitemStoreBinding
        init {
            this.binding = binding
            itemView.setOnClickListener { mClickListener.onStoreItemClick(adapterPosition) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ListitemStoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n", "MissingPermission")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item=items[position]
        holder.binding.title.text = item.name.toString()
        holder.binding.time.text=item.distance
        holder.itemView.let { it1 ->
            if (item.img.isNotEmpty()) {
                Glide.with(it1).load(item.img).into(holder.binding.image)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    interface OnClickListener{
        fun onStoreItemClick(position: Int)
    }
}