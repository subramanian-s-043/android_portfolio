package com.example.portfolio

import ProjectsAdapter
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProjectsAdapter.OnItemClickListener {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        var avatar : ImageView = binding.avatarImageView
        avatar.setImageResource(R.drawable.profile_pic)
        makeRoundImage(avatar)
        val reachOut : Button = binding.button;
        val recyclerView: RecyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dataList = listOf(
            DataModel(R.drawable.project1, getString(R.string.project_1)),
            DataModel(R.drawable.project2, getString(R.string.project_2))
        )
        val adapter = ProjectsAdapter(dataList,this)
        recyclerView.adapter = adapter

        reachOut.setOnClickListener{
            redirect("https://www.linkedin.com/in/subbus-02/")
        }
    }

    private fun makeRoundImage(imageView : ImageView)
    {
        val bitmap: Bitmap = (imageView.drawable).toBitmap()
        val oval: ShapeDrawable = ShapeDrawable(OvalShape())
        oval.setBounds(0, 0, imageView.width, imageView.height)
        var shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val paint = Paint().apply {
            isAntiAlias = true
            shader = shader
        }
        oval.paint.set(paint)
        imageView.background = oval
    }

    override fun onItemClick(data: DataModel) {
        if(data.text == getString(R.string.project_1))
            redirect("https://github.com/subramanian-s-043/Java_Practice_Problems/tree/Console_Application/src/com/subramanians/snakegame")
        else if(data.text == getString(R.string.project_2))
            redirect("https://github.com/subramanian-s-043/Java_Practice_Problems/tree/Console_Application/src/com/subramanians/cricketscore")
    }

    private fun redirect(url: String){
        val intent : Intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}

data class DataModel(val imageResource: Int, val text: String)