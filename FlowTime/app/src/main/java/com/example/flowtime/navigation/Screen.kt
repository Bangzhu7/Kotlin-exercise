// File: com/example/flowtime/navigation/Screen.kt
package com.example.flowtime.navigation

sealed class Screen(val route: String, val title: String) {
    // 三个主屏幕的路由
    object Track : Screen("track_route", "追踪")
    object History : Screen("history_route", "历史")
    object Categories : Screen("categories_route", "分类")

    // 如果需要详情页，可以这样添加，但目前我们只需要这三个主屏幕
    // object Detail : Screen("detail_route/{id}", "详情") {
    //     fun createRoute(id: Int) = "detail_route/$id"
    // }
}