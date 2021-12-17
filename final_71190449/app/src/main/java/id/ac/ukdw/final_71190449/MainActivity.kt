package id.ac.ukdw.final_71190449

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email1 = findViewById<EditText>(R.id.email)
        val password2 = findViewById<EditText>(R.id.password)
        val btnMasuk = findViewById<Button>(R.id.sign_in_btn)
        val btnReg = findViewById<Button>(R.id.btnRegis)

        val masuk = findViewById<Button>(R.id.btnMasuk)
        val edtOr = findViewById<TextView>(R.id.textView2)

        masuk.setOnClickListener {
            email1.visibility = View.VISIBLE
            password2.visibility = View.VISIBLE
            btnMasuk.visibility = View.VISIBLE
            btnReg.visibility = View.GONE
            masuk.visibility = View.GONE
            edtOr.visibility = View.GONE
            auth = FirebaseAuth.getInstance()
            val user = auth.currentUser
            if(user != null){
                val filmIntent = Intent(this, FilmActivity::class.java)
                startActivity(filmIntent)
                finish()
            }
            btnMasuk.setOnClickListener {
                var email = email1.text.toString()
                var password = password2.text.toString()
                if (email.isEmpty() || password.isEmpty()){
                    email1.error = "Isi email"
                    password2.error = "Isi password"
                }else{
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Sign in status", "signInWithEmail:success")
                                val userLogin = auth.currentUser
                                Toast.makeText(baseContext, "Login as ${userLogin?.email}",
                                    Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, FilmActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Sign in status", "signInWithEmail:failure", task.exception)
                                Toast.makeText(baseContext, "Login Failed.",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    email1.setText("")
                    password2.setText("")
                }
            }
        }
        btnReg.setOnClickListener {
            val intentRegis = Intent(this, RegisterActivity::class.java)
            startActivity(intentRegis)
        }

    }
}