package com.rapider.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rapider.utils.Logger
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    @Inject
    lateinit var logger: Logger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView(savedInstanceState)
        initData(savedInstanceState)
    }
    abstract fun getLayoutResId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initData(savedInstanceState: Bundle?)

}