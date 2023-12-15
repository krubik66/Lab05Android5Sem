package com.example.lab05
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RatingBar
import androidx.core.os.bundleOf
import com.example.lab05.databinding.FragmentAddDataBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddDataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var _binding: FragmentAddDataBinding
    lateinit var addName: EditText
    lateinit var addSpec: EditText
    lateinit var addStrength: RatingBar
    lateinit var addType: RadioGroup
    lateinit var addDanger: CheckBox

    lateinit var saveButton: Button
    lateinit var cancelButton: Button

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
        _binding = FragmentAddDataBinding.inflate(inflater, container, false)
        addName = _binding.addName
        addSpec = _binding.addSpec
        addStrength = _binding.addStrengthBar
        addType = _binding.radioAdd
        addDanger = _binding.addDanger
        saveButton = _binding.addSaveButton
        cancelButton=_binding.addCancelButton


        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //changing race depending on item checked
        var race: String = "Lich"
        addType.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                _binding.addTypeLich.id -> race = "Lich"
                _binding.addTypeGhoul.id -> race = "Ghoul"
                _binding.addTypeSkeleton.id -> race = "Skeleton"
            }
        }

        cancelButton.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                "addNewItem", bundleOf(
                    "toAdd" to false
                )
            )
            requireActivity().onBackPressed()
        }

        saveButton.setOnClickListener {
            val name: String = if (addName.text.toString() == "") {
                "Default name"
            } else {
                addName.text.toString()
            }
            val spec: String = if (addSpec.text.toString() == "") {
                "Default spec"
            } else {
                addSpec.text.toString()
            }
            parentFragmentManager.setFragmentResult(
                "addNewItem", bundleOf(
                    "name" to name,
                    "spec" to spec,
                    "strength" to addStrength.rating,
                    "danger" to addDanger.isChecked,
                    "type" to race,
                    "toAdd" to true
                )
            )
            requireActivity().onBackPressed()
        }
    }


        companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddDataFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddDataFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}