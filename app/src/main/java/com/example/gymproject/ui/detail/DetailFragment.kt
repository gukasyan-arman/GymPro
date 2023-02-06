package com.example.gymproject.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import com.example.gymproject.R
import com.example.gymproject.databinding.FragmentDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.visibility = View.GONE

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bodyPartDetail.text = arguments?.getString("bodyPart")
        binding.nameDetail.text = arguments?.getString("name")
        binding.targetDetail.text = arguments?.getString("target")
        binding.detailEquipment.text = arguments?.getString("equipment")
        val gifUrl = arguments?.getString("gifUrl")

        with(binding.webView) {
            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            settings.loadsImagesAutomatically = true
            settings.javaScriptEnabled = true
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // chromium, enable hardware acceleration
                setLayerType(View.LAYER_TYPE_HARDWARE, null)
            } else {
                // older android version, disable hardware acceleration
                setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            }

            settings.cacheMode = WebSettings.LOAD_NO_CACHE
        }

        gifUrl?.let { binding.webView.loadUrl(it) }

    }
}