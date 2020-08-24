package com.example.pintest

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.pintest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        model.output.observe(this) {
            binding.output.text = when (it) {
                is Output.Error -> getString(R.string.invalid_pin);
                is Output.Result -> getString(R.string.pin_block, it.result)
            }
        }

        binding.input.addTextChangedListener {
            model.update(it.toString());
        }
    }
}
