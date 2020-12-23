package com.example.contrade.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contrade.R

class FragmentSetari : Fragment() {
    companion object {

        fun newInstance(): FragmentSetari {
            return FragmentSetari()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_piete_tranzactionare, container, false)


        return view
    }
}