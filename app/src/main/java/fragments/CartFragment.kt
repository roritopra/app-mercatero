package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentStoreDetailBinding
import icesi.edu.co.mercatero_app.databinding.FragmentCartBinding
import models.ProductModel
import viewmodels.SharedViewModel

class CartFragment : BaseFragment() {

    lateinit var binding: FragmentCartBinding
    private val sharedViewModel: SharedViewModel by viewModels({requireActivity()})

    val productsList= mutableListOf<ProductModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)

        binding.backBtn.setOnClickListener { findNavController().navigateUp() }
        binding.confirmBtn.setOnClickListener { navigate() }

        productsList.clear()
        productsList.addAll(sharedViewModel.productsList)
        calculateOrderSum()


    }

    private fun calculateOrderSum(){
        productsList.forEach {
            sharedViewModel.total += it.price * it.counts
            sharedViewModel.totalCounts+=it.counts
        }

        binding.subtotal.text=sharedViewModel.total.toString()
        binding.shipment.text=sharedViewModel.shipment.toString()
        binding.discount.text=sharedViewModel.discount.toString()
        val total=(sharedViewModel.total+sharedViewModel.shipment)-sharedViewModel.discount
        binding.total.text=total.toString()

    }



    private fun navigate(){
        findNavController().navigate(CartFragmentDirections.navToConfirm())
    }

}

