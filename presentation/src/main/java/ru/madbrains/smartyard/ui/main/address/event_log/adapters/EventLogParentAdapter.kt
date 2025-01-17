package ru.madbrains.smartyard.ui.main.address.event_log.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import ru.madbrains.domain.model.response.Plog
import ru.madbrains.domain.utils.listenerGeneric
import ru.madbrains.smartyard.R
import java.util.Locale
import kotlin.collections.HashMap

class EventLogParentAdapter(
    var eventsDay: List<LocalDate>,
    var eventsByDays: HashMap<LocalDate, MutableList<Plog>>,
    private val eventCallback: listenerGeneric<Pair<LocalDate, Int>>
) : RecyclerView.Adapter<EventLogParentAdapter.EventLogParentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventLogParentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event_log_parent, parent, false)
        return EventLogParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventLogParentViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return eventsDay.size
    }

    inner class EventLogParentViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvDay: TextView = itemView.findViewById(R.id.tvEventLogDay)
        private val rvChildren: RecyclerView = itemView.findViewById(R.id.rvEventLogChildren)

        fun onBind(position: Int) {
            tvDay.text = eventsDay[position].format(DateTimeFormatter.ofPattern("eeee, d MMMM")).capitalize(
                Locale.getDefault()
            )
            val lm = LinearLayoutManager(itemView.context)
            rvChildren.layoutManager = lm
            val adapter = EventLogChildAdapter(eventsByDays[eventsDay[position]]?.toList() ?: listOf()) {
                eventCallback(Pair(eventsDay[position], it))
            }
            rvChildren.adapter = adapter
        }
    }
}
