package com.reader.yoshiii.nfcreader.Main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.reader.yoshiii.nfcreader.Adapter.FragmentAdapter;
import com.reader.yoshiii.nfcreader.Fragment.ScanFragment;
import com.reader.yoshiii.nfcreader.R;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * Created by KIM on 2/7/2016.
 */
public class HandlerActivity extends FragmentActivity {

    //Variable Declarations
    private ViewPager viewPager;
    private NfcAdapter nfcAdapter;
    private FragmentAdapter mAdapter;
    public static String NFCvalue = "";
    public static Boolean scanEnabled = false, writeEnabled = false;
    Button scan, info, about;
    ImageView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        try {

            nfcAdapter = NfcAdapter.getDefaultAdapter(this);

            // Initilization
            viewPager = (ViewPager) findViewById(R.id.viewpager);
            mAdapter = new FragmentAdapter(getSupportFragmentManager());

            viewPager.setAdapter(mAdapter);

        }

        catch(Exception err){
            showPopup(HandlerActivity.this, "Something went wrong, please contact the developer, err: " + err.toString() + "- App is closing.", "Proceed");
        }

        checkNFC();


    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);

        if(scanEnabled == true){

            ScanFragment.scan.setText("Scan");

            if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {

                Toast.makeText(HandlerActivity.this, "NFC received.", Toast.LENGTH_SHORT).show();
                ScanFragment.scan.setEnabled(true);

                try {

                    Parcelable[] parcelables = intent.getParcelableArrayExtra(nfcAdapter.EXTRA_NDEF_MESSAGES);

                    if (parcelables != null && parcelables.length > 0) {

                        Intent launchDetails = new Intent(HandlerActivity.this, DetailsActivity.class);
                        launchDetails.putExtra("TAG_ID", readTextFromMessage((NdefMessage) parcelables[0]));
                        startActivity(launchDetails);


                        scanEnabled = false;

                    } else {

                        showPopup(HandlerActivity.this, "No readable data.", "Proceed");
                    }

                }

                catch(Exception err){

                    showPopup(HandlerActivity.this, "This card is empty.", "Proceed");

                }

            }

        }

        if(writeEnabled == true){

            ScanFragment.scan.setText("Write");

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            NdefMessage ndefMessage = createNdefMessage(NFCvalue);

            writeNdefMessage(tag, ndefMessage);

        }

    }

    //  Calling method for reading NDEF from an NFC medium to display the stored details

    private String readTextFromMessage(NdefMessage ndefMessage){

        String tagContent = null;

        NdefRecord[] ndefRecords = ndefMessage.getRecords();

        if(ndefRecords != null && ndefRecords.length>0){

            NdefRecord ndefRecord = ndefRecords[0];

            tagContent = getTextFromRecord(ndefRecord);

        }

        return tagContent;

    }


    /*  This method fetches the NDEF record from the device and returns it
        to the caling method. */

    private String getTextFromRecord(NdefRecord ndefRecord){

        String tagContent = null;
        try{
            byte[] payload = ndefRecord.getPayload();
            String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
            int languageSize = payload[0] & 0063;
            tagContent = new String(payload, languageSize + 1, payload.length - languageSize - 1, textEncoding);
        }catch (UnsupportedEncodingException err){
            Toast.makeText(this, "Exception catched: " + err.toString(), Toast.LENGTH_SHORT).show();
        }

        return tagContent;

    }

    //  This method checks if the NFC card is NDEF Formatable (For writing data)

    private void formatTag(Tag tag, NdefMessage ndefMessage){

        try{

            NdefFormatable ndefFormatable = NdefFormatable.get(tag);

            if(ndefFormatable == null){
                Toast.makeText(this, "Tag is not NDEF formatable, writing is not allowed",
                        Toast.LENGTH_SHORT).show();
            }

            else{

                ndefFormatable.connect();
                ndefFormatable.format(ndefMessage);
                ndefFormatable.close();

            }

        }

        catch (Exception err){
            Toast.makeText(this, "Exception catched format: " + err, Toast.LENGTH_SHORT).show();
        }

    }

    //  This method writes data directly to the NFC medium in NDEF Format

    private void writeNdefMessage(Tag tag, NdefMessage ndefMessage){

        try{

            if(tag == null){
                Toast.makeText(this, "Tag object cannot be null", Toast.LENGTH_SHORT).show();
            }

            Ndef ndef = Ndef.get(tag);

            if(ndef == null){

                formatTag(tag, ndefMessage);

            }

            else{

                ndef.connect();

                if(!ndef.isWritable()){

                    Toast.makeText(this, "Tag is not writable.", Toast.LENGTH_SHORT).show();

                    ndef.close();
                    return;

                }

                ndef.writeNdefMessage(ndefMessage);
                ndef.close();

                Toast.makeText(this, "Tag written - " + NFCvalue, Toast.LENGTH_SHORT).show();

                writeEnabled = false;
                ScanFragment.scan.setEnabled(true);

            }
        }

        catch(Exception err){
            Toast.makeText(this, "Exception catched write ndef: " + err.toString(), Toast.LENGTH_SHORT).show();
        }

    }


    /*  This methods converts the String you input into NDEF format to be written to
        the NDEF medium which will later be fetched*/

    private NdefRecord createTextRecord(String content){

        try{
            byte[] language;
            language = Locale.getDefault().getLanguage().getBytes("UTF-8");

            final byte[] text = content.getBytes("UTF-8");
            final int languageSize = language.length;
            final int textLength = text.length;
            final ByteArrayOutputStream payload = new ByteArrayOutputStream(1 + languageSize + textLength);

            payload.write((byte) (languageSize & 0x1F));
            payload.write(language, 0, languageSize);
            payload.write(text, 0, textLength);

            return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload.toByteArray());

        }

        catch(UnsupportedEncodingException err){
            Toast.makeText(this, "Exception catched create text record: " + err.toString(), Toast.LENGTH_SHORT).show();
        }

        return null;

    }

    //  This method passes the string data to the converted and returns the result

    private NdefMessage createNdefMessage(String content){

        NdefRecord ndefRecord = createTextRecord(content);

        NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{ ndefRecord });

        return ndefMessage;

    }

    // Method that runs once the screen is  active

    @Override
    protected void onResume() {

        Intent intent = new Intent(this, HandlerActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentFilter = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);

        super.onResume();
    }

    // Method that runs once the screen is not active

    @Override
    protected void onPause() {

        nfcAdapter.disableForegroundDispatch(this);

        super.onPause();
    }


    // Method for checking the availability of NFC

    private void checkNFC(){

        try {

            if (nfcAdapter == null) {

                // The app stops here since NFC is needed

                showPopup(HandlerActivity.this, "No NFC detected, app is closing.", "Proceed");

                return;

            }

            if (nfcAdapter.isEnabled()) {

            } else {

                // The app stops here since NFC is needed

                showPopup(HandlerActivity.this, "Please enable NFC first in the app settings," +
                        " app is closing.", "Proceed");

            }

        }

        catch(Exception err){
            showPopup(HandlerActivity.this, "NFC might be missing, app is closing.", "Proceed");
        }

    }


    //  A popup dialog showing warnings or notices

    private void showPopup(Activity activity, String message, String okString){

        AlertDialog.Builder popupMsg = new AlertDialog.Builder(activity);
        popupMsg.setTitle("Notice");
        popupMsg.setMessage(message);
        popupMsg.setNeutralButton(okString, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        popupMsg.show();

    }

}