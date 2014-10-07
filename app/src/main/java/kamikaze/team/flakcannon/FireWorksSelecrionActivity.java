package kamikaze.team.flakcannon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class FireWorksSelecrionActivity extends Activity {

    public ArrayList<String> listItems=new ArrayList<String>();

    public ArrayList<fireWorks> showStuff;

    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_works_selecrion);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);

        ListView list = (ListView) findViewById(R.id.fireWorksList);
        list.setAdapter(adapter);

        showStuff = new ArrayList<fireWorks>();

        final Button button = (Button) findViewById(R.id.SupperButton);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fire_works_selecrion, menu);
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

    public void addFireWorks(View v){
        listItems.add("FireWorks");
        adapter.notifyDataSetChanged();
        fireWorks fw = new fireWorks(1,1,"red");
        showStuff.add(fw);
    }

    public void startShow(View view){
        Intent intent = new Intent(this, DisplayShowActivity.class);

        intent.putParcelableArrayListExtra("key", showStuff);
        startActivity(intent);
    }
}
