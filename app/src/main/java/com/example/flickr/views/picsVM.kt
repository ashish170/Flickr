package com.example.flickr.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickr.data.pics
import com.example.flickr.network.web
import kotlinx.coroutines.launch

class picsVM : ViewModel() {
    private val mutablePhotosListLiveData = MutableLiveData<List<pics>>()
    private val photosListLiveData: LiveData<List<pics>> = mutablePhotosListLiveData
    suspend fun fetchImages(searchTerm: String): List<pics> {
        if (searchTerm.isBlank()) {
            return emptyList()
        }

        val searchResponse = web.client.fetchImages(searchTerm)
        return searchResponse.photos.photo.map { photo ->
            pics(
                id = photo.id,
                url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                title = photo.title
            )


        }
    }
}
