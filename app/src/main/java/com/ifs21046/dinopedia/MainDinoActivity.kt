package com.ifs21046.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21046.dinopedia.databinding.ActivityMainDinoBinding

class MainDinoActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainDinoBinding
        private val dataDino = ArrayList<Dino>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainDinoBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.rvDino.setHasFixedSize(false)
            dataDino.addAll(getListDino())
            showRecyclerList()
            val btnStart = findViewById<Button>(R.id.buttonallfamili)
            btnStart.setOnClickListener {
                val intent = Intent(this@MainDinoActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                android.R.id.home -> {
                    // Kembali ke MainActivity (tidak perlu memanggil AboutActivity)
                    finish() // Menutup MainActivity dan kembali ke Activity sebelumnya (biasanya MainActivity)
                    true
                }
                R.id.menu_about -> {
                    // Panggil AboutActivity
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }

        @SuppressLint("Recycle")
        private fun getListDino(): Collection<Dino> {
            val dataDinoName = resources.getStringArray(R.array.riskianaomi_names)
            val dataDinoIcon = resources.obtainTypedArray(R.array.dinosaurus_iconn)
            val dataDinoDescription = resources.getStringArray(R.array.dinosaurus_descrip)
            val dataDinoperiode = resources.getStringArray(R.array.riskianaomi_kelompok)
            val dataDinoper = resources.getStringArray(R.array.riskianaomi_Habitat)
            val dataDinohab = resources.getStringArray(R.array.riskianaomi_Makanan)
            val dataDinopan = resources.getStringArray(R.array.riskianaomi_Panjang)
            val dataDinoting = resources.getStringArray(R.array.riskianaomi_Tinggi)
            val dataDinobo = resources.getStringArray(R.array.riskianaomi_Bobot)
            val dataDinokelem = resources.getStringArray(R.array.riskianaomi_Kelemahan)

            val listDino = ArrayList<Dino>()
            for (i in dataDinoName.indices) {
                val dino = Dino(
                    dataDinoName[i],
                    dataDinoIcon.getResourceId(i, -1),
                    dataDinoDescription[i],
                    dataDinoperiode[i],
                    dataDinoper[i],
                    dataDinohab[i],
                    dataDinopan[i],
                    dataDinoting[i],
                    dataDinobo[i],
                    dataDinokelem[i]
                )
                listDino.add(dino)
            }
            return listDino
        }


        private fun showRecyclerList() {
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                binding.rvDino.layoutManager = GridLayoutManager(this, 2)
            } else {
                binding.rvDino.layoutManager = LinearLayoutManager(this)
            }

            val listDinoAdapter = ListDinoAdapter(dataDino)
            binding.rvDino.adapter = listDinoAdapter

            listDinoAdapter.setOnItemClickCallback(object : ListDinoAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Dino) {
                    showSelectedGroup(data)
                }
            })
        }

        private fun showSelectedGroup(dino: Dino) {
            val intentWithData = Intent(this@MainDinoActivity, DinoDetailActivity::class.java)
            intentWithData.putExtra(DinoDetailActivity.EXTRA_FAMILI, dino)
            startActivity(intentWithData)
        }

    companion object {
        const val EXTRA_FAMILI = "extra_famili"
    }

    }
