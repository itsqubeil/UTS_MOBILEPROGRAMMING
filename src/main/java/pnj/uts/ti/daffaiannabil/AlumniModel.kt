import android.os.Parcel
import android.os.Parcelable

class AlumniModel(
    var nim: String? = null,
    var nama: String? = null,
    var tempatLahir: String? = null,
    var tanggalLahir: String? = null,
    var alamat: String? = null,
    var agama: String? = null,
    var telepon: String? = null,
    var tahunMasuk: String? = null,
    var tahunLulus: String? = null,
    var pekerjaan: String? = null,
    var jabatan: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nim)
        parcel.writeString(nama)
        parcel.writeString(tempatLahir)
        parcel.writeString(tanggalLahir)
        parcel.writeString(alamat)
        parcel.writeString(agama)
        parcel.writeString(telepon)
        parcel.writeString(tahunMasuk)
        parcel.writeString(tahunLulus)
        parcel.writeString(pekerjaan)
        parcel.writeString(jabatan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlumniModel> {
        override fun createFromParcel(parcel: Parcel): AlumniModel {
            return AlumniModel(parcel)
        }

        override fun newArray(size: Int): Array<AlumniModel?> {
            return arrayOfNulls(size)
        }
    }
}
