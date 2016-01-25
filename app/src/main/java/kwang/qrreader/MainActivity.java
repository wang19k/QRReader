package kwang.qrreader;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
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
import android.hardware.Camera.Parameters;
import android.view.SurfaceHolder;
import android.graphics.PixelFormat;
import android.graphics.Canvas;
import android.graphics.Color;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
        //New added part
        try{ super.onCreate(savedInstanceState);
            //cv = new CustomCameraView(this.getApplicationContext());
            CustomCameraView cv = new CustomCameraView(this.getApplicationContext());
            FrameLayout rl = new FrameLayout( this.getApplicationContext()); setContentView(rl); rl.addView(cv); }
        catch(Exception e){}
    }
    //New
    public class CustomCameraView extends SurfaceView {
        Camera camera;
        SurfaceHolder previewHolder;
        SurfaceHolder.Callback surfaceHolderListener = new SurfaceHolder.Callback()
        { public void surfaceCreated(SurfaceHolder holder) {
                camera=Camera.open();
                try { camera.setPreviewDisplay(previewHolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void surfaceChanged(SurfaceHolder holder, int format, int w, int h)
            { Parameters params = camera.getParameters();
                params.setPreviewSize(800, 480);
                params.setPictureFormat(PixelFormat.JPEG);
                camera.setParameters(params);
                camera.startPreview(); }
            public void surfaceDestroyed(SurfaceHolder arg0)
            {
                camera.stopPreview();
                camera.release(); }
        };
        public CustomCameraView(Context context) { super(context);
            previewHolder = this.getHolder();
            previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            previewHolder.addCallback(surfaceHolderListener);
            setBackgroundColor(Color.TRANSPARENT);
        }
        protected void onDraw (Canvas canvas)
        {
            super.onDraw(canvas);
        }

        public void closeCamera()
        {
            if(camera != null)
                camera.release();
        }
        public void dispatchDraw(Canvas c) {
            super.dispatchDraw(c);
        }
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
