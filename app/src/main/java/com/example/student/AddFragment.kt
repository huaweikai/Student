package com.example.student

import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.student.StudentRoom.Student
import com.example.student.StudentRoom.StudentDao
import com.example.student.StudentRoom.StudentRoomdatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val ed: EditText = requireActivity().findViewById(R.id.newusered)
        val edmm: EditText = requireActivity().findViewById(R.id.newusermm)
        val right: Button = requireActivity().findViewById(R.id.right)

        right.isEnabled = false

        val studentRoomdatabase = StudentRoomdatabase.getDatabase(requireContext())
        val dao = studentRoomdatabase?.getStudentDao()

        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                right.isEnabled = (!ed.text.toString().trim().isEmpty()
                        && !edmm.text.toString().trim().isEmpty())
            }
        }
        ed.addTextChangedListener(textWatcher)
        edmm.addTextChangedListener(textWatcher)

        right.setOnClickListener {
            val user: String = ed.text.toString().trim()
            val usermm: String = edmm.text.toString().trim()
            val student = Student(user, usermm)
            lifecycleScope.launch {
                val foud = dao?.logrest(user).toString()
                if(foud!="null"){
                    Toast.makeText(requireContext(),foud,Toast.LENGTH_SHORT).show()
                }else{
                    dao?.insert(student)
                    Toast.makeText(requireContext(),"注册完毕",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}