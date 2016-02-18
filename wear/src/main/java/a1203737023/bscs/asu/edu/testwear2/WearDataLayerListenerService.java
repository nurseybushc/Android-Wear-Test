package a1203737023.bscs.asu.edu.testwear2;

import android.content.Intent;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by apsMac1 on 2/18/16.
 */
public class WearDataLayerListenerService extends WearableListenerService {
    public static final String START_ACTIVITY_PATH = "/start/MainActivity";
    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);
        if(messageEvent.getPath().equals(START_ACTIVITY_PATH)){
            Intent intent = new Intent(this , MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}