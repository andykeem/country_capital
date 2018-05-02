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
public class MainFragment extends Fragment implements View.OnClickListener {

    protected static final String TAG = MainFragment.class.getSimpleName();
    protected static final int NUM_CHOICES = 4;

    protected List<Country> mItems;
    protected Random mRandom = new Random();
    protected TextView mTvCountry;
    protected TextView mTvCapital_1;
    protected TextView mTvCapital_2;
    protected TextView mTvCapital_3;
    protected TextView mTvCapital_4;
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
        mTvCapital_1 = v.findViewById(R.id.tv_capital_1);
        mTvCapital_2 = v.findViewById(R.id.tv_capital_2);
        mTvCapital_3 = v.findViewById(R.id.tv_capital_3);
        mTvCapital_4 = v.findViewById(R.id.tv_capital_4);



        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_capital_1:

                break;
            case R.id.tv_capital_2:

                break;
            case R.id.tv_capital_3:

                break;
            case R.id.tv_capital_4:

                break;
        }
    }

    public static Fragment newFragment() {
        Fragment f = new MainFragment();
        return f;
    }

    protected void updateUI() {
        int i = mRandom.nextInt(mItems.size());
        mCountry = mItems.get(i);
        mTvCountry.setText(mCountry.getName());

        // get random num for correct choice
        mRandom.nextInt(NUM_CHOICES);

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
