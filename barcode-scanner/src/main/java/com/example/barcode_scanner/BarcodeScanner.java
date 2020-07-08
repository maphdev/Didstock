package com.example.barcode_scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.barcode_scanner.databinding.BarcodeScannerBinding;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarcodeScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    BarcodeScannerBinding binding;

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BarcodeScannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mScannerView = new ZXingScannerView(this);
        binding.getRoot().addView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(this, "Contents = " + rawResult.getText() +
                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // mScannerView.resumeCameraPreview(SimpleScannerActivity.this);
                mScannerView.stopCamera();
                setResult(Activity.RESULT_OK, new Intent().putExtra("barcode", rawResult.getText()));
                finish();
            }
        }, 2000);
    }
}