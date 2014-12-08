package sqlite;

import sqlite.helper.DatabaseHelper;
import sqlite.model.Tag;
import sqlite.model.Fireworks;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	// Database Helper
	DatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

		db = new DatabaseHelper(getApplicationContext());

		// Creating tags
		Tag tag1 = new Tag("Save1");
		Tag tag2 = new Tag("Save2");
		Tag tag3 = new Tag("Save3");
		Tag tag4 = new Tag("Save4");

		// Inserting tags in db
		long tag1_id = db.createTag(tag1);
		long tag2_id = db.createTag(tag2);
		long tag3_id = db.createTag(tag3);
		long tag4_id = db.createTag(tag4);

		Log.d("Tag Count", "Tag Count: " + db.getAllTags().size());

		// Creating fireworks
		Fireworks firework1 = new Fireworks("Confetti2", 0);
		Fireworks firework2 = new Fireworks("Confetti3", 0);
		Fireworks firework3 = new Fireworks("Star Pink", 0);
		Fireworks firework4 = new Fireworks("Star Yellow", 0);
		Fireworks firework5 = new Fireworks("Star White", 0);

		// Inserting fireworks in db
		// Inserting fireworks under "Save1" Tag
		long firework1_id = db.createFIREWORKS(firework1, new long[] { tag1_id });
		long firework2_id = db.createFIREWORKS(firework2, new long[] { tag1_id });
		long firework3_id = db.createFIREWORKS(firework3, new long[] { tag1_id });
		long firework4_id = db.createFIREWORKS(firework4, new long[] { tag1_id });
		long firework5_id = db.createFIREWORKS(firework5, new long[] { tag1_id });

		Log.e("Fireworks Count", "Fireworks count: " + db.getFIREWORKSCount());

		// "Post new Article" - assigning this under "Important" Tag
		// Now this will have - "Androidhive" and "Important" Tags
		//db.createfireworkTag(firework1_id, tag2_id);

		// Getting all tag names
		Log.d("Get Tags", "Getting All Tags");

		List<Tag> allTags = db.getAllTags();
		for (Tag tag : allTags) {
			Log.d("Tag Name", tag.getTagName());
		}

		// Getting all fireworks
		Log.d("Get fireworks", "Getting All fireworks");

		List<Fireworks> allFireworks = db.getAllFIREWORKS();
		for (Fireworks Fireworks : allFireworks) {
			Log.d("Fireworks", Fireworks.getNote());
		}

		// Getting fireworks under "Watchlist" tag name
		Log.d("Fireworks", "Get fireworks under single Tag name");

		List<Fireworks> tagsWatchList = db.getAllFIREWORKSByTag(tag2.getTagName());
		for (Fireworks Fireworks : tagsWatchList) {
			Log.d("Fireworks Watchlist", Fireworks.getNote());
		}

		// Deleting a Fireworks
		Log.d("Delete Fireworks", "Deleting a Fireworks");
		Log.d("Tag Count", "Tag Count Before Deleting: " + db.getFIREWORKSCount());

		//db.deletefirework(firework8_id);

		Log.d("Tag Count", "Tag Count After Deleting: " + db.getFIREWORKSCount());

		// Deleting all fireworks under "Shopping" tag
		Log.d("Tag Count",
				"Tag Count Before Deleting 'Shopping' fireworks: "
						+ db.getFIREWORKSCount());

		//db.deleteTag(tag1, true);

		Log.d("Tag Count",
				"Tag Count After Deleting 'Shopping' fireworks: "
						+ db.getFIREWORKSCount());

		// Updating tag name
		//tag3.setTagName("Movies to watch");
		//db.updateTag(tag3);

		// Don't forget to close database connection
		db.closeDB();
		
	}
}
