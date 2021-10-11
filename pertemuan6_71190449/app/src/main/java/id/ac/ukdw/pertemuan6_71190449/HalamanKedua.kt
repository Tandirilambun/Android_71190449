package id.ac.ukdw.pertemuan6_71190449

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HalamanKedua:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kedua_halaman)
        val btn2 = findViewById<Button>(R.id.btnHal2)
        btn2.setOnClickListener {
            val intent = Intent(this, HalamanKetiga::class.java)
            startActivity(intent)
        }
    }
}