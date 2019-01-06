package thiengo.com.br.luxmetromax.data

import android.content.Context
import thiengo.com.br.luxmetromax.R
import thiengo.com.br.luxmetromax.domain.Lamp

class Lamps {

    companion object {
        fun getLamps( context: Context )
            = listOf(
                Lamp(
                    R.drawable.led,
                    context.getText(R.string.lamp_led)
                ),
                Lamp(
                    R.drawable.incandescente,
                    context.getText(R.string.lamp_incandescent)
                ),
                Lamp(
                    R.drawable.halogena,
                    context.getText(R.string.lamp_halogen)
                ),
                Lamp(
                    R.drawable.fluorescente,
                    context.getText(R.string.lamp_fluorescent)
                ),
                Lamp(
                    R.drawable.hid,
                    context.getText(R.string.lamp_hid)
                )
            )
    }
}