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
    }
}
