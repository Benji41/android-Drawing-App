package noe.basic.drawingapp

import android.Manifest
import android.Manifest.permission
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnPermission = findViewById<Button>(R.id.button_request_permissions)
        btnPermission.setOnClickListener {
            requestPermission()
        }

    }
    private fun hasWriteExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(this,permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationCoarsePermission() =
        ActivityCompat.checkSelfPermission(this,permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

   /* @RequiresApi(Build.VERSION_CODES.Q)
    private fun hasLocationBackGroundPermission() =
        ActivityCompat.checkSelfPermission(this,permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED*/
    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(this,permission.CAMERA)  == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        var permissionListToRequest = mutableListOf<String>()
        if(!hasWriteExternalStoragePermission()){
            permissionListToRequest.add(permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!hasLocationCoarsePermission()){
            permissionListToRequest.add(permission.ACCESS_COARSE_LOCATION)
        }
       /* if(!hasLocationBackGroundPermission()){
            permissionListToRequest.add(permission.ACCESS_BACKGROUND_LOCATION)
        }*/
        if(!hasCameraPermission()){
            permissionListToRequest.add(permission.CAMERA)
        }
        if(permissionListToRequest.isNotEmpty()){
            //if we request different permission at different given times the requestcode will be the flag to differ which operation will
            //be executed onRequestPermission Result
            ActivityCompat.requestPermissions(this, permissionListToRequest.toTypedArray(),0)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode ==0 && grantResults.isNotEmpty()){
            for (i in grantResults.indices){
                Log.d("Permission Request", "${permissions[i]} granted")
            }
        }
    }


}
