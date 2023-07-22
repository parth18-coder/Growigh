package com.example.growigh.onboarding.onboarding_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.growigh.R
import kotlinx.android.synthetic.main.fragment_first_onboarding_screen.view.*

class FirstOnboardingScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_onboarding_screen, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.next_button_first_onboarding_screen.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view
    }
}