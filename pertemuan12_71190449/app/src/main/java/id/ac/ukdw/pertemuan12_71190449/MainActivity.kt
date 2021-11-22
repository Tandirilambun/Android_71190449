package id.ac.ukdw.pertemuan12_71190449

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.emptyList as emptyList1

class MainActivity : AppCompatActivity() {
    var firestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()

        val edtNama   = findViewById<EditText>(R.id.edtNama)
        val edtNim    = findViewById<EditText>(R.id.edtNim)
        val edtIpk    = findViewById<EditText>(R.id.edtIpk)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        val btnCari = findViewById<Button>(R.id.btnCari)
        val txvOutput = findViewById<TextView>(R.id.txvOutput)

        val radioNama = findViewById<RadioButton>(R.id.byNama)
        val radioNim = findViewById<RadioButton>(R.id.byNim)
        val radioIpk = findViewById<RadioButton>(R.id.byIpk)

        val radioDescending = findViewById<RadioButton>(R.id.byDescending)

//        radioNama.isChecked = false
//        radioNim.isChecked = false
//        radioIpk.isChecked = false





        btnSimpan.setOnClickListener {
            val mahasiswa = Mahasiswa(edtNama.text.toString(), edtNim.text.toString(), edtIpk.text.toString().toDouble())
            edtNama.setText("")
            edtNim.setText("")
            edtIpk.setText("")
            firestore?.collection("Mahasiswa")?.add(mahasiswa)

        }
        btnCari.setOnClickListener {
            if(radioNama.isChecked == true){
                firestore?.collection("Mahasiswa")?.whereGreaterThanOrEqualTo("nama",edtNama.text.toString())?.get()!!
                    .addOnSuccessListener { pencarian ->
                        var hasil = ""
                        for ( hasilPencarian in pencarian){
                            hasil += "Nama: ${hasilPencarian["nama"]}\nNIM: ${hasilPencarian["nim"]}\nIPK: ${hasilPencarian["ipk"]}\n\n"
                        }
                        txvOutput.setText(hasil)

                    }
                radioNama.isChecked = false
                radioNim.isChecked = false
                radioIpk.isChecked = false
                radioDescending.isChecked = false
            }
            else if(radioNim.isChecked== true){
                firestore?.collection("Mahasiswa")?.whereGreaterThanOrEqualTo("nim",edtNim.text.toString())?.get()!!
                    .addOnSuccessListener { pencarian ->
                        var hasil = ""
                        for ( hasilPencarian in pencarian){
                            hasil += "Nama: ${hasilPencarian["nama"]}\nNIM: ${hasilPencarian["nim"]}\nIPK: ${hasilPencarian["ipk"]}\n\n"
                        }
                        txvOutput.setText(hasil)
                    }
                radioNama.isChecked = false
                radioNim.isChecked = false
                radioIpk.isChecked = false
                radioDescending.isChecked = false
            }else if (radioIpk.isChecked == true ){
                firestore?.collection("Mahasiswa")?.whereGreaterThanOrEqualTo("ipk",edtIpk.text.toString().toDouble())?.get()!!
                    .addOnSuccessListener { pencarian ->
                        var hasil = ""
                        for ( hasilPencarian in pencarian){
                            hasil += "Nama: ${hasilPencarian["nama"]}\nNIM: ${hasilPencarian["nim"]}\nIPK: ${hasilPencarian["ipk"]}\n\n"
                        }
                        txvOutput.setText(hasil)
                    }
                radioNama.isChecked = false
                radioNim.isChecked = false
                radioIpk.isChecked = false
                radioDescending.isChecked = false
            }

        }
    }
}