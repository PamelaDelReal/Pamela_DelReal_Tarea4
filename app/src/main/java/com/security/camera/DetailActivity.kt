package com.security.camera

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Up Button (volver al listado)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalle cámara"

        val img = findViewById<ImageView>(R.id.imgBig)
        val tvBrand = findViewById<TextView>(R.id.tvBrand)
        val tvModel = findViewById<TextView>(R.id.tvModel)
        val tvPrice = findViewById<TextView>(R.id.tvPrice)

        val brand = intent.getStringExtra("brand") ?: ""
        val model = intent.getStringExtra("model") ?: ""
        val price = intent.getStringExtra("price") ?: ""
        val imageRes = intent.getIntExtra("imageRes", R.drawable.cam1)

        img.setImageResource(imageRes)
        tvBrand.text = "Marca: $brand"
        tvModel.text = "Modelo: $model"
        tvPrice.text = "Precio: $price"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else super.onOptionsItemSelected(item)
    }
}
