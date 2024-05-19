package pnj.uts.ti.daffaiannabil

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pnj.uts.ti.daffaiannabil.databinding.ActivityReadDataBinding

class ReadDataActivity : AppCompatActivity() {
    private lateinit var dbHelper: Databasehelper
    private lateinit var binding: ActivityReadDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = Databasehelper(this)

        try {
            loadData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun loadData() {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM mahasiswa", null)

        val dataList = mutableListOf<String>()
        val nimList = mutableListOf<String>() // Menyimpan NIM dari setiap item
        val namaList = mutableListOf<String>() // Menyimpan nama dari setiap item
        val tempatLahirList = mutableListOf<String>() // Menyimpan tempat lahir dari setiap item
        val tanggalLahirList = mutableListOf<String>() // Menyimpan tanggal lahir dari setiap item
        val alamatList = mutableListOf<String>() // Menyimpan alamat dari setiap item
        val agamaList = mutableListOf<String>() // Menyimpan agama dari setiap item
        val teleponList = mutableListOf<String>() // Menyimpan telepon dari setiap item
        val tahunMasukList = mutableListOf<Int>() // Menyimpan tahun masuk dari setiap item
        val tahunLulusList = mutableListOf<Int>() // Menyimpan tahun lulus dari setiap item
        val pekerjaanList = mutableListOf<String>() // Menyimpan pekerjaan dari setiap item
        val jabatanList = mutableListOf<String>() // Menyimpan jabatan dari setiap item

        if (cursor.moveToFirst()) {
            do {
                val nim = cursor.getString(cursor.getColumnIndexOrThrow("nim"))
                val nama = cursor.getString(cursor.getColumnIndexOrThrow("nama"))
                val tempatLahir = cursor.getString(cursor.getColumnIndexOrThrow("tempat_lahir"))
                val tanggalLahir = cursor.getString(cursor.getColumnIndexOrThrow("tanggal_lahir"))
                val alamat = cursor.getString(cursor.getColumnIndexOrThrow("alamat"))
                val agama = cursor.getString(cursor.getColumnIndexOrThrow("agama"))
                val telepon = cursor.getString(cursor.getColumnIndexOrThrow("telepon"))
                val tahunMasuk = cursor.getInt(cursor.getColumnIndexOrThrow("tahun_masuk"))
                val tahunLulus = cursor.getInt(cursor.getColumnIndexOrThrow("tahun_lulus"))
                val pekerjaan = cursor.getString(cursor.getColumnIndexOrThrow("pekerjaan"))
                val jabatan = cursor.getString(cursor.getColumnIndexOrThrow("jabatan"))

                dataList.add("NIM: $nim\nNama: $nama")
                nimList.add(nim)
                namaList.add(nama)
                tempatLahirList.add(tempatLahir)
                tanggalLahirList.add(tanggalLahir)
                alamatList.add(alamat)
                agamaList.add(agama)
                teleponList.add(telepon)
                tahunMasukList.add(tahunMasuk)
                tahunLulusList.add(tahunLulus)
                pekerjaanList.add(pekerjaan)
                jabatanList.add(jabatan)
            } while (cursor.moveToNext())
        }
        cursor.close()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        binding.lvData.adapter = adapter

        // Tambahkan listener klik pada setiap item ListView
        binding.lvData.setOnItemClickListener { parent, view, position, id ->
            val nim = nimList[position]
            val nama = namaList[position]
            val tempatLahir = tempatLahirList[position]
            val tanggalLahir = tanggalLahirList[position]
            val alamat = alamatList[position]
            val agama = agamaList[position]
            val telepon = teleponList[position]
            val tahunMasuk = tahunMasukList[position]
            val tahunLulus = tahunLulusList[position]
            val pekerjaan = pekerjaanList[position]
            val jabatan = jabatanList[position]

            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("nim", nim)
                putExtra("nama", nama)
                putExtra("tempat_lahir", tempatLahir)
                putExtra("tanggal_lahir", tanggalLahir)
                putExtra("alamat", alamat)
                putExtra("agama", agama)
                putExtra("telepon", telepon)
                putExtra("tahun_masuk", tahunMasuk)
                putExtra("tahun_lulus", tahunLulus)
                putExtra("pekerjaan", pekerjaan)
                putExtra("jabatan", jabatan)
            }
            startActivity(intent)
        }
    }


    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}
