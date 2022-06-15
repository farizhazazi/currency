package com.example.currency

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.icu.text.SimpleDateFormat
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.*
import androidx.versionedparcelable.VersionedParcelize
import com.example.currency.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialCalendar
import com.google.android.material.datepicker.MaterialDatePicker
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*

class AddTarget : AppCompatActivity(), View.OnClickListener {

    private lateinit var btn1 : Button
    private lateinit var btn2: Button
    private lateinit var picker: EditText
    private lateinit var person: EditText
    private lateinit var number: EditText
    private lateinit var date: EditText
    private lateinit var alarmAReceiver: myAReceiver
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var calendar: Calendar

    //SQL
    var mysql : DBHelper? = null

    val addname = findViewById<EditText>(R.id.editTextTextPersonName)
    val price =findViewById<EditText>(R.id.editTextNumber)

    val calenders = findViewById<EditText>(R.id.editTextDate)

    val edit_text1 = findViewById<EditText>(R.id.editTextTextPersonName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_target)

        var alarmr = myAReceiver()

        val btnbatal = findViewById<Button>(R.id.btnBatal)
        val btnset =findViewById<Button>(R.id.btnSet)

        val btnsave =findViewById<Button>(R.id.btnSSave)
        val edit_text2 =findViewById<EditText>(R.id.edit_text2)
        val btnRead = findViewById<Button>(R.id.btnread)
        val edit_text3 = findViewById<EditText>(R.id.edit_text3)
        val btnDelete = findViewById<Button>(R.id.btnreaddir)
        val btnreaddir = findViewById<Button>(R.id.btngetfile)

        val addname = findViewById<EditText>(R.id.editTextTextPersonName)
        val price =findViewById<EditText>(R.id.editTextNumber)


        val calenders = findViewById<EditText>(R.id.editTextDate)
        calenders.transformIntoDatePicker(this, "MM/dd/yyyy")
        calenders.transformIntoDatePicker(this, "MM/dd/yyyy", Date())


        var filter = IntentFilter()
        //filter.addAction(Intent.A)
        registerReceiver(alarmr,filter)

        btnbatal.setOnClickListener{
            val intent = Intent(this@AddTarget, TTarget::class.java)
            startActivity(intent)
        }

            btnset.setOnClickListener{


          alarmManager   = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(this, myAReceiver::class.java)

            pendingIntent = PendingIntent.getBroadcast(this,10,intent,PendingIntent.FLAG_UPDATE_CURRENT)

          val c = Calendar.getInstance()
            c.timeInMillis = System.currentTimeMillis()
            //set alarm to start at 10 PM
            c.set(Calendar.HOUR_OF_DAY,6)
            c.set(Calendar.MINUTE,32)

            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,calendar.timeInMillis, AlarmManager.INTERVAL_DAY,pendingIntent
            )

            createNotificationChannel()

        }

        //SQL
        mysql = DBHelper(this)
        //mysql!!.addTarget(targettmp)
        updateAdapter()
        btnset.setOnClickListener(this)

        //penyimpanan internal
        btnsave.setOnClickListener(){
            var output = openFileOutput("${edit_text2.text}).text",
                Context.MODE_PRIVATE).apply {
                    write(edit_text2.text.toString().toByteArray())
                close()
            }
            edit_text1.text.clear()
            //edit_text2.text.clear()
            Toast.makeText(this,"File Save",Toast.LENGTH_SHORT).show()
        }

        btnRead.setOnClickListener {
            edit_text1.text.clear()
            try {
                var input = openFileInput("${edit_text3.text}.txt")
                    .bufferedReader().useLines {
                        for (text in it.toList()) {
                            edit_text1.setText("${edit_text1.text}\n$text")
                        }
                    }
            } catch (e: FileNotFoundException) {
                edit_text1.setText("File Not Found")
            } catch (e: IOException) {
                edit_text1.setText("File Cant be read")
            }
            edit_text3.text.clear()
        }
        btnreaddir.setOnClickListener {
            if (fileList().size != 0)
                for (i in fileList())
                    edit_text1.setText("${edit_text1.text}\n$i")
            else
                edit_text1.setText("File Empty")
        }

        btnDelete.setOnClickListener {
            if (fileList().size !=0

            ) {
                for (i in fileList())
                    deleteFile(i)
                edit_text1.setText("All File Deteled")
            } else
                edit_text1.setText("File Empty")
        }

    }


    //  createNotificationChannel()
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name : CharSequence = "androidReminderChannel"
            val description = "Channel for Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("android",name,importance)
            channel.description = description
            val notificationManager = getSystemService(NotificationManager::class.java)
        }

    }

    fun EditText.transformIntoDatePicker(context: Context, format: String, maxDate: Date? = null) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false

        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat(format, Locale.UK)
                setText(sdf.format(myCalendar.time))
            }

        setOnClickListener {
            DatePickerDialog(
                context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                maxDate?.time?.also { datePicker.maxDate = it }
                show()
            }
        }
    }


    //nambah database
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnSet -> {
/*
                val targettmp  = TargetP()
                targettmp.nama = addname.text.toString()
                targettmp.harga = price.text.toString()
                targettmp.jangka = calenders.text.toString()


                var hasil  = mysql?.addTarget(targettmp)
                if(hasil!=-1L ){
                    Toast.makeText(this,"Tersimpan",Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                }*/
                updateAdapter()
                addname.text.clear()
                price.text.clear()
                calenders.text.clear()
            }
        }
    }

    //baca database
    private fun updateAdapter(){
        /*doAsync{
            var listnama = mysql?.view()?.toTypedArray()
            runOnUiThread(){
                if( != null && listnama?.size != 0){
                    val arrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, listnama)
                        .adapter = arrayAdapter
                        .onItemSelectedListener = object : AdapterView.onItemSelectedListener{
                        override fun onNothingSelected(v: AdapterView<*>?){

                        }

                        override fun onItemSelected(v:AdapterView<*>?, v1: View?, a: Int, l:Long){
                        Toast.makeText(this@AddTarget, listnama?. get(a), Toast.LENGTH_SHORT).show()
                        }

                }
            }
        }*/
    }


}