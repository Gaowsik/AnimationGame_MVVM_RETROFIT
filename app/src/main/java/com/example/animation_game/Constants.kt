package com.example.animation_game

import android.app.Dialog
import android.content.Context

object Constants {

    const val USER_ID: String = "user_id"
    const val USER_EMAIL: String = "email_id"
    const val TOTAL_QUESTION: String = "total_question"
    const val CORRCT_ANSWERS: String = "correct_answer"


    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        // 1
        val que1 = Question(
            1,
            R.drawable.one,
            "Rick + 7 letters surname", "Rick + 6 letters surname",
            "Rick + 8 letters surname", 1
        )

        questionsList.add(que1)


        val que2 = Question(
            2,
            R.drawable.one,
            "Morty + 7 letters surname", "Morty + 9 letters surname",
            "Morty + 5 letters surname", 3
        )

        questionsList.add(que2)


        val que3 = Question(
            3,
            R.drawable.one,
            "Summer + 12 letters surname", "Summer + 5 letters surname",
            "Summer + 8 letters surname", 2
        )

        questionsList.add(que3)


        val que4 = Question(
            4,
            R.drawable.one,
            "Beth + 5 letters surname", "Beth + 4 letters surname ",
            "Beth + 8 letters surname", 1
        )

        questionsList.add(que4)

        val que5 = Question(
            5,
            R.drawable.one,
            "Jerry + 9 letters surname", "Jerry + 7 letters surname",
            "Jerry + 5 letters surname", 3
        )

        questionsList.add(que5)



        val que6 = Question(
            6,
            R.drawable.one,
            "Abadango Cluster + 12 letters surname", "Abadango Cluster Abadango Cluster + 8 letters surname",
            "Abadango Cluster + 5 letters surname", 2
        )

        questionsList.add(que6)


        val que7 = Question(
            7,
            R.drawable.one,
            "Albert + 8 letters surname", "Albert + 7 letters surname",
            "Albert + 5 letters surname", 1
        )

        questionsList.add(que7)

        val que8 = Question(
            8,
            R.drawable.one,
            "Adjudicator + 4 letters surname", "Adjudicator + 7 letters surname",
            "Adjudicator + 5 letters surname", 1
        )

        questionsList.add(que8)

        val que9 = Question(
            9,
            R.drawable.one,
            "Agency + 10 letters surname", "Agency + 8 letters surname",
            "Agency + 5 letters surname", 2
        )

        questionsList.add(que9)


        val que10 = Question(
            9,
            R.drawable.one,
            "Alan + 12 letters surname", "Alan + 9 letters surname",
            "Alan + 5 letters surname", 3
        )

        questionsList.add(que10)


        return questionsList


    }


    fun customProgressDialogFunction(context:Context)
    {

        val customProgressDialog= Dialog(context)

        /*set the screen content from a layout resource.  the resource will be inflated, adding all top level views to the screen*/

        customProgressDialog.setContentView(R.layout.dialog_progress)

        //start the dialog and display it on screen
        customProgressDialog.show()






    }



}