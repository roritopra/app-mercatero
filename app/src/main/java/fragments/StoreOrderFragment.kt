package fragments


import adapters.StoreOrdersAdapter
import adapters.StoresAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentStoreOrdersBinding
import models.StoreModel
import models.StoreOrderModel


class StoreOrderFragment : BaseFragment(),StoreOrdersAdapter.OnClickListener {

    lateinit var binding: FragmentStoreOrdersBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStoreOrdersBinding.bind(view)


        showOrders()


    }


    private fun showOrders(){

        val list= mutableListOf<StoreOrderModel>()
        for(i in 1..5){
            val order= StoreOrderModel()
            list.add(order)
        }

        val adapter= StoreOrdersAdapter(this,list)
        binding.ordersRV.adapter=adapter
    }


    private fun navigate(){
        //findNavController().navigate(FragmentUserTypeDirections.navToRegister())
    }

    override fun onOrderItemClick(position: Int) {


    }

}