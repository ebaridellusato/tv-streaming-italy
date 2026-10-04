package com.tvstreaming.italy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tvstreaming.italy.data.PreloadedChannels
import com.tvstreaming.italy.databinding.DialogAddChannelBinding

class AddChannelDialog(
    private val onChannelAdded: (name: String, url: String, category: String) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: DialogAddChannelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddChannelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.autoCompleteCategory.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                PreloadedChannels.categories
            )
        )
        binding.autoCompleteCategory.setText("Generale", false)

        binding.btnAddChannel.setOnClickListener {
            val name = binding.etChannelName.text.toString().trim()
            val url = binding.etChannelUrl.text.toString().trim()
            val category = binding.autoCompleteCategory.text.toString().trim()

            if (validate(name, url)) {
                onChannelAdded(name, url, category)
                dismiss()
            }
        }

        binding.btnCancel.setOnClickListener { dismiss() }
    }

    private fun validate(name: String, url: String): Boolean {
        var ok = true

        if (name.isEmpty()) {
            binding.tilChannelName.error = "Inserisci il nome del canale"
            ok = false
        } else binding.tilChannelName.error = null

        if (url.isEmpty()) {
            binding.tilChannelUrl.error = "Inserisci l'URL del flusso"
            ok = false
        } else if (!url.startsWith("http://") && !url.startsWith("https://")) {
            binding.tilChannelUrl.error = "L'URL deve iniziare con http:// o https://"
            ok = false
        } else binding.tilChannelUrl.error = null

        return ok
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
