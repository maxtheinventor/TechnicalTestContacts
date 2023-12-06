package com.example.technicaltestcontacts.util

import android.content.Context
import android.widget.Toast
import com.example.technicaltestcontacts.R

class ToastUtils {

    companion object{

        fun noContactsSavedInDbToast(context: Context){

            Toast.makeText(context, R.string.noContactsSavedInDb,Toast.LENGTH_SHORT).show()

        }

        fun theFieldContainsAnError(context: Context){

            Toast.makeText(context,R.string.theFieldContainsAnError, Toast.LENGTH_SHORT).show()

        }

        fun theFieldsCantContainErrors(context: Context){

            Toast.makeText(context,R.string.theFieldsCantContainErrors,Toast.LENGTH_SHORT).show()

        }

        fun downloadCompleted(context: Context) {

            Toast.makeText(context,R.string.downloadCompleted_E,Toast.LENGTH_SHORT).show()

        }

        fun contactsSavedSuccessfully(context: Context){

            Toast.makeText(context,R.string.contactsSaved_E,Toast.LENGTH_SHORT).show()

        }

    }

}