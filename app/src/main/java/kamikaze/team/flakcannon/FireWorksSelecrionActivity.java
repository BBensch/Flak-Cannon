package kamikaze.team.flakcannon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sqlite.helper.DatabaseHelper;
import sqlite.model.Tag;
import sqlite.model.Fireworks;


public class FireWorksSelecrionActivity extends Activity {

    public ArrayList<String> listItems=new ArrayList<String>();

    public ArrayList<fireWorks> showStuff;

    public ArrayAdapter<String> adapter;

    public DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_works_selecrion);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);

        ListView list = (ListView) findViewById(R.id.fireWorksList);
        list.setAdapter(adapter);

        showStuff = new ArrayList<fireWorks>();

        final Button button = (Button) findViewById(R.id.SupperButton);

        dbh = new DatabaseHelper(getApplicationContext());
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
        Intent intent = new Intent(this, FireWorksOptionsActivity.class);

        startActivityForResult(intent, 1);

    }

    public void startShow(View view){
        Intent intent = new Intent(this, DisplayShowActivity.class);

        intent.putParcelableArrayListExtra("key", showStuff);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            fireWorks fw = data.getParcelableExtra("fireWorks");
            listItems.add(fw.color);
            showStuff.add(fw);
            adapter.notifyDataSetChanged();
        }
        else if(resultCode == 2){
            fireWorks fw = data.getParcelableExtra("fireWorks");
            listItems.add("RandomFireworks");
            showStuff.add(fw);
            adapter.notifyDataSetChanged();
        }
    }

    public void save(View view) {
        //System.out.println(dbh);
        dbh = new DatabaseHelper(getApplicationContext());
        Tag tag1 = new Tag("Save1");
        long tag1_id = dbh.createTag(tag1);
        Fireworks firework1 = new Fireworks("Firework", 0);
        long firework1_id = dbh.createFIREWORKS(firework1, new long[] { tag1_id });
        dbh.createFIREWORKSTag(firework1_id, tag1_id);
        dbh.closeDB();
        System.out.println(dbh.getFIREWORKSCount());
    }
}
