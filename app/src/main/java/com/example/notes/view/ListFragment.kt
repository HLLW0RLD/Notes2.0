package com.example.notes.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.data.Note
import com.example.notes.data.RepositoryImpl
import com.example.notes.view.DetailsFragment.Companion.DETAILS_BUNDLE
import kotlinx.android.synthetic.main.fragment_list.*
import kotlin.properties.Delegates

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    interface onNoteClick {
        fun onNoteClick(note: Note)
    }

    interface onNoteLongClick {
        fun onNoteLongClick(note: Note, index: Int)
    }

    lateinit var longClickedNote : Note

    var longClickedIndex by Delegates.notNull<Int>()

    private var notes: MutableList<Note> = RepositoryImpl.getList()

    lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ListAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_recyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        list_recyclerview.adapter = adapter

        adapter.setData(notes)

        adapter.setNoteClickListener(onNoteClickListener)

        adapter.setNoteLongClickListener(onNoteLongClickListener)

        arguments?.getParcelable<Note>("SAVE_BUNDLE")?.let { addToList(it) }

        button_add.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, UpdateFragment.newInstance())
                .addToBackStack(UpdateFragment.toString())
                .commit()
        }
    }

    fun addToList(note: Note) {
        adapter.add(note)

        adapter.notifyItemInserted(0)
    }

    private val onNoteClickListener = object : onNoteClick {
        override fun onNoteClick(note: Note) {
            val bundle = Bundle()
            bundle.putParcelable(DETAILS_BUNDLE, note)
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(bundle))
                .addToBackStack(DetailsFragment.toString())
                .commitNow()
        }
    }

    private val onNoteLongClickListener = object : onNoteLongClick {
        override fun onNoteLongClick(note: Note, index: Int) {
            longClickedIndex = index

            longClickedNote = note

        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        requireActivity().getMenuInflater().inflate(R.menu.menu_list, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (id) {
            R.id.update -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, UpdateFragment.newInstance(longClickedNote))
                    .addToBackStack(UpdateFragment.toString())
                    .commitNow()

                adapter.update(longClickedNote)

                return true
            }
            R.id.delete -> {
                RepositoryImpl.deleteNote(longClickedNote)

                adapter.remove(longClickedNote)

                adapter.notifyItemRemoved(longClickedIndex)

                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}