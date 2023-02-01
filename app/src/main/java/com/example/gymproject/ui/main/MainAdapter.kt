package com.example.gymproject.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Exercise
import com.example.gymproject.databinding.ExerciseItemBinding

class MainAdapter(
    private val listener: ExerciseItemClickListener
): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    inner class MainViewHolder(val binding: ExerciseItemBinding): RecyclerView.ViewHolder(binding.root)

    private val callback = object : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ExerciseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentExercise = differ.currentList[position]
        if (position == differ.currentList.size) {
            holder.binding.bottomLine.visibility = View.GONE
        }
        holder.binding.apply {
            exerciseItemName.text = currentExercise.name
            exerciseItemBodyPart.text = currentExercise.bodyPart
        }

        holder.itemView.setOnClickListener {
            listener.onItemClicked(differ.currentList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    interface ExerciseItemClickListener {
        fun onItemClicked(exercise: Exercise)
    }
}
