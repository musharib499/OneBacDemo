package com.app.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Musharib Ali on 11/2/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class BaseAdapterBinding<S>(
        private val mContext: Context,
        objects: List<S>?,
        private val mListener: BindAdapterListener?,
        private val layoutId: Int
) : RecyclerView.Adapter<BaseAdapterBinding.DataBindingViewHolder>() {
    private var mObjects = objects as MutableList<S>?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        return DataBindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), layoutId, parent, false))
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        mListener?.onBind(holder, position)

    }

    interface BindAdapterListener {
        fun onBind(holder: DataBindingViewHolder, position: Int)
    }

    class DataBindingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(objects: List<S>?) {
        this.mObjects = objects as MutableList<S>
        this.notifyDataSetChanged()
    }

    fun item(index: Int): S = mObjects?.get(index) as S
    override fun getItemCount(): Int {
        return mObjects?.size ?: 0
    }

}