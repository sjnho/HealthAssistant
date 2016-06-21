package com.sjn.healthassistant.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.r0adkll.slidr.Slidr;
import com.sjn.healthassistant.R;
import com.sjn.healthassistant.SjnApplication;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.pojo.DrugAlarm;
import com.sjn.healthassistant.util.AlarmUtil;
import com.sjn.healthassistant.util.LogUtil;
import com.sjn.healthassistant.util.SpUtil;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.realm.Realm;

public class AddAlarmActvivity extends BaseActivity {


    @Bind(R.id.pick_date_area)
    RelativeLayout mPickDateArea;
    @Bind(R.id.pick_date)
    TextView mPickDate;
    @Bind(R.id.pick_time)
    TextView mPickTime;
    @Bind(R.id.single)
    RadioButton mSingleButton;
    private DatePickerDialog mDatePickerDialog;

    private TimePickerDialog mTimePickerDialog;

    private boolean isSingle;


    private Calendar mSingleCalendar, mPerCalendar;

    private Realm mRealm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm_actvivity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mRealm = Realm.getDefaultInstance();
        setUpToolbar("新建闹钟");
        Slidr.attach(this);
        Calendar calendar = Calendar.getInstance();
        mSingleCalendar = Calendar.getInstance();
        mPerCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(this, mOnDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        mTimePickerDialog = new TimePickerDialog(this, mOnTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        mSingleButton.setChecked(true);
    }


    private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mSingleCalendar.set(year, monthOfYear, dayOfMonth);
            String dateText = year + "年" + String.valueOf(monthOfYear + 1) + "月" + dayOfMonth + "日";
            mPickDate.setText(dateText);

        }
    };
    private TimePickerDialog.OnTimeSetListener mOnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            if (isSingle) {
                mSingleCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mSingleCalendar.set(Calendar.MINUTE, minute);
            } else {
                mPerCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mPerCalendar.set(Calendar.MINUTE, minute);
            }

            String timeText = hourOfDay > 12 ? "下午" : "上午" + hourOfDay + "时" + minute + "分";
            mPickTime.setText(timeText);
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
        Drug drug = mRealm.where(Drug.class).equalTo("id", getIntent().getStringExtra(Constants.EXTRA_DRUG_ID)).findFirst();
        if (drug != null) {
            if (isSingle) {
                if (mSingleCalendar.getTimeInMillis() < System.currentTimeMillis()) {
                    showToast("闹钟时间小于当前时间");
                    return;
                }
            } else {
                if (mPerCalendar.getTimeInMillis() < System.currentTimeMillis()) {
                    showToast("闹钟时间小于当前时间");
                    return;
                }

            }
            DrugAlarm drugAlarm = new DrugAlarm();
            drugAlarm.setId(SpUtil.getInt(Constants.SP_ALARM_ID, 0) + 1);
            drugAlarm.setDrug(drug);
            drugAlarm.setTime(isSingle ? mSingleCalendar.getTimeInMillis() : mPerCalendar.getTimeInMillis());
            drugAlarm.setType(isSingle ? 0 : 1);
            mRealm.beginTransaction();
            mRealm.copyToRealm(drugAlarm);
            mRealm.commitTransaction();
            AlarmUtil.setAlarm(SjnApplication.getContext(), isSingle ? 0 : 1, drugAlarm.getId(), "健康助手喊你吃" + drug.getDrugName(), isSingle ? mSingleCalendar : mPerCalendar);
            SpUtil.putInt(Constants.SP_ALARM_ID, drugAlarm.getId());
            showToast("新建成功");
            finish();
        } else {
            showToast("药品数据未找到,请重新打开");
        }


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
