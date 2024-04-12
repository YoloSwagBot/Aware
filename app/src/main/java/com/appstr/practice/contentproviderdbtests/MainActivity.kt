package com.appstr.practice.contentproviderdbtests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appstr.practice.contentproviderdbtests.ui.theme.ContentProviderDBTestsTheme
import com.appstr.practice.contentproviderdbtests.ui.theme.appstrColor_0
import com.appstr.practice.contentproviderdbtests.ui.theme.appstrColor_1
import com.appstr.practice.contentproviderdbtests.ui.theme.appstrColor_2
import com.appstr.practice.contentproviderdbtests.ui.theme.appstrColor_4
import com.appstr.practice.contentproviderdbtests.ui.theme.appstrColor_5
import com.appstr.practice.contentproviderdbtests.ui.theme.blueGrey_500
import com.appstr.practice.contentproviderdbtests.ui.theme.white

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ContentProviderDBTestsTheme {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(white)
                ) {
                    val maxWidth = maxWidth
                    val maxHeight = maxHeight
                    FeedList(listOf())
                    Toolbar(maxWidth)
                }
            }
        }
        contentResolver
    }
}

@Composable
fun Toolbar(
    maxWidth: Dp,

    mainVM: MainVM = viewModel()
){
    val toolbarHeight = 56.dp
    val iconSizes = 56.dp

    Row(
        modifier = Modifier
            .width(maxWidth)
            .height(toolbarHeight)
            .background(appstrColor_2),
//            .shadow(elevation = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Icon(
            modifier = Modifier
                .size(iconSizes)
                .padding(10.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false, color = appstrColor_4),
                    onClick = {
                        mainVM.onClickQueryDevice()
                    }
                ),
            painter = painterResource(id = R.drawable.ic_query_0),
            contentDescription = "Search device.",
            tint = appstrColor_0
        )
        Icon(
            modifier = Modifier
                .size(iconSizes)
                .padding(10.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false, color = appstrColor_4),
                    onClick = {
                        mainVM.onClickDeleteItems()
                    }
                ),
            painter = painterResource(id = R.drawable.ic_delete_0),
            contentDescription = "Delete list.",
            tint = appstrColor_0
        )
    }

}

@Composable
fun FeedList(
    items: List<String>,

    mainVM: MainVM = viewModel()
){

    if (items.isEmpty()){
        EmptyFeedItem()
    }else{
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(items){ idx, item ->
                ListItem(idx, item)
            }
        }
    }

}

@Composable
fun ListItem(pos: Int, item: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = (16.dp).takeUnless { pos == 0 } ?: 80.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
            .shadow(elevation = 12.dp)
            .background(white)
    ){
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp),
            text = item,
            fontSize = 30.sp
        )
    }
}

@Composable
fun EmptyFeedItem(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ){
        Icon(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.TopStart)
                .offset(x = 16.dp, y = 56.dp),
            painter = painterResource(id = R.drawable.ic_arrow_nw),
            contentDescription = "Click to query.",
            tint = appstrColor_1
        )
        // "Query your device!
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            text = "Query your device!",
            fontSize = 32.sp,
        )
    }
}


