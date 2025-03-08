package com.example.layoutmobile3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CategoryScreen()
        }
    }
}

@Composable
fun CategoryScreen() {
    Scaffold(
        topBar = { TopBar(title = "Categories") },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF607D8B))
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CategoryGrid()
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    SmallTopAppBar(
        title = { Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF455A64),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun CategoryGrid() {
    val categories = listOf(
        "Woman" to R.drawable.woman,
        "Man" to R.drawable.man,
        "Baby" to R.drawable.baby,
        "Travel" to R.drawable.airplane,
        "Tech" to R.drawable.mobile,
        "Food & Drink" to R.drawable.lunch
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        for (row in categories.chunked(2)) { // Divide em pares (2 por linha)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Cada linha ocupa uma fração igual da tela
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (category in row) {
                    CategoryItem(
                        title = category.first,
                        iconId = category.second,
                        modifier = Modifier.weight(1f) // Cada botão ocupa espaço igual
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryItem(title: String, iconId: Int, modifier: Modifier) {
    ElevatedCard(
        modifier = modifier
            .fillMaxHeight()
            .padding(8.dp)
            .clickable { /* Ação ao clicar */ },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF78909C)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryScreen() {
    CategoryScreen()
}
