package fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentSetPassBinding


class SetUpPasswordFragment : BaseFragment() {

    lateinit var binding: FragmentSetPassBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_set_pass, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetPassBinding.bind(view)

        binding.backBtn.setOnClickListener { findNavController().navigateUp() }
        binding.confirmBtn.setOnClickListener { navigate() }

    }



    private fun navigate(){
        findNavController().navigate(SetUpPasswordFragmentDirections.navToCompleteMsg())
    }

}

