package id.ac.ukdw.pertemuan5_71190449

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val kirim = findViewById<Button>(R.id.btnKirim)
        //App Activity
        kirim.setOnClickListener {
            val username = findViewById<EditText>(R.id.edtUsername)
            val password = findViewById<EditText>(R.id.edtPassword)

            val uname = username.text.toString()
            val pass = password.text.toString()

            if(uname.equals("admin") && pass.equals("12345")){
                val intent = Intent(this, HasilActivity::class.java)
                intent.putExtra("username", "admin")
                startActivity(intent)
            }else{
                val message = AlertDialog.Builder(this)
                message.setMessage("Username atau Password Salah!").setNegativeButton("Retry",null).create().show()
            }
        }
    }
}
