package com.lilly.navigationtest

sealed class BottomNavItem(var route: String, var label: String, var icons: Int) {
    object Home : BottomNavItem(ROUTE_HOME, LABEL_HOME, R.drawable.home)
    object Logbook : BottomNavItem(ROUTE_LOGBOOK, LABEL_LOGBOOK, R.drawable.logbook)
    object LogItem : BottomNavItem(ROUTE_LOGITEM, EMPTY_STRING, R.drawable.log_accident)
    object Plan : BottomNavItem(ROUTE_PLAN, LABEL_PLAN, R.drawable.plan)
    object Support : BottomNavItem(ROUTE_SUPPORT, LABEL_SUPPORT, R.drawable.support)
}
