@file:Suppress("MagicNumber")

package com.statistics.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.statistics.core.resources.NoImageImg
import com.statistics.core.resources.PersonFemaleImg
import com.statistics.domain.models.File
import com.statistics.domain.models.User

@Composable
fun OftenVisitorsBlock(
    users: List<User>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
    ) {
        Column {
            users.forEachIndexed { index, user ->
                UserRow(user)
                if (index != users.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 80.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.background
                    )
                }
            }
        }
    }
}

@Composable
private fun UserRow(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.files.firstOrNull()?.url)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(
                    com.statistics.core.resources.R.string.user_avatar,
                    user.username
                ),
                placeholder = rememberVectorPainter(image = PersonFemaleImg),
                error = rememberVectorPainter(image = NoImageImg),
            )
            if (user.isOnline) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = 2.dp, y = 2.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onSecondary)
                )
            }
        }

        Spacer(Modifier.width(12.dp))

        Text(
            text = buildString {
                append(user.username)
                append(", ")
                append(user.age)
            },
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = com.statistics.core.resources.R.drawable.arrow_right_ic_24),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

@Suppress("StringLiteralDuplication", "MaxLineLength")
@Preview(
    showBackground = true,
    backgroundColor = 0xFFF6F6F6
)
@Composable
private fun OftenVisitorsBlockPreview() {
    val users = listOf(
        User(
            id = 1,
            age = 25,
            username = "ann.aeom",
            isOnline = true,
            sex = "W",
            files = listOf(
                File(
                    id = 1,
                    type = "image",
                    url = "https://img.freepik.com/free-photo/smiley-man-relaxing-outdoors_23-2148739334.jpg"
                )
            )
        ),
        User(
            id = 2,
            age = 23,
            username = "akimovahuiw",
            isOnline = false,
            sex = "M",
            files = listOf(
                File(
                    id = 2,
                    type = "image",
                    url = "https://img.freepik.com/free-photo/portrait-young-businesswoman-holding-eyeglasses-hand-against-gray-backdrop_23-2148029483.jp"
                )
            )
        ),
        User(
            id = 3,
            age = 32,
            username = "gulia.filova",
            isOnline = true,
            sex = "W",
            files = listOf(
                File(
                    id = 3,
                    type = "image",
                    url = "https://img.freepik.com/premium-photo/young-woman-smiles-while-walking-in-a-city-street-during-the-day_906809-27175.jpg"
                )
            )
        )
    )

    OftenVisitorsBlock(users = users, modifier = Modifier.padding(16.dp))
}
