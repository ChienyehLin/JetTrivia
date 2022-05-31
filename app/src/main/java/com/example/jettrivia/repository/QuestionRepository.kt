package com.example.jettrivia.repository

import android.util.Log
import com.example.jettrivia.data.DataOrException
import com.example.jettrivia.model.Question
import com.example.jettrivia.model.QuestionItem
import com.example.jettrivia.network.QuestionApi
import javax.inject.Inject

class QuestionRepository@Inject constructor(private val api: QuestionApi) {
    private val dataOrException= DataOrException<ArrayList<QuestionItem>,Boolean,Exception>()

    suspend fun getAllQuestions():DataOrException<ArrayList<QuestionItem>,Boolean,Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if(dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false

        }catch (exception: java.lang.Exception){
            dataOrException.e = exception
            Log.e("Exception", "getAllQuestions: ${dataOrException.e!!.localizedMessage}" )
        }
        return dataOrException
        //下一步是建立ViewModel
    }


}