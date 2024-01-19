import android.content.Context
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.demokotlin.android.R

class DemoBottomSheetDialog(context: Context) : BottomSheetDialog(context) {

    init {
        setContentView(R.layout.demo_custom_bottom_sheet_dialog)
    }

    fun showWith(didOK: (value: String) -> Unit, didCancel: () -> Unit) {
        val view = layoutInflater.inflate(R.layout.demo_custom_bottom_sheet_dialog, null)
        setCancelable(true)
        setContentView(view)

        val btnOK = view.findViewById(R.id.btnOK) as Button
        btnOK.setOnClickListener {
            dismiss()
            didOK.invoke("")
        }
        val btnCancel = view.findViewById(R.id.btnCancel) as Button
        btnCancel.setOnClickListener {
            dismiss()
            didCancel.invoke()
        }

        show()
    }


}

