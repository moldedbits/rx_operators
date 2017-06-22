package com.moldedbits.reactiveoperators

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.moldedbits.reactiveoperators.creating.create.CreateJava
import com.moldedbits.reactiveoperators.creating.create.CreateKotlin
import com.moldedbits.reactiveoperators.creating.create.GenerateJava
import com.moldedbits.reactiveoperators.creating.create.UnsafeCreateSample
import com.moldedbits.reactiveoperators.creating.defer.DeferJava
import com.moldedbits.reactiveoperators.creating.defer.DeferKotlin
import com.moldedbits.reactiveoperators.creating.empty.EmptyJava
import com.moldedbits.reactiveoperators.creating.empty.EmptyKotlin
import com.moldedbits.reactiveoperators.creating.from.FromJava
import com.moldedbits.reactiveoperators.creating.from.FromKotlin
import com.moldedbits.reactiveoperators.transformation.map.MapJava
import com.moldedbits.reactiveoperators.transformation.map.MapKotlin
import com.moldedbits.reactiveoperators.utility.delay.DelayJava
import com.moldedbits.reactiveoperators.utility.delay.DelayKotlin
import com.moldedbits.reactiveoperators.utility.schedulers.SchedulersJava
import com.moldedbits.reactiveoperators.utility.schedulers.SchedulersKotlin
import com.moldedbits.reactiveoperators.utility.timestamp.TimestampJava
import com.moldedbits.reactiveoperators.utility.timestamp.TimestampKotlin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createSample()
    }

    private fun createSample() {
        val output = findViewById(R.id.textview) as TextView

        CreateJava.test(findViewById(R.id.btn_java), output)
        CreateKotlin.test(findViewById(R.id.btn_kotlin), output)

        GenerateJava.test()

        UnsafeCreateSample.test()

        DeferJava.test()
        DeferKotlin.test()

        EmptyJava.test()
        EmptyKotlin.test()

        FromJava.test()
        FromKotlin.test()

        TimestampJava.test()
        TimestampKotlin.test()

        MapJava.test()
        MapKotlin.test()

        SchedulersJava.test()
        SchedulersKotlin.test()

        DelayJava.test()
        DelayKotlin.test()
    }
}
