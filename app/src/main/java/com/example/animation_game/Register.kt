package com.example.animation_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        tv_login_activity.setOnClickListener{

            startActivity(Intent(this@Register,MainActivity::class.java))


        }


        btn_register.setOnClickListener{




            if(et_register_email.text.toString().trim{it <=' ' }.isEmpty())
            {

                Toast.makeText(this@Register,"Please enter email",Toast.LENGTH_SHORT).show()


            }

            else if(et_register_password.text.toString().trim{it <=' ' }.isEmpty())

            {

                Toast.makeText(this@Register,"Please enter password",Toast.LENGTH_SHORT).show()


            }

            else

            {
                Constants.customProgressDialogFunction(this)
                val email =et_register_email.text.toString().trim{it <=' ' }
                val password =et_register_password.text.toString().trim{it <=' ' }

                //create an instance and create a register a user with email anda password

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(OnCompleteListener<AuthResult>{ task ->

                        //If the registration is successfully done
                        if (task.isSuccessful)
                        {
                            //Firebase register user
                            val firebaseUser : FirebaseUser = task.result!!.user!!

                            Toast.makeText(this@Register,"You are registered successfully",Toast.LENGTH_SHORT).show()


                            //after this send the user to the MainActivity
                            val intent = Intent(this@Register,GameActivity::class.java)
                            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK   //get rid of extra layer of activities(Example login activity which runs on the background)
                            intent.putExtra(Constants.USER_ID,firebaseUser.uid)
                            intent.putExtra(Constants.USER_EMAIL,email)
                            startActivity(intent)
                            finish() //close the current Activity

                        }

                        else
                        {
                            //If register not successful
                            Toast.makeText(this@Register,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()

                        }

                    })



            }



        }





    }
}