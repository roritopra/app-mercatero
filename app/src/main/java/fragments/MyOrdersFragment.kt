package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mercatero.app.R
import com.mercatero.app.databinding.FragmentMyOrderBinding
import com.mercatero.app.databinding.FragmentStoreDetailBinding
import com.mercatero.app.databinding.FragmentTrackOrderBinding


class MyOrdersFragment : BaseFragment() {

    lateinit var binding: FragmentMyOrderBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyOrderBinding.bind(view)




    }



    private fun navigate(){
        //findNavController().navigate(FragmentUserTypeDirections.navToRegister())
    }

}

