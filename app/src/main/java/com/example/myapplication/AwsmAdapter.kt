package com.example.myapplication

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView

enum class Lib {
    COIL, FRESCO
}

class AwsmAdapter(val lib: Lib) : RecyclerView.Adapter<AwsmAdapter.AwsmViewHolder>() {

    private val list = listOf(
        "https://64.media.tumblr.com/1851bd60b1104127e0cf0dfa6018a147/8420bfa79f6bcf9e-29/s540x810/59bfb4a70c4a9d0c34a099f298fccd9d0e72fb93.gifv" to
                "https://64.media.tumblr.com/fe2e66d85d7934abf2b3f4194bcf671d/8420bfa79f6bcf9e-97/s540x810/b5bf50c9efa12e993e6aaa02f9ee24ec70862be5.gifv",
        "https://64.media.tumblr.com/961e230d1283a771d5dcac27bbc3a644/8420bfa79f6bcf9e-2f/s540x810/585d72616fe8117b86d61b70f415892e6652da45.gifv" to
                "https://64.media.tumblr.com/497e2277ba8ec5bc76143a8aec17b638/8420bfa79f6bcf9e-35/s540x810/55ead80066a76a0eec946b43ff81781d85d1dcf2.gifv",
        "https://64.media.tumblr.com/574aad63bf62df137ac0187b0f7918a7/8420bfa79f6bcf9e-a1/s540x810/0cc67cb0ac1abbeabfab365d4e6ca0db3f27a282.gifv" to
                "https://64.media.tumblr.com/ea986891a5779cb998e9f96cc78ab5b7/8420bfa79f6bcf9e-75/s540x810/2ff057c3c2bce07de7903a9d95679ae4f34feafd.gifv"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AwsmViewHolder {
        return AwsmViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AwsmViewHolder, position: Int) {
        holder.bind(list[position], lib)
    }

    override fun getItemCount() = list.size

    class AwsmViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(pair: Pair<String, String>, lib: Lib) {
            val imageView1 = view.findViewById<SimpleDraweeView>(R.id.photo_1)
            val imageView2 = view.findViewById<SimpleDraweeView>(R.id.photo_2)
            when (lib) {
                Lib.COIL -> {
                    imageView1.load(pair.first)
                    imageView2.load(pair.second)
                }
                Lib.FRESCO -> {
                    val controller1 = Fresco.newDraweeControllerBuilder()
                        .setUri(Uri.parse(pair.first))
                        .setAutoPlayAnimations(true)
                        .build()
                    val controller2 = Fresco.newDraweeControllerBuilder()
                        .setUri(Uri.parse(pair.second))
                        .setAutoPlayAnimations(true)
                        .build()
                    imageView1.controller = controller1
                    imageView2.controller = controller2
                }
            }
        }
    }
}