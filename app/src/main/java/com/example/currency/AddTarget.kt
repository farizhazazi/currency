package com.example.currency

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.example.currency.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialCalendar
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class AddTarget : AppCompatActivity() {

    private lateinit var btn1 : Button
    private lateinit var btn2: Button
    private lateinit var picker: EditText
    private lateinit var alarmAReceiver: myAReceiver
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_target)

        var alarmr = myAReceiver()

        val btnbatal = findViewById<Button>(R.id.btnBatal)
        val btnset =findViewById<Button>(R.id.btnSet)

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


      //  createNotificationChannel()

    }

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


}