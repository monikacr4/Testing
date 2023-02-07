@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.lilly.navigationtest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavigationGraphs(navController: NavHostController) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState =  BottomSheetState(BottomSheetValue.Collapsed))
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier.fillMaxWidth().height(200.dp).background(Color(0XFF0F9D58))
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { Text(text = "Hello Geek!", fontSize = 20.sp, color = Color.White) }
            }
        },
        sheetPeekHeight = 0.dp
    ){}

    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(BottomNavItem.Logbook.route) {
            LogBookScreen()
        }
        composable(BottomNavItem.LogItem.route) {
            coroutineScope.launch {
                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed){
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
                else{
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        }
        composable(BottomNavItem.Plan.route) {
            PlanScreen()
        }
        composable(BottomNavItem.Support.route) {
            SupportScreen()
        }
    }
}