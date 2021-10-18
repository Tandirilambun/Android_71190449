package id.ac.ukdw.pertemuan7_71190449

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter (val listContact: ArrayList<Contact>, var context: Context): RecyclerView.Adapter<ContactAdapter.ContactHolder>() {
    class ContactHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bind(contact: Contact, context: Context){
            view.findViewById<ImageView>(R.id.contactPhoto).setImageResource(contact.photo)
            view.findViewById<TextView>(R.id.contactName).text = contact.name
            view.findViewById<TextView>(R.id.contactNumber).text = contact.noHp
            view.setOnClickListener {
                val i : Intent = Intent(view.context, ContactDetail::class.java)
                i.putExtra("kontakNama", contact.name)
                i.putExtra("kontakNomor", contact.noHp)
                i.putExtra("kontakPoto", contact.photo)
                view.context.startActivity(i)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent, false)
        return ContactHolder(v)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(listContact[position], context)
    }

    override fun getItemCount(): Int {
        return listContact.size
    }
}