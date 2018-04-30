package com.example.countrycapital;

import android.content.Context;
import android.content.res.Resources;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CountryFetcher {

    protected static final String TAG = CountryFetcher.class.getSimpleName();

    public List getCountries(Context context) {
        List<Country> items = new ArrayList<>();
        Resources res = context.getResources();
        try {
            InputStream in = res.getAssets().open("country-capital.json");
            InputStreamReader isr = new InputStreamReader(in);
            JsonReader reader = new JsonReader(isr);
            reader.beginArray();
            while (reader.hasNext()) {
                Country item = this.getCountry(reader);
                items.add(item);
            }
            reader.endArray();
        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage(), ioe);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return items;
    }

    protected Country getCountry(JsonReader reader) throws IOException {
        Country data = new Country();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("country")) {
                String country = reader.nextString();
                data.setName(country);
            } else if (name.equals("capital")) {
                String capital = reader.nextString();
                data.setCapital(capital);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return data;
    }
}
