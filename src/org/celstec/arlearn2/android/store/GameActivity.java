package org.celstec.arlearn2.android.store;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import authentication.LoginFragment;
import daoBase.DaoConfiguration;
import org.celstec.arlearn2.android.R;
import org.celstec.arlearn2.android.delegators.ARL;
import org.celstec.arlearn2.android.delegators.GameDelegator;
import org.celstec.arlearn2.android.delegators.game.GameDownloadManager;
import org.celstec.arlearn2.android.events.GameEvent;
import org.celstec.dao.gen.StoreGameLocalObject;

import java.text.DateFormat;

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
public class GameActivity extends Activity implements GameDownloadProgressView.DownloadCompleteInterface{
    private long gameId;
    private GameActivity gameView;
    ProgressDialog pd;

    private GameDownloadProgressView progressView ;
    private GameDownloadManager gameDownloadManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ARLearn_schema2);
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            getActionBar().setHomeButtonEnabled(true);
        }

        setContentView(R.layout.store_game_overview);
        gameId = getIntent().getLongExtra(StoreGameLocalObject.class.getName(), 0l);

        ARL.store.syncStoreGame(gameId);
        gameView = this;
        gameView.findViewById(R.id.stars).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent textInputIntent = new Intent(GameActivity.this, RateGameActivity.class);
                startActivityForResult(textInputIntent, 1);
            }
        });

        TextView downloadButton = (TextView) gameView.findViewById(R.id.downloadId);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ARL.accounts.isAuthenticated()) {
                    progressView.show();
                } else {
//                    FragmentManager fm = getActivity().getSupportFragmentManager();
//                    Bundle args = new Bundle();
//                    Fragment frag = new LoginFragment();
//
//                    frag.setArguments(args);
//                    fm.beginTransaction()
//                            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,R.anim.slide_in_left, R.anim.slide_out_right)
//                            .replace(R.id.right_pane, frag).addToBackStack(null).commit();
                }

            }
        });

        pd = ProgressDialog.show(this, "Loading", "Wait", true);
        gameView.findViewById(R.id.gamePane).setVisibility(View.INVISIBLE);
        drawGameContent();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (progressView == null) {
            gameDownloadManager = new GameDownloadManager(gameId);
            progressView = new GameDownloadProgressView(this, this, gameDownloadManager);

        }
        ARL.eventBus.register(this);
        progressView.register();
    }

    @Override
    public void onPause() {
        super.onPause();
        ARL.eventBus.unregister(this);
        progressView.unregister();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            int rating = ((Integer)data.getExtras().get("rating"));
            GameDelegator.getInstance().rating.submitRating(rating, gameId);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;

    }

    private void drawGameContent() {
        GameActivity v = this;
        StoreGameLocalObject localObject = DaoConfiguration.getInstance().getStoreGameLocalObjectDao().load(gameId);
        if (localObject != null) {
            v.findViewById(R.id.gamePane).setVisibility(View.VISIBLE);
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }
            byte[] data = localObject.getIcon();
            if (localObject.getIcon() != null && data.length != 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                ((ImageView) v.findViewById(R.id.icon)).setImageBitmap(bitmap);
            }
            ((TextView) v.findViewById(R.id.gameTitleId)).setText(localObject.getTitle());

            if (localObject.getDescription() != null)
                ((WebView) v.findViewById(R.id.gameStoreDescriptionId)).loadData(localObject.getDescription(), "text/html", "utf-8");

            int resID = 0;
            String licenseCode = localObject.getLicenseCode();
            if (licenseCode != null) {
                if (licenseCode.equals("cc-by")) {
                    resID = R.string.ccby;
                } else if (licenseCode.equals("cc-by-nd")) {
                    resID = R.string.bynd;
                } else if (licenseCode.equals("cc-by-sa")) {
                    resID = R.string.bysa;
                } else if (licenseCode.equals("cc-by-nc")) {
                    resID = R.string.bync;
                } else if (licenseCode.equals("cc-by-nc-sa")) {
                    resID = R.string.byncsa;
                } else if (licenseCode.equals("cc-by-nc-nd")) {
                    resID = R.string.byncnd;
                }
            } else {
                resID = R.string.nolicense;
            }
            ((TextView) v.findViewById(R.id.licenseId)).setText(getString(resID));

            DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(this);
            ((TextView) v.findViewById(R.id.dateId)).setText(dateFormat.format(localObject.getLastModificationDate()));
        }
    }

    public void onEventMainThread(GameEvent event) {
        if (event.getGameId() == gameId) {
            gameId = event.getGameId();
            if (gameView != null) drawGameContent();
        } else {
            if (gameId == 0 && event.getError() == GameEvent.ERROR_SYNCING_FAILED) {
                if (pd != null && pd.isShowing()) {
                    pd.dismiss();
                }
                Toast.makeText(this, getString(R.string.unabletosyncgame), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void downloadComplete() {
        gameView.findViewById(R.id.downloadId).setVisibility(View.GONE);
        gameView.findViewById(R.id.openId).setVisibility(View.VISIBLE);

    }
}
