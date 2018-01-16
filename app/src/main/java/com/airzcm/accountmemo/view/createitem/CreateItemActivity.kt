package com.airzcm.accountmemo.view.createitem

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.airzcm.accountmemo.R
import com.airzcm.accountmemo.model.database.AccountDatabase
import com.airzcm.accountmemo.model.entity.Category
import com.airzcm.accountmemo.model.entity.Event
import com.airzcm.accountmemo.model.entity.Source
import com.airzcm.accountmemo.view.util.toast
import kotlinx.android.synthetic.main.activity_create_new.*
import java.util.*

/**
 * @author airzcm on 2018/1/15.
 */
class CreateItemActivity : AppCompatActivity() {

    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0

    private lateinit var category: Category
    private lateinit var event: Event
    private lateinit var source: Source

    val db = AccountDatabase.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new)

        val isIncome = intent.getBooleanExtra(ACCOUNT_TYPE, false)
        if (isIncome) {
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
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
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
                    category = categoryList[position]
                    selectEvent(category.id)
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
                    event = eventList[position]
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
                    source = sourceList[position]
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                toast("请选择收入来源")
            }
        }
    }

    companion object {
        private val ACCOUNT_TYPE = "ACCOUNT_TYPE"

        fun startMe(context: Context, isIncome: Boolean = false) {
            val intent = Intent(context, CreateItemActivity::class.java)
            intent.putExtra(ACCOUNT_TYPE, isIncome)

            context.startActivity(intent)
        }
    }
}