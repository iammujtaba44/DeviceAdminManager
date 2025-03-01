import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ib.device_admin_manager.R

class AdminPolicyComplianceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_policy_compliance)

        val intent = intent
        setResult(RESULT_OK, intent)
        finish()
    }
}
