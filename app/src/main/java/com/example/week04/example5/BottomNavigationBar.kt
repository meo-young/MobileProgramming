package com.example.week04.example5

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.week04.example5.NavBarItems

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomNavigationBar(navController: NavController) {

    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route //현재 선택된 루트에 대한 정보를 저장

        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true //inclusive 쓰면 포함하는 관계를 만들어줌. 지금은 없으니까 home 한 번의 기록은 남음
                        }
                        launchSingleTop = true
                        restoreState = true //state 저장하지 않으면 화면의 변수값들이 초기값으로 초기화됨
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == navItem.route) navItem.onSelectedIcon else navItem.selectIcon,
                        contentDescription = navItem.title
                    )
                },
                label = { //Icon 밑의 글씨 표시
                    Text(text = navItem.title)
                }
            )
        }
    }
}

