package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import icesi.edu.co.mercatero_app.databinding.ListitemStoreOrderBinding
import io.grpc.LoadBalancer.Helper
import models.OrderModel
import utils.OrderStatus


class StoreOrdersAdapter(private val mClickListener: OnClickListener,
                         private val items: List<OrderModel>,val isStore:Boolean=true) :
    RecyclerView.Adapter<StoreOrdersAdapter.ItemHolder>() {


    inner class ItemHolder(binding: ListitemStoreOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ListitemStoreOrderBinding
        init {
            this.binding = binding
            binding.acceptBtn.setOnClickListener { mClickListener.onOrderItemClick(adapterPosition) }
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
        holder.binding.orderNo.text = "Pedido #${item.orderId}"
        holder.binding.itemsCount.text = item.items.toString()
        holder.binding.date.text = utils.Helper.formatDateTime(item.date.time)
        holder.binding.itemsCount.text = "$${item.total.toString()}"

        if (item.images.isNotEmpty()) {
            Glide.with(holder.itemView).load(item.images[0]).into(holder.binding.productImg)
        }

        val acceptBtnTxt=when(item.status){
            OrderStatus.PENDING.name-> "Aceptar"
            OrderStatus.PROCESSING.name-> "Pedido listo"
            else->"Entregar pedido"
        }

        holder.binding.acceptBtn.text = acceptBtnTxt
        if(isStore) {
            holder.binding.acceptBtn.text = acceptBtnTxt
            holder.binding.acceptBtn.visibility = View.VISIBLE
        }else{
            holder.binding.status.text = item.status
            holder.binding.status.visibility = View.VISIBLE
        }

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