package id.ac.ukdw.pertemuan6_71190449

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class BFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bFr = inflater.inflate(R.layout.fragment_b, container, false)
        val txt = bFr.findViewById<TextView>(R.id.txtView)
        txt.setOnClickListener {
            Toast.makeText(context,"Ini Fragment B", Toast.LENGTH_SHORT).show()
        }
        return bFr
    }
}