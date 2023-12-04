package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext



abstract class BaseFragment: Fragment(),CoroutineScope {

    private lateinit var job: Job
    private var rootView: View? = null
    var hasInitializedRootView = false

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job= Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


    fun getPersistentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        layout: Int
    ): View? {
        val view = rootView
        var view2: View? = null
        if (view == null) {
            if (inflater != null) {
                view2 = inflater.inflate(layout, container, false)
            }
            rootView = view2
        } else {
            val parent = if (view != null) view.parent else null
            if (parent is ViewGroup) {
                view2 = parent
            }
            val viewGroup = view2 as ViewGroup?
            viewGroup?.removeView(rootView)
        }
        return rootView
    }


}