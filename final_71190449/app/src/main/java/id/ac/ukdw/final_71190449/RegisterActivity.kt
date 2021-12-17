package id.ac.ukdw.final_71190449

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity:AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        val edtEmail = findViewById<EditText>(R.id.emailNew)
        val edtPassword = findViewById<EditText>(R.id.passwordNew)
        val edtPassConf = findViewById<EditText>(R.id.passwordConfirmNew)

        val btnReg = findViewById<Button>(R.id.sign_up_btn)


        val user = auth.currentUser

        btnReg.setOnClickListener {
            var email = edtEmail.text.toString()
            var pass = edtPassword.text.toString()
            var passConfirm = edtPassConf.text.toString()
            if(email.isEmpty()){
                edtEmail.error = "Isi E-mail"
            }else if(pass.isEmpty()){
                edtPassword.error = "Isi Password"
            }else if(passConfirm.isEmpty()){
                edtPassConf.error = "Isi Password"
            }
            else if (pass.equals(passConfirm)){
                auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Register", "createUserWithEmail:success")
                            val user = auth.currentUser
                            val intentLogin = Intent(this, MainActivity::class.java)
                            startActivity(intentLogin)
                            finish()
                            Toast.makeText(baseContext, "Registration Success.",
                                Toast.LENGTH_SHORT).show()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Register", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Registration failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                edtEmail.setText("")
                edtPassword.setText("")
                edtPassConf.setText("")
            }else if(!pass.equals(passConfirm)){
                edtPassConf.error = "Password tidak sama"
                Toast.makeText(this, "Registrasi Gagal", Toast.LENGTH_SHORT).show()
            }
        }

    }


}