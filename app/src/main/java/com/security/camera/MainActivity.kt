package com.security.camera

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

data class CameraItem(
    val brand: String,
    val model: String,
    val price: String,
    val imageRes: Int
)

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: CameraAdapter
    private val cameras = arrayListOf(
        CameraItem("HikVision", "DS-2CD1043", "$89.990", R.drawable.cam1),
        CameraItem("Dahua", "IPC-HDW1431", "$99.990", R.drawable.cam2),
        CameraItem("Reolink", "RLK8-410B4", "$159.990", R.drawable.cam3),
        CameraItem("TP-Link", "Tapo C320WS", "$69.990", R.drawable.cam1),
        CameraItem("Ezviz", "C3WN", "$59.990", R.drawable.cam2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listCameras)
        adapter = CameraAdapter(this, cameras)
        listView.adapter = adapter

        // Click simple: ir al detalle con Intent explícito y putExtra
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val item = cameras[position]
            val i = Intent(this, DetailActivity::class.java).apply {
                putExtra("brand", item.brand)
                putExtra("model", item.model)
                putExtra("price", item.price)
                putExtra("imageRes", item.imageRes)
            }
            startActivity(i)
        }

        // Menú contextual: mantener presionado un elemento
        registerForContextMenu(listView)
    }

    // Crear el menú contextual
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu_camera, menu)
    }

    // Manejar opción eliminar
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.action_delete -> {
                cameras.removeAt(info.position)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Cámara eliminada", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
