package filipe.pires.me.adapterdelegatemanager.delegates

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import filipe.pires.me.adapterdelegatemanager.R
import filipe.pires.me.adapterdelegatemanager.adapterdelegate.AdapterDelegate
import filipe.pires.me.adapterdelegatemanager.adapterdelegate.GenericCell
import kotlinx.android.synthetic.main.delegate_type_one.view.*


class TypeOneDelegate(private val viewType: Int) : AdapterDelegate {

    data class Cell(val title: String) : GenericCell

    override fun onCreateViewHolder(parent: ViewGroup): AdapterDelegate.ViewHolderDelegate {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.delegate_type_one, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : AdapterDelegate.ViewHolderDelegate(view)

    override fun onBindView(genericCell: GenericCell, holder: RecyclerView.ViewHolder) {
        genericCell as Cell
        holder.itemView.title.text = genericCell.title
    }

    override fun getItemViewType(): Int = viewType

    override fun getHandlerClass(): Class<*> = Cell::class.java
}