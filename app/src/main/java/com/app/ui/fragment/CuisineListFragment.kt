package com.app.ui.fragment

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.app.R
import com.app.databinding.CuisionListFragmentBinding
import com.app.locale.LocaleChanger
import com.app.locale.Locales
import com.app.ui.SplashActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CuisineListFragment : Fragment() {
    private val NUM_PAGES = 5
    companion object {
        fun newInstance() = CuisineListFragment()
        val BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT:Int = 1
    }

    private lateinit var binding: CuisionListFragmentBinding
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
        var adapter = ScreenSlidePagerAdapter(childFragmentManager)
        binding.pager.adapter = adapter
        Log.d("LANG",LocaleChanger.getLocaleFromPref(requireContext()).language)
        binding.lang = LocaleChanger.getLocaleFromPref(requireContext()).language
        binding.pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {
                if (state === ViewPager.SCROLL_STATE_IDLE) {
                    val index: Int = binding.pager.getCurrentItem()
                    if (index == 0) binding.pager.setCurrentItem(
                        adapter.getCount(),
                        false
                    ) else if (index == adapter.getCount()-1) binding.pager.setCurrentItem(0, false)
                }

            }
        })
        binding.group.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioButton2)
                LocaleChanger.setLocale(requireContext(), Locales.getLang("hi"))
            else
                LocaleChanger.setLocale(requireContext(), Locales.getLang("en"))
            count++
            if (count>2) {
                activity?.finish()
                startActivity(Intent(activity, SplashActivity::class.java));
            }
        }


    }



    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
        fm,
        CuisineListFragment.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment = ScreenSlidePageFragment.newInstance(position)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleChanger.overrideLocale(requireContext())
    }


}




