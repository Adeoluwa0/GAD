package com.example.gadsleaderboard

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_form_submission.*

class FormSubmission : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_submission)

        btnFinal.setOnClickListener{
            var dialogBuilder = android.app.AlertDialog.Builder(this)

            dialogBuilder.setMessage("Are you sure ?")
                .setCancelable(true)
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->dialogBuilder.setMessage("Submission Successful")
                    .setIcon(R.drawable.ic_round_check_circle)})

            var alert = dialogBuilder.create()
            alert.show()

        }
    }
}