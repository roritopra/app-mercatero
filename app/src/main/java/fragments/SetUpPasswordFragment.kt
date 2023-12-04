package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentSetPassBinding
import models.ProductModel
import models.StoreModel
import utils.AppPreferences.getUserType
import utils.Constants.COLLECTION_STORES
import utils.Constants.COLLECTION_USERS
import utils.Constants.KEY_REGISTRATION
import utils.Constants.KEY_STORE


class SetUpPasswordFragment : BaseFragment() {

    val args:SetUpPasswordFragmentArgs by navArgs()
    lateinit var binding: FragmentSetPassBinding
    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth



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

        auth=FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()

        binding.backBtn.setOnClickListener { findNavController().navigateUp() }
        binding.confirmBtn.setOnClickListener {

            val password=binding.passBox.text.toString()
            val confirmPassword=binding.confirmPassBox.text.toString()
            if(password.isEmpty()||confirmPassword.isEmpty()
                || password != confirmPassword
            ){
                Toast.makeText(requireContext(),"la contraseña está vacía o la contraseña no coincide",Toast.LENGTH_LONG).show()
                return@setOnClickListener

            }

            auth.createUserWithEmailAndPassword(args.user.email,password).addOnSuccessListener {
                auth.currentUser?.uid?.let { it1 -> createUser(it1) }

            }.addOnFailureListener {
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()

            }


        }

    }


    private fun createUser(id:String){

        val user=args.user
        user.id=id
        db.collection(COLLECTION_USERS).document(id).set(user).addOnSuccessListener {
            if(context?.getUserType()== KEY_STORE) {
                val store=StoreModel()
                store.id=id
                store.name=args.user.name
                store.category=args.category
                store.distance="10"

                val products= mutableListOf<ProductModel>()
                val product=ProductModel()
                product.name="Chicken"
                product.rating=4.5f
                product.store=store.name
                product.storeId=store.id
                product.price=400.0
                product.description="this is chicken"
                product.images= mutableListOf<String>()
                products.add(product)

                val product1=ProductModel()
                product1.name="Rice"
                product1.rating=4.6f
                product1.store=store.name
                product.storeId=store.id
                product1.price=200.0
                product1.description="this is rice"
                product1.images= mutableListOf()
                products.add(product1)

                store.products=products
                db.collection(COLLECTION_STORES).document(id).set(store).addOnSuccessListener {
                    navigate()
                }
            }
            else{
                navigate()
            }
        }


    }


    private fun navigate(){
        findNavController().navigate(SetUpPasswordFragmentDirections
            .navToCompleteMsg(KEY_REGISTRATION))
    }

}

