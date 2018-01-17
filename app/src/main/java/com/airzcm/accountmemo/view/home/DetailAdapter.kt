package com.airzcm.accountmemo.view.home

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airzcm.accountmemo.R
import com.airzcm.accountmemo.model.entity.Expense

/**
 * @author airzcm on 2018/1/17.
 */
class DetailAdapter(val items : List<Expense>, private val onItemClicked: ()->Unit) : Adapter<DetailAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_detail, parent, false)
        return ViewHolder(view, onItemClicked)
    }

    class ViewHolder(val view: View, val onItemClicked: ()->Unit) : RecyclerView.ViewHolder(view) {
        fun bind(expense: Expense) {

        }

    }

}