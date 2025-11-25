// File: com/example/flowtime/MainActivity.kt

package com.example.flowtime
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List // ⬅️ 添加这一行
//import androidx.compose.material.icons.filled.List
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.flowtime.navigation.Screen
import com.example.flowtime.screens.CategoriesScreen
import com.example.flowtime.screens.HistoryScreen
import com.example.flowtime.screens.TrackScreen
import com.example.flowtime.ui.theme.FlowTimeTheme // 确保主题文件存在

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowTimeTheme {
                // 应用的入口点
                MainAppScreen()
            }
        }
    }
}

// 定义底部导航栏的项目
data class NavItem(
    val screen: Screen,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    NavItem(Screen.Track, Icons.Filled.Timer),
//    NavItem(Screen.History, Icons.Filled.List),
    NavItem(Screen.History, Icons.AutoMirrored.Filled.List), // ✅ 已经修复
    NavItem(Screen.Categories, Icons.Filled.Settings)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavItems.forEach { item ->
                    val selected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true

                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.screen.title) },
                        label = { Text(item.screen.title) },
                        selected = selected,
                        onClick = {
                            navController.navigate(item.screen.route) {
                                // 避免在导航到同一个项时创建多个相同的实例
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // 避免重复创建屏幕
                                launchSingleTop = true
                                // 重新选择时恢复状态
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // NavHost 负责根据路由显示正确的 Composable
        NavHost(
            navController = navController,
            startDestination = Screen.Track.route, // 默认启动页面为追踪页
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Track.route) { TrackScreen() }
            composable(Screen.History.route) { HistoryScreen() }
            composable(Screen.Categories.route) { CategoriesScreen() }
        }
    }
}