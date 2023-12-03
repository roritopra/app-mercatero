package utils

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import icesi.edu.co.mercatero_app.databinding.DialogProgressBinding
import java.util.Locale

object Helper {

    fun formatDateTime(date:Long, format:String= Constants.dateFormat):String{
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(date)
    }


    var dialog : Dialog?=null
    fun Context.showProgressDialog(){
        dialog= Dialog(this)
        val binding =
            DialogProgressBinding.inflate(LayoutInflater.from(this))
        dialog?.setContentView(binding.root)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.show()

    }

    fun hideProgressDialog(){
        dialog?.dismiss()
    }

    var storagePermissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )



    fun Activity.hasPermissionsStorage():Boolean{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            true
        }else{
            hasPermissions(storagePermissions)
        }
    }

    fun Activity.requestStoragePermission(launcher: ActivityResultLauncher<Array<String>>)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            launcher.launch(storagePermissions)
        }

    }

    fun Activity.hasPermissions(permissions: Array<String>?): Boolean {

        if (permissions != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    Log.d("PERMISSIONS", "Permission is not granted: $permission")
                    return false
                }
                Log.d("PERMISSIONS", "Permission already granted: $permission")
            }
            return true
        }
        return false
    }

}