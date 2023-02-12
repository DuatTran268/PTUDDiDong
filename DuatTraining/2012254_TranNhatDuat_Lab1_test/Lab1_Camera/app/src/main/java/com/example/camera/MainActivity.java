package com.example.camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
  public static final int CAMERA_REQUEST_CODE = 100;
  int CAMERA_PERM_CODE = 0;
  ImageView image;
  Button btnCamera;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    InitControl();
  }

  private void InitControl() {
    image = findViewById(R.id.photo);
    btnCamera = findViewById(R.id.btnCamera);

    btnCamera.setOnClickListener(v -> {
//      Toast.makeText(MainActivity.this, "Camera btn is clicked", Toast.LENGTH_SHORT).show();
      askCameraPermissions();


    });
  }

  private void askCameraPermissions() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
    } else {
      openCamera();
    }
  }

  private void openCamera() {
    Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(camera, CAMERA_REQUEST_CODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
      Bitmap bitmap = (Bitmap) data.getExtras().get("data");
      image.setImageBitmap(bitmap);
    }

  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == CAMERA_PERM_CODE) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        openCamera();
      } else {
        Toast.makeText(this, "Camera Permission is Required to use camera", Toast.LENGTH_SHORT).show();
      }
    }
  }

}