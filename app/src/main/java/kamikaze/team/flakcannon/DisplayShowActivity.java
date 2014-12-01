package kamikaze.team.flakcannon;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.plattysoft.leonids.ParticleSystem;

import java.util.Random;


import java.util.ArrayList;


public class DisplayShowActivity extends Activity {

    ArrayList<fireWorks> showStuff;
    ArrayList<String> locations = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_show);
        showStuff = new ArrayList<fireWorks>();
        Intent i = getIntent();
        showStuff = i.getParcelableArrayListExtra("key");
        initPositions();
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


    public void testButton(View v) {
  //      Context context = getApplicationContext();
  //      int duration = Toast.LENGTH_SHORT;

        int size = showStuff.size();
        Random rand = new Random();

        for(int i = 0; i < size; i++) {
            fireWorks fw = showStuff.get(i);

            int num = rand.nextInt(locations.size());
            explosion(v, fw.size, fw.color, rand);
        }
    }

    public void explosion(View v, int size, String pattern, Random rand) {
    //    new ParticleSystem(this, 15, R.drawable.star_pink, 4000)
      //          .setSpeedRange(0.2f, 0.5f)
        //        .emit(findViewById(R.id.emiter_center), 8, 2000);
        int num = rand.nextInt(locations.size());

        int variable = 0;

        if (pattern.contentEquals("star white")) {
            variable = R.drawable.star_white;
        } else if (pattern.contentEquals("star pink")) {
            variable = R.drawable.star_pink;
        } else if(pattern.contentEquals("star yellow")){
            variable = R.drawable.star;
        } else if(pattern.contentEquals("confeti2")){
           variable = R.drawable.confeti2;
        } else {
            variable = R.drawable.confeti3;
        }

        if (num == 0) {
            new ParticleSystem(this, 100, variable, size)
                    .setFadeOut(500)
                    .setSpeedRange(0.2f, 0.2f)
                    .oneShot(findViewById(R.id.emitter_center), 20);
        } else if (num == 1) {
            new ParticleSystem(this, 100, variable, size)
                    .setFadeOut(500)
                    .setSpeedRange(0.2f, 0.2f)
                    .oneShot(findViewById(R.id.emitter_upper_left), 20);
        } else {
            new ParticleSystem(this, 100, variable, size)
                    .setFadeOut(500)
                    .setSpeedRange(0.2f, 0.2f)
                    .oneShot(findViewById(R.id.emitter_upper_right), 20);
        }

        if(variable == R.drawable.confeti2){
            MediaPlayer bang = MediaPlayer.create(this, R.raw.wilhelm);
            bang.start();
        }

        else if(variable == R.drawable.star_pink){
            MediaPlayer bang = MediaPlayer.create(this, R.raw.garand);
            bang.start();
        }

        else if(variable == R.drawable.confeti3){
            MediaPlayer bang = MediaPlayer.create(this, R.raw.shotgun);
            bang.start();
        }

        else if(variable == R.drawable.star_pink){
            MediaPlayer bang = MediaPlayer.create(this, R.raw.bomb);
            bang.start();
        }

        else {
            MediaPlayer bang = MediaPlayer.create(this, R.raw.tank);
            bang.start();
        }

    }

    public void initPositions() {
        locations.add("emitter_center");
        locations.add("emitter_upper_left");
        locations.add("emitter_upper_right");
    }

    public void close () {
        this.finish();
    }
}
