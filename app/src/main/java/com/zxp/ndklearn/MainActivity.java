package com.zxp.ndklearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zxp.ndklearn.bean.DataBean;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private TextView tv;
    private DataBean dataFromNative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        tv = findViewById(R.id.sample_text);
        Button bt1 = findViewById(R.id.bt1);
        Button bt2 = findViewById(R.id.bt2);
        Button bt3 = findViewById(R.id.bt3);
        bt2.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // 将C结构体转为Java类
    public static native DataBean getDataFromNative();

    // 将Java类转为C结构体
    public static native void transferDataToNative(DataBean dataBean);

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                tv.setText(stringFromJNI());
                break;
            case R.id.bt2:
                dataFromNative = getDataFromNative();
                Log.e("00000", dataFromNative.toString());
                break;
            case R.id.bt3:
//                DataBean dataBean = new DataBean();
//                dataBean.mData = new byte[]{1, 2, 3};
//                int[][] doubleDimenArray = {{1, 2}, {1, 2}};
//                dataBean.mDoubleDimenArray = doubleDimenArray;
//                dataBean.mID=99;
//                dataBean.mScore=5.5f;
                transferDataToNative(dataFromNative);
                break;
        }
    }
}
