package com.example.contrade

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

class DialogChangeUsername: DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater ,
                              container: ViewGroup? ,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dialog_change_username,
                container,false)
        //set background transparent
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        return view
    }


}