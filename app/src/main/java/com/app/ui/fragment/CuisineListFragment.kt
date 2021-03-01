package com.app.ui.fragment

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.app.R
import com.app.databinding.CuisionListFragmentBinding
import com.app.locale.LocaleChanger
import com.app.locale.Locales
import com.app.ui.AppNavigatorInterface
import com.app.ui.Command
import com.app.ui.SplashActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CuisineListFragment : Fragment() {
    private val NUM_PAGES = 5
    companion object {
        fun newInstance() = CuisineListFragment()
        val BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT:Int = 1
    }

    private lateinit var binding: CuisionListFragmentBinding
    @Inject
    lateinit var navigatorInterface: AppNavigatorInterface
    private var count  = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CuisionListFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@CuisineListFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        var adapt = ScreenSlidePagerAdapter(this.requireActivity())
        Log.d("LANG",LocaleChanger.getLocaleFromPref(requireContext()).language)
        binding.lang = LocaleChanger.getLocaleFromPref(requireContext()).language
        with(binding.pager){
            adapter = adapt
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    if (state == ViewPager2.SCROLL_STATE_IDLE || state == ViewPager2.SCROLL_STATE_DRAGGING) {
                        if (currentItem == 0)
                            setCurrentItem(adapt.itemCount - 1, false)
                        else if (currentItem == adapt.itemCount - 1)
                            setCurrentItem(1, false)
                    }
                }
            })
            setCurrentItem(1, false)
        }
        binding.group.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioButton2)
                LocaleChanger.setLocale(requireContext(), Locales.getLang("hi"))
            else
                LocaleChanger.setLocale(requireContext(), Locales.getLang("en"))
            count++
            if (count>2) {
                activity?.finish()
                startActivity(Intent(activity, SplashActivity::class.java))
            }
        }
        binding.tvHistory.setOnClickListener { navigatorInterface.navigator(Command.HISTORY) }


    }



    private inner class  ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment.newInstance(position)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleChanger.overrideLocale(requireContext())
    }


}




