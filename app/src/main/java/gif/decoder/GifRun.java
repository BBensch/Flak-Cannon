// Created by JeffMeJones@gmail.com
package gif.decoder;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;


public class GifRun implements Runnable, Callback {

	
	public Bitmap bmb;
	public GIFDecode decode;
	public int ind;
	public int gifCount;
	public SurfaceHolder mSurfaceHolder ;
	boolean surfaceExists;
	
	public void LoadGiff(SurfaceView v, android.content.Context theTHIS, int R_drawable)
	{		
		//InputStream Raw= context.getResources().openRawResource(R.drawable.image001);
	       mSurfaceHolder = v.getHolder();
	       mSurfaceHolder.addCallback(this);
	       decode = new GIFDecode();
	       decode.read(theTHIS.getResources().openRawResource(R_drawable));
	       ind = 0;
			// decode.
			gifCount = decode.getFrameCount();
			bmb = decode.getFrame(0);
			surfaceExists=true;
			Thread t = new Thread(this);
			t.start();
            Log.w("myApp", ind + " We started the stuff");
	}

	public void run() 
	{
        int count = 0;

		while (count < 38) {
            Log.w("myApp", count +" Still looping");
			try {
                    Canvas rCanvas = mSurfaceHolder.lockCanvas();
                    int scaleX = (int)((float)(rCanvas.getWidth() * .70) / (float)bmb.getWidth());
                    int scaleY = (int)((float)(rCanvas.getHeight() * .70) / (float)bmb.getHeight());

                    int centreX = (rCanvas.getWidth()  - bmb.getWidth()) /2;

                    int centreY = (rCanvas.getHeight() - bmb.getHeight()) /2;

					rCanvas.drawBitmap(bmb, centreX, centreY, new Paint());
					//ImageView im = (ImageView) findViewById(R.id.imageView1);
					//im.setImageBitmap(bmb);

					mSurfaceHolder.unlockCanvasAndPost(rCanvas);
					bmb = decode.next();

                  //  Log.w("myApp", " " + bmb);
              //      if(bmb.toString().equals("android.graphics.Bitmap@b222e1e8")){
                //        surfaceExists = false;
                  //  }
                    count ++;
                    mSurfaceHolder.unlockCanvasAndPost(rCanvas);

				Thread.sleep(100);
			} catch (Exception ex) {

			}

		}
        Log.w("myApp", "Not running anymore");
        Canvas rCanvas = mSurfaceHolder.lockCanvas();
        rCanvas.drawColor(Color.BLACK);
        //ImageView im = (ImageView) findViewById(R.id.imageView1);
        //im.setImageBitmap(bmb);

        mSurfaceHolder.unlockCanvasAndPost(rCanvas);
		
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) 
	{
		
		
		
	}

	public void surfaceCreated(SurfaceHolder holder) 
	{
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		
		surfaceExists=false;
	}
	
}
