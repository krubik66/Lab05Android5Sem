package com.example.lab05
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [firstTab.newInstance] factory method to
 * create an instance of this fragment.
 */
class firstTab : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var topText: EditText
    lateinit var bottomText1: EditText
    lateinit var bottomText2: EditText
    lateinit var saveButton: Button
    lateinit var data: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_first_tab, container, false)

        topText = view.findViewById(R.id.forTextAbove)
        bottomText1 = view.findViewById(R.id.forTextUnder1)
        bottomText2 = view.findViewById(R.id.forTextUnder2)
        saveButton = view.findViewById(R.id.firstTabSaveButton)

        return view
    }

    fun saveData(){
        val var1 = topText.text.toString()
        val var2 = bottomText1.text.toString()
        val var3 = bottomText2.text.toString()
        val editor = data.edit()
        editor.putString("beforeImage", var1)
        editor.putString("afterImage1", var2)
        editor.putString("afterImage2", var3)
        editor.apply()
        requireActivity().onBackPressed()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = requireActivity().getSharedPreferences("L4_preferences", Context.MODE_PRIVATE)
        topText.setText(data.getString("beforeImage", "I am on the top!"))
        bottomText1.setText(data.getString("afterImage1", "edit"))
        bottomText2.setText(data.getString("afterImage2", "me!"))


        saveButton.setOnClickListener { _ ->
            saveData()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment firstTab.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            firstTab().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}