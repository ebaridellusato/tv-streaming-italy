package com.tvstreaming.italy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tvstreaming.italy.adapter.ChannelAdapter
import com.tvstreaming.italy.data.PreloadedChannels
import com.tvstreaming.italy.database.Channel
import com.tvstreaming.italy.databinding.ActivityMainBinding
import com.tvstreaming.italy.viewmodel.ChannelViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ChannelViewModel
    private lateinit var adapter: ChannelAdapter

    private var all: List<Channel> = emptyList()
    private var searchQuery = ""
    private var selectedCategory = "Tutti"
    private var onlyFavorites = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ChannelViewModel::class.java]

        adapter = ChannelAdapter(
            onChannelClick = ::openPlayer,
            onFavoriteClick = { viewModel.toggleFavorite(it) },
            onDeleteClick = ::confirmDelete
        )
        binding.recyclerChannels.layoutManager = LinearLayoutManager(this)
        binding.recyclerChannels.adapter = adapter

        binding.searchView.setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = false
                override fun onQueryTextChange(newText: String?): Boolean {
                    searchQuery = newText.orEmpty()
                    applyFilters()
                    return true
                }
            }
        )

        setupChips()
        binding.fabAddChannel.setOnClickListener { showAddChannelDialog() }

        viewModel.allChannels.observe(this) { list ->
            all = list
            binding.progressBar.visibility = View.GONE
            applyFilters()
        }
    }

    private fun setupChips() {
        val group = binding.chipGroupCategories

        fun chip(label: String): Chip = Chip(this).apply {
            text = label
            isCheckable = true
        }

        val chipAll = chip("Tutti")
        chipAll.isChecked = true
        group.addView(chipAll)
        group.addView(chip("\u2B50 Preferiti"))
        PreloadedChannels.categories.forEach { group.addView(chip(it)) }

        group.setOnCheckedStateChangeListener { g, ids ->
            if (ids.isEmpty()) return@setOnCheckedStateChangeListener
            val label = g.findViewById<Chip>(ids[0]).text.toString()
            onlyFavorites = label.contains("Preferiti")
            selectedCategory = if (onlyFavorites) "Tutti" else label
            applyFilters()
        }
    }

    private fun applyFilters() {
        val filtered = all.filter { ch ->
            (!onlyFavorites || ch.isFavorite) &&
                (selectedCategory == "Tutti" || ch.category.equals(selectedCategory, ignoreCase = true)) &&
                (searchQuery.isBlank() || ch.name.contains(searchQuery, ignoreCase = true))
        }
        adapter.submitList(filtered)
        binding.tvChannelCount.text = "${filtered.size} di ${all.size} canali"
        binding.tvEmpty.visibility = if (filtered.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun showAddChannelDialog() {
        AddChannelDialog { name, url, category ->
            viewModel.addChannel(name, url, category)
            Toast.makeText(this, "Canale \"$name\" aggiunto!", Toast.LENGTH_SHORT).show()
        }.show(supportFragmentManager, "AddChannelDialog")
    }

    private fun confirmDelete(channel: Channel) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Elimina canale")
            .setMessage("Vuoi eliminare \"${channel.name}\"?")
            .setPositiveButton("Elimina") { _, _ ->
                viewModel.deleteChannel(channel)
                Toast.makeText(this, "Canale eliminato", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Annulla", null)
            .show()
    }

    private fun openPlayer(channel: Channel) {
        startActivity(Intent(this, PlayerActivity::class.java).apply {
            putExtra("CHANNEL_NAME", channel.name)
            putExtra("CHANNEL_URL", channel.streamUrl)
        })
    }
}
