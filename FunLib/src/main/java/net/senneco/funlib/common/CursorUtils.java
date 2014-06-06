package net.senneco.funlib.common;

import android.database.Cursor;
import com.j256.ormlite.android.AndroidCompiledStatement;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.StatementBuilder.StatementType;
import com.j256.ormlite.support.DatabaseConnection;

import java.sql.SQLException;

/**
 * 09.01.2013
 * 
 * @author denis.mirochnik
 */
public class CursorUtils
{
	/**
	 * @param helper
	 *            your helper
	 * @param preparedQuery
	 *            your query
	 * @return Cursor
	 * @throws java.sql.SQLException
	 *             on any Sql error
	 *
	 */
	public static Cursor getCursor(OrmLiteSqliteOpenHelper helper, PreparedQuery<?> preparedQuery) throws SQLException
	{
		return getCursor(helper, preparedQuery, StatementType.SELECT);
	}

	/**
	 * @param helper
	 *            your helper
	 * @param preparedQuery
	 *            your query
	 * @param type
	 *            Type of statement
	 * @return Cursor
	 * @throws java.sql.SQLException
	 *             on any Sql error
	 *
	 */
	public static Cursor getCursor(OrmLiteSqliteOpenHelper helper, PreparedQuery<?> preparedQuery, StatementType type) throws SQLException
	{
		return ((AndroidCompiledStatement) preparedQuery.compile(helper.getConnectionSource().getReadOnlyConnection(), type)).getCursor();
	}

	/**
	 * @param connection
	 *            your connection
	 * @param preparedQuery
	 *            your query
	 * @return Cursor
	 * @throws java.sql.SQLException
	 *             on any Sql error
	 *
	 */
	public static Cursor getCursor(DatabaseConnection connection, PreparedQuery<?> preparedQuery) throws SQLException
	{
		return getCursor(connection, preparedQuery, StatementType.SELECT);
	}

	/**
	 * @param connection
	 *            your connection
	 * @param preparedQuery
	 *            your query
	 * @param type
	 *            Type of statement
	 * @return Cursor
	 * @throws java.sql.SQLException
	 *             on any Sql error
	 * 
	 */
	public static Cursor getCursor(DatabaseConnection connection, PreparedQuery<?> preparedQuery, StatementType type) throws SQLException
	{
		return ((AndroidCompiledStatement) preparedQuery.compile(connection, type)).getCursor();
	}
}
