package com.example.notes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.data.Note
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    companion object {

        const val DETAILS_BUNDLE = "DETAILS_BUNDLE"

        fun newInstance(bundle: Bundle) : DetailsFragment{
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = arguments?.getParcelable<Note>("DETAILS_BUNDLE")

        details_name.text = result?.name

        details_description.text = result?.description
    }
}