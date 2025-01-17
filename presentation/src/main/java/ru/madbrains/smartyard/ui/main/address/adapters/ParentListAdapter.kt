package ru.madbrains.smartyard.ui.main.address.adapters

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.madbrains.smartyard.ui.main.address.adaterdelegates.AddressAdapterDelegate
import ru.madbrains.smartyard.ui.main.address.adaterdelegates.IssueAdapterDelegate
import ru.madbrains.smartyard.ui.main.address.models.IssueModel
import ru.madbrains.smartyard.ui.main.address.models.interfaces.DisplayableItem
import ru.madbrains.smartyard.ui.main.address.models.interfaces.EventLogModel
import ru.madbrains.smartyard.ui.main.address.models.interfaces.VideoCameraModel

/**
 * @author Nail Shakurov
 * Created on 2020-02-11.
 */
class ParentListAdapter(setting: ParentListAdapterSetting) : ListDelegationAdapter<List<DisplayableItem>>(
    AddressAdapterDelegate(setting),
    IssueAdapterDelegate(setting)
)

class ParentListAdapterSetting(
    val context: Context,
    val clickOpen: (domophoneId: Int, doorId: Int) -> Unit,
    val clickPos: (pos: Int) -> Unit,
    val clickItemIssue: (issueModel: IssueModel) -> Unit,
    val clickQrCode: () -> Unit,
    val clickCamera: (model: VideoCameraModel) -> Unit,
    val clickEventLog: (model: EventLogModel) -> Unit
)
