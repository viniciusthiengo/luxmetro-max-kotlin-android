package thiengo.com.br.luxmetromax

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import thiengo.com.br.luxmetromax.domain.Lamp

class LampsAdapter( val lamps: List<Lamp> ):
    RecyclerView.Adapter<LampsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup, type: Int
        ): ViewHolder {

        val layout = LayoutInflater
            .from( parent.context )
            .inflate(
                R.layout.lamp_old,
                parent,
                false
            )

        return ViewHolder( layout )
    }

    override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int
        ) {
        holder.setModel( lamps[ position ] )
    }

    override fun getItemCount() = lamps.size

    inner class ViewHolder( itemView: View): RecyclerView.ViewHolder( itemView ){

        val ivLamp: ImageView
        val tvDescription: TextView

        init{
            ivLamp = itemView.findViewById( R.id.iv_lamp )
            tvDescription = itemView.findViewById( R.id.tv_description )
        }

        fun setModel( lamp: Lamp ){

            ivLamp.setImageResource( lamp.imageRes )

            tvDescription.text = lamp.getDescriptionStyled( itemView.context )
        }
    }
}