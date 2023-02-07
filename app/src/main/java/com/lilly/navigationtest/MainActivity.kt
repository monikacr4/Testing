package com.lilly.navigationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lilly.navigationtest.ui.theme.NavigationTestTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState =  BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    val systemUiController=
    BottomSheetScaffold(
        sheetBackgroundColor = Color.White,
        sheetElevation = dimensionResource(id = R.dimen.bottomNavElevation),
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.card),
            topEnd = dimensionResource(id = R.dimen.card)
        ),
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Column {
                BottomSheetItem(
                    text = stringResource(R.string.bottomSheet_symptom),
                    imageResource = R.drawable.logbook
                ) {}
                Divider(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.bottomSheetDivider)))
                BottomSheetItem(
                    text = stringResource(R.string.bottomSheet_accident),
                    imageResource = R.drawable.log_accident
                ) {
                    //onClick()
                    scope.launch {
                        state.hide()
                    }
                }
                Divider(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.bottomSheetDivider)))
                BottomSheetItem(
                    text = stringResource(R.string.bottomSheet_infusion),
                    imageResource = R.drawable.support
                ) {}
                Divider(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.bottomSheetDivider)))
                BottomSheetItem(
                    text = stringResource(R.string.bottomSheet_injection),
                    imageResource = R.drawable.plan
                ) {}
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
