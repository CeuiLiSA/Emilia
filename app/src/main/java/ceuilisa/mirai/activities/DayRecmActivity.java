package ceuilisa.mirai.activities;

import android.support.v4.app.Fragment;

import ceuilisa.mirai.fragments.FragmentDayRecm;

public class DayRecmActivity extends FragmentActivity{

    @Override
    protected Fragment createNewFragment() {
        return new FragmentDayRecm();
    }
}
