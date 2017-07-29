package uwanttolearn.astro.home_feature.adapter

import android.databinding.*
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.realm.Realm
import timber.log.Timber
import uwanttolearn.astro.R
import uwanttolearn.astro.core.data.pojos.ChannelInfo
import uwanttolearn.astro.core.data.source.ChannelsDataSource
import uwanttolearn.astro.databinding.RowHomeBinding

/**
 * Created by waleed on 27/07/2017.
 */
class HomeAdapter : RecyclerView.Adapter<HomeViewHolder>() {

    private val data: MutableList<ChannelInfo> = arrayListOf()
    private val handler = Handler()
    private val publishSubject = PublishSubject.create<Pair<ChannelInfo, Boolean>>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeViewHolder =
            HomeViewHolder(
                    DataBindingUtil.inflate<RowHomeBinding>
                    (LayoutInflater.from(parent?.context),
                            R.layout.row_home, parent, false))


    override fun onBindViewHolder(holder: HomeViewHolder?, position: Int) {
        holder?.bind(data[position], publishSubject)
    }

    override fun getItemCount() = data.size

    fun saveClickObservable(): Observable<Pair<ChannelInfo, Boolean>> = publishSubject

    fun addData(allChannelsInfo: MutableList<ChannelInfo>) {
        data.addAll(allChannelsInfo)
        handler.post { notifyDataSetChanged() }
    }

    fun reset() {
        data.clear()
        handler.post { notifyDataSetChanged() }
    }


}

class HomeViewHolder(val binding: RowHomeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(channelInfo: ChannelInfo, publishSubject: PublishSubject<Pair<ChannelInfo, Boolean>>) {
        binding.viewModel = RowHomeViewModel(channelInfo, publishSubject)
    }

}

class RowHomeViewModel(val channelInfo: ChannelInfo, val publishSubject : PublishSubject<Pair<ChannelInfo, Boolean>>) : BaseObservable() {

    var channelTitle = ObservableField<String>(channelInfo.channelTitle)
    var channelNumber = ObservableInt(channelInfo.channelStbNumber)
    var channelLogoUrl = ObservableField<String>(channelInfo.url)
    var channelIsSaved = ObservableBoolean(channelInfo.isSave)


    fun onSaveClick(view: View){
        view as CheckBox
        publishSubject.onNext(Pair(channelInfo, view.isChecked))
    }

}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String) {

    Glide.with(imageView.context)
            .load(url)
            .dontAnimate()
            .placeholder(R.drawable.ic_android_black_24dp)
            .error(R.drawable.ic_android_black_24dp)
            .into(imageView)

}