package pnj.uts.ti.daffaiannabil

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var textViewNim: TextView
    private lateinit var textViewNama: TextView
    private lateinit var textViewTempatLahir: TextView
    private lateinit var textViewTanggalLahir: TextView
    private lateinit var textViewAlamat: TextView
    private lateinit var textViewAgama: TextView
    private lateinit var textViewTelepon: TextView
    private lateinit var textViewTahunMasuk: TextView
    private lateinit var textViewTahunLulus: TextView
    private lateinit var textViewPekerjaan: TextView
    private lateinit var textViewJabatan: TextView
    private lateinit var editTextTanggalLahir: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Inisialisasi EditText untuk tanggal lahir
        editTextTanggalLahir = findViewById(R.id.editTextTanggalLahir)

        // Set onClickListener untuk menampilkan DatePickerDialog
        editTextTanggalLahir.setOnClickListener {
            showDatePickerDialog()
        }


        // Inisialisasi TextView untuk menampilkan detail
        textViewNim = findViewById(R.id.textViewNim)
        textViewNama = findViewById(R.id.et_nama)
        textViewTempatLahir = findViewById(R.id.et_tempat_lahir)
        textViewTanggalLahir = findViewById(R.id.editTextTanggalLahir)
        textViewAlamat = findViewById(R.id.et_alamat)
        textViewAgama = findViewById(R.id.et_agama)
        textViewTelepon = findViewById(R.id.et_telepon)
        textViewTahunMasuk = findViewById(R.id.et_tahun_masuk)
        textViewTahunLulus = findViewById(R.id.et_tahun_lulus)
        textViewPekerjaan = findViewById(R.id.et_pekerjaan)
        textViewJabatan = findViewById(R.id.et_jabatan)

        val buttonEdit = findViewById<Button>(R.id.buttonEdit)
        val buttonDelete = findViewById<Button>(R.id.buttonDelete)

        val id = intent.getStringExtra("id")

        val nim = intent.getStringExtra("nim")
        val nama = intent.getStringExtra("nama")
        val tempatLahir = intent.getStringExtra("tempat_lahir")
        val tanggalLahir = intent.getStringExtra("tanggal_lahir")
        val alamat = intent.getStringExtra("alamat")
        val agama = intent.getStringExtra("agama")
        val telepon = intent.getStringExtra("telepon")
        val tahunMasuk = intent.getIntExtra("tahun_masuk", 0)
        val tahunLulus = intent.getIntExtra("tahun_lulus", 0)
        val pekerjaan = intent.getStringExtra("pekerjaan")
        val jabatan = intent.getStringExtra("jabatan")

        // Tampilkan detail pada TextView masing-masing
        textViewNim.text = nim
        textViewNama.text = nama
        textViewTempatLahir.text = tempatLahir
        textViewTanggalLahir.text = tanggalLahir
        textViewAlamat.text = alamat
        textViewAgama.text = agama
        textViewTelepon.text = telepon
        textViewTahunMasuk.text = tahunMasuk.toString()
        textViewTahunLulus.text = if (tahunLulus == 0) "Belum lulus" else tahunLulus.toString()
        textViewPekerjaan.text = pekerjaan
        textViewJabatan.text = jabatan

        buttonEdit.setOnClickListener {
            val dbHelper = Databasehelper(this)
            val isSuccess = dbHelper.updateData(nim!!, textViewNama.text.toString(), textViewTempatLahir.text.toString(), textViewTanggalLahir.text.toString(), textViewAlamat.text.toString(),
                textViewAgama.text.toString(), textViewTelepon.text.toString(), textViewTahunMasuk.text.toString().toInt(), textViewTahunLulus.text.toString().toInt(), textViewPekerjaan.text.toString(), textViewJabatan.text.toString())

            if (isSuccess) {
                Toast.makeText(this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, index::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Gagal memperbarui data", Toast.LENGTH_SHORT).show()
            }
        }

        buttonDelete.setOnClickListener {
            val dialogClickListener =
                DialogInterface.OnClickListener { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            val dbHelper = Databasehelper(this)
                            dbHelper.deleteItem(nim.toString())
                            Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, index::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish() // Selesai dan kembali ke Activity sebelumnya
                        }

                        DialogInterface.BUTTON_NEGATIVE -> {
                            // Tidak melakukan apa-apa jika pengguna membatalkan penghapusan
                        }
                    }
                }

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setMessage("Apakah Anda yakin ingin menghapus data ini?")
                .setPositiveButton("Ya", dialogClickListener)
                .setNegativeButton("Tidak", dialogClickListener).show()

        }
    }
        private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                // Saat pengguna memilih tanggal, set EditText dengan tanggal yang dipilih
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, day)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                editTextTanggalLahir.setText(sdf.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}

