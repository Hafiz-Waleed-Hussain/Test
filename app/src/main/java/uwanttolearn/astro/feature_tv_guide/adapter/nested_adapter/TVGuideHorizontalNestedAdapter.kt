package uwanttolearn.astro.feature_tv_guide.adapter.nested_adapter

import android.databinding.BaseObservable
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import uwanttolearn.astro.R
import uwanttolearn.astro.core.data.pojos.TVGuideEvent
import uwanttolearn.astro.databinding.RowTvGuideNestedHorizonatlBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by waleed on 29/07/2017.
 */
class TVGuideHorizontalNestedAdapter(var events: MutableList<TVGuideEvent>) : RecyclerView.Adapter<TVGuideHorizontalNestedViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            TVGuideHorizontalNestedViewHolder(
                    DataBindingUtil.inflate<RowTvGuideNestedHorizonatlBinding>
                    (LayoutInflater.from(parent?.context),
                            R.layout.row_tv_guide_nested_horizonatl, parent, false))


    override fun onBindViewHolder(holder: TVGuideHorizontalNestedViewHolder?, position: Int) {
        holder?.bind(events[position])
    }

    override fun getItemCount() = events.size

    fun reset(events: MutableList<TVGuideEvent>) {
        this.events = events
        notifyDataSetChanged()
    }


}


class TVGuideHorizontalNestedViewHolder(val binding: RowTvGuideNestedHorizonatlBinding)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(tvGuideEvent: TVGuideEvent) {
        binding.viewModel = TVGuideHorizontalNestedViewModel(tvGuideEvent)
    }
}

class TVGuideHorizontalNestedViewModel(val tvGuideEvent: TVGuideEvent) : BaseObservable() {
    val time: String

    init {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:SS")
        val parse = simpleDateFormat.parse(tvGuideEvent.displayDateTime)
        val calendar = Calendar.getInstance()
        calendar.time = parse
        time = "${getHour(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))}"
    }

    private fun getHour(hourOfDay: Int, minutes: Int) =
            when (hourOfDay) {
                in 1..12 -> "$hourOfDay:${getMinute(minutes)} AM"
                in 13..23 -> "${hourOfDay - 12}:${getMinute(minutes)} PM"
                else -> "12:${getMinute(minutes)} AM"
            }


    private fun getMinute(minutes: Int) =
            if (minutes.toString().toCharArray().size == 1)
                "0$minutes"
            else
                minutes.toString()


}