package com.example.kotlindz3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindz3.adapters.GoatAdapters
import com.example.kotlindz3.databinding.ActivityMainBinding
import android.content.Intent

class MainActivity : AppCompatActivity(), GoatAdapters.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recycler: RecyclerView
    private lateinit var grid: GridLayoutManager
    private lateinit var arrayList: ArrayList<Int>
    private lateinit var goatAdapters: GoatAdapters
    private var favoritesList = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recycler = findViewById(R.id.recycler_view)
        grid = GridLayoutManager(applicationContext, 3, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = grid
        recycler.setHasFixedSize(true)
        arrayList = ArrayList()
        arrayList = setDataInList()
        goatAdapters = GoatAdapters(arrayList)
        goatAdapters.setOnItemClickListener(this)
        recycler.adapter = goatAdapters

        binding.btnSend.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(MAIN_ACTIVITY_EXTRA,favoritesList)
            startActivity(intent)
        }
    }

    private fun setDataInList(): ArrayList<Int> {

        val items = arrayListOf<Int>()
        for (i in 0..14) {
            items.add(R.drawable.img)
        }
        return items
    }

    override fun onSave(goatChar: Int) {
        favoritesList.add(goatChar)
    }

    override fun onDelete(goatChar: Int) {
        favoritesList.remove(goatChar)
    }

    companion object {
        const val MAIN_ACTIVITY_EXTRA = "imageKey"
    }
}