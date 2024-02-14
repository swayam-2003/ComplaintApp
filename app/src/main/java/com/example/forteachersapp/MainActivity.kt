package com.example.forteachersapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private var t1: EditText? = null
    private var t2: EditText? = null
    private var t3: EditText? = null
    private var t4: EditText? = null
    private var t5: EditText? = null
    private var t6: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        t1 = findViewById(R.id.t1)
        t2 = findViewById(R.id.t2)
        t3 = findViewById(R.id.t3)
        t4 = findViewById(R.id.t4)
        t5 = findViewById(R.id.t5)
        t6 = findViewById(R.id.t6)
    }

    fun process(view: View?) {
        val studentName = t1!!.text.toString().trim { it <= ' ' }
        val usnNumber = t2!!.text.toString().trim { it <= ' ' }
        val complaint = t3!!.text.toString().trim { it <= ' ' }
        val subject = t4!!.text.toString().trim { it <= ' ' }
        val email = t5!!.text.toString().trim { it <= ' ' }
        val section = t6!!.text.toString().trim { it <= ' ' }
        val obj = dataholder(studentName, usnNumber, complaint, subject, email, section)
        val db = FirebaseDatabase.getInstance()
        db.getReference("students").child(usnNumber).setValue(obj)
            .addOnCompleteListener { task: Task<Void?> ->
                if (task.isSuccessful) {
                    // Sending email
                    sendEmail(
                        email, "COMPLAINT", """
     Dear $studentName,
     
     There has been a complaint registered under your name.
     The complaint is $complaint. Kindly meet the HOD tomorrow.
     
     Regards,
     Complaint APP
     """.trimIndent()
                    )
                    Toast.makeText(applicationContext, "Value Inserted", Toast.LENGTH_LONG).show()
                    clearEditTextFields()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Error: " + task.exception!!.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun sendEmail(email: String, subject: String, message: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)

        // Check if there is an email app available
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "No email app found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearEditTextFields() {
        t1!!.setText("")
        t2!!.setText("")
        t3!!.setText("")
        t4!!.setText("")
        t5!!.setText("")
        t6!!.setText("")


    }
}
