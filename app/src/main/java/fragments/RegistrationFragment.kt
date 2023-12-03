package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mercatero.app.R
import com.mercatero.app.databinding.FragmentRegisterBinding


class RegistrationFragment : BaseFragment() {

    lateinit var binding: FragmentRegisterBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        binding.signupBtn.setOnClickListener { navigate() }

    }



    private fun navigate(){
        findNavController().navigate(RegistrationFragmentDirections.navToSetupPass())
    }

}

