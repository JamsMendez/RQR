package com.next_io.crqr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_mode_scanner).setOnClickListener {
            val intent = IntentIntegrator.forSupportFragment(this)
            intent.setOrientationLocked(false)
            intent.initiateScan()
        }

        view.findViewById<Button>(R.id.btn_create_qr).setOnClickListener {
            Snackbar.make(view, "No hay nada ....", Snackbar.LENGTH_SHORT).show()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        Log.e("QR-LOG", "$requestCode $resultCode")

        val intentResult = IntentIntegrator.parseActivityResult(resultCode, data)
        if (intentResult != null) {
            if (intentResult.contents == null){
                Log.e("QR-LOG", "Cancelado ...")
            }else {
                val value = intentResult.contents
                Log.e("QR-LOG", value)
            }

        } else {
            Log.e("QR-LOG", "intentResult ES NULL")
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
