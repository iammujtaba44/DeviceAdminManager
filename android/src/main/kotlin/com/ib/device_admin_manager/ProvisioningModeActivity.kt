package com.ib.device_admin_manager

import android.app.admin.DevicePolicyManager.EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ib.device_admin_manager.R

class ProvisioningModeActivity : AppCompatActivity() {

    private val EXTRA_PROVISIONING_ALLOWED_PROVISIONING_MODES = "android.app.extra.PROVISIONING_ALLOWED_PROVISIONING_MODES"
    private val PROVISIONING_MODE_FULLY_MANAGED_DEVICE = 1
    private val PROVISIONING_MODE_MANAGED_PROFILE = 2
    private val EXTRA_PROVISIONING_MODE = "android.app.extra.PROVISIONING_MODE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_provisioning_mode)

        val intent = intent
        var provisioningMode = PROVISIONING_MODE_FULLY_MANAGED_DEVICE
        val allowedProvisioningModes = intent.getIntegerArrayListExtra(EXTRA_PROVISIONING_ALLOWED_PROVISIONING_MODES)

        if (allowedProvisioningModes != null) {
            provisioningMode = when {
                allowedProvisioningModes.contains(PROVISIONING_MODE_FULLY_MANAGED_DEVICE) -> PROVISIONING_MODE_FULLY_MANAGED_DEVICE
                allowedProvisioningModes.contains(PROVISIONING_MODE_MANAGED_PROFILE) -> PROVISIONING_MODE_MANAGED_PROFILE
                else -> provisioningMode
            }
        }

        // Grab the extras (might contain needed values from QR code) and pass to AdminPolicyComplianceActivity
        val extras: PersistableBundle? = intent.getParcelableExtra(EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE)
        val resultIntent = intent

        extras?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                resultIntent.putExtra(EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE, it)
            }
        }

        resultIntent.putExtra(EXTRA_PROVISIONING_MODE, provisioningMode)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}
