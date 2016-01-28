package kwang.qrreader;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Oliver on 1/28/2016.
 */
public class ScannerActivity extends Activity implements ZXingScannerView.ResultHandler, View.OnClickListener{
    private ZXingScannerView mScannerView;
    Button back;
    Fragment snack;
    boolean isSnack = false;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.scanner_activity);

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.scan_frame);
        mScannerView = new ZXingScannerView(this) {
        };
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);
        if (!isSnack) {
            back.setAlpha(0f);
        }
        contentFrame.addView(mScannerView);
        back.bringToFront();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleResult(Result rawResult) {
        //Toast.makeText(this, rawResult.getText(), Toast.LENGTH_SHORT).show();
        if (!isSnack) {
            if (rawResult.getText().contentEquals("123")) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                snack = new RecoveryBarFragment();
                fragmentTransaction.add(R.id.scan_frame, snack);
                fragmentTransaction.commit();
                back.setAlpha(1f);
                isSnack = true;
            }
            if (rawResult.getText().contentEquals("test")) {
                FragmentManager fragmentManger = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManger.beginTransaction();
                snack = new TestFragment();
                fragmentTransaction.add(R.id.scan_frame, snack);
                fragmentTransaction.commit();
                back.setAlpha(1f);
                isSnack = true;
            }
        }
        mScannerView.resumeCameraPreview(ScannerActivity.this);
//        Toast.makeText(this, "Contents = " + rawResult.getText() +
//                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
//
//        // Note:
//        // * Wait 2 seconds to resume the preview.
//        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
//        // * I don't know why this is the case but I don't have the time to figure out.
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mScannerView.resumeCameraPreview(SimpleScannerActivity.this);
//            }
//        }, 2000);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back && isSnack) {
            getFragmentManager().beginTransaction().remove(snack).commit();
            back.setAlpha(0f);
            isSnack = false;
        }
    }
}
