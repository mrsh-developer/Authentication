package mrsh.com.besh.sakkiz.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_malumot.*
import kotlinx.android.synthetic.main.activity_sms.*

class Malumot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_malumot)


    }

    override fun onResume() {
        super.onResume()
        var number = intent.getStringExtra("malumot")

        val raqamKodi = number!!.substring(4, number!!.indexOf("+") + 6)
        val raqam = number!!.substring(6, 9)
        val raqam1 = number!!.substring(10, 12)
        val raqam2 = number!!.substring(12, 14)

        message.text = "(+99 8$raqamKodi) $raqam-$raqam1-$raqam2"

    }
}