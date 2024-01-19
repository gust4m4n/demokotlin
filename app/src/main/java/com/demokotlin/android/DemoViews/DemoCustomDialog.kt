import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.demokotlin.android.R

class DemoCustomDialog(context: Context) : Dialog(context) {

    init {
        setContentView(R.layout.demo_custom_dialog)
        val dm = Resources.getSystem().displayMetrics
        val r = dm.run {
            Rect(0, 0, widthPixels, heightPixels)
        }
        window?.setLayout((r.width() * 0.9).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun showWith(initialValue: String, didSave: (value: String) -> Unit, didCancel: () -> Unit) {
        val editValue = findViewById(R.id.editValue) as EditText
        editValue.setText(initialValue)
        val btnSave = findViewById(R.id.btnSave) as Button
        btnSave.setOnClickListener {
            dismiss()
            didSave.invoke(editValue.text.toString())
        }
        val btnCancel = findViewById(R.id.btnCancel) as Button
        btnCancel.setOnClickListener {
            dismiss()
            didCancel.invoke()
        }

        show()
    }


}

