package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mercatero.app.R
import com.mercatero.app.databinding.FragmentStoreDetailBinding
import com.mercatero.app.databinding.FragmentStoreRegisterBinding
import models.UserModel
import utils.Constants


class StoreRegistrationFragment : BaseFragment() {

    lateinit var binding: FragmentStoreRegisterBinding
    lateinit var navController:NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStoreRegisterBinding.bind(view)
        navController=findNavController()

        binding.backBtn.setOnClickListener { navController.navigateUp() }
        binding.signupBtn.setOnClickListener {

            val category=binding.categoryBox.text.toString()
            val user= UserModel()
            user.name=binding.nameBox.text.toString()
            user.email=binding.emailBox.text.toString()
            user.phone=binding.phoneBox.text.toString()
            user.userType= Constants.KEY_STORE

            if(user.name.isEmpty()
                ||user.email.isEmpty()||user.phone.isEmpty()){
                Toast.makeText(requireContext(),"Todos los campos son obligatorios", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            navController.navigate(RegistrationFragmentDirections.navToSetupPass(user,category))
        }
        binding.loginBtn.setOnClickListener {
            navController.navigate(RegistrationFragmentDirections.navToLogin())
        }

    }





}

