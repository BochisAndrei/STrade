package com.example.contrade.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contrade.R

class FragmentPortofoliu : Fragment() {
    companion object {

        fun newInstance(): FragmentPortofoliu {
            return FragmentPortofoliu()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_portofoliu, container, false)


        return view
    }
}