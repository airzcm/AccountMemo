package com.airzcm.accountmemo.view.createitem

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.airzcm.accountmemo.App
import com.airzcm.accountmemo.R
import com.airzcm.accountmemo.base.BaseActivity
import com.airzcm.accountmemo.model.entity.Expense
import com.airzcm.accountmemo.model.entity.Income
import com.airzcm.accountmemo.util.toast
import kotlinx.android.synthetic.main.activity_create_new.*
import java.util.*

/**
 * @author airzcm on 2018/1/15.
 */
class CreateItemActivity : BaseActivity() {

    private var type: Int = 0

    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0

    private var categoryId: Int = 0
    private var eventId: Int = 0
    private var sourceId: Int = 0

    val db = App.database

    override fun layout() = R.layout.activity_create_new

    override fun initView() {
        type = intent.getIntExtra(ACCOUNT_TYPE, 0)
        if (type == 1) {
            layout_category.visibility = View.GONE
            layout_event.visibility = View.GONE
            layout_source.visibility = View.VISIBLE
        } else {
            layout_category.visibility = View.VISIBLE
            layout_event.visibility = View.VISIBLE
            layout_source.visibility = View.GONE
        }

        val calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        tv_date.text = resources.getString(R.string.text_date, year, month + 1, day)

        selectCategory()
        selectSource()

        tv_date.setOnClickListener {
            showDatePickerDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                saveItem()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun showDatePickerDialog() {
        DatePickerDialog(this@CreateItemActivity,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    tv_date.text = resources.getString(R.string.text_date, year, month + 1, dayOfMonth)
                    this.year = year
                    this.month = month
                    this.day = dayOfMonth
                },
                year, month, day).show()
    }

    private fun selectCategory() {
        val categoryList = db.getCategoryDao().getAllCategory()
        val showItem = categoryList.mapTo(ArrayList()) { it.category }
        showItem.add("添加..")
        val adapter = ArrayAdapter<String>(this@CreateItemActivity, android.R.layout.simple_spinner_item, showItem)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_category.adapter = adapter
        sp_category.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == categoryList.size) {
                    Log.i("aaaaa", position.toString())
                } else {
                    categoryId = categoryList[position].id
                    Log.i("aaaaacategory", categoryId.toString())
                    selectEvent(categoryId)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                toast("请选择类别")
            }
        }
    }

    private fun selectEvent(categoryId: Int) {
        val eventList = db.getEventDao().getEvent(categoryId)
        val showItem = eventList.mapTo(ArrayList()) { it.event }
        showItem.add("添加..")
        val adapter = ArrayAdapter<String>(this@CreateItemActivity, android.R.layout.simple_spinner_item, showItem)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_event.adapter = adapter
        sp_event.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == eventList.size) {
                    Log.i("aaaaa", position.toString())
                } else {
                    eventId = eventList[position].id
                    Log.i("aaaaaevent", eventId.toString())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                toast("请选择项目")
            }
        }
    }

    private fun selectSource() {
        val sourceList = db.getSourceDao().getAllSource()
        val showItem = sourceList.mapTo(ArrayList()) { it.source }
        showItem.add("添加..")
        val adapter = ArrayAdapter<String>(this@CreateItemActivity, android.R.layout.simple_spinner_item, showItem)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_source.adapter = adapter
        sp_source.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == sourceList.size) {
                    Log.i("aaaaa", position.toString())
                } else {
                    sourceId = sourceList[position].id
                    Log.i("aaaaasource", sourceId.toString())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                toast("请选择收入来源")
            }
        }
    }

    private fun saveItem() {
        val amount = et_value.text.toString()
        val comment = et_comment.text.toString()
        if (amount.isEmpty()) {
            toast("请输入金额")
        } else {
            if (type == 1) {
                val income = Income(amount = amount.toDouble(), day = day, month = month + 1, year = year, sourceId = sourceId, comment = comment)
                db.getIncomeDao().insertIncome(income)
            } else {
                val expense = Expense(amount = amount.toDouble(), day = day, month = month + 1, year = year, eventId = eventId, comment = comment)
                db.getExpenseDao().insertExpense(expense)
            }
            finish()
        }
    }

    companion object {
        private val ACCOUNT_TYPE = "ACCOUNT_TYPE"

        //        0 for expense, 1 for income
        fun startMe(context: Context, type: Int = 0) {
            val intent = Intent(context, CreateItemActivity::class.java)
            intent.putExtra(ACCOUNT_TYPE, type)

            context.startActivity(intent)
        }
    }
}