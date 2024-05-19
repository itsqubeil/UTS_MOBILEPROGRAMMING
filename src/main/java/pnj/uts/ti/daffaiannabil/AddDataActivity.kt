package pnj.uts.ti.daffaiannabil

import android.app.DatePickerDialog
import android.content.ContentValues
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pnj.uts.ti.daffaiannabil.databinding.ActivityAddDataBinding
import java.util.Calendar

class AddDataActivity : AppCompatActivity() {
    private lateinit var dbHelper: Databasehelper
    private lateinit var binding: ActivityAddDataBinding
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = Databasehelper(this)
        binding.etTanggalLahir.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnSave.setOnClickListener {
            try {
                val nim = binding.etNim.text.toString()
                val nama = binding.etNama.text.toString()
                val tempatLahir = binding.etTempatLahir.text.toString()
                val tanggalLahir = binding.etTanggalLahir.text.toString()
                val alamat = binding.etAlamat.text.toString()
                val agama = binding.etAgama.text.toString()
                val telepon = binding.etTelepon.text.toString()
                val tahunMasuk = binding.etTahunMasuk.text.toString().toIntOrNull()
                val tahunLulus = binding.etTahunLulus.text.toString().toIntOrNull()
                val pekerjaan = binding.etPekerjaan.text.toString()
                val jabatan = binding.etJabatan.text.toString()

                if (tahunMasuk == null) {
                    Toast.makeText(this, "Tahun masuk harus berupa angka", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val values = ContentValues().apply {
                    put("nim", nim)
                    put("nama", nama)
                    put("tempat_lahir", tempatLahir)
                    put("tanggal_lahir", tanggalLahir)
                    put("alamat", alamat)
                    put("agama", agama)
                    put("telepon", telepon)
                    put("tahun_masuk", tahunMasuk)
                    put("tahun_lulus", tahunLulus)
                    put("pekerjaan", pekerjaan)
                    put("jabatan", jabatan)
                }

                val db = dbHelper.writableDatabase
                val newRowId = db.insert("mahasiswa", null, values)
                if (newRowId != -1L) {

                    Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    finish()  // Close activity
                } else {

                    Toast.makeText(this, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Terjadi kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->

                calendar.set(year, month, dayOfMonth)


                val dateFormat = DateFormat.getDateFormat(applicationContext)
                binding.etTanggalLahir.setText(dateFormat.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}
