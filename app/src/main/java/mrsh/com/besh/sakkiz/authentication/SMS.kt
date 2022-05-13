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
import kotlinx.android.synthetic.main.activity_sms.*
import java.util.concurrent.TimeUnit

class SMS : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var code = ""
    var mverificationId = ""
    var phoneNumber = ""
    var state = ""
    var a = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)


    }

    override fun onResume() {
        super.onResume()

        auth = Firebase.auth

        val number2 = intent.getStringExtra("number")
        val number = intent.getStringExtra("raqam")

        val raqamKodi = number!!.substring(4, number!!.indexOf("+") + 6)
        val raqam = number!!.substring(6, 9)

        message.text = "Bir martalik kod (+99 8$raqamKodi) $raqam-**-**\nraqamiga yuborildi"

        edt_sms.addTextChangedListener {
            code += edt_sms.text.toString()


            if (edt_sms.text.toString().trim().length == 6) {

                val credential =
                    PhoneAuthProvider.getCredential(number2!!, edt_sms.text.toString().trim())
                signInWithPhoneAuthCredential(credential)
            }


        }
        if (state == "success") {
            Toast.makeText(this, "$a", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, Malumot::class.java)
            intent.putExtra("malumot", a)
            startActivity(intent)
        }


    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    state = "success"
                    a = auth.currentUser!!.phoneNumber.toString()
                    Toast.makeText(this, "$a", Toast.LENGTH_SHORT).show()
                    // val user = task.result?.user


                    val intent = Intent(this, Malumot::class.java)
                    intent.putExtra("malumot", a)
                    startActivity(intent)



                } else {

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {

                    }

                }
            }
    }

}