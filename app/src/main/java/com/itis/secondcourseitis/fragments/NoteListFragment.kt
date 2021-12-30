package com.itis.secondcourseitis.fragments

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.itis.secondcourseitis.SpaceItemDecorator
import com.itis.secondcourseitis.database.NoteDatabase
import com.itis.secondcourseitis.databinding.FragmentNoteListBinding
import com.itis.secondcourseitis.recycler.NoteListAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.itis.secondcourseitis.ExtConstants.NOTE_ID
import com.itis.secondcourseitis.R
import com.itis.secondcourseitis.database.NoteDao
import com.itis.secondcourseitis.model.Note

class NoteListFragment : Fragment() {
    private lateinit var binding: FragmentNoteListBinding
    private lateinit var noteListAdapter: NoteListAdapter
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteDatabase = NoteDatabase.getInstance(this.requireContext())
        noteDao = noteDatabase.noteDao()

        noteListAdapter = NoteListAdapter(
            { deleteNote(it) },
            { navigate(it) }
        )


        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        with(binding) {
            rvNotes.run {
                adapter = noteListAdapter
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
            fabAdd.setOnClickListener {
                navigate(null)
            }
        }
        update(noteDao.findAllNotes())
    }

    private fun navigate(id: Int?) {
        var bundle: Bundle? = null
        id?.let {
            bundle = Bundle().apply {
                putInt(NOTE_ID, id)
            }
        }

        findNavController().navigate(
            R.id.action_noteListFragment_to_noteDetailsFragment,
            bundle
        )
    }

    private fun deleteNote(id: Int) {
        noteDao.deleteNoteById(id)
        update(noteDao.findAllNotes())
    }

    private fun update(notes: List<Note>) {
        with(binding) {
            if (notes.isEmpty()) {
                tvZeroNotes.visibility = VISIBLE
                rvNotes.visibility = GONE
            } else {
                rvNotes.visibility = VISIBLE
                tvZeroNotes.visibility = GONE
            }
        }
        noteListAdapter.submitList(ArrayList(notes))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete_all, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        NavigationUI.onNavDestinationSelected(item, findNavController())
        noteDao.deleteAllNotes()
        update(noteDao.findAllNotes())
        return true
    }
}
