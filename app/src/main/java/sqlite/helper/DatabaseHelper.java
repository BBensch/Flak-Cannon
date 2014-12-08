package sqlite.helper;

import sqlite.model.Tag;
import sqlite.model.Fireworks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "fireworksManager";

	// Table Names
	private static final String TABLE_FIREWORKS = "fireworks";
	private static final String TABLE_TAG = "tags";
	private static final String TABLE_FIREWORKS_TAG = "fireworks_tags";

	// Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_CREATED_AT = "created_at";

	// NOTES Table - column nmaes
	private static final String KEY_FIREWORKS = "Fireworks";
	private static final String KEY_STATUS = "status";

	// TAGS Table - column names
	private static final String KEY_TAG_NAME = "tag_name";

	// NOTE_TAGS Table - column names
	private static final String KEY_FIREWORKS_ID = "fireworks_id";
	private static final String KEY_TAG_ID = "tag_id";

	// Table Create Statements
	// Fireworks table create statement
	private static final String CREATE_TABLE_FIREWORKS = "CREATE TABLE "
			+ TABLE_FIREWORKS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FIREWORKS
			+ " TEXT," + KEY_STATUS + " INTEGER," + KEY_CREATED_AT
			+ " DATETIME" + ")";

	// Tag table create statement
	private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAG_NAME + " TEXT,"
			+ KEY_CREATED_AT + " DATETIME" + ")";

	// FIREWORKS_tag table create statement
	private static final String CREATE_TABLE_FIREWORKS_TAG = "CREATE TABLE "
			+ TABLE_FIREWORKS_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_FIREWORKS_ID + " INTEGER," + KEY_TAG_ID + " INTEGER,"
			+ KEY_CREATED_AT + " DATETIME" + ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// creating required tables
		db.execSQL(CREATE_TABLE_FIREWORKS);
		db.execSQL(CREATE_TABLE_TAG);
		db.execSQL(CREATE_TABLE_FIREWORKS_TAG);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIREWORKS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIREWORKS_TAG);

		// create new tables
		onCreate(db);
	}

	// ------------------------ "fireworks" table methods ----------------//

	/*
	 * Creating a Fireworks
	 */
	public long createFIREWORKS(Fireworks Fireworks, long[] tag_ids) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FIREWORKS, Fireworks.getNote());
		values.put(KEY_STATUS, Fireworks.getStatus());
		values.put(KEY_CREATED_AT, getDateTime());

		// insert row
		long fireworks_id = db.insert(TABLE_FIREWORKS, null, values);

		// insert tag_ids
		for (long tag_id : tag_ids) {
			createFIREWORKSTag(fireworks_id, tag_id);
		}

		return fireworks_id;
	}

	/*
	 * get single Fireworks
	 */
	public Fireworks getFIREWORKS(long fireworks_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_FIREWORKS + " WHERE "
				+ KEY_ID + " = " + fireworks_id;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		Fireworks td = new Fireworks();
		td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		td.setNote((c.getString(c.getColumnIndex(KEY_FIREWORKS))));
		td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

		return td;
	}

	/**
	 * getting all FIREWORKS
	 * */
	public List<Fireworks> getAllFIREWORKS() {
		List<Fireworks> FIREWORKS = new ArrayList<Fireworks>();
		String selectQuery = "SELECT  * FROM " + TABLE_FIREWORKS;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Fireworks td = new Fireworks();
				td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				td.setNote((c.getString(c.getColumnIndex(KEY_FIREWORKS))));
				td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

				// adding to Fireworks list
				FIREWORKS.add(td);
			} while (c.moveToNext());
		}

		return FIREWORKS;
	}

	/**
	 * getting all FIREWORKS under single tag
	 * */
	public List<Fireworks> getAllFIREWORKSByTag(String tag_name) {
		List<Fireworks> FIREWORKS = new ArrayList<Fireworks>();

		String selectQuery = "SELECT  * FROM " + TABLE_FIREWORKS + " td, "
				+ TABLE_TAG + " tg, " + TABLE_FIREWORKS_TAG + " tt WHERE tg."
				+ KEY_TAG_NAME + " = '" + tag_name + "'" + " AND tg." + KEY_ID
				+ " = " + "tt." + KEY_TAG_ID + " AND td." + KEY_ID + " = "
				+ "tt." + KEY_FIREWORKS_ID;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Fireworks td = new Fireworks();
				td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				td.setNote((c.getString(c.getColumnIndex(KEY_FIREWORKS))));
				td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

				// adding to Fireworks list
				FIREWORKS.add(td);
			} while (c.moveToNext());
		}

		return FIREWORKS;
	}

	/*
	 * getting Fireworks count
	 */
	public int getFIREWORKSCount() {
		String countQuery = "SELECT  * FROM " + TABLE_FIREWORKS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);

		int count = cursor.getCount();
		cursor.close();

		// return count
		return count;
	}

	/*
	 * Updating a Fireworks
	 */
	public int updateFIREWORKS(Fireworks Fireworks) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FIREWORKS, Fireworks.getNote());
		values.put(KEY_STATUS, Fireworks.getStatus());

		// updating row
		return db.update(TABLE_FIREWORKS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(Fireworks.getId()) });
	}

	/*
	 * Deleting a Fireworks
	 */
	public void deleteFIREWORKS(long tado_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FIREWORKS, KEY_ID + " = ?",
				new String[] { String.valueOf(tado_id) });
	}

	// ------------------------ "tags" table methods ----------------//

	/*
	 * Creating tag
	 */
	public long createTag(Tag tag) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TAG_NAME, tag.getTagName());
		values.put(KEY_CREATED_AT, getDateTime());

		// insert row
		long tag_id = db.insert(TABLE_TAG, null, values);

		return tag_id;
	}

	/**
	 * getting all tags
	 * */
	public List<Tag> getAllTags() {
		List<Tag> tags = new ArrayList<Tag>();
		String selectQuery = "SELECT  * FROM " + TABLE_TAG;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Tag t = new Tag();
				t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				t.setTagName(c.getString(c.getColumnIndex(KEY_TAG_NAME)));

				// adding to tags list
				tags.add(t);
			} while (c.moveToNext());
		}
		return tags;
	}

	/*
	 * Updating a tag
	 */
	public int updateTag(Tag tag) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TAG_NAME, tag.getTagName());

		// updating row
		return db.update(TABLE_TAG, values, KEY_ID + " = ?",
				new String[] { String.valueOf(tag.getId()) });
	}

	/*
	 * Deleting a tag
	 */
	public void deleteTag(Tag tag, boolean should_delete_all_tag_FIREWORKS) {
		SQLiteDatabase db = this.getWritableDatabase();

		// before deleting tag
		// check if FIREWORKS under this tag should also be deleted
		if (should_delete_all_tag_FIREWORKS) {
			// get all FIREWORKS under this tag
			List<Fireworks> allTagFIREWORKS = getAllFIREWORKSByTag(tag.getTagName());

			// delete all FIREWORKS
			for (Fireworks Fireworks : allTagFIREWORKS) {
				// delete Fireworks
				deleteFIREWORKS(Fireworks.getId());
			}
		}

		// now delete the tag
		db.delete(TABLE_TAG, KEY_ID + " = ?",
				new String[] { String.valueOf(tag.getId()) });
	}

	// ------------------------ "FIREWORKS_tags" table methods ----------------//

	/*
	 * Creating FIREWORKS_tag
	 */
	public long createFIREWORKSTag(long fireworks_id, long tag_id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FIREWORKS_ID, fireworks_id);
		values.put(KEY_TAG_ID, tag_id);
		values.put(KEY_CREATED_AT, getDateTime());

		long id = db.insert(TABLE_FIREWORKS_TAG, null, values);

		return id;
	}

	/*
	 * Updating a Fireworks tag
	 */
	public int updateNoteTag(long id, long tag_id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TAG_ID, tag_id);

		// updating row
		return db.update(TABLE_FIREWORKS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(id) });
	}

	/*
	 * Deleting a Fireworks tag
	 */
	public void deleteFIREWORKSTag(long id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FIREWORKS, KEY_ID + " = ?",
				new String[] { String.valueOf(id) });
	}

	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}

	/**
	 * get datetime
	 * */
	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
}
