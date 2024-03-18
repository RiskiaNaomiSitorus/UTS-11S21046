package com.ifs21046.dinopedia

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

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
            supportActionBar?.title = "Kelompok ${group!!.namefamili}"
            loadData(group!!)
        } else {
            finish()
        }
    }

    private fun loadData(group: Group) {
        binding.dinosaurusriskiaIconRiskia.setImageResource(group.icon)
        binding.dinosaurusriskiaNameRiskia.text = group.namefamili
        binding.dinosaurusriskiaDescRiskia.text = group.description
        binding.dinosaurusriskiaPeriodehidup.text = group.periodehidup
        binding.dinosaurusriskiaKarakteristik.text = group.karakteristikfisik
        binding.dinosaurusriskiaHabitat.text = group.habitatdanlingkungan
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

interface ActivityDetailBinding {

}
