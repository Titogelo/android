package org.celstec.arlearn2.android.game.generalItem.itemTypes;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import daoBase.DaoConfiguration;
import org.celstec.arlearn2.android.R;
import org.celstec.arlearn2.android.delegators.ARL;
import org.celstec.arlearn2.android.game.generalItem.GeneralItemActivity;
import org.celstec.arlearn2.android.game.generalItem.GeneralItemActivityFeatures;
import org.celstec.arlearn2.android.game.generalItem.GeneralItemMapper;
import org.celstec.arlearn2.android.game.generalItem.NarratorItemJavascriptInterface;
import org.celstec.arlearn2.android.util.MediaFolders;
import org.celstec.arlearn2.beans.generalItem.NarratorItem;
import org.celstec.arlearn2.beans.generalItem.OpenQuestion;
import org.celstec.dao.gen.GeneralItemLocalObject;

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
public class NarratorItemFeatures extends GeneralItemActivityFeatures{

    @Override
    protected boolean showDataCollection() {
        return true;
    }

    @Override
    protected int getImageResource() {
        return GeneralItemMapper.mapConstantToDrawable(GeneralItemMapper.NARRATOR_ITEM);
    }

    public NarratorItemFeatures(GeneralItemActivity activity, GeneralItemLocalObject generalItemLocalObject) {
        this(activity, generalItemLocalObject, true);
    }

    public NarratorItemFeatures(GeneralItemActivity activity, GeneralItemLocalObject generalItemLocalObject, boolean dataCollection) {
        super(activity, generalItemLocalObject);
        if (activity.findViewById(R.id.audioLayout)==null) return;
        if (dataCollection) {
            OpenQuestion openQuestion = ((NarratorItem) generalItemBean).getOpenQuestion();
            if (openQuestion != null){
                dataCollectionViewController.setVisibilities(openQuestion.isWithAudio(),openQuestion.isWithPicture(),openQuestion.isWithVideo(),openQuestion.isWithValue(), openQuestion.isWithText());
            } else {
                dataCollectionViewController.hideDataCollection();
            }
        } else {
            dataCollectionViewController.hideDataCollection();
        }
    }

    public void setMetadata(){
        super.setMetadata();
        WebView webView = (WebView) this.activity.findViewById(R.id.descriptionId);
        webView.setBackgroundColor(0x00000000);
        String baseUrl = "";
        if (ARL.config.getBooleanProperty("white_label") && !ARL.config.getBooleanProperty("white_label_online_sync")) {
            baseUrl = "file:///android_res/raw/";
        } else {
            baseUrl = "file://"+MediaFolders.getIncommingFilesDir().getParent().toString()+"/";
        }
        String prefix = ARL.config.getProperty("message_html_prefix");
        String postfix = ARL.config.getProperty("message_html_postfix");
        if (prefix == null){
            prefix = "";
        }
        if (postfix == null){
            postfix = "";
        }
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new NarratorItemJavascriptInterface(((NarratorItem) generalItemBean), activity.getGameActivityFeatures().getRunId()), "arlearn");
        webView.loadDataWithBaseURL(baseUrl, prefix+((NarratorItem) generalItemBean).getRichText()+postfix, "text/html", "UTF-8", null);

    }
}
// <div onclick="arlearn.startChat('Taak');" style="width: 300px;">klik hier om de chat te starten</div> <div onclick="arlearn.hint('test','test','test');" style="width: 300px;">hint</div>
// <div onclick="arlearn.startChat('Taak');">klik test hier om de chat te starten</div>