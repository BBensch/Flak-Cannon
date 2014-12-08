package kamikaze.team.flakcannon;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;

import gif.decoder.GifRun;


public class StarWarsActivity extends Activity {

    SurfaceView v;
    GifRun run;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_wars);
        v = (SurfaceView) findViewById(R.id.surfaceView);
        run = new GifRun();



    }

    public void causeBoom(View q){
        Canvas canvas = v.getHolder().lockCanvas();
        canvas.drawColor(Color.BLACK);

        v.getHolder().unlockCanvasAndPost(canvas);

        run.LoadGiff(v, this, R.raw.starwars);

        MediaPlayer bang = MediaPlayer.create(this, R.raw.boom);
        bang.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.star_wars, menu);
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
}
