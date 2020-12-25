package com.example.contrade.Fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.contrade.*
import com.google.firebase.auth.FirebaseAuth

class FragmentSetari : Fragment() {
    lateinit var mAuth: FirebaseAuth
    private var sharedP = "SHARED_USER"
    companion object {

        fun newInstance(): FragmentSetari {
            return FragmentSetari()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_setari, container, false)
        (activity as MainActivity).toolbar.findViewById<TextView>(R.id.main_activity_toolbar_title).text = "Settings"
        (activity as MainActivity).toolbar.background = ContextCompat.getDrawable(activity as MainActivity, R.color.primary_green)

        val openChangeName = view.findViewById<ImageView>(R.id.fragment_setari_change_usernane)
        val openChangePassword = view.findViewById<ImageView>(R.id.fragment_setari_change_password)

        var logout = view.findViewById<Button>(R.id.fragment_setari_btn_logout)

        logout.setOnClickListener {
            signOut()
            var mainIntent = Intent(activity, LoginActivity::class.java)
            startActivity(mainIntent)
        }

        openChangeName.setOnClickListener{
            var dialog = DialogChangeUsername()
            dialog.show(requireActivity().supportFragmentManager, "ElementsDialog")
        }

        openChangePassword.setOnClickListener{
            var dialog = DialogChangePassword()
            dialog.show(requireActivity().supportFragmentManager, "ElementsDialog")
        }

        return view
    }
    fun signOut(){
        FirebaseAuth.getInstance().signOut() //sign out from firebase
        var sharedPreferences : SharedPreferences = activity!!.getSharedPreferences(sharedP, Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("USERNAME", "")
        editor.apply()
    }
}