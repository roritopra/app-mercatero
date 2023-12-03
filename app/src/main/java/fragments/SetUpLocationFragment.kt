package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mercatero.app.R
import com.mercatero.app.databinding.FragmentRegCompleteBinding
import com.mercatero.app.databinding.FragmentRegisterBinding
import com.mercatero.app.databinding.FragmentSetLocationBinding


class SetUpLocationFragment : BaseFragment() {

    lateinit var binding: FragmentSetLocationBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_set_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetLocationBinding.bind(view)

        //binding.okBtn.setOnClickListener { navigate() }

    }



    private fun navigate(){
       // findNavController().navigate(Set.navToSetupPass())
    }

}

