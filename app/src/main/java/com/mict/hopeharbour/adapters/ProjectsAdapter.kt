package com.mict.hopeharbour.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.mict.hopeharbour.R
import com.mict.hopeharbour.databinding.ItemProjectBinding
import com.mict.hopeharbour.interfaces.CountryNameInterface
import com.mict.hopeharbour.model.Project

class ProjectsAdapter(
    private val fragment: Fragment,
    private val projects: List<Project>,
    private val projectInterface: CountryNameInterface
) :
    RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>() {
    inner class ProjectViewHolder(private val binding: ItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            binding.apply {
                projectNameTxt.text = project.title
                projectSummaryTxt.text = project.summary
                container.setOnClickListener {
                    projectInterface.onClick(Gson().toJson(project))
                }
                if (!project.imageLink.isNullOrEmpty())
                    Glide.with(fragment.requireContext()).load(project.imageLink)
                        .placeholder(R.drawable.vec_img_loading).into(projectImg)
                else projectImg.visibility = View.GONE
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(
            ItemProjectBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projects[position])
    }

    override fun getItemCount() = projects.size
}