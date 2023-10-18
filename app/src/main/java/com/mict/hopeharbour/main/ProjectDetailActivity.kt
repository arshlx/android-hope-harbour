package com.mict.hopeharbour.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mict.hopeharbour.databinding.ActivityProjectDetailBinding
import com.mict.hopeharbour.model.Project
import global_objects.Constants

class ProjectDetailActivity : AppCompatActivity() {

    private var _binding: ActivityProjectDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var project: Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProjectDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        project = Gson().fromJson(intent.getStringExtra(Constants.PROJECT), Project::class.java)
        binding.apply {
            /*assnNameTxt.text = project.assnName
            dueDateTxt.text = project.dueDate
            descriptionTxt.text = project.desc
            groupLayout.isVisible = project.isGroup
            sendEmail.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:") // only email apps should handle this
                    putExtra(Intent.EXTRA_EMAIL, "arshdeep100@gmail.com")
                }
                try {
                    startActivity(intent)
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(
                        this@ProjectDetailActivity,
                        R.string.application_not_found,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }*/
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}