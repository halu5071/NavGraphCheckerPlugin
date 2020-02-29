package io.moatwel.android.plugin.navigation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.moatwel.android.plugin.navigation.first.FirstActivity
import io.moatwel.android.plugin.navigation.second.SecondActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.move_to_first).setOnClickListener {
            FirstActivity.start(this)
        }

        findViewById<TextView>(R.id.move_to_second).setOnClickListener {
            SecondActivity.start(this)
        }
    }
}
