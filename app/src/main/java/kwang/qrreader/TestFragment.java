package kwang.qrreader;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.hardware.Camera;
import android.util.Log;
import android.widget.ImageButton;


/**
 * Created by Oliver on 1/25/2016.
 */
public class TestFragment extends Fragment implements Button.OnClickListener {

    private Button exit;
    private ImageButton calorie;
    private Boolean fat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.test_fragment, container, false);
        exit = (Button) view.findViewById(R.id.button);
        exit.setOnClickListener(this);

        fat = false;
        calorie = (ImageButton) view.findViewById(R.id.imageButton);
        calorie.setOnClickListener(this);
        return view;
    }


    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            getActivity().getFragmentManager().beginTransaction().remove(this).commit();
        } else if (v.getId() == R.id.imageButton) {
            if (fat) {
                Drawable cal;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    cal = ContextCompat.getDrawable(getContext(), R.drawable.calories);
                } else {
                    cal = getResources().getDrawable(R.drawable.calories);
                }
                calorie.setImageDrawable(cal);
                fat = false;
            } else {
                Drawable f;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    f = ContextCompat.getDrawable(getContext(), R.drawable.fat);
                } else {
                    f = getResources().getDrawable(R.drawable.fat);
                }
                calorie.setImageDrawable(f);
                fat = true;
            }
        }

    }
}

