package filipe.pires.me.adapterdelegatemanager

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import filipe.pires.me.adapterdelegatemanager.adapterdelegate.AdapterDelegate
import filipe.pires.me.adapterdelegatemanager.adapterdelegate.AdapterDelegateManager
import filipe.pires.me.adapterdelegatemanager.adapterdelegate.GenericCell


class ExampleDelegateManager(vararg delegates: AdapterDelegate) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val delegateManager = AdapterDelegateManager()
    private val cells = ArrayList<GenericCell>()

    init {
        for (delegate in delegates)
            delegateManager.addDelegate(delegate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateManager.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int = cells.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateManager.onBindViewHolder(cells[position], holder, position)
    }

    override fun getItemViewType(position: Int): Int = delegateManager.getItemViewType(cells[position])


    fun addCell(cell: GenericCell) {
        cells.add(cell)
    }
}