package com.bibaswan.wipro_telstra.ui.canadaInfo

import android.R
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bibaswan.wipro_telstra.data.entities.CanadaInfo
import com.bibaswan.wipro_telstra.databinding.ItemCanadaInfoBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions


class CanadaInfoAdapter(private val listener: CharacterItemListener) : RecyclerView.Adapter<CharacterViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter(characterId: Int)
    }

    private val items = ArrayList<CanadaInfo>()

    fun setItems(items: ArrayList<CanadaInfo>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemCanadaInfoBinding = ItemCanadaInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(items[position])
}

class CharacterViewHolder(private val itemBinding: ItemCanadaInfoBinding, private val listener: CanadaInfoAdapter.CharacterItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var canadaInfo: CanadaInfo
    private val Title_Not_Found:String="Title not found"
    private val Description_Not_Found:String="Description not found"

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: CanadaInfo) {
        this.canadaInfo = item
        var url =item.imageHref
        var title =item.title
        var description =item.description
        if(title != null){
            itemBinding.title.text = item.title
        }else{
            itemBinding.title.text =  Title_Not_Found
        }
        if(description != null){
            itemBinding.description.text = """${item.description}"""
        }else{
            itemBinding.description.text =  Description_Not_Found
        }
        if (url != null) {
            if (url.startsWith("http://"))
                url = url.replace("http://", "https://")
        }else{
            url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
        }

        itemBinding.title.text = item.title

        val requestOptions = RequestOptions()
        requestOptions.override(120,120)
        requestOptions.placeholder(com.bibaswan.wipro_telstra.R.mipmap.ic_logo)
        requestOptions.error(com.bibaswan.wipro_telstra.R.mipmap.ic_logo)
        Glide.with(itemBinding.root)
            .load(url)
            .apply(requestOptions)
            .transform(CircleCrop())
            .into(itemBinding.image)


    }

    override fun onClick(v: View?) {
        canadaInfo.id?.let { listener.onClickedCharacter(it) }
    }
}

