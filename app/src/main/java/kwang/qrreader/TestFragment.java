package kwang.qrreader;
<<<<<<< HEAD

=======
>>>>>>> 0354641d356ecd710fae8fe39cba398fed7d9647
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.hardware.Camera;
import android.util.Log;


/**
 * Created by Oliver on 1/25/2016.
 */
public class TestFragment extends Fragment implements Button.OnClickListener {

    private Button exit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
<<<<<<< HEAD
                                 Bundle savedInstanceState) {
=======
                             Bundle savedInstanceState) {
>>>>>>> 0354641d356ecd710fae8fe39cba398fed7d9647
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.test_fragment, container, false);
        exit = (Button) view.findViewById(R.id.button);
        exit.setOnClickListener(this);
        return view;
<<<<<<< HEAD
    }
=======


        }

>>>>>>> 0354641d356ecd710fae8fe39cba398fed7d9647

    public void onClick(View v) {
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }

}

