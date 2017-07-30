package uwanttolearn.astro.home_feature

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import io.realm.RealmResults
import io.realm.annotations.PrimaryKey
import kotlinx.android.synthetic.main.fragment_home.*
import uwanttolearn.astro.R
import uwanttolearn.astro.app.App
import uwanttolearn.astro.core.abstracts.AstroFragment
import uwanttolearn.astro.core.data.pojos.ChannelInfo
import uwanttolearn.astro.core.data.source.ChannelsDataSource
import uwanttolearn.astro.core.data.source.local.ChannelsLocalDataSource
import uwanttolearn.astro.databinding.FragmentHomeBinding
import uwanttolearn.astro.home_feature.adapter.HomeAdapter
import uwanttolearn.astro.home_feature.dagger.DaggerHomeFragmentComponent
import uwanttolearn.astro.home_feature.dagger.HomeFragmentModule
import javax.inject.Inject

/**
 * Created by waleed on 25/07/2017.
 */
class HomeFragment : AstroFragment(), HomeFragmentContract {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager
    @Inject
    lateinit var adapter: HomeAdapter
    @Inject
    lateinit var viewModel: HomeViewModel
    @Inject
    lateinit var handler: Handler

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        DaggerHomeFragmentComponent.builder()
                .appComponent(App.app.appComponent)
                .homeFragmentModule(HomeFragmentModule(this))
                .build().inject(this)

        binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        adapter.saveClickObservable().subscribe {
            viewModel.saveClick(it)
        }

        binding.viewModel = viewModel
        viewModel.onCreateView()


        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity.setSupportActionBar(HomeFeatureFragment_toolbar)
        binding.HomeFeatureFragmentNavigationView.setCheckedItem(R.id.HomeFeature_Nav_sort_by_number)
        binding.HomeFeatureFragmentNavigationView.setNavigationItemSelectedListener {
            viewModel.sortDataBy(when (it.title) {
                getString(R.string.number) -> "channelStbNumber"
                getString(R.string.name) -> "channelTitle"
                getString(R.string.favourite) -> "isSave"
                else -> "channelStbNumber"
            }
            )
            binding.HomeFeatureFragmentDrawerLayout.closeDrawer(Gravity.RIGHT)
            true
        }

        binding.viewModel


    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onDestroyView()
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.home_feature, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.HomeFeatureMenu_sort -> {
            binding.HomeFeatureFragmentDrawerLayout.openDrawer(Gravity.RIGHT, true)
            true
        }
        else ->
            super.onOptionsItemSelected(item)
    }

    override fun addData(allChannelsInfo: RealmResults<ChannelInfo>?) {
        adapter.addData(allChannelsInfo!!)
    }

    override fun notifyDataChanged() {

        handler.post { adapter.notifyDataSetChanged() }
    }

    override fun resetAdapter() {
        adapter.reset()
    }

    override fun updateRowOnPosition(position: Int) {
        handler.post { adapter.notifyItemChanged(position) }
    }


}