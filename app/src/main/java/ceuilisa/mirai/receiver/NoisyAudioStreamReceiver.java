package ceuilisa.mirai.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ceuilisa.mirai.MusicService;


/**
 * 来电/耳机拔出时暂停播放
 * Created by wcy on 2016/1/23.
 */
public class NoisyAudioStreamReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MusicService.get().stopOrPlay();
    }
}
