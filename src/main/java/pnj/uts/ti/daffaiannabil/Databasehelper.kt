package pnj.uts.ti.daffaiannabil

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Databasehelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mahasiswa.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_MAHASISWA = "mahasiswa"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NIM = "nim"
        private const val COLUMN_NAMA = "nama"
        private const val COLUMN_TEMPAT_LAHIR = "tempat_lahir"
        private const val COLUMN_TANGGAL_LAHIR = "tanggal_lahir"
        private const val COLUMN_ALAMAT = "alamat"
        private const val COLUMN_AGAMA = "agama"
        private const val COLUMN_TELEPON = "telepon"
        private const val COLUMN_TAHUN_MASUK = "tahun_masuk"
        private const val COLUMN_TAHUN_LULUS = "tahun_lulus"
        private const val COLUMN_PEKERJAAN = "pekerjaan"
        private const val COLUMN_JABATAN = "jabatan"

        private const val TABLE_CREATE = """
            CREATE TABLE $TABLE_MAHASISWA (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NIM TEXT NOT NULL,
                $COLUMN_NAMA TEXT NOT NULL,
                $COLUMN_TEMPAT_LAHIR TEXT NOT NULL,
                $COLUMN_TANGGAL_LAHIR TEXT NOT NULL,
                $COLUMN_ALAMAT TEXT NOT NULL,
                $COLUMN_AGAMA TEXT NOT NULL,
                $COLUMN_TELEPON TEXT NOT NULL,
                $COLUMN_TAHUN_MASUK INTEGER NOT NULL,
                $COLUMN_TAHUN_LULUS INTEGER,
                $COLUMN_PEKERJAAN TEXT,
                $COLUMN_JABATAN TEXT
            )
        """
    }
    fun updateData(nim: String, nama: String, tempatLahir: String, tanggalLahir: String,
                   alamat: String, agama: String, telepon: String, tahunMasuk: Int,
                   tahunLulus: Int, pekerjaan: String, jabatan: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAMA, nama)
        contentValues.put(COLUMN_TEMPAT_LAHIR, tempatLahir)
        contentValues.put(COLUMN_TANGGAL_LAHIR, tanggalLahir)
        contentValues.put(COLUMN_ALAMAT, alamat)
        contentValues.put(COLUMN_AGAMA, agama)
        contentValues.put(COLUMN_TELEPON, telepon)
        contentValues.put(COLUMN_TAHUN_MASUK, tahunMasuk)
        contentValues.put(COLUMN_TAHUN_LULUS, tahunLulus)
        contentValues.put(COLUMN_PEKERJAAN, pekerjaan)
        contentValues.put(COLUMN_JABATAN, jabatan)

        val result = db.update(TABLE_MAHASISWA, contentValues, "$COLUMN_NIM = ?", arrayOf(nim))
        return result != -1
    }
    fun deleteItem(nim: String): Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_MAHASISWA, "$COLUMN_NIM = ?", arrayOf(nim))
        return result != -1
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MAHASISWA")
        onCreate(db)
    }
}