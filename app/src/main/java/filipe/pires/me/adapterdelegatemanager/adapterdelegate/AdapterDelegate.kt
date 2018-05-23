package filipe.pires.me.adapterdelegatemanager.adapterdelegate

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup


interface AdapterDelegate {
    abstract class ViewHolderDelegate(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun onCreateViewHolder(parent: ViewGroup): ViewHolderDelegate
    fun onBindView(genericCell: GenericCell, holder: RecyclerView.ViewHolder)
    fun getItemViewType(): Int
    fun getHandlerClass(): Class<*>
}