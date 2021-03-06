package org.celstec.arlearn2.android.qrCodeScanning;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.FrameLayout;

import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Size;

import android.widget.TextView;

/* Import ZBar Class files */
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import net.sourceforge.zbar.Config;
import org.celstec.arlearn2.android.UserGameIntentAnalyser;
import org.celstec.arlearn2.android.db.PropertiesAdapter;
import org.celstec.arlearn2.android.delegators.ARL;
import org.celstec.arlearn2.android.delegators.AccountDelegator;
import org.celstec.arlearn2.android.game.GameSplashScreen;
import org.celstec.arlearn2.android.store.GameFragment;
import org.celstec.arlearn2.android.R;
import org.celstec.arlearn2.beans.game.Game;
import org.celstec.arlearn2.beans.run.Run;

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
public class ScannerFragment  extends SherlockFragment implements QRScanner.ScanResultInterface {
    QRScanner qrScanner;

//    private Camera mCamera;
//    private CameraPreview mPreview;
//    private Handler autoFocusHandler;

    TextView scanText;
    private UserGameIntentAnalyser analyzer = new UserGameIntentAnalyser() {
        @Override
        public void scannedGame(Game game) {
            GameFragment frag = new GameFragment();
            Bundle bundle = new Bundle();
            bundle.putLong("gameId", game.getGameId());
            frag.setArguments(bundle);

            launchFragment(frag);
        }

        @Override
        public void scannedRun(Run run) {
//            GameFragment frag = new GameFragment();
//            Bundle bundle = new Bundle();
//            bundle.putLong("gameId", run.getGame().getGameId());
//            frag.setArguments(bundle);
//
//            frag.setRun(run);
//            launchFragment(frag);
//            System.out.println(run);
            ARL.runs.selfRegisterForRun(run.getRunId());
            GameSplashScreen.startActivity(getActivity(), run.getGame().getGameId(), run.getRunId(), false);

        }

        @Override
        public void scannedLoginToken(String loginToken) {
            if (AccountDelegator.getInstance().isAuthenticated()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ScannerFragment.this.getActivity());
                builder.setMessage(R.string.youMustLogOut)
                        .setPositiveButton(R.string.logMeOut, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ARL.accounts.disAuthenticate();
                            }
                        })
                        .setNegativeButton(R.string.leaveMeLogedIn, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                builder.create().show();
            } else {
                PropertiesAdapter.getInstance().setAuthToken(loginToken);
                AccountDelegator.getInstance().syncMyAccountDetails();

            }
            ScannerFragment.this.getActivity().getSupportFragmentManager().popBackStack();
        }
    };

//    Button scanButton;

    ImageScanner scanner;

    private boolean barcodeScanned = false;
    private boolean previewing = true;


    static {
        System.loadLibrary("iconv");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.qr_scanner, container, false);


        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        qrScanner = new QRScanner(getActivity(), this, (FrameLayout)v.findViewById(R.id.cameraPreview));

//        autoFocusHandler = new Handler();
//        mCamera = getCameraInstance();
//
//        /* Instance barcode scanner */
//        scanner = new ImageScanner();
//        scanner.setConfig(0, Config.X_DENSITY, 3);
//        scanner.setConfig(0, Config.Y_DENSITY, 3);
//
//        mPreview = new CameraPreview(getActivity(), mCamera, previewCb, autoFocusCB);
//        FrameLayout preview = (FrameLayout)v.findViewById(R.id.cameraPreview);
//        preview.addView(mPreview);
//
        scanText = (TextView)v.findViewById(R.id.scanText);
//
////        scanButton = (Button)v.findViewById(R.id.ScanButton);
//
////        scanButton.setOnClickListener(new OnClickListener() {
////            public void onClick(View v) {
////                if (barcodeScanned) {
////                    barcodeScanned = false;
////                    scanText.setText("Scanning...");
////                    mCamera.setPreviewCallback(previewCb);
////                    mCamera.startPreview();
////                    previewing = true;
////                    mCamera.autoFocus(autoFocusCB);
////                }
////            }
////        });
        return v;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            getActivity().getActionBar().setIcon(R.drawable.ic_ab_back);
        }
    }

    public void onPause() {
        super.onPause();
//        releaseCamera();
        qrScanner.releaseCamera();
    }

//    /** A safe way to get an instance of the Camera object. */
//    public static Camera getCameraInstance(){
//        Camera c = null;
//        try {
//            c = Camera.open();
//        } catch (Exception e){
//        }
//        return c;
//    }
//
//    private void releaseCamera() {
//        if (mCamera != null) {
//            previewing = false;
//            mCamera.setPreviewCallback(null);
//            mCamera.release();
//            mCamera = null;
//        }
//    }

//    private Runnable doAutoFocus = new Runnable() {
//        public void run() {
//            if (previewing)
//                mCamera.autoFocus(autoFocusCB);
//        }
//    };

//    PreviewCallback previewCb = new PreviewCallback() {
//        public void onPreviewFrame(byte[] data, Camera camera) {
//            Camera.Parameters parameters = camera.getParameters();
//            Size size = parameters.getPreviewSize();
//
//            Image barcode = new Image(size.width, size.height, "Y800");
//            barcode.setData(data);
//
//            int result = scanner.scanImage(barcode);
//
//            if (result != 0) {
//                previewing = false;
//                mCamera.setPreviewCallback(null);
//                mCamera.stopPreview();
//
//                SymbolSet syms = scanner.getResults();
//                for (Symbol sym : syms) {
//                    scanText.setText("barcode result " + sym.getData());
//                    analyzer.analyze(sym.getData());
//                    barcodeScanned = true;
//                }
//            }
//        }
//    };
//
//    // Mimic continuous auto-focusing
//    AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
//        public void onAutoFocus(boolean success, Camera camera) {
//            autoFocusHandler.postDelayed(doAutoFocus, 1000);
//        }
//    };

    private void launchFragment(Fragment frag) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Bundle args = new Bundle();

        frag.setArguments(args);
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.right_pane, frag).addToBackStack(null).commit();
    }

    @Override
    public void data(String data) {
        scanText.setText("barcode result " + data);
        analyzer.analyze(data);
    }
}
