package com.example.animation_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)




        val userId=intent.getStringExtra(Constants.USER_ID)
        val userEmail=intent.getStringExtra(Constants.USER_EMAIL)

        tvUserName.text= userEmail

        val toTotalQuestion=intent.getIntExtra(Constants.TOTAL_QUESTION,0)//if value not there then 0 will be the default value
        val correctAnswers=intent.getIntExtra(Constants.CORRCT_ANSWERS,0)//if value not there then 0 will be the default value

        tvResult.text="You have Scored $correctAnswers out of $toTotalQuestion"

        when(correctAnswers)
        {

        in  0..3 ->  tvLevel.text="You are Weak"
            in 4..7 -> tvLevel.text="You are Good"
            in 8..10 -> tvLevel.text="You are Excellent"


        }




        btn_finish.setOnClickListener{

            startActivity(Intent(this,GameActivity::class.java))
            finish()


        }


        btn_logout.setOnClickListener{

            //logout from the app
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@FinishActivity, IntroActivity::class.java))
            finish()



        }
    }
}