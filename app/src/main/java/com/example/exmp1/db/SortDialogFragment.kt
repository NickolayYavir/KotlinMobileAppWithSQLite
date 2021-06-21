package com.example.exmp1.db

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.exmp1.R
import kotlinx.android.synthetic.main.dialogfragment_sort.*
import kotlinx.android.synthetic.main.dialogfragment_sort.view.*

class SortDialogFragment: DialogFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.dialogfragment_sort, container, false)
        rootView.btnSortCancel.setOnClickListener {
            dismiss()
        }

        rootView.btnSortApply.setOnClickListener {

            val selectedColumn = rgSortFragmentColumn.checkedRadioButtonId
            val radioColumn = rootView.findViewById<RadioButton>(selectedColumn)

            val selectedMethodID = rgSortFragmentMethod.checkedRadioButtonId
            val radioMethod = rootView.findViewById<RadioButton>(selectedMethodID)

            val columnResult = radioColumn.text.toString()
            val methodResult = radioMethod.text.toString()

            Toast.makeText(context, "Sort by $columnResult $methodResult method", Toast.LENGTH_SHORT).show()
            (activity as? BdInterface)?.StoreData(columnResult, methodResult)
            dismiss()
        }

        return rootView
    }
}