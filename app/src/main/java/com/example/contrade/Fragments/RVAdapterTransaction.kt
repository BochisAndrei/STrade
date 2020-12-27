package com.example.contrade.Fragments

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contrade.R
import com.example.contrade.RoomDatabase.Tranzaction

class RVAdapterTransaction(var context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataSet = emptyList<Tranzaction>() // Cached copy of notes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.card_view_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    internal fun setNotes(transaction: List<Tranzaction>) {
        this.dataSet = transaction
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var type = dataSet[position]
        (holder as TransactionViewHolder)
            .initializeUIComponents(type.name, type.type, type.share_price.toString())
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class TransactionViewHolder(myView: View) : RecyclerView.ViewHolder(myView){
        var companyName = myView.findViewById<TextView>(R.id.card_view_transaction_company_name)
        var transactionType = myView.findViewById<TextView>(R.id.card_view_transaction_type)
        var companyValue = myView.findViewById<TextView>(R.id.card_view_transaction_company_value)

        fun initializeUIComponents(fName : String, fType : String, fClose : String){
            companyName.text = fName
            var string = "($fType)"
            transactionType.text = string
            companyValue.text = fClose
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}