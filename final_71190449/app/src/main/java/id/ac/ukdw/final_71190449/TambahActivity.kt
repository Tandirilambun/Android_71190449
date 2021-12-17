package id.ac.ukdw.final_71190449

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class TambahActivity:AppCompatActivity() {

    var firestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        firestore = FirebaseFirestore.getInstance()

        val edtJudul = findViewById<EditText>(R.id.edtJudul)
        val edtRilis = findViewById<EditText>(R.id.edtTahun)
        val edtGenre = findViewById<EditText>(R.id.edtGenre)
        val edtRating = findViewById<EditText>(R.id.edtRating)

        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnTambah = findViewById<Button>(R.id.btnTambah)

        btnTambah.setOnClickListener {
            val film = Film(edtJudul.text.toString(), edtRilis.text.toString(),edtGenre.text.toString(), edtRating.text.toString())
            edtJudul.setText("")
            edtRilis.setText("")
            edtGenre.setText("")
            edtRating.setText("")
            firestore?.collection("film")?.add(film)?.addOnSuccessListener {
                Toast.makeText(this, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show()
            }

        }
        btnBack.setOnClickListener {
            val backIntent = Intent(this, FilmActivity::class.java)
            startActivity(backIntent)
        }
    }
}