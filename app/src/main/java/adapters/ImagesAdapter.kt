package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import icesi.edu.co.mercatero_app.databinding.ListItemImageBinding


class ImagesAdapter(private val items: List<String>) :
    RecyclerView.Adapter<ImagesAdapter.ItemHolder>() {


    inner class ItemHolder(binding: ListItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ListItemImageBinding
        init {
            this.binding = binding
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ListItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n", "MissingPermission")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item=items[position]
        Glide.with(holder.itemView).load(item).into(holder.binding.imageView)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}