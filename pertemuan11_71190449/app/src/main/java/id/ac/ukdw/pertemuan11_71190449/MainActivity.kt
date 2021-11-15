package id.ac.ukdw.pertemuan11_71190449

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var dbHelper: SQLiteOpenHelper? = null
    var db: SQLiteDatabase? = null
    var listPenduduk = ArrayList<String>()
    var adapter: PendudukAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        db = dbHelper?.writableDatabase

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtUsia = findViewById<EditText>(R.id.edtUsia)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        val btnCari = findViewById<Button>(R.id.btnCari)

        btnSimpan.setOnClickListener {
            val values = ContentValues().apply {
                put(DatabaseContract.Penduduk.COLUMN_NAME_NAMA, edtNama.text.toString())
                put(DatabaseContract.Penduduk.COLUMN_NAME_USIA, edtUsia.text.toString())
            }
            db?.insert(DatabaseContract.Penduduk.TABLE_NAME, null, values)
            edtNama.setText("")
            edtUsia.setText("")
        }
        btnCari.setOnClickListener {
            searchData(edtNama.text.toString(), edtUsia.text.toString().toInt())
            edtNama.setText("")
            edtUsia.setText("")
        }

        val rcyPenduduk = findViewById<RecyclerView>(R.id.rcypenduduk)
        rcyPenduduk.layoutManager = LinearLayoutManager(this)
        val adapter = PendudukAdapter(listPenduduk, db)
        rcyPenduduk.adapter = adapter

        reloadData()

    }
    @SuppressLint("Range")
    fun searchData(nama: String, usia:Int) {
        val selection = "${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? AND " +
                "${DatabaseContract.Penduduk.COLUMN_NAME_USIA} LIKE ?"
        val selectionArgs = arrayOf(nama, usia.toString())
        val columns = arrayOf(
            DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
            DatabaseContract.Penduduk.COLUMN_NAME_USIA
        )

        val cursor = db?.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var result = ""
        with(cursor!!) {
            while (moveToNext()) {
                val penduduk = Penduduk(getString(0), getInt(1))
                result += "Nama: ${penduduk.nama}, Usia: ${penduduk.usia}"
            }
        }
        val txvHasil = findViewById<TextView>(R.id.txvHasil)
        txvHasil.text = result
    }
    @SuppressLint("Range")
    fun reloadData(){
        listPenduduk.clear()

        val columns = arrayOf(
            BaseColumns._ID,
            DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
            DatabaseContract.Penduduk.COLUMN_NAME_USIA
        )
        val cursor = db?.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null,
        )

        with(cursor!!){
            while (moveToNext()){
                val nama = getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_NAMA))
                val usia = getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_USIA))
                listPenduduk.add("${nama}(${usia})")
                Log.d("SQLITE","${nama}(${usia})")
            }
        }
        adapter?.notifyDataSetChanged()
    }
}