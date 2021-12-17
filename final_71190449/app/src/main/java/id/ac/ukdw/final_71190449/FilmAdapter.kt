package id.ac.ukdw.final_71190449

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.text.FieldPosition

class FilmAdapter(private val filmList: ArrayList<Film>, var context: Context):RecyclerView.Adapter<FilmAdapter.FilmHolder>() {
    inner class FilmHolder(val view: View): RecyclerView.ViewHolder(view) {
        var firestore: FirebaseFirestore? = null
        fun bind(film: Film, context: Context) {

            firestore = FirebaseFirestore.getInstance()

            val judul = view.findViewById<TextView>(R.id.txvJudul)
            val rilis = view.findViewById<TextView>(R.id.txvTahun)
            val genre = view.findViewById<TextView>(R.id.txvGenre)
            val rating = view.findViewById<TextView>(R.id.txvRating)

            judul.setText(film.judul)
            rilis.setText(film.tahunRilis)
            genre.setText(film.genre)
            rating.setText(film.rating)


            val btnEdit = view.findViewById<Button>(R.id.btnEdit)
            val btnHapus = view.findViewById<Button>(R.id.btnHapus)


            btnEdit.setOnClickListener {
                val intent = Intent(context, EditActivity::class.java)
                intent.putExtra("judul", judul.text)
                intent.putExtra("rilis", rilis.text)
                intent.putExtra("genre", genre.text)
                intent.putExtra("rating", rating.text)
                context.startActivity(intent)
            }
            btnHapus.setOnClickListener {

                firestore?.collection("film")?.whereEqualTo("judul",judul.text)?.get()!!.addOnSuccessListener {
                    for (hapus in it){
                        firestore?.collection("film")?.document(hapus.id)?.delete()
                    }
                }
                filmList.removeAt(adapterPosition)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FilmHolder(v)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(filmList[position],context)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }
}
