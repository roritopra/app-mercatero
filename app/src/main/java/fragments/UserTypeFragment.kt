package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentUserTypeBinding
import utils.AppPreferences.setUserType
import utils.Constants.KEY_CONSUMER
import utils.Constants.KEY_DRIVER
import utils.Constants.KEY_STORE


class UserTypeFragment : BaseFragment() {

    lateinit var binding: FragmentUserTypeBinding
    lateinit var navController: NavController
    var userType= KEY_CONSUMER



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserTypeBinding.bind(view)

        navController=findNavController()

        binding.backBtn.setOnClickListener { navController.navigateUp() }
        binding.signupBtn.setOnClickListener { navigate() }

        binding.storeBtn.setOnClickListener {
            changeBg(it as FrameLayout)
            userType= KEY_STORE
        }
        binding.driverBtn.setOnClickListener {
            changeBg(it as FrameLayout)
            userType= KEY_DRIVER
        }
        binding.cstBtn.setOnClickListener {
            changeBg(it as FrameLayout)
            userType= KEY_CONSUMER
        }

    }


    private fun changeBg(view: FrameLayout){
        binding.cstBtn.setBackgroundResource(R.drawable.bg_green_empty)
        binding.driverBtn.setBackgroundResource(R.drawable.bg_green_empty)
        binding.storeBtn.setBackgroundResource(R.drawable.bg_green_empty)
        binding.customer.setTextColor(ContextCompat.getColor(requireContext(),R.color.textGray))
        binding.driver.setTextColor(ContextCompat.getColor(requireContext(),R.color.textGray))
        binding.store.setTextColor(ContextCompat.getColor(requireContext(),R.color.textGray))
        binding.customer.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(),R.color.iconGray))
        binding.driver.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(),R.color.iconGray))
        binding.store.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(),R.color.iconGray))


        view.setBackgroundResource(R.drawable.bg_green)
        when(view.id){
            binding.cstBtn.id-> {
                binding.customer.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                binding.customer.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(),R.color.white))
            }
            binding.driverBtn.id-> {
                binding.driver.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                binding.driver.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(),R.color.white))

            }
            binding.storeBtn.id-> {
                binding.store.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                binding.store.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(),R.color.white))

            }
        }
    }


    private fun navigate(){
        context?.setUserType(userType)
        if(userType== KEY_CONSUMER) {
            navController.navigate(UserTypeFragmentDirections.navToRegister())
        }
        else if(userType== KEY_STORE) {
            navController.navigate(UserTypeFragmentDirections.navToStoreRegister())
        }
    }

}

