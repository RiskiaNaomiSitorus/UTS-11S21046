package com.ifs21046.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ifs21046.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var group: Group? = null

    companion object {
        const val EXTRA_GROUP = "extra_group" // Definisi konstanta EXTRA_GROUP
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        group = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_GROUP)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_GROUP)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (group != null) {
            supportActionBar?.title = "Famili ${group!!.name}"
            loadData(group!!)
        } else {
            finish()
        }

        // Menambahkan onClickListener untuk tombol "Lihat Anggota Dinosaurus"
        binding.showMembersButton.setOnClickListener {
            onShowMembersButtonClick()
        }
    }

    // Fungsi untuk menangani klik tombol "Lihat Anggota Dinosaurus"
    private fun onShowMembersButtonClick() {
        val intent = Intent(this, MainDinoActivity::class.java)
        intent.putExtra(MainDinoActivity.EXTRA_FAMILI, group!!)
        startActivity(intent)
    }

    private fun loadData(group: Group) {
        binding.dinosaurusIconRiskia.setImageResource(group.icon)
        binding.dinosaurusNamesRiskianaomi.text = group.name
        binding.dinosaurusDescripsiRiskianaomi.text = group.description
        binding.dinosaurusPeriodeRiskianaomi.text = group.periodehidup
        binding.dinosaurusCarRiskianaomi.text = group.perilakudanklasifikasi
        binding.dinosaurusHabRiskianaomi.text = group.habitatdanlingkungan
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

