package adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import fragments.StoreOrderFragment
import utils.Constants.KEY_TAB_POSITION


class TabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        val fragment = StoreOrderFragment()
        fragment.arguments = Bundle().apply {
            putInt(KEY_TAB_POSITION, position)
        }
        return fragment
    }

}
