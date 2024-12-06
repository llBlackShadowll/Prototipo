package com.example.prototipo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.prototipo.databinding.ActivityMainBinding
import com.example.prototipo.databinding.ActivityLavadoVehiculosBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private var lavadoVehiculosBinding: ActivityLavadoVehiculosBinding? = null // Manejar el binding del otro layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el DrawerToggle
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configurar el botón Hamburger
        binding.imgHamburger.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        // Manejar las opciones del menú del Drawer
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_inicio -> {
                    // Acción para Inicio
                    showMainLayout() // Regresa al layout principal
                }
                R.id.menu_reserva -> {
                    // Acción para abrir la actividad de reserva
                    val intent = Intent(this, ReservaActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu_salir -> {
                    finish() // Cierra la aplicación
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Configurar el botón de Lavado de Vehículos
        binding.imgLavadoVehiculos.setOnClickListener {
            showLavadoVehiculosLayout()
        }

        // Configurar el carrusel (ViewPager2)
        setupCarousel()
    }

    override fun onBackPressed() {
        // Si estamos en el layout de Lavado de Vehículos, volvemos al layout principal
        if (lavadoVehiculosBinding != null) {
            showMainLayout()
        } else {
            super.onBackPressed()
        }
    }

    private fun setupCarousel() {
        val imageList = listOf(
            R.drawable.ic_example_image1,
            R.drawable.ic_example_image2,
            R.drawable.ic_example_image3
        )
        val adapter = ImageAdapter(imageList)
        binding.viewPager.adapter = adapter

        // Configurar el comportamiento del ViewPager2
        binding.viewPager.apply {
            offscreenPageLimit = 3
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    private fun showLavadoVehiculosLayout() {
        // Infla el layout de lavado de vehículos
        lavadoVehiculosBinding = ActivityLavadoVehiculosBinding.inflate(layoutInflater)
        setContentView(lavadoVehiculosBinding!!.root)

        // Manejar la interacción con los botones "Ver Más" en este layout
        lavadoVehiculosBinding!!.btnVerMasExpreso.setOnClickListener {
            // Acción para Ver Más en Lavado Expreso
        }
        lavadoVehiculosBinding!!.btnVerMasEconomico.setOnClickListener {
            // Acción para Ver Más en Lavado Económico
        }
        lavadoVehiculosBinding!!.btnVerMasEjecutivo.setOnClickListener {
            // Acción para Ver Más en Lavado Ejecutivo
        }
        lavadoVehiculosBinding!!.btnVerMasInterior.setOnClickListener {
            // Acción para Ver Más en Lavado Interior
        }
    }

    private fun showMainLayout() {
        // Regresa al layout principal
        setContentView(binding.root)
        lavadoVehiculosBinding = null // Limpiar el binding del otro layout
    }
}