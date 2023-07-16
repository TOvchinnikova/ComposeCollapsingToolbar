package com.tovchinnikova.composecollapsingtoolbar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.tovchinnikova.composecollapsingtoolbar.ui.theme.colors

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionAppBar(
    lazyScrollState: LazyListState
) {

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }

    val progress by animateFloatAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex in 0..1) 0f else 1f,
        tween(500)
    )
    val motionHeight by animateDpAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex in 0..1) 112.dp else 56.dp,
        tween(500)
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(motionHeight)
        ) {

            val boxProperties = motionProperties(id = "box")
            val roundedShape = RoundedCornerShape(
                bottomStart = boxProperties.value.int("roundValue").dp,
                bottomEnd = boxProperties.value.int("roundValue").dp
            )

            Box(
                modifier = Modifier
                    .layoutId("box")
                    .clip(roundedShape)
                    .background(
                        brush = Brush.verticalGradient(
                            colors,
                            endY = 350f
                        )
                    )
            )

            Image(
                modifier = Modifier.layoutId("back_button"),
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White)
            )

            Text(
                text = "Title",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.layoutId("title")
            )
            Image(
                modifier = Modifier.layoutId("delete_button"),
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White)
            )

            BasicTextField(
                value = "",
                onValueChange = {  },
                modifier = Modifier
                    .layoutId("search")
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(10.dp)
            )
        }
    }
}