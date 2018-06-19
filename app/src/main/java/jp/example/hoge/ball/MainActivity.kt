package jp.example.hoge.ball

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    // ボールが壁にぶつかる　だけ
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun gameStart1(v : View) {
        val intent = Intent(this, blockBreaking::class.java)
        startActivity(intent)
    }
}
