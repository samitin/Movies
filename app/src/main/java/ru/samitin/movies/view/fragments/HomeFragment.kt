package ru.samitin.movies.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.samitin.movies.R
import ru.samitin.movies.databinding.HomeFragmentBinding
import ru.samitin.movies.entities.CardMovie
import ru.samitin.movies.entities.CategoryMovies
import ru.samitin.movies.view.DESCRIPTION_CARD_KEY
import ru.samitin.movies.view.DescriptionActivity
import ru.samitin.movies.view.adapter.VerticalRecyclerViewAdapter
import ru.samitin.movies.viewmodel.AppState
import ru.samitin.movies.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }
    private var adapter : VerticalRecyclerViewAdapter? = null
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding=HomeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initRecyclerView()
        initViewModel()
    }
    private fun initAdapter(){
        adapter =  VerticalRecyclerViewAdapter(context, ArrayList<CategoryMovies>())
        adapter?.setOnHorisontalClickListener(object : VerticalRecyclerViewAdapter.OnHorisontalClickListener{
            override fun onClick(cardMovie: CardMovie) {
                Toast.makeText(context,cardMovie.name,Toast.LENGTH_SHORT).show()
                val intent=Intent(activity,DescriptionActivity::class.java)
                intent.putExtra(DESCRIPTION_CARD_KEY,cardMovie)
                startActivity(intent)
            }
        })
        adapter?.setOnVerticalClickListener(object :VerticalRecyclerViewAdapter.OnVerticalClickListener{
            override fun onClick(view: View) {
                Toast.makeText(context,"Category",Toast.LENGTH_SHORT).show()
                viewModel.getListMovie()
            } })
    }
    private fun initRecyclerView(){
        binding.mainFragmentRecyclerView.adapter=adapter
        binding?.mainFragmentRecyclerView?.setHasFixedSize(true)
        binding?.mainFragmentRecyclerView?.layoutManager=LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    }
    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { setData(it) })
        viewModel.getListMovie()
    }

    private fun setData(appState: AppState){
        when(appState){
            is AppState.Success->{
                binding.homeFragmentLoadingLayout.visibility=View.GONE
                adapter?.addCategoryModel(CategoryMovies("Category", appState.movieListData))

            }

            is AppState.Loading->{
                binding.homeFragmentLoadingLayout.visibility=View.VISIBLE
            }
            is AppState.Error->{
                binding.homeFragmentLoadingLayout.visibility=View.VISIBLE
                Snackbar
                    .make(binding.homeFragmentLoadingLayout, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) { viewModel.getListMovie() }
                    .show()
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}