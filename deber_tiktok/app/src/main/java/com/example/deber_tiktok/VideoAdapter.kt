package com.example.deber_tiktok

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(private val videos: List<VideoItem>) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val videoImage: ImageView = view.findViewById(R.id.videoImage)
        val userName: TextView = view.findViewById(R.id.userName)
        val videoDescription: TextView = view.findViewById(R.id.videoDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        holder.videoImage.setImageResource(video.imageResource)
        holder.userName.text = video.userName
        holder.videoDescription.text = video.description
    }

    override fun getItemCount() = videos.size
}

data class VideoItem(val userName: String, val description: String, val imageResource: Int)