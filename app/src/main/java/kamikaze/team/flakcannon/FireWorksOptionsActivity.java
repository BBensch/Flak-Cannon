package kamikaze.team.flakcannon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;


public class FireWorksOptionsActivity extends Activity {

    Spinner spinner;
    Spinner spinner2;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_works_options);

        spinner = (Spinner) findViewById(R.id.patterns_spinner);
        adapter = ArrayAdapter.createFromResource(
                this, R.array.patterns_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        adapter2 = ArrayAdapter.createFromResource(
                this, R.array.size_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fire_works_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSave(View v){
       // Context context = getApplicationContext();
       // int duration = Toast.LENGTH_SHORT;

        String text = spinner.getSelectedItem().toString() + " " + spinner2.getSelectedItem().toString();
     //   Toast toast = Toast.makeText(context, text, duration);
   //     toast.show();

        String size = spinner2.getSelectedItem().toString();

        int s = 0;
        if(size.equals("large")){
            s = 1000;
        } else if(size.equals("medium")) {
            s = 750;
        } else {
            s = 500;
        }

        fireWorks fw = new fireWorks(0, s, spinner.getSelectedItem().toString());
        System.out.println(fw.size);
        Intent data = new Intent();
        data.putExtra("fireWorks", fw);
        setResult(1, data);
        finish(); // ends current activity
    }

    public void cancel(View v){
        Intent data = new Intent();
        setResult(0, data);
        finish();
    }

    public void onRandom(View v){

        String pattern = spinner.getItemAtPosition(randInt(0, 4)).toString();
        String size = spinner2.getItemAtPosition(randInt(0, 2)).toString();

        int s = 0;
        if(size.equals("large")){
            s = 1000;
        } else if( size.equals("medium")) {
            s = 750;
        } else {
            s = 500;
        }

        fireWorks fw = new fireWorks(0, s, pattern);
        Intent data = new Intent();
        data.putExtra("fireWorks", fw);
        setResult(2, data);
        finish(); // ends current activity
    }

    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
