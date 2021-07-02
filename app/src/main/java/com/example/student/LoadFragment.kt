package com.example.student

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.student.StudentRoom.StudentRoomdatabase
import kotlinx.coroutines.launch
import kotlin.math.log

class LoadFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_load, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val studentRoomdatabase=StudentRoomdatabase.getDatabase(requireContext())
        val dao=studentRoomdatabase?.getStudentDao()
        val zhuce:Button=requireActivity().findViewById(R.id.newuser)
        val login:Button=requireActivity().findViewById(R.id.login)
        val ud:EditText=requireActivity().findViewById(R.id.loged)
        val um:EditText=requireActivity().findViewById(R.id.logmm)
        val check:CheckBox=requireActivity().findViewById(R.id.checkBox)
        zhuce.setOnClickListener {
            val nav:NavController=Navigation.findNavController(it)
            nav.navigate(R.id.action_loadFragment_to_addFragment)
        }
        login.setOnClickListener {
            val user:String=ud.text.toString().trim()
            val usermm:String=um.text.toString().trim()
            lifecycleScope.launch {
                val foud = dao?.logrest(user).toString()
                Log.d("TAG", "onActivityCreated: " + foud)
                Log.d("TAG", ("onActivityCreated: " + foud == usermm).toString())
                if (foud == "null") {
                    Toast.makeText(requireContext(), "无此用户，请您注册", Toast.LENGTH_SHORT).show()
                }else{
                    if (foud == usermm) {
                        Toast.makeText(requireContext(), "密码正确，正在登陆", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "密码错误，重新输入", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        check.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(!isChecked){
                    um.transformationMethod=PasswordTransformationMethod.getInstance()
                }else{
                    um.transformationMethod=HideReturnsTransformationMethod.getInstance()
                }
            }

        })
    }
}