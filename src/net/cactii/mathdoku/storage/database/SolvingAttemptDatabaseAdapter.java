package net.cactii.mathdoku.storage.database;

import java.util.ArrayList;

import net.cactii.mathdoku.Grid;
import net.cactii.mathdoku.developmentHelper.DevelopmentHelper;
import net.cactii.mathdoku.developmentHelper.DevelopmentHelper.Mode;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * The database adapter for the solving attempt table. For each grid one or more
 * solving attempt records can exists in the database.
 */
public class SolvingAttemptDatabaseAdapter extends DatabaseAdapter {
	private static final String TAG = "MathDoku.SolvingAttemptDatabaseAdapter";

	public static final boolean DEBUG_SQL = (DevelopmentHelper.mMode == Mode.DEVELOPMENT) && true;

	// Columns for table statistics
	private static final String TABLE = "solving_attempt";
	private static final String KEY_ROWID = "_id";
	private static final String KEY_GRID_ID = "grid_id";
	private static final String KEY_DATE_CREATED = "date_created";
	private static final String KEY_DATE_UPDATED = "date_updated";
	private static final String KEY_SAVED_WITH_REVISION = "revision";
	private static final String KEY_DATA = "data";

	private static final String[] dataColumns = { KEY_ROWID, KEY_GRID_ID,
			KEY_DATE_CREATED, KEY_DATE_UPDATED, KEY_SAVED_WITH_REVISION,
			KEY_DATA };

	// Delimiters used in the data field to separate objects, fields and values
	// in a field which can hold multiple values.
	public static final String EOL_DELIMITER = "\n"; // Separate objects
	public static final String FIELD_DELIMITER_LEVEL1 = ":"; // Separate fields
	public static final String FIELD_DELIMITER_LEVEL2 = ","; // Separate values
																// in fields

	/**
	 * Get the table name.
	 * 
	 * @return The table name;
	 */
	protected String getTableName() {
		return TABLE;
	}

	/**
	 * Builds the SQL create statement for this table.
	 * 
	 * @return The SQL create statement for this table.
	 */
	protected static String buildCreateSQL() {
		return createTable(
				TABLE,
				createColumn(KEY_ROWID, "integer", "primary key autoincrement"),
				createColumn(KEY_GRID_ID, "integer", " not null"),
				createColumn(KEY_DATE_CREATED, "datetime", "not null"),
				createColumn(KEY_DATE_UPDATED, "datetime", "not null"),
				createColumn(KEY_SAVED_WITH_REVISION, "integer", " not null"),
				createColumn(KEY_DATA, "string", "not null"),
				createForeignKey(KEY_GRID_ID, GridDatabaseAdapter.TABLE,
						GridDatabaseAdapter.KEY_ROWID));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cactii.mathdoku.storage.database.DatabaseAdapter#getCreateSQL()
	 */
	protected String getCreateSQL() {
		return buildCreateSQL();
	}

	/**
	 * Creates the table.
	 * 
	 * @param db
	 *            The database in which the table has to be created.
	 */
	protected static void create(SQLiteDatabase db) {
		String sql = buildCreateSQL();
		if (DevelopmentHelper.mMode == Mode.DEVELOPMENT) {
			Log.i(TAG, sql);
		}

		// Execute create statement
		db.execSQL(sql);
	}

	/**
	 * Upgrades the table to an other version.
	 * 
	 * @param db
	 *            The database in which the table has to be updated.
	 * @param oldVersion
	 *            The old version of the database. Use the app revision number
	 *            to identify the database version.
	 * @param newVersion
	 *            The new version of the database. Use the app revision number
	 *            to identify the database version.
	 */
	protected static void upgrade(SQLiteDatabase db, int oldVersion,
			int newVersion) {
		if (oldVersion < 265) {
			// In development revisions the table is simply dropped and
			// recreated.
			try {
				String sql = "DROP TABLE " + TABLE;
				if (DEBUG_SQL) {
					Log.i(TAG, sql);
				}
				db.execSQL(sql);
			} catch (SQLiteException e) {
				if (DevelopmentHelper.mMode == Mode.DEVELOPMENT) {
					e.printStackTrace();
				}
			}
			create(db);
		}
		if (oldVersion >= 276 && oldVersion < 298 && newVersion >= 298 ) {
			dropColumn(db, TABLE, new String[] {"preview_image_filename"}, buildCreateSQL());
		}
	}

	/**
	 * Inserts a new solving attempt record for a grid into the database.
	 * 
	 * @param grid
	 *            The grid for which a new solving attempt record has to be
	 *            inserted.
	 * @param data
	 *            The data for the solving attempt.
	 * @return The row id of the row created. -1 in case of an error.
	 */
	public int insert(Grid grid, int revision, String data) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_GRID_ID, grid.getRowId());
		initialValues.put(KEY_DATE_CREATED,
				toSQLiteTimestamp(grid.getDateCreated()));
		initialValues.put(KEY_DATE_UPDATED,
				toSQLiteTimestamp(grid.getDateSaved()));
		initialValues.put(KEY_SAVED_WITH_REVISION, revision);
		initialValues.put(KEY_DATA, data);

		long id = -1;
		try {
			id = mSqliteDatabase.insertOrThrow(TABLE, null, initialValues);
		} catch (SQLiteException e) {
			if (DevelopmentHelper.mMode == Mode.DEVELOPMENT) {
				e.printStackTrace();
			}
		}

		return (int) id;
	}

	/**
	 * Get the data for the given solving attempt id.
	 * 
	 * @param solvingAttemptId
	 *            The solving attempt id for which the data has to be retrieved.
	 * @return The data of the solving attempt.
	 */
	public SolvingAttemptData getData(int solvingAttemptId) {
		SolvingAttemptData solvingAttemptData = null;
		Cursor cursor = null;
		try {
			cursor = mSqliteDatabase.query(true, TABLE, dataColumns, KEY_ROWID
					+ "=" + solvingAttemptId, null, null, null, null, null);

			if (cursor == null || !cursor.moveToFirst()) {
				// No record found for this grid.
				return null;
			}

			// Convert cursor record to a SolvingAttempt row
			solvingAttemptData = new SolvingAttemptData();
			solvingAttemptData.mId = cursor.getInt(cursor
					.getColumnIndexOrThrow(KEY_ROWID));
			solvingAttemptData.mGridId = cursor.getInt(cursor
					.getColumnIndexOrThrow(KEY_GRID_ID));
			solvingAttemptData.mDateCreated = valueOfSQLiteTimestamp(cursor
					.getString(cursor.getColumnIndexOrThrow(KEY_DATE_CREATED)));
			solvingAttemptData.mDateUpdated = valueOfSQLiteTimestamp(cursor
					.getString(cursor.getColumnIndexOrThrow(KEY_DATE_UPDATED)));
			solvingAttemptData.mSavedWithRevision = cursor.getInt(cursor
					.getColumnIndexOrThrow(KEY_SAVED_WITH_REVISION));
			solvingAttemptData.setData(cursor.getString(cursor
					.getColumnIndexOrThrow(KEY_DATA)));
		} catch (SQLiteException e) {
			if (DevelopmentHelper.mMode == Mode.DEVELOPMENT) {
				e.printStackTrace();
			}
			return null;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return solvingAttemptData;
	}

	/**
	 * Get the most recently played solving attempt
	 * 
	 * @return The id of the solving attempt which was last played. -1 in case
	 *         of an error.
	 */
	public int getMostRecentPlayedId() {
		int id = -1;
		Cursor cursor = null;
		try {
			cursor = mSqliteDatabase.query(true, TABLE,
					new String[] { KEY_ROWID }, null, null, null, null,
					KEY_DATE_UPDATED + " DESC", "1");

			if (cursor == null || !cursor.moveToFirst()) {
				// No record found
				return -1;
			}

			// Convert cursor record to a SolvingAttempt row
			id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ROWID));
		} catch (SQLiteException e) {
			if (DevelopmentHelper.mMode == Mode.DEVELOPMENT) {
				e.printStackTrace();
			}
			return -1;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return id;
	}

	/**
	 * Update the data of a solving attempt with given data. It is required that
	 * the record already exists. The id should never be changed.
	 * 
	 * @param id
	 *            The id of the solving attempt to be updated.
	 * @param data
	 *            The data string to be stored for this solving attempt.
	 * 
	 * @return True in case the statistics have been updated. False otherwise.
	 */
	public boolean update(int id, String data) {
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_DATE_UPDATED,
				toSQLiteTimestamp(new java.util.Date().getTime()));
		newValues.put(KEY_DATA, data);

		return (mSqliteDatabase.update(TABLE, newValues,
				KEY_ROWID + " = " + id, null) == 1);
	}

	/**
	 * Gets a list of id's for all solving attempts which need to be converted.
	 * 
	 * @return The list of id's for all solving attempts which need to be
	 *         converted.
	 */
	public ArrayList<Integer> getAllToBeConverted() {
		ArrayList<Integer> idArrayList = null;
		Cursor cursor = null;
		String[] columns = { KEY_ROWID };
		try {
			// Currently all solving attempts are returned. In future this can
			// be restricted to games which are not solved.
			cursor = mSqliteDatabase.query(true, TABLE, columns, null, null,
					null, null, null, null);

			if (cursor == null || !cursor.moveToFirst()) {
				// No record found for this grid.
				return null;
			}

			// Convert cursor records to an array list of id's.
			idArrayList = new ArrayList<Integer>();
			do {
				idArrayList.add(cursor.getInt(cursor
						.getColumnIndexOrThrow(KEY_ROWID)));
			} while (cursor.moveToNext());
		} catch (SQLiteException e) {
			if (DevelopmentHelper.mMode == Mode.DEVELOPMENT) {
				e.printStackTrace();
			}
			return null;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return idArrayList;
	}
}