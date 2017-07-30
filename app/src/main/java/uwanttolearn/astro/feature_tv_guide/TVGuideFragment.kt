package uwanttolearn.astro.favourites

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uwanttolearn.astro.R
import uwanttolearn.astro.app.App
import uwanttolearn.astro.core.abstracts.AstroFragment
import uwanttolearn.astro.core.data.pojos.TVGuideChannel
import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import uwanttolearn.astro.databinding.FragmentTvGuideBinding
import uwanttolearn.astro.tv_guide.TVGuideFragmentContract
import uwanttolearn.astro.tv_guide.TVGuideFragmentViewModel
import uwanttolearn.astro.tv_guide.adapter.TVGuideAdapter
import uwanttolearn.astro.tv_guide.adapter.RowTVGuideViewModel
import uwanttolearn.astro.tv_guide.dagger.DaggerTVGuideFragmentComponent
import uwanttolearn.astro.tv_guide.dagger.TVGuideFragmentModule
import javax.inject.Inject

/**
 * Created by waleed on 25/07/2017.
 */
class TVGuideFragment : AstroFragment(), TVGuideFragmentContract {

    private lateinit var binding: FragmentTvGuideBinding

    @Inject
    lateinit var viewModel: TVGuideFragmentViewModel
    @Inject
    lateinit var tvGuideAdapter: TVGuideAdapter
    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        DaggerTVGuideFragmentComponent.builder()
                .appComponent(App.app.appComponent)
                .tVGuideFragmentModule(TVGuideFragmentModule(this))
                .build().inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_guide
                , container, false)

        binding.viewModel = viewModel
        binding.TVGuideFragmentRecyclerView.layoutManager = layoutManager
        binding.TVGuideFragmentRecyclerView.adapter = tvGuideAdapter
        binding.TVGuideFragmentRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        tvGuideAdapter.paginationListener().subscribe { viewModel.loadMoreData() }

        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onDestroyView()
    }

    override fun addData(tvGuide: MutableList<TVGuideChannel>) =
            tvGuideAdapter.add(tvGuide)
    override fun downloadComplete() {
        tvGuideAdapter.downloadedComplete()
    }


}