package org.celstec.arlearn.delegators;

import android.util.Log;
import daoBase.DaoConfiguration;
import org.celstec.arlearn2.android.download.FileByteDownloader;
import org.celstec.arlearn2.android.delegators.ARL;
import org.celstec.arlearn2.android.delegators.AbstractDelegator;
import org.celstec.dao.gen.GameLocalObject;
import org.celstec.dao.gen.RunLocalObject;
import org.celstec.events.InquiryEvent;
import org.celstec.arlearn2.client.InquiryClient;
import org.celstec.dao.gen.InquiryLocalObject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * ****************************************************************************
 * Copyright (C) 2013 Open Universiteit Nederland
 * <p/>
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * Contributors: Stefaan Ternier
 * ****************************************************************************
 */
public class InquiryDelegator extends AbstractDelegator {

    public static final String SYNC_TAG = "SYNCING";

    private static InquiryDelegator instance;
    private InquiryLocalObject currentInquiry;

    private InquiryDelegator() {
        ARL.eventBus.register(this);
    }

    public static InquiryDelegator getInstance() {
        if (instance == null) {
            instance = new InquiryDelegator();
        }
        return instance;
    }

    public void syncDataCollectionTasks(){
        ARL.eventBus.post(new SyncDataCollectionTasks(null));
    }

    public void syncDataCollectionTasks(InquiryLocalObject inquiry){
        ARL.eventBus.post(new SyncDataCollectionTasks(inquiry));
    }

    public InquiryLocalObject getCurrentInquiry() {
        return currentInquiry;
    }

    public void setCurrentInquiry(InquiryLocalObject currentInquiry) {
        this.currentInquiry = currentInquiry;
    }

    public InquiryLocalObject getInquiryLocalObject(long inquiryId) {
        return INQ.dao.getInquiryLocalObjectDao().load(inquiryId);
    }

    public void syncInquiries() {
        ARL.eventBus.post(new SyncInquiries());
    }

    public void syncHypothesis(long inquiryId) {
        ARL.eventBus.post(new SyncInquiriesHypothesis(inquiryId));
    }

    public void onEventAsync(SyncInquiries sge) {
//        uploadInquiries();
        downloadInquiries();
    }

//    private void uploadInquiries() {
//        InquiryLocalObjectDao dao = DaoConfiguration.getInstance().getInquiryLocalObjectDao();
//        QueryBuilder<InquiryLocalObject> qb = dao.queryBuilder();
//        qb.where(InquiryLocalObjectDao.Properties.IsSynchronized.eq(false));
//        for (InquiryLocalObject inquiry: qb.list()) {
//            try {
//                InquiryClient.getInquiryClient().createInquiry(inquiry);
//                dao.delete(inquiry);
//            } catch (Exception e) {
//                Log.e("ARLearn", e.getMessage(), e);
//            }
//        }
//    }


    private void downloadInquiries() {
        Log.i(SYNC_TAG, "Syncing user inquires ");
        String token = returnTokenIfOnline();
        if (token != null) {
            String inquiries = InquiryClient.getInquiryClient().userInquiries(token);

            if (inquiries == null) return;
            JSONObject json = null;
            try {
                json = new JSONObject(inquiries);
                JSONArray array = json.getJSONArray("result");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject inqJsonObject = array.getJSONObject(i);
                    long inquiryId = inqJsonObject.getLong("inquiryId");
                    InquiryLocalObject inquiry = DaoConfiguration.getInstance().getInquiryLocalObjectDao().load(inquiryId);
                    if (inquiry == null) {
                        inquiry = new InquiryLocalObject();
                    }
                    inquiry.setId(inquiryId);
                    inquiry.setTitle(inqJsonObject.getString("title"));
                    inquiry.setDescription(inqJsonObject.getString("description"));
                    inquiry.setIsSynchronized(true);
                    long runId = InquiryClient.getInquiryClient().getArlearnRunId(token, inquiry.getId());
                    inquiry.setRunId(runId);

                    inquiry.setIcon(new FileByteDownloader(inqJsonObject.getString("icon")).syncDownload());


                    long rid = DaoConfiguration.getInstance().getInquiryLocalObjectDao().insertOrReplace(inquiry);
                    ARL.eventBus.post(new InquiryEvent(inquiry.getId()));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void onEventAsync(SyncDataCollectionTasks dcTask) {
        if (dcTask.getInquiry() == null) {
            dcTask.setInquiry(getCurrentInquiry());
        }

        Log.i(SYNC_TAG, "Syncing data collection tasks");
        if (dcTask.getInquiry() != null) {
            if (dcTask.getInquiry().getRunLocalObject() == null) {
                if (dcTask.getInquiry().getRunId() == 0) return;
                INQ.runs.asyncRun(dcTask.getInquiry().getRunId()); //this is done synchronously
            }
            RunLocalObject run = DaoConfiguration.getInstance().getRunLocalObjectDao().load(dcTask.getInquiry().getRunId());
            if (run != null) {
                dcTask.getInquiry().setRunLocalObject(run);
                if (run.getGameLocalObject() == null) {
                    GameLocalObject gameLocalObject = INQ.games.asyncGame(run.getGameId(),true);
                    run.setGameLocalObject(gameLocalObject);
                }
            run.refresh();
            INQ.generalItems.syncGeneralItems(run.getGameId());
        }
        }
    }

    public void onEventAsync(SyncInquiriesHypothesis syncInquiriesHypothesis) {
        String token = returnTokenIfOnline();
        if (token != null) {
            InquiryClient.Hypothesis hypothesis = InquiryClient.getInquiryClient().getInquiryHypothesis(token, syncInquiriesHypothesis.inquiryId);
            if (hypothesis != null) {
                InquiryLocalObject inquiry = DaoConfiguration.getInstance().getInquiryLocalObjectDao().load(syncInquiriesHypothesis.inquiryId);
                if (inquiry == null) {
                    inquiry = new InquiryLocalObject();
                    inquiry.setId(syncInquiriesHypothesis.inquiryId);
                }
                inquiry.setHypothesisTitle(hypothesis.getTitle());
                inquiry.setHypothesisDescription(hypothesis.getDescription());
                DaoConfiguration.getInstance().getInquiryLocalObjectDao().insertOrReplace(inquiry);
                ARL.eventBus.post(new InquiryEvent(inquiry.getId()));
            }
        }
    }

    private class SyncInquiries{}

    private class SyncInquiriesHypothesis {
        private long inquiryId;

        private SyncInquiriesHypothesis(long inquiryId) {
            this.inquiryId = inquiryId;
        }

        public long getInquiryId() {
            return inquiryId;
        }

        public void setInquiryId(long inquiryId) {
            this.inquiryId = inquiryId;
        }
    }

    private class SyncDataCollectionTasks {
        InquiryLocalObject inquiry;

        private SyncDataCollectionTasks(InquiryLocalObject inquiry) {
            this.inquiry = inquiry;
        }

        public InquiryLocalObject getInquiry() {
            return inquiry;
        }

        public void setInquiry(InquiryLocalObject inquiry) {
            this.inquiry = inquiry;
        }
    }
}
