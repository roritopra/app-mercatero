package fragments


import adapters.StoreOrdersAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentStoreOrdersBinding
import models.OrderModel
import utils.AppPreferences.getUserId
import utils.Constants
import utils.Constants.COLLECTION_ORDERS
import utils.Constants.KEY_ORDER
import utils.Constants.KEY_TAB_POSITION
import utils.OrderStatus


class StoreOrderFragment : BaseFragment(),StoreOrdersAdapter.OnClickListener {

    lateinit var binding: FragmentStoreOrdersBinding
    lateinit var db: FirebaseFirestore
    val ordersList= mutableListOf<OrderModel>()
    lateinit var status: OrderStatus
    lateinit var adapter: StoreOrdersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStoreOrdersBinding.bind(view)
        db= FirebaseFirestore.getInstance()

        status=when(arguments?.getInt(KEY_TAB_POSITION)){
            0->OrderStatus.PENDING
            1->OrderStatus.PROCESSING
            else->OrderStatus.READY
        }




    }

    override fun onResume() {
        super.onResume()
        context?.getUserId()?.let { getOrders(it,status.Name) }
    }


    private fun getOrders(id:String,status:String){

        binding.progressBar.visibility=View.VISIBLE
        ordersList.clear()
        var query: Query = db.collection(Constants.COLLECTION_ORDERS)

        query = query.whereEqualTo("storeId",id)
        query = query.whereEqualTo("status",status)
        query.get().addOnSuccessListener {
            it.documents.forEach {
                val order = it.toObject(OrderModel::class.java)
                if (order != null) {
                    ordersList.add(order)
                }
            }

            adapter=StoreOrdersAdapter(this,ordersList)
            binding.ordersRV.adapter=adapter
            binding.progressBar.visibility=View.GONE

        }.addOnFailureListener {
            binding.progressBar.visibility=View.GONE
            Log.v("Profile",it.message.toString())
        }
    }

    override fun onOrderItemClick(position: Int) {

        val orderId=ordersList[position].orderId
        val newStatus=when(status){
            OrderStatus.PENDING->OrderStatus.PROCESSING
            OrderStatus.PROCESSING->OrderStatus.READY
            else->null
        }

        if(newStatus!=null) {
            val updates: MutableMap<String, Any> = HashMap()
            updates["status"] = newStatus.Name
            db.collection(COLLECTION_ORDERS).document(orderId).update(updates).addOnSuccessListener {
                ordersList.removeAt(position)
                adapter.notifyItemRemoved(position)

            }
        }
    }


}