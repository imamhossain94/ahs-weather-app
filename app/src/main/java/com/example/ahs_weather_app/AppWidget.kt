package com.example.ahs_weather_app

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.orhanobut.hawk.Hawk

/**
 * Implementation of App Widget functionality.
 */
class AppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

//Widget will update every 30 minutes

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {


    Hawk.init(context).build()
    val status: String = Hawk.get("status")
    val temp: String = Hawk.get("temp")
    val tempMaxMin: String = Hawk.get("temp_max_min")

    val views = RemoteViews(context.packageName, R.layout.app_widget)
    views.setTextViewText(R.id.status, status)
    views.setTextViewText(R.id.temp, temp)
    views.setTextViewText(R.id.temp_min_max, tempMaxMin)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}