package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentStoreDetailBinding


class ToDeliverOrdersFragment : BaseFragment() {

    lateinit var binding: FragmentStoreDetailBinding



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




    }



    private fun navigate(){
        //findNavController().navigate(FragmentUserTypeDirections.navToRegister())
    }

}

