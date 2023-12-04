package fragments


import adapters.ProductsAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentStoreDetailBinding
import models.ProductModel
import utils.Constants.ITEM_VERTICAL
import viewmodels.SharedViewModel


class StoreDetailFragment : BaseFragment(),ProductsAdapter.OnClickListener {


    private val sharedViewModel: SharedViewModel by viewModels({requireActivity()})
    lateinit var binding: FragmentStoreDetailBinding
    val productsList= mutableListOf<ProductModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStoreDetailBinding.bind(view)

        binding.backBtn.setOnClickListener { findNavController().navigateUp() }

        initViews()


    }


    @SuppressLint("SetTextI18n")
    private fun initViews(){
        val store=sharedViewModel.store
        store.products.let { productsList.addAll(it) }

        binding.name.text=store.name
        binding.distance.text="${store.distance.toString()} Km"
        binding.rating.text="${store.rating.toString()} calificación"
        binding.description.text=store.des
        binding.ratingBar.rating=store.rating

        val prdAdapter= ProductsAdapter(this,store.products,ITEM_VERTICAL)
        binding.productsRV.adapter=prdAdapter


    }



    override fun onProductClick(position: Int) {
        val product=productsList[position]
        findNavController().navigate(StoreDetailFragmentDirections.navToProduct(product))
    }

}
