package com.example.androidcamwork;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String TAG ="StateChange";
    private String currentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i(TAG , "onResume");
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i(TAG , "onStop");
    }
    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.i(TAG , "onRestart");
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG , "onDestroy");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.i(TAG , "onSaveInstanceState");

        final TextView editText = findViewById(R.id.textView);

        outState.putCharSequence("userText" , editText.getText());
        final ImageView imageView = findViewById(R.id.imageView);
        Drawable draw = imageView.getDrawable();

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG , "onRestoreInstanceState");

        final TextView editText =findViewById(R.id.textView);
        CharSequence userText = savedInstanceState.getCharSequence("userText");
        editText.setText(userText);
        final ImageView imageView = findViewById(R.id.imageView);
        Bitmap bitmap= savedInstanceState.getParcelable("imageView");
        imageView.setImageBitmap(bitmap);

    }
    public void launchCameraAction(View view)
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView view = this.findViewById(R.id.imageView);
            view.setImageBitmap(imageBitmap);

        }
    }

}