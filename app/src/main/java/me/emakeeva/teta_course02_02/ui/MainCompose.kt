package me.emakeeva.teta_course02_02.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.emakeeva.teta_course02_02.R
import me.emakeeva.teta_course02_02.model.CalendarModel
import me.emakeeva.teta_course02_02.utils.month
import me.emakeeva.teta_course02_02.ui.theme.Green200
import me.emakeeva.teta_course02_02.ui.theme.Typography

private const val DAY_IN_WEEK_COUNT = 7

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(calendar: CalendarModel, onClick: () -> Unit) {
    val weeks = listOf("пн", "вт", "ср", "чт", "пт", "сб", "вс")

    Column(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MonthHeader(title = "${calendar.month} ${calendar.year}")
        LazyVerticalGrid(cells = GridCells.Fixed(DAY_IN_WEEK_COUNT)) {
            items(weeks) { item ->
                Text(
                    text = item.uppercase(),
                    style = Typography.body2.copy(textAlign = TextAlign.Center),
                )
            }

            items(calendar.days.size + calendar.startIndex) { index ->
                if (index >= calendar.startIndex) {
                    // немного колхозненько, но времени не хватило придумать получше :)
                    val dayIndex = index - calendar.startIndex
                    Day(
                        day = calendar.days[dayIndex].toString(),
                        isActive = calendar.days[dayIndex] == calendar.activeDayState.value
                    ) {
                        calendar.activeDayState.value = calendar.days[dayIndex]
                    }
                } else {
                    Box {}
                }
            }
        }

        Button(
            enabled = calendar.activeDayState.value != -1,
            modifier = Modifier
                .padding(top = 42.dp)
                .width(282.dp),
            onClick = { onClick.invoke() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
                disabledBackgroundColor = Green200
            )
        ) {
            Text("Открыть расписание", style = Typography.body1.copy(color = Color.White))
        }
    }
}

@Composable
fun Schedule(day: Int, schedule: List<String>) {
    LazyColumn(modifier = Modifier.padding(horizontal = 22.dp)) {
        item {
            Text(
                text = "Планы на $day число",
                style = Typography.h3,
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
            )
        }

        items(schedule) { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bullet),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 12.dp),
                    tint = Color.Unspecified
                )
                Text(item, style = Typography.h3.copy(fontWeight = FontWeight.Normal))
            }
        }
    }
}

@Composable
private fun Day(day: String, isActive: Boolean, onClick: () -> Unit) {
    Text(
        style = Typography.h3.copy(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            color = if (isActive) Color.Red else Color.Black
        ),
        text = day,
        modifier = Modifier
            .padding(8.dp)
            .height(24.dp)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                onClick.invoke()
            }
    )
}

@Composable
private fun MonthHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = Typography.h3.copy(textAlign = TextAlign.Center),
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_arrows),
            contentDescription = "switch",
            modifier = Modifier.padding(start = 2.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "arrow left",
            tint = Color.Unspecified,
            modifier = Modifier.padding(end = 8.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "arrow right",
            tint = Color.Unspecified
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalendarPreview() {
    Calendar(CalendarModel((1..31).toList(), 8.month(), 2022, 0)) {}
}