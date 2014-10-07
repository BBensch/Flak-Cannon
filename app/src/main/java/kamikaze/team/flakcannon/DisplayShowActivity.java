package kamikaze.team.flakcannon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class DisplayShowActivity extends Activity {


    ArrayList<fireWorks> showStuff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_show);
        showStuff = new ArrayList<fireWorks>();
        Intent i = getIntent();
        showStuff = i.getParcelableArrayListExtra("key");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_show, menu);

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

    public void testButton(View v){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        int size = showStuff.size();
        String text = showStuff.get(0).color;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
