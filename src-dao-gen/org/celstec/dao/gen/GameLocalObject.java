package org.celstec.dao.gen;

import java.util.List;
import org.celstec.dao.gen.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
import org.celstec.arlearn2.beans.deserializer.json.JsonBeanDeserializer;
import org.celstec.arlearn2.beans.game.Game;
import org.codehaus.jettison.json.JSONException;
// KEEP INCLUDES END
/**
 * Entity mapped to table GAME_LOCAL_OBJECT.
 */
public class GameLocalObject {

    private Long id;
    /** Not-null value. */
    private String title;
    private String licenseCode;
    private String description;
    private String bean;
    private Boolean mapAvailable;
    private Boolean deleted;
    private Long lastModificationDate;
    private Long lastSyncGeneralItemsDate;
    private byte[] icon;
    private Double lat;
    private Double lng;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient GameLocalObjectDao myDao;

    private List<GameContributorLocalObject> contributors;
    private List<GeneralItemLocalObject> generalItems;
    private List<GameFileLocalObject> gameFiles;
    private List<RunLocalObject> runs;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public GameLocalObject() {
    }

    public GameLocalObject(Long id) {
        this.id = id;
    }

    public GameLocalObject(Long id, String title, String licenseCode, String description, String bean, Boolean mapAvailable, Boolean deleted, Long lastModificationDate, Long lastSyncGeneralItemsDate, byte[] icon, Double lat, Double lng) {
        this.id = id;
        this.title = title;
        this.licenseCode = licenseCode;
        this.description = description;
        this.bean = bean;
        this.mapAvailable = mapAvailable;
        this.deleted = deleted;
        this.lastModificationDate = lastModificationDate;
        this.lastSyncGeneralItemsDate = lastSyncGeneralItemsDate;
        this.icon = icon;
        this.lat = lat;
        this.lng = lng;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGameLocalObjectDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public Boolean getMapAvailable() {
        return mapAvailable;
    }

    public void setMapAvailable(Boolean mapAvailable) {
        this.mapAvailable = mapAvailable;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Long lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastSyncGeneralItemsDate() {
        return lastSyncGeneralItemsDate;
    }

    public void setLastSyncGeneralItemsDate(Long lastSyncGeneralItemsDate) {
        this.lastSyncGeneralItemsDate = lastSyncGeneralItemsDate;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<GameContributorLocalObject> getContributors() {
        if (contributors == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameContributorLocalObjectDao targetDao = daoSession.getGameContributorLocalObjectDao();
            List<GameContributorLocalObject> contributorsNew = targetDao._queryGameLocalObject_Contributors(id);
            synchronized (this) {
                if(contributors == null) {
                    contributors = contributorsNew;
                }
            }
        }
        return contributors;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetContributors() {
        contributors = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<GeneralItemLocalObject> getGeneralItems() {
        if (generalItems == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GeneralItemLocalObjectDao targetDao = daoSession.getGeneralItemLocalObjectDao();
            List<GeneralItemLocalObject> generalItemsNew = targetDao._queryGameLocalObject_GeneralItems(id);
            synchronized (this) {
                if(generalItems == null) {
                    generalItems = generalItemsNew;
                }
            }
        }
        return generalItems;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetGeneralItems() {
        generalItems = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<GameFileLocalObject> getGameFiles() {
        if (gameFiles == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameFileLocalObjectDao targetDao = daoSession.getGameFileLocalObjectDao();
            List<GameFileLocalObject> gameFilesNew = targetDao._queryGameLocalObject_GameFiles(id);
            synchronized (this) {
                if(gameFiles == null) {
                    gameFiles = gameFilesNew;
                }
            }
        }
        return gameFiles;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetGameFiles() {
        gameFiles = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<RunLocalObject> getRuns() {
        if (runs == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RunLocalObjectDao targetDao = daoSession.getRunLocalObjectDao();
            List<RunLocalObject> runsNew = targetDao._queryGameLocalObject_Runs(id);
            synchronized (this) {
                if(runs == null) {
                    runs = runsNew;
                }
            }
        }
        return runs;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetRuns() {
        runs = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here

    public Game getGameBean() {
        try{
            return (Game) JsonBeanDeserializer.deserialize(getBean());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    // KEEP METHODS END

}
