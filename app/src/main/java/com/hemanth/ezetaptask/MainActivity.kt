package com.hemanth.ezetaptask

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hemanth.ezetaptask.databinding.ActivityMainBinding
import com.hemanth.ezetaptask.model.DisplayData
import com.hemanth.ezetaptask.model.DisplayModel
import com.hemanth.ezetaptask.viewModel.EzetapViewModel
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val provider = ViewModelProvider(this).get(EzetapViewModel::class.java)
        provider.getCustomUI().observe(this, Observer { androidAssignment ->
            if (!androidAssignment.uidata.isNullOrEmpty()) {
                for (i in 0 until androidAssignment.uidata.size) {
                    when (androidAssignment.uidata[i].uitype) {
                        "label" -> {
                            val textView = TextView(this)
                            textView.text = androidAssignment.uidata[i].value
                            binding.linearLayout.addView(textView)
                        }

                        "edittext" -> {
                            val edtText = EditText(this)
                            edtText.hint = androidAssignment.uidata[i].hint
                            edtText.maxLines = 1
                            edtText.inputType = InputType.TYPE_CLASS_TEXT
                            binding.linearLayout.addView(edtText)
                        }

                        "button" -> {
                            val btn = Button(this)
                            btn.text = androidAssignment.uidata[i].value
                            binding.linearLayout.addView(btn)

                            btn.setOnClickListener {
                                val displayModel: DisplayModel = DisplayModel(ArrayList())
                                val intent = Intent(this, SecondActivity::class.java)

                                for (j in 0 until binding.linearLayout.childCount) {
                                    val view: View = binding.linearLayout.getChildAt(j)

                                    when (androidAssignment.uidata[j].uitype) {
                                        "label" -> {
                                            val txtView = view as TextView
                                            displayModel.displayData.add(
                                                DisplayData(
                                                    androidAssignment.uidata[j].key,
                                                    txtView.text.toString()
                                                )
                                            )
                                        }

                                        "edittext" -> {
                                            val edtText = view as EditText

                                            if (edtText.text.toString().isEmpty()) {
                                                Toast.makeText(this, androidAssignment.uidata[j].hint, Toast.LENGTH_SHORT).show()
                                                return@setOnClickListener
                                            }
                                            displayModel.displayData.add(
                                                DisplayData(
                                                    androidAssignment.uidata[j].key,
                                                    edtText.text.toString()
                                                )
                                            )

                                        }
                                    }
                                }
                                val bundle = Bundle()
                                bundle.putSerializable("list", displayModel.displayData as Serializable?)
                                intent.putExtra("bundle", bundle)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        })

    }
}