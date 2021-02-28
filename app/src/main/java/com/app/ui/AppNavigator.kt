package com.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.app.R
import com.app.ui.fragment.CuisineListFragment
import com.app.ui.fragment.DetailsListFragment
import com.app.ui.fragment.OrderFragment
import javax.inject.Inject

/**
 * Created by Musharib Ali on 11/2/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class AppNavigator @Inject constructor(val activity: FragmentActivity) : AppNavigatorInterface {
    override fun navigator(command: Command, currentFragment: Fragment?, b: Bundle?) {
        val fragment: Fragment = when (command) {
            Command.HOME -> CuisineListFragment.newInstance()
            Command.DETAILS -> DetailsListFragment.newInstance()
            Command.ORDER -> OrderFragment.newInstance()
        }
        fragment.let {
            b?.let { v -> it.arguments = v }
            with(activity.supportFragmentManager.beginTransaction()) {
                add(R.id.main_container, it, it::class.java.canonicalName)
                if (currentFragment != null)
                    hide(currentFragment)

                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                it.retainInstance = true
                addToBackStack(it::class.java.canonicalName).commit()
            }
        }

    }


}


interface AppNavigatorInterface {
  open fun navigator(command: Command, currentFragment: Fragment? = null, b: Bundle? = null)
}

enum class Command {
    HOME,
    DETAILS,
    ORDER
}