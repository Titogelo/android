package org.celstec.dao.gen;

import java.util.List;
import org.celstec.dao.gen.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
import java.util.HashMap;
import org.celstec.arlearn2.beans.generalItem.GeneralItem;
import org.celstec.arlearn2.beans.deserializer.json.JsonBeanDeserializer;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
// KEEP INCLUDES END
/**
 * Entity mapped to table GENERAL_ITEM_LOCAL_OBJECT.
 */
public class GeneralItemLocalObject {

    private Long id;
    private String type;
    private Boolean deleted;
    private String title;
    private String description;
    private String bean;
    private Boolean autoLaunch;
    private Long lastModificationDate;
    private Double lat;
    private Double lng;
    private long gameId;
    private Long dependsOn;
    private Long disappearAt;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient GeneralItemLocalObjectDao myDao;

    private GameLocalObject gameLocalObject;
    private Long gameLocalObject__resolvedKey;

    private DependencyLocalObject dependencyLocalObject;
    private Long dependencyLocalObject__resolvedKey;

    private DependencyDisappearLocalObject dependencyDisappearLocalObject;
    private Long dependencyDisappearLocalObject__resolvedKey;

    private List<GameFileLocalObject> generalItemFiles;
    private List<GeneralItemMediaLocalObject> generalItemMedia;
    private List<ActionLocalObject> actions;
    private List<ResponseLocalObject> responses;
    private List<GeneralItemVisibilityLocalObject> visibilities;

    // KEEP FIELDS - put your custom fields here

    private HashMap<Long, Boolean> runsToRead = new HashMap<Long, Boolean>();
    // KEEP FIELDS END

    public GeneralItemLocalObject() {
    }

    public GeneralItemLocalObject(Long id) {
        this.id = id;
    }

    public GeneralItemLocalObject(Long id, String type, Boolean deleted, String title, String description, String bean, Boolean autoLaunch, Long lastModificationDate, Double lat, Double lng, long gameId, Long dependsOn, Long disappearAt) {
        this.id = id;
        this.type = type;
        this.deleted = deleted;
        this.title = title;
        this.description = description;
        this.bean = bean;
        this.autoLaunch = autoLaunch;
        this.lastModificationDate = lastModificationDate;
        this.lat = lat;
        this.lng = lng;
        this.gameId = gameId;
        this.dependsOn = dependsOn;
        this.disappearAt = disappearAt;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGeneralItemLocalObjectDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Boolean getAutoLaunch() {
        return autoLaunch;
    }

    public void setAutoLaunch(Boolean autoLaunch) {
        this.autoLaunch = autoLaunch;
    }

    public Long getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Long lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
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

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public Long getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Long dependsOn) {
        this.dependsOn = dependsOn;
    }

    public Long getDisappearAt() {
        return disappearAt;
    }

    public void setDisappearAt(Long disappearAt) {
        this.disappearAt = disappearAt;
    }

    /** To-one relationship, resolved on first access. */
    public GameLocalObject getGameLocalObject() {
        long __key = this.gameId;
        if (gameLocalObject__resolvedKey == null || !gameLocalObject__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameLocalObjectDao targetDao = daoSession.getGameLocalObjectDao();
            GameLocalObject gameLocalObjectNew = targetDao.load(__key);
            synchronized (this) {
                gameLocalObject = gameLocalObjectNew;
            	gameLocalObject__resolvedKey = __key;
            }
        }
        return gameLocalObject;
    }

    public void setGameLocalObject(GameLocalObject gameLocalObject) {
        if (gameLocalObject == null) {
            throw new DaoException("To-one property 'gameId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.gameLocalObject = gameLocalObject;
            gameId = gameLocalObject.getId();
            gameLocalObject__resolvedKey = gameId;
        }
    }

    /** To-one relationship, resolved on first access. */
    public DependencyLocalObject getDependencyLocalObject() {
        Long __key = this.dependsOn;
        if (dependencyLocalObject__resolvedKey == null || !dependencyLocalObject__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DependencyLocalObjectDao targetDao = daoSession.getDependencyLocalObjectDao();
            DependencyLocalObject dependencyLocalObjectNew = targetDao.load(__key);
            synchronized (this) {
                dependencyLocalObject = dependencyLocalObjectNew;
            	dependencyLocalObject__resolvedKey = __key;
            }
        }
        return dependencyLocalObject;
    }

    public void setDependencyLocalObject(DependencyLocalObject dependencyLocalObject) {
        synchronized (this) {
            this.dependencyLocalObject = dependencyLocalObject;
            dependsOn = dependencyLocalObject == null ? null : dependencyLocalObject.getId();
            dependencyLocalObject__resolvedKey = dependsOn;
        }
    }

    /** To-one relationship, resolved on first access. */
    public DependencyDisappearLocalObject getDependencyDisappearLocalObject() {
        Long __key = this.disappearAt;
        if (dependencyDisappearLocalObject__resolvedKey == null || !dependencyDisappearLocalObject__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DependencyDisappearLocalObjectDao targetDao = daoSession.getDependencyDisappearLocalObjectDao();
            DependencyDisappearLocalObject dependencyDisappearLocalObjectNew = targetDao.load(__key);
            synchronized (this) {
                dependencyDisappearLocalObject = dependencyDisappearLocalObjectNew;
            	dependencyDisappearLocalObject__resolvedKey = __key;
            }
        }
        return dependencyDisappearLocalObject;
    }

    public void setDependencyDisappearLocalObject(DependencyDisappearLocalObject dependencyDisappearLocalObject) {
        synchronized (this) {
            this.dependencyDisappearLocalObject = dependencyDisappearLocalObject;
            disappearAt = dependencyDisappearLocalObject == null ? null : dependencyDisappearLocalObject.getId();
            dependencyDisappearLocalObject__resolvedKey = disappearAt;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<GameFileLocalObject> getGeneralItemFiles() {
        if (generalItemFiles == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameFileLocalObjectDao targetDao = daoSession.getGameFileLocalObjectDao();
            List<GameFileLocalObject> generalItemFilesNew = targetDao._queryGeneralItemLocalObject_GeneralItemFiles(id);
            synchronized (this) {
                if(generalItemFiles == null) {
                    generalItemFiles = generalItemFilesNew;
                }
            }
        }
        return generalItemFiles;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetGeneralItemFiles() {
        generalItemFiles = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<GeneralItemMediaLocalObject> getGeneralItemMedia() {
        if (generalItemMedia == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GeneralItemMediaLocalObjectDao targetDao = daoSession.getGeneralItemMediaLocalObjectDao();
            List<GeneralItemMediaLocalObject> generalItemMediaNew = targetDao._queryGeneralItemLocalObject_GeneralItemMedia(id);
            synchronized (this) {
                if(generalItemMedia == null) {
                    generalItemMedia = generalItemMediaNew;
                }
            }
        }
        return generalItemMedia;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetGeneralItemMedia() {
        generalItemMedia = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<ActionLocalObject> getActions() {
        if (actions == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ActionLocalObjectDao targetDao = daoSession.getActionLocalObjectDao();
            List<ActionLocalObject> actionsNew = targetDao._queryGeneralItemLocalObject_Actions(id);
            synchronized (this) {
                if(actions == null) {
                    actions = actionsNew;
                }
            }
        }
        return actions;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetActions() {
        actions = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<ResponseLocalObject> getResponses() {
        if (responses == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ResponseLocalObjectDao targetDao = daoSession.getResponseLocalObjectDao();
            List<ResponseLocalObject> responsesNew = targetDao._queryGeneralItemLocalObject_Responses(id);
            synchronized (this) {
                if(responses == null) {
                    responses = responsesNew;
                }
            }
        }
        return responses;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetResponses() {
        responses = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<GeneralItemVisibilityLocalObject> getVisibilities() {
        if (visibilities == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GeneralItemVisibilityLocalObjectDao targetDao = daoSession.getGeneralItemVisibilityLocalObjectDao();
            List<GeneralItemVisibilityLocalObject> visibilitiesNew = targetDao._queryGeneralItemLocalObject_Visibilities(id);
            synchronized (this) {
                if(visibilities == null) {
                    visibilities = visibilitiesNew;
                }
            }
        }
        return visibilities;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetVisibilities() {
        visibilities = null;
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

    public GeneralItem getGeneralItemBean(){
        try {
            return (GeneralItem) JsonBeanDeserializer.deserialize(getBean());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isRead(long runId) {
        if (!runsToRead.containsKey(runId)) {
            for (ActionLocalObject action : getActions()) {
                if (action.getRunId() == runId && "read".equals(action.getAction())) {
                    runsToRead.put(runId, true);
                    return true;
                }
            }
            return false;
        }
        return runsToRead.get(runId);
    }

    public JSONObject getJSONBean(){
        JSONObject jsonObject = null;
        try {
            return new JSONObject(getBean());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean isPicture() throws JSONException {
        JSONObject jsonObject = getJSONBean();
        if (!jsonObject.has("withPicture")) return false;
        return (Boolean) jsonObject.get("withPicture");
    }

    public boolean isVideo() throws JSONException {
        JSONObject jsonObject = getJSONBean();
        if (!jsonObject.has("withVideo")) return false;
        return (Boolean) jsonObject.get("withVideo");
    }

    public boolean isAudio() throws JSONException {
        JSONObject jsonObject = getJSONBean();
        if (!jsonObject.has("withAudio")) return false;
        return (Boolean) jsonObject.get("withAudio");
    }

    public boolean isText() throws JSONException {
        JSONObject jsonObject = getJSONBean();
        if (!jsonObject.has("withText")) return false;
        return (Boolean) jsonObject.get("withText");
    }

    public boolean isValue() throws JSONException {
        JSONObject jsonObject = getJSONBean();
        if (!jsonObject.has("withValue")) return false;
        return (Boolean) jsonObject.get("withValue");
    }
    // KEEP METHODS END

}
