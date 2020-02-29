package io.moatwel.android.plugin.navigation.second

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.moatwel.android.plugin.navigation.R

class SecondAFragment : Fragment(R.layout.fragment_second_a) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.move_to_b).setOnClickListener {
            findNavController().navigate(R.id.action_second_A_to_second_B)
        }
    }
}