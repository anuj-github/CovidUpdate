package com.graduateguy.covid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.Composable
import androidx.fragment.app.Fragment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.padding
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class UpdatesFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @Composable
    fun Greeting(message: String){
        Text(text = message, modifier = Modifier.padding(12.dp))
    }

    @Preview
    @Composable
    fun DefaultPreview(){
        MaterialTheme {
            Greeting("Coming soon")
        }
    }
}