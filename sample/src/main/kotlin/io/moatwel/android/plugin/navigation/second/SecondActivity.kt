package io.moatwel.android.plugin.navigation.second

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.moatwel.android.plugin.navigation.R

class SecondActivity : AppCompatActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, SecondActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}