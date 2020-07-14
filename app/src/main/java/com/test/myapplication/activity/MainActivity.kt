package com.test.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.myapplication.R
import com.test.myapplication.Status
import com.test.myapplication.model.Post
import com.test.myapplication.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by lazy{
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainViewModel.getPosts().observe(this, Observer {

            when(it.status){
                Status.SUCCESS-> {
                    if (it.data != null) {
                        var str: String = ""
                        for (post: Post in it.data) {
                            str += post.id.toString() + " " + post.userId + " " + post.title + " " + post.body
                        }
                        text.text = str
                    }
                }
                Status.ERROR->{
                        Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                }
                Status.LOADING-> Log.d("TAG","Loading")
            }

        })
    }

}