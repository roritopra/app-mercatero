package fragments


import adapters.ImagesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentProductDetailBinding
import models.ProductModel


class ProductDetailFragment : BaseFragment() {

    private val sharedViewModel: SharedViewModel by viewModels({requireActivity()})
    val args:ProductDetailFragmentArgs by navArgs()
    lateinit var binding: FragmentProductDetailBinding
    var itemCounts=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)

        binding.backBtn.setOnClickListener { findNavController().navigateUp() }

        initViews()

    }

    private fun initViews(){
        val product=args.product
        binding.name.text=product.name
        binding.description.text=product.description
        binding.rating.text="${product.rating.toString()} calificación"
        binding.ratingBar.rating=product.rating

        val adapter= ImagesAdapter(product.imgs)
        binding.viewPager.adapter=adapter
        binding.dotsIndicator.attachTo(binding.viewPager)

        binding.addBtn.setOnClickListener {
            itemCounts++
            binding.counts.text=itemCounts.toString()
            product.counts=itemCounts
        }

        binding.removeBtn.setOnClickListener {
            if(itemCounts>0) {
                itemCounts--
                binding.counts.text = itemCounts.toString()
                product.counts=itemCounts
            }
        }

        binding.confirmBtn.setOnClickListener {
            navigate(product)
        }

    }


    private fun navigate(product: ProductModel){
        sharedViewModel.productsList.add(product)
        findNavController().navigate(ProductDetailFragmentDirections.navToCart())
    }

}

