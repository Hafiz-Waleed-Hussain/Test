package uwanttolearn.astro.tv_guide.adapter

import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import uwanttolearn.astro.R
import uwanttolearn.astro.core.data.json.Getevent
import uwanttolearn.astro.core.data.json.TVGuide
import uwanttolearn.astro.core.data.pojos.TVGuideChannel
import uwanttolearn.astro.core.data.pojos.TVGuideEvent
import uwanttolearn.astro.databinding.RowHomeBinding
import uwanttolearn.astro.databinding.RowTvGuideBinding
import uwanttolearn.astro.favourites.TVGuideFragment
import uwanttolearn.astro.home_feature.adapter.HomeViewHolder

/**
 * Created by waleed on 29/07/2017.
 */
class TVGuideAdapter : RecyclerView.Adapter<TVGuideViewHolder>() {

    private val data: MutableList<TVGuideChannel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            TVGuideViewHolder(
                    DataBindingUtil.inflate<RowTvGuideBinding>
                    (LayoutInflater.from(parent?.context),
                            R.layout.row_tv_guide, parent, false))


    override fun onBindViewHolder(holder: TVGuideViewHolder?, position: Int) {
        holder?.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun add(data: MutableList<TVGuideChannel>) {
        data.addAll(data)
        notifyDataSetChanged()
    }

}


class TVGuideViewHolder(val binding: RowTvGuideBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(tvGuideChannel: TVGuideChannel) {
        binding.viewModel = TVGuideViewModel(tvGuideChannel.channelTitle,
                tvGuideChannel.url, tvGuideChannel.events)
    }
}

class TVGuideViewModel(val channelName: String,
                       val channelLogoUrl: String,
                       val data: List<TVGuideEvent>) : BaseObservable() {

}


@BindingAdapter("data")
fun data(recyclerView: RecyclerView, data: List<TVGuideEvent>) {
}