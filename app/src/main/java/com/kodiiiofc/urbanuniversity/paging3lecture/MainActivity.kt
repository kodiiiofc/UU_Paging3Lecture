package com.kodiiiofc.urbanuniversity.paging3lecture

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kodiiiofc.urbanuniversity.paging3lecture.adapter.MainAdapter
import com.kodiiiofc.urbanuniversity.paging3lecture.adapter.MainLoadStateAdapter
import com.kodiiiofc.urbanuniversity.paging3lecture.databinding.ActivityMainBinding
import com.kodiiiofc.urbanuniversity.paging3lecture.db.ItemDao
import com.kodiiiofc.urbanuniversity.paging3lecture.db.ItemDatabase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dao: ItemDao
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(dao) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = ItemDatabase.getInstance(this).itemDao()

        val adapter = MainAdapter()
        binding.recyclerView.adapter = adapter.withLoadStateFooter(MainLoadStateAdapter())

        lifecycleScope.launch {
            viewModel.data.collectLatest {
                adapter.submitData(it)
            }
        }


    }
}