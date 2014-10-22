package kamikaze.team.flakcannon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;
import com.plattysoft.leonids.ParticleSystem;
import java.util.Random;


import com.plattysoft.leonids.ParticleSystem;

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
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        int size = showStuff.size();
        Random rand = new Random();

        for(int i = 0; i < size; i++) {
            int num = rand.nextInt(locations.size());
            explosion(v, rand);
        }
    }

    public void explosion(View v, Random rand) {
    //    new ParticleSystem(this, 15, R.drawable.star_pink, 4000)
      //          .setSpeedRange(0.2f, 0.5f)
        //        .emit(findViewById(R.id.emiter_center), 8, 2000);
        int num = rand.nextInt(locations.size());
        if (num == 0) {
            new ParticleSystem(this, 100, R.drawable.star_pink, 500)
                    .setFadeOut(500)
                    .setSpeedRange(0.2f, 0.2f)
                    .oneShot(findViewById(R.id.emitter_center), 20);
        } else if (num == 1) {
            new ParticleSystem(this, 100, R.drawable.star_pink, 500)
                    .setFadeOut(500)
                    .setSpeedRange(0.2f, 0.2f)
                    .oneShot(findViewById(R.id.emitter_upper_left), 20);
        } else {
            new ParticleSystem(this, 100, R.drawable.star_pink, 500)
                    .setFadeOut(500)
                    .setSpeedRange(0.2f, 0.2f)
                    .oneShot(findViewById(R.id.emitter_upper_right), 20);
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
