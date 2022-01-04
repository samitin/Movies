package ru.samitin.movies.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.samitin.movies.R
import ru.samitin.movies.viewmodel.RatingsViewModel

class RatingsFragment : Fragment() {

    companion object {
        fun newInstance() = RatingsFragment()
    }

    private lateinit var viewModel: RatingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.ratings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RatingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}