package com.example.musicapi


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    lateinit var recyclerView:RecyclerView
    var genre= MusicAPI.initRetrofit().getRock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_musics)
        tabs.addOnTabSelectedListener(this)

        //Refresh for the music lists
        refresh_layout.setOnRefreshListener{
            if(tabs.selectedTabPosition==0){
                genre= MusicAPI.initRetrofit().getRock()
                show(genre)
                refresh_layout.isRefreshing=false
            }
            if(tabs.selectedTabPosition==1){
                genre= MusicAPI.initRetrofit().getClassic()
                show(genre)
                refresh_layout.isRefreshing=false
            }
            if(tabs.selectedTabPosition==2){
                genre= MusicAPI.initRetrofit().getPop()
                show(genre)
                refresh_layout.isRefreshing=false
            }
        }

        show(genre)
        }

//reloading the data when the tab changed
    override fun onTabSelected(tab: TabLayout.Tab?) {

        when(tab?.text){
            "Rock"->   genre= MusicAPI.initRetrofit().getRock()
            "Classic"->  genre= MusicAPI.initRetrofit().getClassic()
            "Pop"->  genre= MusicAPI.initRetrofit().getPop()
        }
        show(genre)

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }
    //show the list on the recycler view
    private fun show(call: retrofit2.Call<MusicList>){
        call.enqueue(
            object:Callback<MusicList>{
                override fun onResponse(
                    call: retrofit2.Call<MusicList>,
                    response: Response<MusicList>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            recyclerView.layoutManager = GridLayoutManager(this@MainActivity,1)
                            recyclerView.adapter = MusicAdapter(it,::playMusic)
                        }

                    }
                }

                override fun onFailure(call: retrofit2.Call<MusicList>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_SHORT).show()
                }

            }
        )

    }
//function for the play music
    private fun playMusic(musicinfo: Musicinfo){
    val intent =Intent()
        intent.action=Intent.ACTION_VIEW

        intent.setDataAndType(Uri.parse(musicinfo.previewUrl),"video/mp4")

        startActivity(intent)
    }

}









