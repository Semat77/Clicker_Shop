package pl.mastercoding.intentest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.mastercoding.intentest.databinding.ActivityShopBinding
class Shop : AppCompatActivity() {
    private lateinit var binding: ActivityShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mIntent = intent
        var clickSum = mIntent.getIntExtra("CLICK_COUNTER", 0)
        var upgrade = mIntent.getIntExtra("UPGRADE_COUNT", 1)

        binding.clickSum.text = "Coins Counter: $clickSum"
        binding.upgradeInfo.text = "Points Per Click: $upgrade"
        binding.upgradeButton.setOnClickListener{
            if(clickSum >= 10) {
                clickSum -= 10
                upgrade += 1
                binding.clickSum.text = "Coins Counter: $clickSum"
                binding.upgradeInfo.text = "Points Per Click: $upgrade"
            }
        }

        binding.returnButton.setOnClickListener{
            val explicitIntent = Intent().apply {
                putExtra("CLICK_COUNTER", clickSum)
                putExtra("UPGRADE_COUNT", upgrade)
            }
            setResult(Activity.RESULT_OK, explicitIntent)
            finish()
        }
    }
}
