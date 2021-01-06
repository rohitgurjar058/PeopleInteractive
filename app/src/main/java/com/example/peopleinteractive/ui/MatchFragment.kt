package com.example.peopleinteractive.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.peopleinteractive.MainApplication
import com.example.peopleinteractive.R
import com.example.peopleinteractive.adapter.Adapter
import com.example.peopleinteractive.adapter.Listener
import com.example.peopleinteractive.databinding.MatchFragmentBinding
import com.example.peopleinteractive.models.Match
import com.example.peopleinteractive.viewmodels.MatchViewModel
import kotlinx.android.synthetic.main.match_item_view.*
import javax.inject.Inject

class MatchFragment : Fragment(R.layout.match_fragment), Listener {

    @Inject lateinit var viewModel: MatchViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApplication).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = MatchFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the NameViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the RecyclerView
        val adapter = Adapter(this)
        binding.matches.adapter = adapter

        viewModel.matches.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onItemClick(match: Match) {
        viewModel.updateMatch(match)
    }
}