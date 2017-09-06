package com.example.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IrregularImageView ivBlowUP;
    private IrregularImageView ivBlowFront;
    private IrregularImageView ivBlowDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBlowUP = (IrregularImageView) findViewById(R.id.iv_blower_up);
        ivBlowUP.setOnClickListener(this);
        ivBlowFront = (IrregularImageView) findViewById(R.id.iv_blower_front);
        ivBlowFront.setOnClickListener(this);
        ivBlowDown = (IrregularImageView) findViewById(R.id.iv_blower_down);
        ivBlowDown.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getVisibility() == View.VISIBLE)
            v.setVisibility(View.INVISIBLE);
        else if (v.getVisibility() == View.INVISIBLE)
            v.setVisibility(View.VISIBLE);
    }
}
