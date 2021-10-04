package id.ac.ukdw.pertemuan5_71190449

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HasilActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil)

        val username = intent.getStringExtra("username")

        val hasil = findViewById<TextView>(R.id.txtHasil)
        hasil.text = "Selamat datang ${username}"
    }
}