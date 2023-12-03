package fragments


import adapters.CatgsAdapter
import adapters.ProductsAdapter
import adapters.StoresAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mercatero.app.R
import com.mercatero.app.databinding.FragmentDashboardBinding
import com.mercatero.app.databinding.FragmentLoginBinding
import models.CatgsModel
import models.ProductModel
import models.StoreModel
import utils.Constants


class DashboardFragment : BaseFragment(),CatgsAdapter.OnClickListener,
StoresAdapter.OnClickListener,ProductsAdapter.OnClickListener{

    lateinit var binding: FragmentDashboardBinding
    lateinit var navController: NavController



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
        navController=findNavController()
        showCatgs()
        showStores()
        showProdcuts()


    }

    private fun showCatgs(){

        val catgsList= mutableListOf<CatgsModel>()
        Constants.catgs.forEachIndexed {index,item->
            val catg=CatgsModel()

            val icon=Constants.catgsIcons[index]
            catg.name=item
            catg.icon=icon
            catgsList.add(catg)
        }

        val adapter=CatgsAdapter(this,catgsList)
        binding.catgsRV.adapter=adapter

    }

    private fun showStores(){

        val storesList= mutableListOf<StoreModel>()
        for(i in 1..5){
            val store=StoreModel()
            storesList.add(store)
        }

        val adapter=StoresAdapter(this,storesList)
        binding.storesRV.adapter=adapter
    }

    private fun showProdcuts(){

        val productsList= mutableListOf<ProductModel>()
        for(i in 1..5){
            val item=ProductModel()
            productsList.add(item)
        }

        val adapter=ProductsAdapter(this,productsList)
        binding.productsRV.adapter=adapter
    }



    override fun onStoreItemClick(position: Int) {
        navController.navigate(DashboardFragmentDirections.navToStore())
    }

    override fun onClick(position: Int) {

    }

    override fun onProductClick(position: Int) {
        navController.navigate(DashboardFragmentDirections.navToProduct())
    }

}

