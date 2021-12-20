package com.example.notes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.data.Note
import com.example.notes.data.RepositoryImpl
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UpdateFragment : Fragment() {

    companion object{
        const val SAVE_BUNDLE = "SAVE_BUNDLE"

        const val UPDATE_BUNDLE = "UPDATE_BUNDLE"
        
        fun newInstance(note: Note) : UpdateFragment{
            val bundle = Bundle()
            bundle.putParcelable(UpdateFragment.SAVE_BUNDLE, note)
            val fragment = UpdateFragment()
            return fragment
        }

        fun newInstance() = UpdateFragment()
    }

    lateinit var name : EditText

    lateinit var description : EditText

    lateinit var doneBtn : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = view.findViewById<EditText>(R.id.update_name)

        description = view.findViewById<EditText>(R.id.update_description)

        doneBtn = view.findViewById<FloatingActionButton>(R.id.button_done)

        doneBtn.setOnClickListener { saveNote(name.text.toString(), description.text.toString()) }
    }

    fun saveNote(name : String, description : String){
        RepositoryImpl.addNote(name, description)

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container, ListFragment.newInstance())
            .addToBackStack(ListFragment.toString())
            .commit()

    }

}