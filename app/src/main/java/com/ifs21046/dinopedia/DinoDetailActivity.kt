
package com.ifs21046.dinopedia

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ifs21046.dinopedia.databinding.ActivityDinoDetailBinding

class DinoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinoDetailBinding
    private var dino: Dino? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILI,
                Dino::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "Buah ${dino!!.name}"
            loadData(dino!!)
        } else {
            finish()
        }
    }
    private fun loadData(dino: Dino) {
        binding.ivDetailIcondino.setImageResource(dino.icon)
        binding.tvDetailNamedino.text = dino.name
        binding.tvDetaildescriptiondino.text = dino.description
        binding.tvDetailKelompokdino.text = dino.kelompok
        binding.tvDetailHabitatdino.text = dino.habitat
        binding.tvDetailPanjangTinggiBobot.text = dino.panjang
        binding.tvDetailKelemahan.text = dino.kelemahan
        binding.tvDetailMakanan.text = dino.makanan
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        const val EXTRA_FAMILI = "extra_famili"
    }
}