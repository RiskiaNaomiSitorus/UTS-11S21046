package com.ifs21046.dinopedia

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21046.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var group: Group? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        group = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_GROUP, Group::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_GROUP)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (group != null) {
            supportActionBar?.title = "Kelompok ${group!!.name}"
            loadData(group!!)
        } else {
            finish()
        }
    }

    private fun loadData(group: Group) {
        binding.dinosaurusIconRiskia.setImageResource(group.icon)
        binding.dinosaurusNameRiskianaomi.text = group.name
        binding.dinosaurusDescRiskianaomi.text = group.description
        binding.dinosaurusPerRiskianaomi.text = group.periodehidup
        binding.dinosaurusCarRiskianaomi.text = group.perilakudanklasifikasi
        binding.dinosaurusHabRiskianaomi.text = group.habitatdanlingkungan
        binding.dinosaurusPerRiskianaomi.text = group.perilakudanklasifikasi
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
        const val EXTRA_GROUP = "extra_group"
    }
}