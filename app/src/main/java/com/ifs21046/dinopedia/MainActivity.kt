package com.ifs21046.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21046.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataGroup = ArrayList<Group>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.exogroup.setHasFixedSize(false)
        dataGroup.addAll(getListGroup())
        showRecyclerList()
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
    private fun getListGroup(): Collection<Group> {
        val dataGroupName = resources.getStringArray(R.array.riskianaomi_name)
        val dataGroupIcon = resources.obtainTypedArray(R.array.dinosaurus_icon)
        val dataGroupDescription = resources.getStringArray(R.array.dinosaurus_description)
        val dataGroupperiode = resources.getStringArray(R.array.dinosaurus_periodehidup)
        val dataGroupper = resources.getStringArray(R.array.dinosaurus_perilakudanklasifikasi)
        val dataGrouphab = resources.getStringArray(R.array.dinosaurus_habitatdanlingkungan)

        val listGroup = ArrayList<Group>()
        for (i in dataGroupName.indices) {
            val group = Group(
                dataGroupName[i],
                dataGroupIcon.getResourceId(i, -1),
                dataGroupDescription[i],
                dataGrouphab[i],
                dataGroupperiode[i],
                dataGroupper[i]
            )
            listGroup.add(group)
        }
        return listGroup
    }


    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.exogroup.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.exogroup.layoutManager = LinearLayoutManager(this)
        }

        val listGroupAdapter = ListGroupAdapter(dataGroup)
        binding.exogroup.adapter = listGroupAdapter

        listGroupAdapter.setOnItemClickCallback(object : ListGroupAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Group) {
                showSelectedGroup(data)
            }
        })
    }

    private fun showSelectedGroup(group: Group) {
        val intentWithData = Intent(this@MainActivity, DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_GROUP, group)
        startActivity(intentWithData)
    }
}
