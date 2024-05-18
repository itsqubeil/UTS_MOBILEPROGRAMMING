package pnj.uts.ti.daffaiannabil

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username == "admin" && password == "password") {

                val sharedPreferences = getSharedPreferences("PRofil", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("email", "test@mail.com")
                editor.putString("nim", "2207411029")
                editor.putString("nama", "dapoeck")
                editor.putString("kelas", "4A")
                editor.apply()
                with(sharedPreferences.edit()) {
                    putBoolean("isLoggedIn", true)
                    apply()
                }
                val email = sharedPreferences.getString("email", "N/A")
                val nim = sharedPreferences.getString("nim", "N/A")
                val nama = sharedPreferences.getString("nama", "N/A")
                val kelas = sharedPreferences.getString("kelas", "N/A")

                Toast.makeText(this, "Login Successful!\nEmail: $email\nNIM: $nim\nName: $nama\nClass: $kelas", Toast.LENGTH_LONG).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}