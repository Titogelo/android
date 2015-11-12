package org.celstec.dao.gen;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import org.celstec.dao.gen.ProximityEventRegistryLocalObject;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table PROXIMITY_EVENT_REGISTRY_LOCAL_OBJECT.
*/
public class ProximityEventRegistryLocalObjectDao extends AbstractDao<ProximityEventRegistryLocalObject, Long> {

    public static final String TABLENAME = "PROXIMITY_EVENT_REGISTRY_LOCAL_OBJECT";

    /**
     * Properties of entity ProximityEventRegistryLocalObject.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Lat = new Property(1, Double.class, "lat", false, "LAT");
        public final static Property Lng = new Property(2, Double.class, "lng", false, "LNG");
        public final static Property Radius = new Property(3, Long.class, "radius", false, "RADIUS");
        public final static Property Expires = new Property(4, Long.class, "expires", false, "EXPIRES");
        public final static Property RunId = new Property(5, long.class, "runId", false, "RUN_ID");
    };

    private Query<ProximityEventRegistryLocalObject> runLocalObject_ProximityEventsQuery;

    public ProximityEventRegistryLocalObjectDao(DaoConfig config) {
        super(config);
    }
    
    public ProximityEventRegistryLocalObjectDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'PROXIMITY_EVENT_REGISTRY_LOCAL_OBJECT' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'LAT' REAL," + // 1: lat
                "'LNG' REAL," + // 2: lng
                "'RADIUS' INTEGER," + // 3: radius
                "'EXPIRES' INTEGER," + // 4: expires
                "'RUN_ID' INTEGER NOT NULL );"); // 5: runId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'PROXIMITY_EVENT_REGISTRY_LOCAL_OBJECT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ProximityEventRegistryLocalObject entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Double lat = entity.getLat();
        if (lat != null) {
            stmt.bindDouble(2, lat);
        }
 
        Double lng = entity.getLng();
        if (lng != null) {
            stmt.bindDouble(3, lng);
        }
 
        Long radius = entity.getRadius();
        if (radius != null) {
            stmt.bindLong(4, radius);
        }
 
        Long expires = entity.getExpires();
        if (expires != null) {
            stmt.bindLong(5, expires);
        }
        stmt.bindLong(6, entity.getRunId());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ProximityEventRegistryLocalObject readEntity(Cursor cursor, int offset) {
        ProximityEventRegistryLocalObject entity = new ProximityEventRegistryLocalObject( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getDouble(offset + 1), // lat
            cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2), // lng
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // radius
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // expires
            cursor.getLong(offset + 5) // runId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ProximityEventRegistryLocalObject entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLat(cursor.isNull(offset + 1) ? null : cursor.getDouble(offset + 1));
        entity.setLng(cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2));
        entity.setRadius(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setExpires(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setRunId(cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ProximityEventRegistryLocalObject entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ProximityEventRegistryLocalObject entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "proximityEvents" to-many relationship of RunLocalObject. */
    public List<ProximityEventRegistryLocalObject> _queryRunLocalObject_ProximityEvents(long runId) {
        synchronized (this) {
            if (runLocalObject_ProximityEventsQuery == null) {
                QueryBuilder<ProximityEventRegistryLocalObject> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.RunId.eq(null));
                runLocalObject_ProximityEventsQuery = queryBuilder.build();
            }
        }
        Query<ProximityEventRegistryLocalObject> query = runLocalObject_ProximityEventsQuery.forCurrentThread();
        query.setParameter(0, runId);
        return query.list();
    }

}
