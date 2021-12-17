package id.ac.ukdw.final_71190449

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditActivity:AppCompatActivity() {

    var firestore:FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        firestore = FirebaseFirestore.getInstance()

        val getJudul = intent.getStringExtra("judul").toString()
        val getTahun = intent.getStringExtra("rilis").toString()
        val getGenre = intent.getStringExtra("genre").toString()
        val getRating = intent.getStringExtra("rating").toString()

        val edtJudul = findViewById<EditText>(R.id.updateJudul)
        val edtTahun = findViewById<EditText>(R.id.updateTahun)
        val edtGenre = findViewById<EditText>(R.id.updateGenre)
        val edtRating = findViewById<EditText>(R.id.updateRating)

        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnKembali = findViewById<Button>(R.id.btnKembali)


        edtJudul.setText(getJudul)
        edtTahun.setText(getTahun)
        edtGenre.setText(getGenre)
        edtRating.setText(getRating)

        btnSimpan.setOnClickListener {
            val updateFilm = Film(edtJudul.text.toString(),edtTahun.text.toString(),edtGenre.text.toString(),edtRating.text.toString())
            firestore?.collection("film")?.whereEqualTo("judul",getJudul)?.get()!!.addOnSuccessListener {
                for (update in it){
                    firestore?.collection("film")?.document(update.id)?.set(updateFilm)?.addOnCompleteListener{
                        if(it.isSuccessful){
                            Toast.makeText(this, "Edit Berhasil", Toast.LENGTH_SHORT).show()
                            val i = Intent(this, FilmActivity::class.java)
                            startActivity(i)
                        }
                    }
                }
            }

        }



    }
}