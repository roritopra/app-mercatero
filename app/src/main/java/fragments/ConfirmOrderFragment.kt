package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentConfirmOrderBinding


class ConfirmOrderFragment : BaseFragment() {

    lateinit var binding: FragmentConfirmOrderBinding



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

        binding.backBtn.setOnClickListener { findNavController().navigateUp() }
        binding.confirBtn.setOnClickListener { navigate() }

    }



    private fun navigate(){
        findNavController().navigate(ConfirmOrderFragmentDirections.navToCompleteMsg())
    }

}

