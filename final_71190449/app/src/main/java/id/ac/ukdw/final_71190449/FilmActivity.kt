package id.ac.ukdw.final_71190449

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FilmActivity:AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView

    var firestore: FirebaseFirestore? = null
    var listFilm = arrayListOf<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)

        firestore = FirebaseFirestore.getInstance()

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser


        //variable untuk pencarian
        val btnCari = findViewById<ImageButton>(R.id.btnCari)
        val edtCari = findViewById<EditText>(R.id.edtCari)
        val rcyFilm = findViewById<RecyclerView>(R.id.rcyFilm)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val loading = ProgressDialog(this)
        loading.setMessage("Loading Data...")
        loading.show()
        firestore?.collection("film")?.get()?.addOnSuccessListener {
                docs ->
            var hasil = ""
            for (doc in docs){
                hasil += "${doc["judul"]}"
                val filmAdd = Film("${doc["judul"]}","${doc["tahunRilis"]}","${doc["genre"]}","${doc["rating"]}")
                listFilm.add(filmAdd)
            }
            loading.dismiss()
        }

        Handler().postDelayed({
            rcyFilm.layoutManager = LinearLayoutManager(this)
            val adapter = FilmAdapter(listFilm, this)
            rcyFilm.adapter = adapter
        },2000)


        btnCari.setOnClickListener {
            var pencarian = edtCari.text.toString()
            if (pencarian.isEmpty()){
                Toast.makeText(this, "Pencarian Kosong", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    rcyFilm.layoutManager = LinearLayoutManager(this)
                    val adapter = FilmAdapter(listFilm, this)
                    rcyFilm.adapter = adapter
                },2000)
            }else if(!pencarian.isEmpty()){
                loading.setMessage("Mencari...")
                loading.show()
                listFilm.clear()
                firestore?.collection("film")?.get()?.addOnSuccessListener { docs ->
                    for (cari in docs) {
                        var bool = true
                        val filmCari = Film("${cari["judul"]}","${cari["tahunRilis"]}","${cari["genre"]}","${cari["rating"]}")
                        if (pencarian.equals("${cari["judul"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                        if (pencarian.equals("${cari["tahunRilis"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                        if (pencarian.equals("${cari["genre"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                    }
                }
                loading.dismiss()

//            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()

                Handler().postDelayed({
                    rcyFilm.layoutManager = LinearLayoutManager(this)
                    val adapter = FilmAdapter(listFilm, this)
                    rcyFilm.adapter = adapter
                },1000)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_custom, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when(item.itemId){
            R.id.log_out -> {
                auth.signOut()
                val intentKeluar = Intent(this, MainActivity::class.java)
                startActivity(intentKeluar)
                this.finish()
            }
            R.id.tambah -> {
                val intentTambah = Intent(this, TambahActivity::class.java)
                startActivity(intentTambah)
            }
            R.id.home -> {
                val intentHome = Intent(this, FilmActivity::class.java)
                startActivity(intentHome)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}