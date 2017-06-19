package com.moldedbits.reactiveoperators.creating.create

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import io.reactivex.Observable

object CreateKotlin {

    @SuppressLint("SetTextI18n")
    fun test(view: View, output: TextView) {
        val clickProvider = CreateJava.ClickProvider(view)

        val observable = Observable.create<View> {
            val callback = object : CreateJava.Callback {
                override fun onEvent(view: View) {
                    it.onNext(view)
                }

                override fun onFailure(e: Exception) {
                    it.onError(e)
                }
            }

            val c = clickProvider.listen(callback)

            it.setCancellable(c::close)
        }

        observable.subscribe { output.text = "${output.text}\nClick detected from Kotlin" }
    }
}