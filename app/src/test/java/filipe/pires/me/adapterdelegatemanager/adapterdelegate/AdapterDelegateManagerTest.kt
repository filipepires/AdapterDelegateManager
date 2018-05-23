package filipe.pires.me.adapterdelegatemanager.adapterdelegate

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Test

class AdapterDelegateManagerTest {
    private val adapterDelegateManager = AdapterDelegateManager()

    @Test(expected = IllegalStateException::class)
    fun `given no adapter, throw exception`() {
        adapterDelegateManager.addDelegate(null)
    }

    @Test(expected = IllegalStateException::class)
    fun `given view type already registered, throw exception`() {
        val delegate = mock<AdapterDelegate>()
        whenever(delegate.getItemViewType()).thenReturn(1)
        adapterDelegateManager.delegates.put(1, delegate)
        adapterDelegateManager.addDelegate(delegate)
    }

    @Test
    fun `given empty manager, add delegate`() {
        val delegate = mock<AdapterDelegate>()
        whenever(delegate.getItemViewType()).thenReturn(1)
        adapterDelegateManager.addDelegate(delegate)
        assertEquals(1, adapterDelegateManager.delegates.size())
    }

    @Test
    fun `given valid delegate, add delegate`() {
        val delegate = mock<AdapterDelegate>()
        whenever(delegate.getItemViewType()).thenReturn(1)
        adapterDelegateManager.delegates.put(2, delegate)
        adapterDelegateManager.addDelegate(delegate)
        assertEquals(2, adapterDelegateManager.delegates.size())
    }

    @Test(expected = IllegalStateException::class)
    fun `when class is not provided, throw exception`(){
        adapterDelegateManager.getItemViewType(mock())
    }

}