package id.ac.ukdw.pertemuan7_71190449

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listContact = arrayListOf<Contact>()
        listContact.add(Contact("Mama","082193687072",R.mipmap.contact1))
        listContact.add(Contact("Papa","082193687072",R.mipmap.contact1))
        listContact.add(Contact("Kakak","082193687072",R.mipmap.contact1))
        listContact.add(Contact("Adek","082193687072",R.mipmap.contact1))
        listContact.add(Contact("Nenek","082193687072",R.mipmap.contact1))
        listContact.add(Contact("Kakek","082193687072",R.mipmap.contact1))

        val rcyContact = findViewById<RecyclerView>(R.id.rcyView)
        rcyContact.layoutManager = LinearLayoutManager(this)
        val adapter = ContactAdapter(listContact, this)
        rcyContact.adapter = adapter

    }
}