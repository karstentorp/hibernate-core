package org.hibernate.spatial.dialect.mysql;

import java.util.Iterator;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.spatial.SpatialDialect;
import org.hibernate.spatial.SpatialFunction;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

/**
 * @author Karel Maesen, Geovise BVBA
 *         creation-date: 6/21/12
 */
public class MySQL5SpatialInnoDBDialect extends MySQL5InnoDBDialect implements SpatialDialect {

	private MySQLSpatialDialect dialectDelegate = new MySQLSpatialDialect();


	public MySQL5SpatialInnoDBDialect() {
		super();
		Map<String, StandardSQLFunction> functionsToRegister = dialectDelegate.getFunctionsToRegister();
		Map<String, Integer> columnTypes = dialectDelegate.getColumnTypesToRegister();
		if ( null != columnTypes ) {
			Iterator<String> keys = columnTypes.keySet().iterator();
			while ( keys.hasNext() ) {
				String aKey = keys.next();
				registerColumnType( columnTypes.get( aKey ), aKey );
			}
		}

		if ( null != functionsToRegister ) {
			Iterator<String> keys = functionsToRegister.keySet().iterator();
			while ( keys.hasNext() ) {
				String aKey = keys.next();
				registerFunction( aKey, functionsToRegister.get( aKey ) );

			}
		}
	}

	public Map<String, Integer> getColumnTypesToRegister() {
		return dialectDelegate.getColumnTypesToRegister();
	}

	public Map<String, StandardSQLFunction> getFunctionsToRegister() {
		return dialectDelegate.getFunctionsToRegister();
	}

	@Override
	public String getTypeName(int code, long length, int precision, int scale) throws HibernateException {
		return dialectDelegate.getTypeName( code, length, precision, scale );
	}

	@Override
	public SqlTypeDescriptor remapSqlTypeDescriptor(SqlTypeDescriptor sqlTypeDescriptor) {
		return dialectDelegate.remapSqlTypeDescriptor( sqlTypeDescriptor );
	}

	@Override
	public String getSpatialRelateSQL(String columnName, int spatialRelation) {
		return dialectDelegate.getSpatialRelateSQL( columnName, spatialRelation );
	}

	@Override
	public String getSpatialFilterExpression(String columnName) {
		return dialectDelegate.getSpatialFilterExpression( columnName );
	}

	@Override
	public String getSpatialAggregateSQL(String columnName, int aggregation) {
		return dialectDelegate.getSpatialAggregateSQL( columnName, aggregation );
	}

	@Override
	public String getDWithinSQL(String columnName) {
		return dialectDelegate.getDWithinSQL( columnName );
	}

	@Override
	public String getHavingSridSQL(String columnName) {
		return dialectDelegate.getHavingSridSQL( columnName );
	}

	@Override
	public String getIsEmptySQL(String columnName, boolean isEmpty) {
		return dialectDelegate.getIsEmptySQL( columnName, isEmpty );
	}

	public String getDbGeometryTypeName() {
		return dialectDelegate.getDbGeometryTypeName();
	}

	@Override
	public boolean supportsFiltering() {
		return dialectDelegate.supportsFiltering();
	}

	@Override
	public boolean supports(SpatialFunction function) {
		return dialectDelegate.supports( function );
	}
}