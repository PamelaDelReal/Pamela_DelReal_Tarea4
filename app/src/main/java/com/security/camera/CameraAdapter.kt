package com.security.camera

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CameraAdapter(
    private val ctx: Context,
    private val data: List<CameraItem>
) : BaseAdapter() {

    override fun getCount(): Int = data.size
    override fun getItem(position: Int): Any = data[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.item_camera, parent, false)
            holder = ViewHolder(
                img = view.findViewById(R.id.imgCam),
                brand = view.findViewById(R.id.txtBrand)
            )
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val item = data[position]
        holder.img.setImageResource(item.imageRes)
        holder.brand.text = item.brand

        return view
    }

    private data class ViewHolder(
        val img: ImageView,
        val brand: TextView
    )
}
