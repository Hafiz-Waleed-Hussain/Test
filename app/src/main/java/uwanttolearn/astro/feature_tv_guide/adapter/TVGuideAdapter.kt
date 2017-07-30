package uwanttolearn.astro.tv_guide.adapter

import android.databinding.BaseObservable
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.subjects.BehaviorSubject
import uwanttolearn.astro.R
import uwanttolearn.astro.core.data.pojos.TVGuideChannel
import uwanttolearn.astro.core.data.pojos.TVGuideEvent
import uwanttolearn.astro.databinding.RowLoaderBinding
import uwanttolearn.astro.databinding.RowTvGuideBinding
import uwanttolearn.astro.tv_guide.adapter.nested_adapter.TVGuideHorizontalNestedAdapter

/**
 * Created by waleed on 29/07/2017.
 */
class TVGuideAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<TVGuideChannel> = arrayListOf()
    private val paginationListener: BehaviorSubject<Any> = BehaviorSubject.create()
    private var previousTotalCount: Int = 0

    enum class DownloadingStatus {
        DOWNLOAD_MORE_DATA,
        DOWNLOAD_INTERRUPTED_DUE_TO_INTERNET,
        DOWNLOAD_COMPLETE
    }

    private var downloadStatus = DownloadingStatus.DOWNLOAD_MORE_DATA

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent?.context),
                viewType, parent, false)
        return if (viewType == R.layout.row_tv_guide)
            TVGuideViewHolder(binding as RowTvGuideBinding)
        else
            TVGuideLoaderViewHolder(binding as RowLoaderBinding)

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        downloadMore(position)
        if (holder is TVGuideViewHolder)
            holder?.bind(data[position])
        else if (holder is TVGuideLoaderViewHolder) {
            holder.bind(downloadStatus)
        }

    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) =
            if (position != 0 && position == itemCount - 1)
                R.layout.row_loader
            else
                R.layout.row_tv_guide


    private fun downloadMore(position: Int) {
        if (position == itemCount - 1 && downloadStatus == DownloadingStatus.DOWNLOAD_MORE_DATA) {
            if (previousTotalCount != itemCount - 1) {
                previousTotalCount = itemCount - 1
                paginationListener.onNext(Any())
            }
        }
    }


    fun paginationListener(): BehaviorSubject<Any> = paginationListener

    fun add(data: MutableList<TVGuideChannel>) {
        val startPosition = this.data.size + 1
        this.data.addAll(data)
        notifyItemRangeInserted(startPosition, data.size)
    }

    fun downloadedComplete() {
        downloadStatus = TVGuideAdapter.DownloadingStatus.DOWNLOAD_COMPLETE
    }

}


class TVGuideViewHolder(val binding: RowTvGuideBinding) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.TVGuideChannelEventDetailRecyclerView.layoutManager =
                LinearLayoutManager(binding.root.context,
                        LinearLayoutManager.HORIZONTAL,
                        false)
        binding.TVGuideChannelEventDetailRecyclerView.addItemDecoration(DividerItemDecoration(binding.root.context, DividerItemDecoration.HORIZONTAL))
    }

    fun bind(tvGuideChannel: TVGuideChannel) {
        val channelNumber = if (tvGuideChannel.channelStbNumber == 0L)
            "Astro Go Exlusive"
        else
            "CH${tvGuideChannel.channelStbNumber}"
        binding.viewModel = RowTVGuideViewModel(channelNumber,
                tvGuideChannel.channelTitle, tvGuideChannel.events)

        if (binding.TVGuideChannelEventDetailRecyclerView.adapter == null)
            binding.TVGuideChannelEventDetailRecyclerView.adapter = TVGuideHorizontalNestedAdapter(tvGuideChannel.events)
        else {
            val adapter = binding.TVGuideChannelEventDetailRecyclerView.adapter as TVGuideHorizontalNestedAdapter
            adapter.reset(tvGuideChannel.events)
        }
    }
}


class TVGuideLoaderViewHolder(val binding: RowLoaderBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(status: TVGuideAdapter.DownloadingStatus) {
        if (status == TVGuideAdapter.DownloadingStatus.DOWNLOAD_MORE_DATA)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }

}

class RowTVGuideViewModel(val channelNumber: String,
                          val channelTitle: String,
                          val data: List<TVGuideEvent>) : BaseObservable()