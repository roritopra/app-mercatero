package fragments


import adapters.StoreOrdersAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentMyOrderBinding
import models.OrderModel
import utils.AppPreferences.getUserId
import utils.Constants


class MyOrdersFragment : BaseFragment(),StoreOrdersAdapter.OnClickListener {

    lateinit var binding: FragmentMyOrderBinding
    lateinit var db: FirebaseFirestore
    val ordersList= mutableListOf<OrderModel>()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyOrderBinding.bind(view)
        db= FirebaseFirestore.getInstance()


    }


    override fun onResume() {
        super.onResume()
        context?.getUserId()?.let { getOrders(it) }
    }

    private fun getOrders(id:String){

        binding.progressBar.visibility=View.VISIBLE
        ordersList.clear()
        var query: Query = db.collection(Constants.COLLECTION_ORDERS)

        query = query.whereEqualTo("userId",id)
        query.get().addOnSuccessListener {
            it.documents.forEach {
                val order = it.toObject(OrderModel::class.java)
                if (order != null) {
                    ordersList.add(order)
                }
            }

            val adapter= StoreOrdersAdapter(this,ordersList, isStore = false)
            binding.ordersRV.adapter=adapter
            binding.progressBar.visibility=View.GONE

        }.addOnFailureListener {
            binding.progressBar.visibility=View.GONE

        }
    }

    override fun onOrderItemClick(position: Int) {

    }

}
