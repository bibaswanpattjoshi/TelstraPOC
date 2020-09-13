package com.bibaswan.wipro_telstra.ui.canadaInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bibaswan.wipro_telstra.databinding.CanadaInfoFragmentBinding
import com.bibaswan.wipro_telstra.utils.Resource
import com.bibaswan.wipro_telstra.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.canada_info_fragment.*

@AndroidEntryPoint
class CanadaInfosFragment : Fragment(), CanadaInfoAdapter.CharacterItemListener {

    private var binding: CanadaInfoFragmentBinding by autoCleared()
    private val viewModel: CanadaInfoViewModel by viewModels()
    private lateinit var adapter: CanadaInfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CanadaInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        SetuprefreshLayout()
    }

    private fun SetuprefreshLayout() {
        binding.refreshInfo.setOnRefreshListener {
            setupObservers()

        }
    }


    private fun setupRecyclerView() {
        adapter = CanadaInfoAdapter(this)
        binding.infosRv.layoutManager = LinearLayoutManager(requireContext())
        binding.infosRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.infos.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data?.rows.isNullOrEmpty()){
                        adapter.setItems(ArrayList(it.data!!.rows))
                         refreshInfo.isRefreshing = false;
                        }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedCharacter(characterId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
