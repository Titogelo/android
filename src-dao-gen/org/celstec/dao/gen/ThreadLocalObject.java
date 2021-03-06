package org.celstec.dao.gen;

import java.util.List;
import org.celstec.dao.gen.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table THREAD_LOCAL_OBJECT.
 */
public class ThreadLocalObject {

    private Long id;
    private String name;
    private Long lastModificationDate;
    private long runId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ThreadLocalObjectDao myDao;

    private List<MessageLocalObject> messages;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public ThreadLocalObject() {
    }

    public ThreadLocalObject(Long id) {
        this.id = id;
    }

    public ThreadLocalObject(Long id, String name, Long lastModificationDate, long runId) {
        this.id = id;
        this.name = name;
        this.lastModificationDate = lastModificationDate;
        this.runId = runId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getThreadLocalObjectDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Long lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public long getRunId() {
        return runId;
    }

    public void setRunId(long runId) {
        this.runId = runId;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<MessageLocalObject> getMessages() {
        if (messages == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MessageLocalObjectDao targetDao = daoSession.getMessageLocalObjectDao();
            List<MessageLocalObject> messagesNew = targetDao._queryThreadLocalObject_Messages(id);
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
    // KEEP METHODS END

}
