package filipe.pires.me.adapterdelegatemanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import filipe.pires.me.adapterdelegatemanager.delegates.TypeOneDelegate
import filipe.pires.me.adapterdelegatemanager.delegates.TypeTwoDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var delegateManager: ExampleDelegateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.layoutManager = LinearLayoutManager(applicationContext)

        delegateManager = ExampleDelegateManager(TypeOneDelegate(0), TypeTwoDelegate(1))
        recycler.adapter = delegateManager

        for (i in 0..10)
            delegateManager.addCell(TypeOneDelegate.Cell("Type One $i"))

        for (i in 0..10)
            delegateManager.addCell(TypeTwoDelegate.Cell("Type Two $i"))

        for (i in 0..10)
            delegateManager.addCell(TypeOneDelegate.Cell("Type Two $i"))

        delegateManager.notifyDataSetChanged()
    }
}
