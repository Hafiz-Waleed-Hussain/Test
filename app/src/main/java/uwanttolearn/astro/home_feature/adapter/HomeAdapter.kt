package uwanttolearn.astro.home_feature.adapter

import android.databinding.*
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.realm.RealmResults
import uwanttolearn.astro.R
import uwanttolearn.astro.core.data.pojos.ChannelInfo
import uwanttolearn.astro.databinding.RowHomeBinding

/**
 * Created by waleed on 27/07/2017.
 */
class HomeAdapter : RecyclerView.Adapter<HomeViewHolder>() {

    private val data: MutableList<ChannelInfo> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeViewHolder =
            HomeViewHolder(
                    DataBindingUtil.inflate<RowHomeBinding>
                    (LayoutInflater.from(parent?.context),
                            R.layout.row_home, parent, false))


    override fun onBindViewHolder(holder: HomeViewHolder?, position: Int) {
        holder?.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun addData(allChannelsInfo: MutableList<ChannelInfo>) {
        data.addAll(allChannelsInfo)
        notifyDataSetChanged()
    }

    fun reset() {
        data.clear()
        notifyDataSetChanged()
    }


}

class HomeViewHolder(val binding: RowHomeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(channelInfo: ChannelInfo) {
        binding.viewModel = RowHomeViewModel(channelInfo)
    }

}

class RowHomeViewModel(channelInfo: ChannelInfo) : BaseObservable() {

    var channelTitle = ObservableField<String>(channelInfo.channelTitle)
    var channelNumber = ObservableInt(channelInfo.channelId)
    var channelLogoUrl = ObservableField<String>(channelInfo.url)

}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String) {

    Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_android_black_24dp)
            .error(R.drawable.ic_android_black_24dp)
            .into(imageView)

}