package adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import icesi.edu.co.mercatero_app.databinding.ListitemProductBinding
import icesi.edu.co.mercatero_app.databinding.ListitemProductVerticalBinding
import models.ProductModel
import utils.Constants.ITEM_HORIZONTAL
import utils.Constants.ITEM_VERTICAL


class ProductsAdapter(private val mClickListener:
                      OnClickListener, private val items: List<ProductModel>,
                      val viewType: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    inner class ItemHolder(binding: ListitemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ListitemProductBinding
        init {
            this.binding = binding
            itemView.setOnClickListener { mClickListener.onProductClick(adapterPosition) }
        }
    }

    inner class ItemVerticalHolder(binding: ListitemProductVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ListitemProductVerticalBinding
        init {
            this.binding = binding
            itemView.setOnClickListener { mClickListener.onProductClick(adapterPosition) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==ITEM_VERTICAL){
            return ItemVerticalHolder(
                ListitemProductVerticalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }else{
            return ItemHolder(
                ListitemProductBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item=items[position]

        if(viewType== ITEM_HORIZONTAL){
            val hHolder=holder as ItemHolder
            hHolder.binding.title.text = item.name.toString()
            hHolder.binding.store.text=item.store
            hHolder.binding.price.text="$${item.price.toString()}"
            hHolder.itemView.let { it1 ->
                if (item.images.isNotEmpty()) {
                    Glide.with(it1).load(item.images[0]).into(hHolder.binding.image)
                }
            }
        }else{
            val vHolder=holder as ItemVerticalHolder
            vHolder.binding.name.text = item.name.toString()
            vHolder.binding.price.text="$${item.price.toString()}"
            vHolder.itemView.let { it1 ->
                if (item.images.isNotEmpty()) {
                    Glide.with(it1).load(item.images[0]).into(vHolder.binding.image)
                }
            }
        }

    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }


    interface OnClickListener{
        fun onProductClick(position: Int)
    }
}