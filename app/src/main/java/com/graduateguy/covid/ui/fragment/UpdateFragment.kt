package com.graduateguy.covid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.graduateguy.covid.databinding.UpdatesFragmentLayoutBinding

class UpdateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return UpdatesFragmentLayoutBinding.inflate(inflater).root
    }
}
