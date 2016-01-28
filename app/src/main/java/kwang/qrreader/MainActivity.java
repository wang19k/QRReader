package kwang.qrreader;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.FrameLayout;
import android.content.Context;
import android.view.SurfaceView;
import android.hardware.Camera;
import android.view.SurfaceHolder;


import java.io.IOException;
import kwang.qrreader.CameraPreview;


public class MainActivity extends AppCompatActivity implements OnClickListener{

    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = (Button)findViewById(R.id.scan_button);
        scanBtn.setOnClickListener(this);
        scanBtn.setAlpha(0);
        mCamera = getCameraInstance();
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        Intent scan = new Intent(this.getApplicationContext(), ScannerActivity.class);
        startActivity(scan);
    }
        //New added part
        public static Camera getCameraInstance(){
            Camera c = null;
            try {
                c = Camera.open();
            }
            catch (Exception e){
                // Camera is not available (in use or does not exist)
            }
            return c; // returns null if camera is unavailable
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        /**if (this.mCamera == null){
        this.mCamera = getCameraInstance();
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }**/
        try {
            mCamera = Camera.open();
            mCamera.setPreviewCallback(null);
            mPreview = new CameraPreview(this, mCamera);// set preview
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);
            mCamera.startPreview();
        } catch (Exception e) {

        }
        try {
            scanBtn.setOnClickListener(this);
        } catch (Exception e) {

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mPreview.getHolder().removeCallback(mPreview);
            mCamera.release();
            mCamera = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            scanBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        } catch (Exception e) {

        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            if (scanContent.equals("123")) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RecoveryBarFragment fragment = new RecoveryBarFragment();
                fragmentTransaction.add(R.id.test, fragment);
                fragmentTransaction.commit();
            }
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
        onResume();
    }
}
