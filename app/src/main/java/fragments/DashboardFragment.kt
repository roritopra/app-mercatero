package fragments


import adapters.CatgsAdapter
import adapters.ProductsAdapter
import adapters.StoresAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentDashboardBinding
import models.CatgsModel
import models.ProductModel
import models.StoreModel
import models.UserModel
import utils.Constants
import utils.Constants.ITEM_HORIZONTAL
import viewmodels.SharedViewModel


class DashboardFragment : BaseFragment(),CatgsAdapter.OnClickListener,
    StoresAdapter.OnClickListener,ProductsAdapter.OnClickListener{

    private val sharedViewModel: SharedViewModel by viewModels({requireActivity()})
    lateinit var binding: FragmentDashboardBinding
    lateinit var navController: NavController
    lateinit var db: FirebaseFirestore

    val catgsList= mutableListOf<CatgsModel>()
    val productsList= mutableListOf<ProductModel>()
    val storesList= mutableListOf<StoreModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
        navController = findNavController()
        db = FirebaseFirestore.getInstance()

        binding.cart.setOnClickListener {
            navController.navigate(DashboardFragmentDirections.navToCart())
        }

        showCatgs()
    }


    private fun showCatgs(){

        Constants.catgs.forEachIndexed {index,item->
            val catg=CatgsModel()

            val icon=Constants.catgsIcons[index]
            catg.name=item
            catg.icon=icon
            catgsList.add(catg)
        }

        val adapter=CatgsAdapter(this,catgsList)
        binding.catgsRV.adapter=adapter

        showStores(catgsList[0].name)

    }

    private fun showStores(store:String){

        productsList.clear()
        storesList.clear()


        db.collection(Constants.COLLECTION_STORES).whereEqualTo("category",store).get().addOnSuccessListener {
            it.documents.forEach {
                val store = it?.toObject(StoreModel::class.java)
                store?.products?.forEach {
                    productsList.add(it)
                }
                if (store != null) {
                    storesList.add(store)
                }
            }

            val adapter=StoresAdapter(this,storesList)
            binding.storesRV.adapter=adapter

            val prdAdapter=ProductsAdapter(this,productsList,ITEM_HORIZONTAL)
            binding.productsRV.adapter=prdAdapter

        }.addOnFailureListener {
            Log.v("Profile",it.message.toString())
        }



    }


    override fun onStoreItemClick(position: Int) {
        val store=storesList[position]
        sharedViewModel.store=store
        navController.navigate(DashboardFragmentDirections.navToStore())
    }

    override fun onCategoryClick(position: Int) {
        showStores(catgsList[position].name)


    }

    override fun onProductClick(position: Int) {
        val product=productsList[position]
        sharedViewModel.store= storesList.find { it.id==product.storeId }!!
        navController.navigate(DashboardFragmentDirections.navToProduct(product))
    }

}
