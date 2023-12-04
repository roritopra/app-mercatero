package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentRegisterBinding
import models.UserModel
import utils.Constants.KEY_CONSUMER


class RegistrationFragment : BaseFragment() {

    lateinit var binding: FragmentRegisterBinding
    lateinit var navController: NavController




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
        navController=findNavController()

        binding.signupBtn.setOnClickListener {

            val user=UserModel()
            user.name=binding.nameBox.text.toString()
            user.surName=binding.surnameBox.text.toString()
            user.email=binding.emailBox.text.toString()
            user.phone=binding.phoneBox.text.toString()
            user.userType=KEY_CONSUMER

            if(user.name.isEmpty()||user.surName.isEmpty()
                ||user.email.isEmpty()||user.phone.isEmpty()){
                Toast.makeText(requireContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            navController.navigate(RegistrationFragmentDirections.navToSetupPass(user))
        }
        binding.loginBtn.setOnClickListener {
            navController.navigate(RegistrationFragmentDirections.navToLogin())
        }




    }





}

