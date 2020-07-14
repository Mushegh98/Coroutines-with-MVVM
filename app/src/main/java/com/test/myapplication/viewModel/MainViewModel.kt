package com.test.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.Resource
import com.test.myapplication.model.Post
import com.test.myapplication.network.NetworkBoundResource
import com.test.myapplication.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel : ViewModel() {
    var liveData : MutableLiveData<List<Post>> = MutableLiveData()


     fun getPosts() : MutableLiveData<Resource<List<Post>?>> {
        return object : NetworkBoundResource<List<Post>>(){
            override fun createCall() {
                viewModelScope.launch {
                    val data = DataRepository.getPosts()
                    withContext(Dispatchers.Main){setValue(data)}
                }
            }

        }.asMutableLiveData()
    }
}