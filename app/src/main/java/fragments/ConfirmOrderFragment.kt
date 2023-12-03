package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentConfirmOrderBinding
import icesi.edu.co.mercatero_app.databinding.FragmentStoreDetailBinding
import models.OrderModel
import models.ProductModel
import utils.Constants.COLLECTION_ORDERS
import utils.Constants.KEY_ORDER
import utils.OrderStatus
import viewmodels.SharedViewModel
import java.util.Date


class ConfirmOrderFragment : BaseFragment() {

    lateinit var binding: FragmentConfirmOrderBinding
    private val sharedViewModel: SharedViewModel by viewModels({requireActivity()})
    lateinit var db: FirebaseFirestore

    val productsList= mutableListOf<ProductModel>()
    var productsByStores= mapOf<String,List<ProductModel>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirm_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConfirmOrderBinding.bind(view)

        productsList.addAll(sharedViewModel.productsList)

        productsByStores=productsList.groupBy { it.storeId }

        initViews()

    }

    private fun initViews(){
        binding.subtotal.text=sharedViewModel.total.toString()
        binding.shipment.text=sharedViewModel.shipment.toString()
        binding.discount.text=sharedViewModel.discount.toString()
        val total=(sharedViewModel.total+sharedViewModel.shipment)-sharedViewModel.discount
        binding.total.text=total.toString()
        binding.cash.text=total.toString()

        binding.backBtn.setOnClickListener { findNavController().navigateUp() }
        binding.confirBtn.setOnClickListener {

            var total=0.0
            var totalCounts=0
            productsByStores.values.forEachIndexed {index,products->
                val storeId=products[0].storeId
                products.forEach {
                    total += it.price * it.counts
                    totalCounts+=it.counts
                }

                val order=OrderModel()
                order.total=total
                order.date= Date()
                order.items=totalCounts
                order.status=OrderStatus.PENDING.name
                order.storeId=storeId

                val document=db.collection(COLLECTION_ORDERS).document()
                order.orderId=document.id
                document.set(order).addOnSuccessListener {
                    if(index==productsByStores.size-1){
                        navigate()
                    }

                }

            }




        }

    }

    private fun navigate(){
        findNavController().navigate(ConfirmOrderFragmentDirections
            .navToCompleteMsg(KEY_ORDER))
    }

}

