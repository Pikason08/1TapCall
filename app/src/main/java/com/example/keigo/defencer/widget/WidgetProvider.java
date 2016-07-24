package com.example.keigo.defencer.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.keigo.defencer.R;
import com.example.keigo.defencer.registration.InputActivity;

/**
 * Created by keigo on 2016/07/21.
 *
 */

public class WidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int[] appId) {
        super.onUpdate(context, manager, appId);
        RemoteViews remoteviews = new RemoteViews(context.getPackageName(), R.layout.widget);
        SharedPreferences SaveData = context.getSharedPreferences("save",Context.MODE_PRIVATE);
        String num = SaveData.getString("importantNum", "ボタンを押すと登録できます");
        String name = SaveData.getString("importantName", "none");


        if (name.isEmpty()){
            Intent intent = new Intent(context, InputActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            remoteviews.setOnClickPendingIntent(R.id.widget_multiply, pendingIntent);
            manager.updateAppWidget(appId, remoteviews);
        }else {
            remoteviews.setTextViewText(R.id.widgetSetName, name);
            remoteviews.setOnClickPendingIntent(R.id.widget_multiply, clickCall(context,num));
        }


        pushWidgetUpdate(context, remoteviews);
    }

    public static PendingIntent clickCall(Context context, String number) {
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        intent.setAction("UPDATE_WIDGET");

        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews){
        ComponentName widget =  new ComponentName(context, WidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(widget, remoteViews);
    }
}
