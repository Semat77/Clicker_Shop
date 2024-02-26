package pl.mastercoding.intentest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import pl.mastercoding.intentest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var clickSum = 0
    private var upgrade = 1

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let { data ->
                clickSum = data.getIntExtra("CLICK_COUNTER", clickSum)
                upgrade = data.getIntExtra("UPGRADE_COUNT", upgrade)
                binding.counter.text = "Coins Counter: $clickSum"
                binding.upgradeInfo.text = "Points Per Click: $upgrade"
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.counter.text = "Coins Counter: $clickSum"
        binding.upgradeInfo.text = "Points Per Click: $upgrade"

        binding.buttonClicker.setOnClickListener {
            clickSum += upgrade
            binding.counter.text = "Coins Counter: $clickSum"
        }

        binding.shopButton.setOnClickListener {
            val intent = Intent(this, Shop::class.java).apply {
                putExtra("CLICK_COUNTER", clickSum)
                putExtra("UPGRADE_COUNT", upgrade)
            }
            startForResult.launch(intent)
        }
    }
}