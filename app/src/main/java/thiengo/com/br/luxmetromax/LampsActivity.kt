package thiengo.com.br.luxmetromax

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_lamps.*
import thiengo.com.br.luxmetromax.data.Lamps

class LampsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lamps)
        setSupportActionBar(toolbar)

        /*
         * Somente para a apresentação da seta de "back screen"
         * na barra de topo.
         * */
        supportActionBar?.setDisplayHomeAsUpEnabled( true )
        supportActionBar?.setDisplayShowHomeEnabled( true )

        initLampList()
    }

    private fun initLampList(){
        rv_lamps.setHasFixedSize( false )

        val layoutManager = LinearLayoutManager( this )
        rv_lamps.layoutManager = layoutManager

        val divider = DividerItemDecoration(
            this,
            layoutManager.orientation
        )
        divider.setDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.divider_layout
            )!!
        )

        rv_lamps.addItemDecoration( divider )

        val adapter = LampsAdapter( Lamps.getLamps( this ) )
        rv_lamps.adapter = adapter
    }
}
