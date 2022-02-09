package com.example.flickr.views


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flickr.R
import com.example.flickr.data.pics
import kotlinx.coroutines.delay
import kotlinx.android.synthetic.main.pics_new.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
private const val SEARCH_DELAY_MS = 200L


class picsview : AppCompatActivity() {
    private val photosViewModel: picsVM by viewModels()
    private val photosAdapter = picsAdapter()

    private var searchJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pics_new)

        searchBox.addTextChangedListener{text: Editable? ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(SEARCH_DELAY_MS)
                val  imagesList= photosViewModel.fetchImages(text.toString())
                with(photosAdapter)
                {
                    photos.clear()
                    photos.addAll(imagesList)
                    notifyDataSetChanged()
                }
            }
        }
        photosRecyclerView.adapter = photosAdapter
        photosRecyclerView.layoutManager = GridLayoutManager(this, 3)
    }
}