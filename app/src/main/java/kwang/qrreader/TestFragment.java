package kwang.qrreader;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Oliver on 1/25/2016.
 */
public class TestFragment extends Fragment{

    private Button exit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.test_fragment, container, false);
    }
}
