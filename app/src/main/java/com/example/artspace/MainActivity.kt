package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {

    var artworkId by remember { mutableIntStateOf(0) }
    var imageId = 0
    var artTitleId = 0
    var quoteId = 0
    var titleId = 0

    when (artworkId) {
        0 -> {
            imageId = R.drawable.lost_carcosa_2
            artTitleId = R.string.the_king_in_yellow_art_title
            quoteId = R.string.the_king_in_yellow_quote
            titleId = R.string.the_king_in_yellow_source_title
        }
        1 -> {
            imageId = R.drawable.the_core
            artTitleId = R.string.undertale_art_title
            quoteId = R.string.undertale_quote
            titleId = R.string.undertale_source_title
        }
        2 -> {
            imageId = R.drawable.the_library_2
            artTitleId = R.string.the_library_of_babel_art_title
            quoteId = R.string.the_library_of_babel_quote
            titleId = R.string.the_library_of_babel_source_title
        }
        3 -> {
            imageId = R.drawable.the_house
            artTitleId = R.string.piranesi_art_title
            quoteId = R.string.piranesi_quote
            titleId = R.string.piranesi_source_title
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
            .pointerInput(Unit) {
                detectTapGestures { offset: Offset ->
                    val width = size.width
                    if (offset.x < width / 2) {
                        artworkId--
                        if (artworkId < 0) artworkId = 3
                    } else {
                        artworkId++
                        if (artworkId > 3) artworkId = 0
                    }
                }
            },
    ) {
        if (maxWidth < maxHeight) {
            var artFrameWidth by remember { mutableIntStateOf(0) }

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .safeDrawingPadding()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                ArtFrame(
                    imageId,
                    "",
                    modifier = modifier.onGloballyPositioned { coordinates -> artFrameWidth = coordinates.size.width }
                )
                Spacer(modifier = Modifier.height(12.dp))
                ArtPlaque(
                    artTitleId = artTitleId,
                    quoteId = quoteId,
                    titleId = titleId,
                    modifier = modifier
                        .width(with(LocalDensity.current) {artFrameWidth.toDp()})
                )
            }
        } else {
            var artFrameWidth by remember { mutableIntStateOf(0) }
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .safeDrawingPadding()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ArtFrame(
                    imageId,
                    "",
                    modifier = modifier.onGloballyPositioned { coordinates -> artFrameWidth = coordinates.size.width }
                )
                Spacer(modifier = Modifier.height(12.dp))
                ArtPlaque(
                    artTitleId = artTitleId,
                    quoteId = quoteId,
                    titleId = titleId,
                    modifier = modifier
                        .width(with(LocalDensity.current) {artFrameWidth.toDp()})
                )
            }
        }
    }
}

@Composable
fun ArtFrame(imageId: Int, contentDescription: String, modifier: Modifier = Modifier) {
    Surface(
        tonalElevation = 2.dp,
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = contentDescription,
            modifier = modifier
                .padding(24.dp)
        )
    }
}

@Composable
fun ArtPlaque(artTitleId: Int, quoteId: Int, titleId: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.wrapContentWidth(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = modifier,
            tonalElevation = 38.dp,
            shadowElevation = 4.dp,
        ) {
            Column(
                modifier = modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom

            ) {
                Text(
                    text = stringResource(id = artTitleId),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left,
                    modifier = modifier.fillMaxWidth().padding(start = 15.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "\" ${stringResource(id = quoteId)} \"",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "-  ${stringResource(id = titleId)}",
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth().padding(end = 15.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview(modifier: Modifier = Modifier) {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}