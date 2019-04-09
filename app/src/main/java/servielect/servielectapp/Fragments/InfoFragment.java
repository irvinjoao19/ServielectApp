package servielect.servielectapp.Fragments;


import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import servielect.servielectapp.R;

public class InfoFragment extends Fragment {


    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);


        textView2 = (TextView) view.findViewById(R.id.textView2);
        textView3 = (TextView) view.findViewById(R.id.textView3);
        textView4 = (TextView) view.findViewById(R.id.textView4);
        textView5 = (TextView) view.findViewById(R.id.textView5);


        String brand = Build.BRAND;
        String model = Build.MODEL;
        String release = Build.VERSION.RELEASE;
        String deviceId = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        textView2.setText(brand);
        textView3.setText(model);
        textView4.setText(release);
        textView5.setText(deviceId);


        return view;
    }

}
