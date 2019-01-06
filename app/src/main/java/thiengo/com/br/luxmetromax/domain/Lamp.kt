package thiengo.com.br.luxmetromax.domain

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import android.text.Annotation
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannedString
import thiengo.com.br.luxmetromax.R
import thiengo.com.br.luxmetromax.util.CustomTypefaceSpan

/*
 * O tipo de "description" foi alterado de String para
 * CharSequence, pois String não retém formatação, Span.
 * */
class Lamp(
    val imageRes: Int,
    val description: CharSequence ) {

    /*
     * Propriedade utilizada para conter a description já com
     * a tag Span configurada corretamente e assim evitar
     * re-chamada de algoritmo de colocação de Span de família
     * de fonte. spannableString trabalha como cache de conteúdo.
     * */
    private var spannableString = SpannableString("")

    fun getDescriptionStyled( context: Context ) : SpannableString{

        /*
         * Padrão Cláusula de Guarda para evitar que o algoritmo
         * de colocação de Span de família de fonte não seja
         * chamado novamente quando não mais for necessário
         * (somente uma execução é o suficiente).
         * */
        if( spannableString.length > 0 ){
            return spannableString
        }

        /*
         * Aplicando o casting de CharSequence para SpannedString
         * para que seja possível acessar as Spans presentes no
         * texto.
         *
         * O trim() está sendo utilizado para remover os espaços
         * em branco nos limites do texto, devido às formatações
         * em strings.xml.
         * */
        val spannedDesc = (description.trim()) as SpannedString

        /*
         * Obtendo todas as Annotation Span do texto.
         * */
        val annotations = spannedDesc.getSpans(
            0,
            spannedDesc.length,
            Annotation::class.java
        )

        /*
         * Criando uma cópia do texto, com SpannableString, para
         * que seja possível adicionar ou remover Span.
         * */
        spannableString = SpannableString( spannedDesc )

        for( annotation in annotations ){

            /*
             * Annotation trabalha no modelo <key, value>, onde o
             * rótulo do atributo utilizado na tag <annotation> é
             * a key e o valor do atributo é o value.
             *
             * No condicional abaixo estamos verificando se é a
             * chave "fontFamily" no atual annotation do loop.
             * Podemos utilizar qualquer chave de nossa criação.
             * */
            if( annotation.key.equals("fontFamily") ){

                /*
                 * Como estamos trabalhando somente com uma
                 * família de fonte via Span, não há necessidade
                 * de acessar o valor em annotation.value para
                 * ainda mais comparações em blocos condicionais.
                 * */
                val typeface = ResourcesCompat.getFont( context, R.font.open_sans_condensed_bold )

                spannableString.setSpan(
                    CustomTypefaceSpan( typeface!! ),
                    spannedDesc.getSpanStart( annotation ),
                    spannedDesc.getSpanEnd( annotation ),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        return spannableString
    }
}