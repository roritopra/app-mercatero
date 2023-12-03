package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mercatero.app.R
import com.mercatero.app.databinding.FragmentCartBinding
import com.mercatero.app.databinding.FragmentStoreDetailBinding


class CartFragment : BaseFragment() {

    lateinit var binding: FragmentCartBinding



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



    }



    private fun navigate(){
        findNavController().navigate(CartFragmentDirections.navToConfirm())
    }

}

