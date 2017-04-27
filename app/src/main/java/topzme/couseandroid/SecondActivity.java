package topzme.couseandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView textResult;
    Button finishButton;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initInstances();
    }

    private void initInstances() {
        Intent intent = getIntent();
        sum = intent.getIntExtra("result", 0);

        Bundle bundle = intent.getBundleExtra("cBundle");
        int x = bundle.getInt("x", 0);
        int y = bundle.getInt("y", 0);
        int z = bundle.getInt("z", 0);

        // Serializable
        CoordinateSerializable  c2 = (CoordinateSerializable) intent.getSerializableExtra("cSerializable");

        CoordinateParcelable c3 = intent.getParcelableExtra("cParcelable");



        textResult = (TextView) findViewById(R.id.resultTextView);
        finishButton = (Button) findViewById(R.id.finishedBtn);
        textResult.setText(String.valueOf(sum));

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
