package id.ac.ukdw.pertemuan7_71190449

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactDetail: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_contact)
        val nama = intent.getStringExtra("kontakNama")
        val nomor = intent.getStringExtra("kontakNomor")
        val poto = intent.getIntExtra("kontakPoto",0)

        val txtNama = findViewById<TextView>(R.id.detailName)
        val txtNomor = findViewById<TextView>(R.id.detailNomor)
        val foto = findViewById<ImageView>(R.id.detailCover)

        txtNama.text = nama
        txtNomor.text = nomor
        if (poto != null){
            foto.setImageResource(poto)
        }
    }
}