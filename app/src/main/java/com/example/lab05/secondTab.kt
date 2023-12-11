package com.example.lab05
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [secondTab.newInstance] factory method to
 * create an instance of this fragment.
 */
class secondTab : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var image: ImageView

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
        val view = inflater.inflate(R.layout.fragment_second_tab, container, false)

        image = view.findViewById(R.id.imageViewToSave)
        when(param2) {
            "page 1" -> image.setImageResource(R.drawable.playericon)
            "page 2" -> image.setImageResource(R.drawable.skeletonicon)
            "page 3" -> image.setImageResource(R.drawable.orcicon)
            else -> image.setImageResource(R.drawable.homeicon)
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        saveImage()
    }

    fun saveImage() {
        val data = requireActivity().getSharedPreferences("additional", Context.MODE_PRIVATE)
        val editor = data.edit()
        when(param2) {
            "page 1" -> editor.putInt("imageNumber", 1)
            "page 2" -> editor.putInt("imageNumber", 2)
            "page 3" -> editor.putInt("imageNumber", 3)
            else -> editor.putInt("imageNumber", 4)
        }
        editor.apply()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment secondTab.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            secondTab().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}