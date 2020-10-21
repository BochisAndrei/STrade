package com.example.contrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentPieteTranzactionare : Fragment() {
    companion object {

        fun newInstance(): FragmentPieteTranzactionare {
            return FragmentPieteTranzactionare()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_piete_tranzactionare, container, false)


        return view
    }
}