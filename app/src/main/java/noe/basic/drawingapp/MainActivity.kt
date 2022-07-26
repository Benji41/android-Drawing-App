package noe.basic.drawingapp

import android.Manifest
import android.Manifest.permission
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    //objeto que dibuja el dialogo para obtener permisos
    private var cameraResultLauncher : ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){
            //trabaja e imprime el resultado dado la opcion elegida
                isGranted ->
                    if(isGranted){
                        Toast.makeText(this,"permission granted qp rahu xd",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"permission ungranted",Toast.LENGTH_SHORT).show()
                    }
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnPermission = findViewById<Button>(R.id.button_request_permissions)
        btnPermission.setOnClickListener {
            setPermission()
        }
    }

    private fun showRtionaleDialog(title : String, message: String){
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("cancel"){
            dialog,_-> dialog.dismiss()
        }
        builder.setNegativeButton(
            "Grant permission"
        ) { dialog, which ->
            cameraResultLauncher.launch(permission.CAMERA)
            dialog.dismiss()
        }
        builder.create().show()
    }
    private fun setPermission(){
        //ya asignaste pero el permiso fue denegado
        if (shouldShowRequestPermissionRationale(permission.CAMERA)){
            showRtionaleDialog("This app needs a Camera permission","you already denied the permission")
        }else{
            //asignas que permiso deseas solicitar en base a la string permission.
            cameraResultLauncher.launch(permission.CAMERA)
        }
    }
}
