package com.example.keigo.defencer.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.keigo.defencer.Main.MainActivity;
import com.example.keigo.defencer.registration.InputActivity;
import com.jp.keigo.dial.R;

/**
 * Created by keigo on 2016/07/21.
 *
 */

public class WidgetProvider extends AppWidgetProvider {

    private int appWidgeteId;
    String num;
    String name;
    boolean times;
    private static String filter = "android.appwidget.action.APPWIDGET_UPDATE";

    @Override
    public void onEnabled(Context context){
        super.onEnabled(context);
        Log.d("check","onEnabled");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int[] appId) {
        super.onUpdate(context, manager, appId);
        Log.d("check","onUpdate");
        Log.d("check","context:"+context);
        Log.d("check","manager:"+manager);
        Log.d("check","appId:"+appId[0]);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent intent = new Intent(context, WidgetService.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget_multiply, pendingIntent);
        Log.d("check","onUpdate is end");
   }

    @Override
    public void onReceive(Context context, Intent intent){
        super.onReceive(context, intent);

        String action = intent.getAction();
        if (filter.equals(action)){
            Toast.makeText(context, "Widget Click now!! ", Toast.LENGTH_SHORT).show();
        }
        Log.d("check", "getAction :"+ action);
    }




    @Override
    public void onDeleted(Context context, int[] appWidgetId){
        super.onDeleted(context, appWidgetId);
        Log.d("check", "onDeleted");
    }

    @Override
    public void onDisabled(Context context){
        super.onDisabled(context);
        Log.d("check","onDisabled");
    }
}
