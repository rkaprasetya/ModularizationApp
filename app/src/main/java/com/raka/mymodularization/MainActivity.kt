package com.raka.mymodularization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Modularization tutorial
 * https://medium.com/google-developer-experts/modularizing-android-applications-9e2d18f244a0
 * https://blog.mindorks.com/how-to-build-a-modular-android-app-architecture
 */
class MainActivity : AppCompatActivity() {
    private lateinit var splitInstallManager:SplitInstallManager
    private lateinit var request:SplitInstallRequest
    val DYNAMIC_FEATURE = "productfeature"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDynamicModules()
        setClickListeners()
    }
    private fun initDynamicModules() {
        splitInstallManager = SplitInstallManagerFactory.create(this)
        request = SplitInstallRequest
                .newBuilder()
                .addModule(DYNAMIC_FEATURE)
                .build()
    }
    private fun isDynamicFeatureDownloaded(feature:String):Boolean = splitInstallManager.installedModules.contains(feature)
    private fun setClickListeners(){
        buttonClick.setOnClickListener {
            if (!isDynamicFeatureDownloaded(DYNAMIC_FEATURE)){
                downloadFeature()
            }else{
                buttonDeleteNewsModule.visibility = View.VISIBLE
                buttonOpenNewsModule.visibility = View.VISIBLE
            }
        }
        buttonOpenNewsModule.setOnClickListener {
            val intent = Intent().setClassName(this,"com.raka.productfeature.CarActivity")
            startActivity(intent)
        }
        buttonDeleteNewsModule.setOnClickListener {
            val list = ArrayList<String>()
            list.add(DYNAMIC_FEATURE)
            uninstallDynamicFeature(list)
        }
    }

    private fun uninstallDynamicFeature(list: java.util.ArrayList<String>) {
        splitInstallManager.deferredUninstall(list)
                .addOnSuccessListener {
                    buttonDeleteNewsModule.visibility = View.GONE
                    buttonOpenNewsModule.visibility = View.GONE
                }
    }

    private fun downloadFeature() {
        splitInstallManager.startInstall(request)
                .addOnFailureListener {  }
                .addOnSuccessListener {
                    buttonOpenNewsModule.visibility = View.VISIBLE
                    buttonDeleteNewsModule.visibility = View.VISIBLE
                }
                .addOnCompleteListener {  }
    }


}