package com.app.app.ui.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.app.databinding.FragmentErrorBinding

class ErrorFragment: Fragment() {

    private var _binding: FragmentErrorBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val TAG = "Screen Error"
        fun newInstance(): ErrorFragment {
            return ErrorFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorBinding
            .inflate(layoutInflater, null, false)
        return binding.root
    }
}