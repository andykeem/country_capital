package com.example.countrycapital;

public class Country {
    protected String mName;
    protected String mCapital;
    protected boolean mValidCapital;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCapital() {
        return mCapital;
    }

    public void setCapital(String capital) {
        mCapital = capital;
    }

    public boolean isValidCapital(String capital) {
        return (mCapital.equalsIgnoreCase(capital));
    }
}
