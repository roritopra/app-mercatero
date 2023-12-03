package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mercatero.app.R
import com.mercatero.app.databinding.FragmentRegCompleteBinding
import com.mercatero.app.databinding.FragmentRegisterBinding
import utils.AppPreferences.getUserType
import utils.Constants


class CompletedMsgFragment : BaseFragment() {

    lateinit var binding: FragmentRegCompleteBinding
    lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reg_complete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegCompleteBinding.bind(view)
        navController=findNavController()

        binding.okBtn.setOnClickListener { navigate() }

    }



    private fun navigate(){
        if(context?.getUserType()== Constants.KEY_CUSTOMER) {
            navController.navigate(CompletedMsgFragmentDirections.navToDashboard())
        }
        else if(context?.getUserType()== Constants.KEY_STORE) {
            navController.navigate(CompletedMsgFragmentDirections.navToStoreDashboard())
        }
    }

}

