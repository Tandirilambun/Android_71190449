package id.ac.ukdw.pertemuan6_71190449

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class AFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val aFr = inflater.inflate(R.layout.fragment_a, container, false)
        val txt = aFr.findViewById<TextView>(R.id.txtView)
        txt.setOnClickListener {
            Toast.makeText(context,"Ini Fragment A", Toast.LENGTH_SHORT).show()
        }
        return aFr
    }
}