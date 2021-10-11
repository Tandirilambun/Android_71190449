package id.ac.ukdw.pertemuan6_71190449

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btnHal1)
        btn1.setOnClickListener {
            val intent = Intent(this, HalamanKedua::class.java)
            startActivity(intent)
        }

    }
}