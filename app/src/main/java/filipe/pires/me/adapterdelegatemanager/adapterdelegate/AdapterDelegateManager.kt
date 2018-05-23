package filipe.pires.me.adapterdelegatemanager.adapterdelegate

import android.support.annotation.VisibleForTesting
import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


class AdapterDelegateManager {

    @VisibleForTesting
    var delegates = SparseArrayCompat<AdapterDelegate>()
    private var classes = HashMap<Class<*>, Int>()

    fun addDelegate(delegate: AdapterDelegate?): AdapterDelegateManager {
        if (delegate == null)
            throw IllegalStateException("Adapater delegate cannot be null")

        val viewType = delegate.getItemViewType()

        val delegateClass = delegate.getHandlerClass()
        classes[delegateClass] = viewType

        if (delegates.get(viewType) != null)
            throw IllegalStateException("View type " + viewType + "already registered as " + delegates.get(viewType))

        delegates.put(viewType, delegate)

        return this
    }

    fun getItemViewType(genericCell: GenericCell): Int {
        return if (classes.containsKey(genericCell.javaClass))
            classes[genericCell.javaClass]!!
        else
            throw IllegalStateException("No adapter delegate for type " + genericCell.javaClass)
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val delegate = delegates.get(viewType)
                ?: throw NullPointerException("Adapter delegate for $viewType missing")

        return delegate.onCreateViewHolder(parent)
    }

    fun onBindViewHolder(genericCell: GenericCell, holder: RecyclerView.ViewHolder, position: Int) {
        val delegate = delegates.get(holder.itemViewType)
                ?: throw NullPointerException("Adapter delegate for " + holder.getItemViewType() + " not found")

        delegate.onBindView(genericCell, holder)
    }
}