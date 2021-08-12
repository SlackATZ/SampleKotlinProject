package com.example.sample

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

class Sample : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample)
        try {
            when {
                intent?.action == Intent.ACTION_VIEW -> {
                    //val line_msg = intent.toString().split("msg/text/")
                    val uri = intent.toUri(0).split("msg/text/")
                    val msg = uri[1].split("#Intent")

                    val manager =
                        applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip: ClipData = ClipData.newPlainText("label", msg[0])
                    manager.setPrimaryClip(clip)

                    //val text: TextView = findViewById(R.id.nest_text)
                    //text.setText(msg[0])

                    super.onBackPressed()
                }
            }
        } catch (ex: java.lang.Exception) {
            Log.d("Error", ex.toString())
        }

        val decoView = window.decorView
        decoView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
        val drawerLayout: DrawerLayout = findViewById(R.id.nav_sample_drawer)
        val navItem: LinearLayout = findViewById(R.id.nav_item)

        for (i in 0..navItem.childCount - 1) {
            navItem.getChildAt(i).setOnClickListener {
                when (navItem.getChildAt(i).id) {
                    R.id.nav_nav -> {
                        drawerLayout.closeDrawer(GravityCompat.START)
                        //val intent = Intent(this, NavSample::class.java)
                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        //startActivity(intent)
                    }
                    else -> {
                        //drawerLayout.closeDrawer(GravityCompat.START)
                        //startActivity(Intent(this, MainActivity::class.java))
                    }
                }
            }
        }
    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        var decoView = window.decorView
        decoView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }
}
