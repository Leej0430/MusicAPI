package com.example.musicapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class MusicAdapter(val dataSet: MusicList,val activityCallback: (music:Musicinfo)->Unit)
    :RecyclerView.Adapter<MusicAdapter.MusicViewHolder>()
{


    class MusicViewHolder(val musicView:View)
    :RecyclerView.ViewHolder(musicView){

    private val artistName: TextView
    =musicView.findViewById(R.id.tv_artist_name)
    private val collectionName: TextView
            =musicView.findViewById(R.id.tv_collection_name)
    private val trackPrice: TextView
            =musicView.findViewById(R.id.tv_track_price)
        private val imagePoster: ImageView
                =musicView.findViewById(R.id.img_poster)

        fun onBind(dataItem: Musicinfo,openPlayCallback: (music:Musicinfo)->Unit){
            musicView.setOnClickListener {
                openPlayCallback.invoke(dataItem)
            }

            artistName.text=dataItem.artistName
            collectionName.text = dataItem.collectionName
            trackPrice.text = dataItem.trackPrice
            Picasso.get().load(dataItem.artworkUrl60).into(imagePoster)
        }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.music_detail,
                parent,false))
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
      holder.onBind(dataSet.results[position],activityCallback )
    }

    override fun getItemCount(): Int {
        return dataSet.results.size
    }


}