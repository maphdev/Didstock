package com.example.didstock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.barcode_scanner.BarcodeScanner;
import com.example.didstock.data.database.entity.Reference;
import com.example.didstock.data.repository.ReferenceRepository;
import com.example.didstock.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ReferenceRepository referenceRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        referenceRepository = new ReferenceRepository(getApplication());

        binding.button.setOnClickListener((e) -> {
            launchBarcodeScanner();
        });

        referenceRepository.getAllReferenceOrderedByCode().observe(this, new Observer<List<Reference>>() {
            @Override
            public void onChanged(List<Reference> references) {
                String s = "";
                for (Reference r: references) {
                    s += r.getCode() + "\n";
                }
                binding.text.setText(s);
            }
        });
    }

    private static final int ZXING_CAMERA_PERMISSION = 1;
    private static final int BARCODE_SCANNER_REQUEST_CODE = 0;

    public void launchBarcodeScanner() {
        // if camera permission not already granted, we ask for it
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        }
        // if camera permission already granted, we launch the Barcode Scanner
        else {
            Intent intent = new Intent(this, BarcodeScanner.class);
            startActivityForResult(intent, BARCODE_SCANNER_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZXING_CAMERA_PERMISSION:
                // if camera permission granted by user, we launch the Barcode Scanner
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    launchBarcodeScanner();
                }
                // if camera permission not granted by user, we display a toast
                else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BARCODE_SCANNER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            Log.i("Result", data.getStringExtra("barcode"));
            String code = data.getStringExtra("barcode");
            Reference ref = referenceRepository.getReferenceByCode(code);
            Log.i("REF", String.valueOf(ref == null));
            if (ref == null) {
                Reference refToAdd = new Reference(code, "img", "desc", null);
                referenceRepository.insert(refToAdd);
                Toast.makeText(this, "Barcode added in reference database!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Barcode already exist in reference database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}