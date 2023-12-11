package com.example.lab05
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.lab05.databinding.FragmentEditDataBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditDataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var _binding: FragmentEditDataBinding
    lateinit var showName: EditText
    lateinit var showSpec: EditText
    lateinit var showStrength: SeekBar
    lateinit var showType: ImageView
    lateinit var showDanger: CheckBox
    lateinit var returnButton: Button
    lateinit var cancelButton: Button

    lateinit var saveType: String
    var pos: Int = -1
    var name = ""
    var spec = ""
    var str = 1.0F
    var danger = false



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
        _binding = FragmentEditDataBinding.inflate(inflater, container, false)
        showName=_binding.showName
        showSpec=_binding.showSpec
        showStrength=_binding.showStrengthBar
        showType=_binding.showType
        showDanger=_binding.showDangerous
        returnButton=_binding.showReturnButton
        cancelButton=_binding.cancelButton
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        returnButton.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                "editItem", bundleOf(
                    "name" to showName.text.toString(),
                    "spec" to showSpec.text.toString(),
                    "strength" to showStrength.progress,
                    "danger" to showDanger.isChecked,
                    "type" to saveType,
                    "position" to pos,
                    "edit" to true
                )
            )
            findNavController().navigate(R.id.action_editDataFragment_to_thirdFragment)
        }
        cancelButton.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                "msgtochild", bundleOf(
                    "name" to name,
                    "spec" to spec,
                    "strength" to str,
                    "danger" to danger,
                    "type" to saveType,
                    "position" to pos,
                    "edit" to true
                )
            )
            Log.d("str", str.toString())
            requireActivity().onBackPressed()
        }
        parentFragmentManager.setFragmentResultListener("msgtoedit", viewLifecycleOwner){
                _, bundle ->
            run {
                name = bundle.getString("name", "")
                spec = bundle.getString("spec", "")
                str = bundle.getFloat("strength")
                danger = bundle.getBoolean("danger")

                showName.setText(name)
                showSpec.setText(spec)
                showDanger.isChecked = danger
                showStrength.progress = str.toInt()
                saveType = bundle.getString("type", "Skeleton")
                pos = bundle.getInt("position", -1)
                when(saveType) {
                    "Lich" -> showType.setImageResource(R.drawable.playericon)
                    else -> showType.setImageResource(R.drawable.skeletonicon)
                }
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditDataFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditDataFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}