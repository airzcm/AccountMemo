package com.airzcm.accountmemo.view.createitem

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.airzcm.accountmemo.R
import com.airzcm.accountmemo.model.database.AccountDatabase
import kotlinx.android.synthetic.main.activity_create_new.*
import java.util.*

/**
 * @author airzcm on 2018/1/15.
 */
class CreateItemActivity : AppCompatActivity() {

    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new)

        val calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)

        tv_date.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        DatePickerDialog(this@CreateItemActivity,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    tv_date.text = year.toString() + "-" + (month + 1) + "-" + dayOfMonth
                    this.year = year
                    this.month = month
                    this.day = day
                },
                year, month, day).show()
    }

    private fun selectCategory() {
        val db = AccountDatabase.getInstance(this)
        val categoryList = db.getCategoryDao().getAllCategory()
        
    }

    companion object {
        fun startMe(context: Context) {
            val intent = Intent(context, CreateItemActivity::class.java)
            context.startActivity(intent)
        }
    }
}