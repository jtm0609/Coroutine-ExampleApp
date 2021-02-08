package com.example.corutineexample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Request
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var iv=findViewById<ImageView>(R.id.iv)
        GlobalScope.launch { //백그라운드 스레드에서 작동
            var bitmap=requestImage()
            iv.setImageBitmap(bitmap) //윗라인이 끝날때까지 대기
        }

    }
    //네트워크 작업
    fun requestImage(): Bitmap?{
        try {
            var imgURL = URL("https://blog.kakaocdn.net/dn/0mySg/btqCUccOGVk/nQ68nZiNKoIEGNJkooELF1/img.jpg")
            var conn = imgURL.openConnection() as HttpURLConnection;
            conn.doInput = true
            conn.connect()
            var inputstream = conn.inputStream
            var retBitmap = BitmapFactory.decodeStream(inputstream)
            return retBitmap
        }catch(e:Exception){
            e.printStackTrace()
        }
        return null
    }


}