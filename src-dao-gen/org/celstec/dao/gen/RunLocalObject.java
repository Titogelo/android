package org.celstec.dao.gen;

import java.util.List;
import org.celstec.dao.gen.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
import java.util.TreeSet;
// KEEP INCLUDES END
/**
 * Entity mapped to table RUN_LOCAL_OBJECT.
 */
public class RunLocalObject {

    private Long id;
    /** Not-null value. */
    private String title;
    private String roles;
    private Boolean deleted;
    private long gameId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient RunLocalObjectDao myDao;

    private GameLocalObject gameLocalObject;
    private Long gameLocalObject__resolvedKey;

    private List<ActionLocalObject> actions;
    private List<ResponseLocalObject> responses;
    private List<ProximityEventRegistryLocalObject> proximityEvents;
    private List<ThreadLocalObject> lastModificationDate;
    private List<MessageLocalObject> messages;
    private List<GeneralItemVisibilityLocalObject> visibilities;

    // KEEP FIELDS - put your custom fields here
    private TreeSet<String> userRoles;
    // KEEP FIELDS END

    public RunLocalObject() {
    }

    public RunLocalObject(Long id) {
        this.id = id;
    }

    public RunLocalObject(Long id, String title, String roles, Boolean deleted, long gameId) {
        this.id = id;
        this.title = title;
        this.roles = roles;
        this.deleted = deleted;
        this.gameId = gameId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRunLocalObjectDao() : null;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
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

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<ActionLocalObject> getActions() {
        if (actions == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ActionLocalObjectDao targetDao = daoSession.getActionLocalObjectDao();
            List<ActionLocalObject> actionsNew = targetDao._queryRunLocalObject_Actions(id);
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
            List<ResponseLocalObject> responsesNew = targetDao._queryRunLocalObject_Responses(id);
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
    public List<ProximityEventRegistryLocalObject> getProximityEvents() {
        if (proximityEvents == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProximityEventRegistryLocalObjectDao targetDao = daoSession.getProximityEventRegistryLocalObjectDao();
            List<ProximityEventRegistryLocalObject> proximityEventsNew = targetDao._queryRunLocalObject_ProximityEvents(id);
            synchronized (this) {
                if(proximityEvents == null) {
                    proximityEvents = proximityEventsNew;
                }
            }
        }
        return proximityEvents;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetProximityEvents() {
        proximityEvents = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<ThreadLocalObject> getLastModificationDate() {
        if (lastModificationDate == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ThreadLocalObjectDao targetDao = daoSession.getThreadLocalObjectDao();
            List<ThreadLocalObject> lastModificationDateNew = targetDao._queryRunLocalObject_LastModificationDate(id);
            synchronized (this) {
                if(lastModificationDate == null) {
                    lastModificationDate = lastModificationDateNew;
                }
            }
        }
        return lastModificationDate;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetLastModificationDate() {
        lastModificationDate = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<MessageLocalObject> getMessages() {
        if (messages == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MessageLocalObjectDao targetDao = daoSession.getMessageLocalObjectDao();
            List<MessageLocalObject> messagesNew = targetDao._queryRunLocalObject_Messages(id);
            synchronized (this) {
                if(messages == null) {
                    messages = messagesNew;
                }
            }
        }
        return messages;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetMessages() {
        messages = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<GeneralItemVisibilityLocalObject> getVisibilities() {
        if (visibilities == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GeneralItemVisibilityLocalObjectDao targetDao = daoSession.getGeneralItemVisibilityLocalObjectDao();
            List<GeneralItemVisibilityLocalObject> visibilitiesNew = targetDao._queryRunLocalObject_Visibilities(id);
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
    public TreeSet<String> getUserRoles(){
        if (userRoles == null) {
            userRoles = new TreeSet<>();
            if (getRoles() != null){
                for (String role:getRoles().split(";")){
                    userRoles.add(role);
                }
            }
        }
        return userRoles;
    }
    // KEEP METHODS END

}
