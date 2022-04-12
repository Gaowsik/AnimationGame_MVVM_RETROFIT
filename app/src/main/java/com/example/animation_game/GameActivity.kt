package com.example.animation_game

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.retrofitexample.network.Character
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity(), View.OnClickListener {


    //lazy mean viewModel will be initialized only once it is needed
    private val viewModel:MainViewModel by lazy {

        ViewModelProvider(this).get(MainViewModel::class.java)

    }

    private var mCurrrentPosition:Int=1
    private var mQuestionList:ArrayList<Question>?=null
    private var mSelectedPosition:Int=0 //which option is selected
    private var toTotalCorrectAnswer : Int=0
    private var mUserId:String?=null
    private var mUserEmail:String?=null
    private var isSelected:Boolean=false
    var charlist:ArrayList<Character>? = null



    //lazy mean only initialized when is needed




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        viewModel.characterLiveData.observe(this,{characters ->


            charlist= characters as ArrayList<Character>?
            Log.d("characters1",""+ charlist)
            setQuestion()


        })


/* val client=ApiClient.apiService.fetchCharacters("1")


        client.enqueue(object :retrofit2.Callback<CharacterResponce> {
            override fun onResponse(
                call: Call<CharacterResponce>,
                response: Response<CharacterResponce>
            ) {
                if(response.isSuccessful)
                {


                    val result = response.body()?.result
                    charlist= result as ArrayList<Character>?
                    Log.d("characters1",""+ charlist)
                    setQuestion()


                }
            }

            override fun onFailure(call: Call<CharacterResponce>, t: Throwable) {
               Log.e("failed",""+t.message)
            }


        }) */


        mUserId=intent.getStringExtra(Constants.USER_ID)
        mUserEmail=intent.getStringExtra(Constants.USER_EMAIL)

        mQuestionList=Constants.getQuestions()







        tv_option_one.setOnClickListener (this)//first you start to do this it will give error because by default class is not implemented OnclickListener//then we code under Onclick method
        tv_option_two.setOnClickListener (this)
        tv_option_three.setOnClickListener (this)
        btn_finish.setOnClickListener(this)



    }





    private fun setQuestion()
    {







        if(mCurrrentPosition==mQuestionList!!.size)
        {
            btn_finish.text="FINISH"
        }

        else
        {
            btn_finish.text="SUBMIT"
        }



        progressBar.max = 10
        val question: Question = mQuestionList!![mCurrrentPosition-1]
        defaultOptionView()

        progressBar.progress = mCurrrentPosition
        tv_process.text = "$mCurrrentPosition" + "/" + progressBar.max
       // iv_image.setImageResource(question.image)

       //api image
        val character= charlist!![mCurrrentPosition-1]

        //add api image
        iv_image.load(character!!.image)




        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree



    }



    private fun defaultOptionView()
    {

        val options=ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)



        for(option in options)
        {

            option.setTextColor(Color.parseColor("#808080"))//set the default color to grey for the option (when it is selected it changes to another color)
            option.typeface= Typeface.DEFAULT//default appearence
            option.background= ContextCompat.getDrawable(this,R.drawable.default_option_border)


        }

    }

    override fun onClick(v: View?)
    {


        when(v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(tv_option_one, 1)
            }

            R.id.tv_option_two -> {
                selectedOptionView(tv_option_two, 2)
            }

            R.id.tv_option_three -> {
                selectedOptionView(tv_option_three, 3)
            }



            R.id.btn_finish ->{


                if (mSelectedPosition == 0 && isSelected) {
                    isSelected = false
                    mCurrrentPosition++


                    when {
                        mCurrrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        } //go to next question

                        else ->
                        {
                            val intent= Intent(this,FinishActivity::class.java)
                            intent.putExtra(Constants.USER_ID,mUserId)
                            intent.putExtra(Constants.USER_EMAIL,mUserEmail)
                            intent.putExtra(Constants.CORRCT_ANSWERS,toTotalCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTION,mQuestionList!!.size)
                            startActivity(intent)
                        }


                    }

                }

                else if(isSelected){
                    val question = mQuestionList?.get(mCurrrentPosition - 1)

                    if (question!!.corrctAnswer != mSelectedPosition)
                    {
                        answerView(mSelectedPosition, R.drawable.wrong_option_border) //wrong answer background changes to red
                    }
                    else
                    {
                        toTotalCorrectAnswer++
                    }

                    answerView(question.corrctAnswer, R.drawable.correct_option_border) //correct answer background changes to green

                    if (mCurrrentPosition == mQuestionList!!.size) {
                        btn_finish.text = "FINISH"
                    } else {
                        btn_finish.text = "GO TO NEXT QUESTION"
                    }

                    //to go to next question because we set above if(mSelectedPosition ==0) only it goes to question(setQuestion())
                    mSelectedPosition=0

                }
                else
                {
                    Toast.makeText(this,"Please select the answer", Toast.LENGTH_LONG).show()
                }



















            }


        }





















    }





    private fun answerView(answer: Int, drawableView: Int)
    {
        when(answer)
        {
            1 ->  {tv_option_one.background=ContextCompat.getDrawable(this,drawableView)}

            2 -> {tv_option_two.background=ContextCompat.getDrawable(this,drawableView)}

            3 -> {tv_option_three.background=ContextCompat.getDrawable(this,drawableView)}




        }


    }












    private fun selectedOptionView(tv: TextView, selectedOptionNum:Int)
    {

        defaultOptionView() //we reset everything for default
        isSelected = true

        mSelectedPosition=selectedOptionNum



        tv.setTextColor(Color.parseColor("#363A43"))//set the default color to grey for the option (when it is selected it changes to another color)
        tv.setTypeface(tv.typeface,Typeface.BOLD) //set the selected textview to bold
        tv.background=ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)



    }








}

