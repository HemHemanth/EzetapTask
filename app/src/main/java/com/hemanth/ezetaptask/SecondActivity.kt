package com.hemanth.ezetaptask

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hemanth.ezetaptask.databinding.ActivitySecondBinding
import com.hemanth.ezetaptask.model.DisplayData

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val bundle = intent.getBundleExtra("bundle")
//        val json = bundle?.getSerializable("json")
//        val map: HashMap<String, String> = intent.getSerializableExtra("map") as HashMap<String, String>
        /*val extras = intent.extras

        if (extras != null) {
            val keySey = extras.keySet()

            val iterator = keySey.iterator()

            while (iterator.hasNext()) {
                val key = iterator.next()
                map.put(key, extras.getString(key) ?: "")
            }
        }*/

        /*map.forEach {
            val txtView = TextView(this)
            txtView.text = it.value
            binding.linearLayout.addView(txtView)
        }*/

        /*for ((key, value) in map) {
            val txtView = TextView(this)
            txtView.text = value
            binding.linearLayout.addView(txtView)
        }*/

        val bundle = intent.getBundleExtra("bundle")
        val displayData: ArrayList<DisplayData> = bundle?.getSerializable("list") as ArrayList<DisplayData>

        for (i in 0 until displayData.size) {
            val txtView = TextView(this)
            txtView.text = displayData[i].value
            binding.linearLayout.addView(txtView)
        }
    }
}