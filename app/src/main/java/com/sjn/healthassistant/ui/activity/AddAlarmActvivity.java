package com.sjn.healthassistant.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sjn.healthassistant.R;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class AddAlarmActvivity extends BaseActivity {


    @Bind(R.id.pick_date_area)
    RelativeLayout mPickDateArea;
    private DatePickerDialog mDatePickerDialog;

    private TimePickerDialog mTimePickerDialog;

    private boolean isSingle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm_actvivity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Calendar calendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(this, mOnDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        mTimePickerDialog = new TimePickerDialog(this, mOnTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
    }


    private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        }
    };
    private TimePickerDialog.OnTimeSetListener mOnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        }
    };

    @OnCheckedChanged({R.id.single, R.id.repeat})
    public void onCheckChanged(CompoundButton button, boolean isChecked) {
        button.setTextSize(isChecked ? 18 : 14);
        if (button.getId() == R.id.single) {
            isSingle = isChecked;
            mPickDateArea.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        }
    }


    @OnClick(R.id.ensure)
    public void onEnsure() {

    }


    @OnClick({R.id.pick_date, R.id.pick_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pick_date:
                mDatePickerDialog.show();
                break;
            case R.id.pick_time:
                mTimePickerDialog.show();
                break;
        }
    }
}
