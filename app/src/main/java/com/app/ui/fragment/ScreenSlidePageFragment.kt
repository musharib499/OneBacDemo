package com.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.data.model.FoodMenu
import com.app.databinding.FragmentScreenSlidePageBinding
import com.app.locale.LocaleChanger
import com.app.ui.AppNavigatorInterface
import com.app.ui.Command
import com.app.ui.viewModule.SlideViewModel
import com.app.utils.DETAILS
import com.app.utils.foodMenu
import com.app.utils.foodMenuH
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScreenSlidePageFragment : Fragment() {
    private  val ARG_PARAM = "param"
    private var position: Int = 0
    private val viewModel: SlideViewModel by viewModels()
    private lateinit var binding:FragmentScreenSlidePageBinding
    @Inject
    lateinit var navigatorInterface: AppNavigatorInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_PARAM)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentScreenSlidePageBinding.inflate(inflater,container,false).apply {
             lifecycleOwner = this@ScreenSlidePageFragment
             vm = viewModel
             fragment = this@ScreenSlidePageFragment
         }
        init()
        return binding.root

    }
    fun init(){
        viewModel._item.value = if (LocaleChanger.getLocaleFromPref(requireContext()).language.equals("en")) foodMenu.categories?.get(position) else foodMenuH.categories?.get(position)
    }

    companion object {


        @JvmStatic
        fun newInstance(position: Int) =
                ScreenSlidePageFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM, position)
                    }
                }
    }
    fun moveNext(item : FoodMenu.Category?){
        navigatorInterface.navigator(Command.DETAILS,b = bundleOf(DETAILS to item) )

    }
}