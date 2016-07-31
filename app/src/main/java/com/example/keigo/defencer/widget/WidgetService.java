package com.example.keigo.defencer.widget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.example.keigo.defencer.R;

/**
 * Created by keigo on 2016/07/31.
 */
public class WidgetService extends Service {

    private final String button_click = "BUTTON_CLICK_ACTION";

    @Override
    public void onStart(Intent intent, int startId){
        super.onStart(intent, startId);

        Intent button = new Intent();
        button.setAction(button_click);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, button,0);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget);
        remoteViews.setOnClickPendingIntent(R.id.widget_multiply, pendingIntent);

        if (button_click.equals(intent.getAction())){
            remoteViews.setTextViewText(R.id.widgetSetName, "year");
        }

        ComponentName widget = new ComponentName(this, WidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(widget, remoteViews);
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
}
