package pnj.uts.ti.daffaiannabil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class index : AppCompatActivity() {
    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_index)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(Home())
                    true
                }
                R.id.Berita -> {
                    loadFragment(beritaFragment())
                    true
                }
                R.id.profil -> {
                    loadFragment(profilFragment())
                    true
                }
                else -> false
            }
        }
        // Menampilkan fragment Home ketika activity pertama kali dibuat
        loadFragment(Home())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_dropdown, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_tambah -> {
                val intent = Intent(this, AddDataActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_data_alumni -> {
                val intent = Intent(this, ReadDataActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_logout -> {
                val sharedPreferences = getSharedPreferences("Profil", Context.MODE_PRIVATE)
                sharedPreferences.edit().clear().apply()

                val intent = Intent(this, Login::class.java)
                startActivity(intent)


                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}
