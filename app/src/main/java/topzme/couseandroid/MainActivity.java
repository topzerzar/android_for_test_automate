package topzme.couseandroid;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int x, y, z; // Save/Restore in Activity's instance states

    private TextView textHello, tvResult, tvSymbol;
    private EditText editTextHello, editTextCalculateOne, editTextCalculateTwo;
    private Button copyBtn;
    private FancyButton calculateBtn;
    private RadioGroup radioGroup;
    private CustomViewGroup viewGroup1, viewGroup2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width  = size.x; // Screen Width
        int height = size.y; // Screen Height

        Toast.makeText(getApplicationContext(), "Width " + width + " Height : " + height, Toast.LENGTH_LONG).show();
    }

    private void initInstances() {
        textHello = (TextView) findViewById(R.id.textView);
        textHello.setMovementMethod(LinkMovementMethod.getInstance());
        //noinspection deprecation
        textHello.setText(Html.fromHtml("<b><u>Hello</u></b> word <a href=\"http://google.com\">click!</a>"));

        editTextHello = (EditText) findViewById(R.id.editTextHello);
        editTextHello.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Copy text in EditText to TextView
                    textHello.setText(editTextHello.getText());
                    return true;
                }

                return false;
            }
        });

        copyBtn = (Button) findViewById(R.id.copyBtn);
        copyBtn.setOnClickListener(this);


        ////////////////////
        // Start Code Here
        ///////////////////

        editTextCalculateOne = (EditText) findViewById(R.id.editTextNumberOne);
        editTextCalculateTwo = (EditText) findViewById(R.id.editTextNumberTwo);
        tvResult = (TextView) findViewById(R.id.tvResult);
        calculateBtn = (FancyButton) findViewById(R.id.calculateBtn);
        calculateBtn.setOnClickListener(this);
        tvSymbol = (TextView) findViewById(R.id.symbol);
        radioGroup = (RadioGroup) findViewById(R.id.groupCalculate);

        viewGroup1 = (CustomViewGroup) findViewById(R.id.viewgroup1);
        viewGroup2 = (CustomViewGroup) findViewById(R.id.viewgroup2);

        viewGroup1.setButtonText("hi");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.copyBtn:
                textHello.setText(editTextHello.getText());
                break;
            case R.id.calculateBtn:
                tvResult.setText("= " + String.valueOf(calculate()));
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("result", calculate());


                Coordinate c1 = new Coordinate();
                c1.x = 5;
                c1.y = 10;
                c1.z = 20;

                Bundle bundle = new Bundle();
                bundle.putInt("x", c1.x);
                bundle.putInt("u", c1.y);
                bundle.putInt("z", c1.z);

                intent.putExtra("cBundle", bundle);

                // Serializable
                CoordinateSerializable c2 = new CoordinateSerializable();
                c2.x = 5;
                c2.y = 15;
                c2.z = 25;
                intent.putExtra("cSerializable", c2);

                CoordinateParcelable c3 = new CoordinateParcelable();
                c3.x = 2;
                c3.y = 12;
                c3.z = 22;
                intent.putExtra("cParcelable", c3);

//                startActivity(intent);
                break;
        }
    }

    private int calculate() {
        int val1 = 0, val2 = 0, sum = 0;

        try {
            val1 = Integer.parseInt(editTextCalculateOne.getText().toString());
        } catch (NumberFormatException e) {
            Log.e("MainActivity", "Error Number Format");
        }

        try {
            val2 = Integer.parseInt(editTextCalculateTwo.getText().toString());
        } catch (NumberFormatException e) {
            Log.e("MainActivity", "Error Number Format");
        }

        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.plus:
                sum = val1 + val2;
                tvSymbol.setText(" + ");
                break;
            case R.id.minus:
                sum = val1 - val2;
                tvSymbol.setText(" _ ");
                break;
            case R.id.multiply:
                sum = val1 * val2;
                tvSymbol.setText(" * ");
                break;
            case R.id.divide:
                // Handle divide by zero
                try {
                    sum = val1 / val2;
                } catch (ArithmeticException e) {
                    Log.e("MainActivity", "Error divide by zero");
                }
                tvSymbol.setText(" / ");
                break;
        }

        return sum;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_setting) {
            Toast.makeText(getApplicationContext(), "Setting Click", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save thing(s) here
//        outState.putString("text", tvResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore thing(s) here
//        tvResult.setText(savedInstanceState.getString("text"));
    }
}
