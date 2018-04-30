package com.example.countrycapital;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    protected static final String TAG = MainFragment.class.getSimpleName();
    protected List<Country> mItems;
    protected Random mRandom = new Random();
    protected TextView mTvCountry;
    protected EditText mEtCapital;
    protected Button mBtSubmit;
    protected Country mCountry;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        new CountryFetchTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mTvCountry = v.findViewById(R.id.tv_country_val);
        mEtCapital = v.findViewById(R.id.et_capital);
        mBtSubmit = v.findViewById(R.id.bt_submit);
        mBtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = mEtCapital.getText().toString();
                if (TextUtils.isEmpty(val)) {
                    Toast.makeText(getContext(), R.string.empty_capital, Toast.LENGTH_SHORT).show();
                } else {
                    if (mCountry.isValidCapital(val)) {
                        Toast.makeText(getContext(), R.string.correct_answer, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), R.string.wrong_answer, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return v;
    }

    public static Fragment newFragment() {
        Fragment f = new MainFragment();
        return f;
    }

    protected void updateUI() {
        int i = mRandom.nextInt(mItems.size());
        mCountry = mItems.get(i);
        mTvCountry.setText(mCountry.getName());
    }

    private class CountryFetchTask extends AsyncTask<Void, Void, List> {
        @Override
        protected List doInBackground(Void... params) {
            List list = new CountryFetcher().getCountries(getContext());
            return list;
        }
        @Override
        protected void onPostExecute(List result) { {
            mItems = result;
            updateUI();
        }}
    }
}
