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


import com.plattysoft.leonids.ParticleSystem;

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

    public void testButton(View v) {
  //      Context context = getApplicationContext();
  //      int duration = Toast.LENGTH_SHORT;

        int size = showStuff.size();
//        String text = showStuff.get(0).color;
  //      Toast toast = Toast.makeText(context, text, duration);
    //    toast.show();

        for(int i = 0; i < size; i++) {

            fireWorks fw = showStuff.get(i);

            explosion(v, fw.duration, fw.color);
        }
    }

    public void explosion(View v, int size, String pattern) {
    //    new ParticleSystem(this, 15, R.drawable.star_pink, 4000)
      //          .setSpeedRange(0.2f, 0.5f)
        //        .emit(findViewById(R.id.emiter_center), 8, 2000);
        ParticleSystem ps;
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, pattern, duration);
        toast.show();

        if(pattern.contentEquals("star white")){
            ps = new ParticleSystem(this, 100, R.drawable.star_white, 800);
        } else if(pattern.contentEquals("star pink")){
            ps = new ParticleSystem(this, 100, R.drawable.star_pink, 800);
        } else if(pattern.contentEquals("star yellow")){
            ps = new ParticleSystem(this, 100, R.drawable.star, 800);
        } else if(pattern.contentEquals("confeti2")){
            ps = new ParticleSystem(this, 100, R.drawable.confeti2, 800);
        } else {
            ps = new ParticleSystem(this, 100, R.drawable.confeti3, 800);
        }

        ps.setScaleRange(0.7f, 1.3f);
        ps.setSpeedRange(0.2f, 0.5f);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new AccelerateInterpolator());
        ps.oneShot(findViewById(R.id.emiter_center), 70);
    }

    public void close () {
        this.finish();
    }
}
