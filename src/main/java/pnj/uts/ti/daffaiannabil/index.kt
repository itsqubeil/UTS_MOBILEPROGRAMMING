package pnj.uts.ti.daffaiannabil

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
                // Logika untuk menangani klik pada opsi Tambah
                true
            }
            R.id.menu_data_alumni -> {
                // Logika untuk menangani klik pada opsi Menu Data Alumni
                true
            }
            R.id.menu_logout -> {
                // Logika untuk menangani klik pada opsi Logout
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
