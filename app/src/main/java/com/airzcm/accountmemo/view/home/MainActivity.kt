package com.airzcm.accountmemo.view.home

import android.animation.Animator
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.airzcm.accountmemo.App
import com.airzcm.accountmemo.R
import com.airzcm.accountmemo.base.BaseActivity
import com.airzcm.accountmemo.di.module.ActivityModule
import com.airzcm.accountmemo.model.database.AccountDatabase
import com.airzcm.accountmemo.model.entity.Category
import com.airzcm.accountmemo.model.entity.Event
import com.airzcm.accountmemo.model.entity.Source
import com.airzcm.accountmemo.view.createitem.CreateItemActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var isFABOpen = false

    @Inject
    lateinit var db: AccountDatabase

    override fun layout(): Int {
        return R.layout.activity_main
    }

    override fun onBackPressed() {
        when {
            drawer_layout.isDrawerOpen(GravityCompat.START) -> drawer_layout.closeDrawer(GravityCompat.START)
            isFABOpen -> closeFABMenu()
            else -> super.onBackPressed()
        }
    }

    override fun setupActivityComponent() {
        App[this].appComponent.plus(ActivityModule(this)).inject(this)
    }

    override fun initView() {

//        floating action button
        fab.setOnClickListener { view ->
            if (!isFABOpen) {
                showFABMenu()
            } else {
                CreateItemActivity.startMe(this@MainActivity, 0)
                closeFABMenu()
            }
        }

        fab1.setOnClickListener {
            CreateItemActivity.startMe(this@MainActivity, 1)
            closeFABMenu()
        }

        fabBGLayout.setOnClickListener { closeFABMenu() }

//        drawer
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        layout_expense.setOnClickListener {
            layout_expense.bringToFront()
            tv_expense_tab.visibility = View.INVISIBLE
            tv_income_tab.visibility = View.VISIBLE
        }

        layout_income.setOnClickListener {
            layout_income.bringToFront()
            tv_expense_tab.visibility = View.VISIBLE
            tv_income_tab.visibility = View.INVISIBLE
        }
    }

    override fun initDatabase() {
        if (db.getCategoryDao().getAllCategory().isEmpty()) {
            val category0 = Category(category = "吃")
            val category1 = Category(category = "穿")
            val category2 = Category(category = "住")
            val category3 = Category(category = "行")
            db.getCategoryDao().insertCategory(category0, category1, category2, category3)
        }
        if (db.getEventDao().getAllEvent().isEmpty()) {
            val event0 = Event(categoryId = 1, event = "早饭")
            val event1 = Event(categoryId = 1, event = "午饭")
            val event2 = Event(categoryId = 1, event = "晚饭")
            db.getEventDao().insertEvent(event0, event1, event2)

            val event3 = Event(categoryId = 2, event = "外衣")
            val event4 = Event(categoryId = 2, event = "内衣")
            db.getEventDao().insertEvent(event3, event4)

            val event5 = Event(categoryId = 3, event = "房租")
            val event6 = Event(categoryId = 3, event = "水费")
            val event7 = Event(categoryId = 3, event = "电费")
            db.getEventDao().insertEvent(event5, event6, event7)

            val event8 = Event(categoryId = 4, event = "公交")
            val event9 = Event(categoryId = 4, event = "地铁")
            val event10 = Event(categoryId = 4, event = "打车")
            db.getEventDao().insertEvent(event8, event9, event10)
        }

        if (db.getSourceDao().getAllSource().isEmpty()) {
            val source0 = Source(source = "工资")
            val source1 = Source(source = "奖金")
            val source2 = Source(source = "彩票")
            db.getSourceDao().insertSource(source0, source1, source2)
        }
    }

    private fun dataDemonstrate() {
        val expenseList = db.getExpenseDao().getAllExpense()
        val incomeList = db.getIncomeDao().getAllIncome()
    }

    private fun showFABMenu() {
        isFABOpen = true
        fabLayout1.visibility = View.VISIBLE
        fabBGLayout.visibility = View.VISIBLE
        fab_expense.visibility = View.VISIBLE

        fab.animate().rotationBy(180f)
        fab.setImageDrawable(resources.getDrawable(R.drawable.ic_arrow_down, null))
        fabLayout1.animate().translationY(-resources.getDimension(R.dimen.fab_pop_60))
    }

    private fun closeFABMenu() {
        isFABOpen = false
        fabBGLayout.visibility = View.GONE
        fab_expense.visibility = View.GONE

        fab.animate().rotationBy(-180f)
        fab.setImageDrawable(resources.getDrawable(R.drawable.ic_add, null))
        fabLayout1.animate().translationY(0f).setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }

            override fun onAnimationEnd(animator: Animator) {
                if (!isFABOpen) {
                    fabLayout1.visibility = View.GONE
                }

            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_day -> {
            }
            R.id.nav_month -> {

            }
            R.id.nav_year -> {

            }
            R.id.nav_all -> {

            }
            R.id.nav_diagram -> {

            }
            R.id.nav_settings -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
