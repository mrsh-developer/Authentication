package mrsh.com.besh.sakkiz.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sms.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var phoneNumber = ""
    var mverificationId = ""
    var code = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auth = Firebase.auth

        kirish.setOnClickListener {



            if (edt_number.text.toString().trim().isNotEmpty()) {
                sendSMS()
                Toast.makeText(this, "So'rov jo'natildi Kuting", Toast.LENGTH_SHORT).show()

            }




        }





    }





    fun sendSMS() {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(edt_number.text.toString().trim())
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {

        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Toast.makeText(this@MainActivity, "Qaytatdan Urinib Ko'ring", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(p0, p1)

            mverificationId = p0

            val toString = edt_number.text.toString().trim()

            phoneNumber = toString

            Toast.makeText(this@MainActivity, "onCodeSent", Toast.LENGTH_SHORT).show()


            val intent = Intent(this@MainActivity, SMS::class.java)
            intent.putExtra("number", p0)
            intent.putExtra("raqam",edt_number.text.toString().trim())

            startActivity(intent)
        }

    }



}