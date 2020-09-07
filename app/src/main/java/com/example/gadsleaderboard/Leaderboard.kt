package com.example.gadsleaderboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_leaderboard.*
import kotlinx.android.synthetic.main.fragment_view_pager.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Leaderboard : AppCompatActivity() {

    lateinit var dataAdpter: DataAdpter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        //recyclerView = findViewById(R.id.recyclerview)

        val apiInterface = ApiInterface.create().getModels()
        apiInterface.enqueue( object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>?, response: Response<List<Model>>?) {

                if(response?.body() != null)
                  dataAdpter.setModelListItems(response.body()!!)
            }

            override fun onFailure(call: Call<List<Model>>?, t: Throwable?) {

            }
        })

        ibtn.setOnClickListener {
            Intent(applicationContext, FormSubmission::class.java).also {
                startActivity(it)
            }
        }

        viewpager.setAdapter(MyAdapter(supportFragmentManager, lifecycle))
        TabLayoutMediator(tabLayout, viewpager, TabLayoutMediator.TabConfigurationStrategy{tab, position ->
            when (position) {
                0 -> tab.text = "Learning Leaders"
                1 -> tab.text = "Skill IQ Leaders"
            }
        }).attach()

    }

    private inner class MyAdapter(fm: FragmentManager?, lifecycle: Lifecycle) : FragmentStateAdapter(fm!!, lifecycle) {
        private val int_items = 2
        override fun createFragment(position: Int): Fragment {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = fragment_view_pager()
                1 -> fragment = fragment_view_pager()

            }
            return fragment!!
        }
        override fun getItemCount(): Int {
            return int_items
        }
    }
}

