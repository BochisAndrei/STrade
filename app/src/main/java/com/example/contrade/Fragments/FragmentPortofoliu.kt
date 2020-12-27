package com.example.contrade.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contrade.MainActivity
import com.example.contrade.OnItemClickListener
import com.example.contrade.R
import com.example.contrade.RoomDatabase.TransactionViewModel
import com.example.contrade.RoomDatabase.Tranzaction

class FragmentPortofoliu : Fragment() {

    private lateinit var transactionViewModel: TransactionViewModel

    companion object {

        fun newInstance(): FragmentPortofoliu {
            return FragmentPortofoliu()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = activity as Context
        val view: View = inflater.inflate(R.layout.fragment_portofoliu, container, false)
        (activity as MainActivity).toolbar.findViewById<TextView>(R.id.main_activity_toolbar_title).text =
            "Portofoliu"


        val recyclerView = view.findViewById<RecyclerView>(R.id.portfolio_recycler_view)
        var rvTransactionAdapter = RVAdapterTransaction(activity)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = rvTransactionAdapter

        transactionViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

        transactionViewModel.allTransactions.observe(this.viewLifecycleOwner, Observer { list ->
            // Update the cached copy of the notes in the adapter.
            rvTransactionAdapter.setNotes(list)
        })
        return view
    }
}