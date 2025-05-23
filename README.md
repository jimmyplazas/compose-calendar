# Compose Calendar

![ComposeCalendar](https://github.com/jimmyplazas/compose-calendar/blob/main/img/banner.png)

A simple, customizable and easy-to-use calendar component built entirely with Jetpack Compose.
Designed to fit seamlessly into modern Android applications, this library offers a flexible
interface for displaying calendar events focused on simplicity and material design principles.

- **Easy to Use**: Simple integration into your application.
- **Customizable**: Modify the style and functionality to suit your needs.

![ComposeCalendar](https://github.com/jimmyplazas/compose-calendar/blob/main/img/cover.png)

Made with ❤️ by <a target="_blank" href="https://jimmyplazas.dev">Jimmy Plazas</a>

## Getting Started

Before to start, it is important you to know that this library works with `minSdk = 26`.

To integrate the Compose Calendar library into your Android app, follow the next steps:

1. Add your MavenCentral and Jitpack to you `settings.gradle.kts` file

```
dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()

        // 👇 Aquí agregas jitpack
        maven(url = "https://jitpack.io") {
            content {
                includeGroupByRegex("com\\.github\\..*")
            }
        }
    }
}
```

If you are using an older versión of Android Studio, then add MavenCentral and Jitpack to your root
build.gradle file

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri(path = "https://jitpack.io") }
    }
}
```
2. Add the dependency in your project `libs.versions.toml` file

```
[versions]
composeCalendar = "{latest_version}"

[libraries]
compose-calendar = { module = "com.github.jimmyplazas:compose-calendar", version.ref = "composeCalendar" }
```

And then add the library to your `build.gradle.kts` file

```
implementation(libs.compose.calendar)
```

Replace the `{latest_version}`
with [![GitHub Release](https://img.shields.io/github/v/release/jimmyplazas/compose-calendar?label=ComposeCalendar&sort=semver)](https://github.com/jimmyplazas/compose-calendar/releases/latest)

## Documentation

### SimpleComposeCalendar

The `SimpleComposeCalendar` is a lightweight Jetpack Compose calendar for monthly views with event
indicators. It offers customizable colors, month navigation, and day click handling with events,
designed for easy integration.

```
@Composable
fun SimpleComposeCalendar(
    modifier: Modifier = Modifier,
    initDate: LocalDate = LocalDate.now().withDayOfMonth(1),
    events: List<LocalDate> = emptyList(),
    onDayClick: (date: LocalDate, events: List<LocalDate>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors = CalendarDefaults.calendarColors(),
    firstDayOfWeek: DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek,
    eventIndicator: (@Composable (event: LocalDate, position: Int, count: Int) -> Unit)?,
    maxIndicators: CalendarDefaults.IndicatorLimit = CalendarDefaults.IndicatorLimit.Four,
    indicatorLayout: CalendarDefaults.IndicatorLayout = CalendarDefaults.IndicatorLayout.Row,
    isContentClickable: Boolean = true,
    onPreviousMonthClick: () -> Unit = {},
    onNextMonthClick: () -> Unit = {}
)
```

**Parameters**

- `modifier`: An optional parameter that allows you to apply styling or positioning to the 
 calendar component.
- `initDate`: Defines the initial date displayed in the calendar. By default, it shows the 
 first day of the current month. You can specify any date from which the calendar should start.
- `events`: A list of `LocalDate` objects representing the events to display on
 the calendar. If no events are provided, the calendar initializes with an empty list.
- `onDayClick`: A callback function triggered when a day is clicked. It receives the 
 clicked `LocalDate` and a list of `LocalDate`s for that date. The list will be
 empty if there are no events on the selected day.
- `calendarColors`: Allows customization of the calendar’s color scheme. If not specified, a default 
color set is applied.
- `firstDayOfWeek`: Specifies the first day of the week (e.g., `DayOfWeek.MONDAY`). By default, it
uses the locale’s first day.
- `eventIndicator`: An optional composable lambda that renders a custom visual indicator for each
  event. It receives the event `LocalDate`, its position in the list, and the total count of
  indicators for the day.
- `maxIndicators`: The maximum number of indicators to display per day. Defaults to
  `CalendarDefaults.IndicatorLimit.Four`.
- `indicatorLayout`: Defines how indicators are laid out (e.g., `Row` or `Column`). Defaults to
  `CalendarDefaults.IndicatorLayout.Row`.
- `isContentClickable`: Determines whether the calendar days are clickable. Defaults to `true`.
- `onPreviousMonthClick`: Callback invoked when navigating to the previous month.
- `onNextMonthClick`: Callback invoked when navigating to the next month.

### ComposeCalendar

The `ComposeCalendar` is a versatile Jetpack Compose calendar for monthly views with generic event
type support. It offers customizable colors, month navigation, and day click handling for easy integration. Ideal for complex
complex event data handling.

```
@Composable
fun <T> ComposeCalendar(
    modifier: Modifier = Modifier,
    initDate: LocalDate = LocalDate.now().withDayOfMonth(1),
    events: List<CalendarEvent<T>> = emptyList(),
    onDayClick: (date: LocalDate, events: List<CalendarEvent<T>>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors = CalendarDefaults.calendarColors(),
    firstDayOfWeek: DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek,
    eventIndicator: (@Composable (event: CalendarEvent<T>, position: Int, count: Int) -> Unit)?,
    maxIndicators: CalendarDefaults.IndicatorLimit = CalendarDefaults.IndicatorLimit.Four,
    indicatorLayout: CalendarDefaults.IndicatorLayout = CalendarDefaults.IndicatorLayout.Row,
    isContentClickable: Boolean = true,
    onPreviousMonthClick: () -> Unit = {},
    onNextMonthClick: () -> Unit = {}
)
```

**Parameters**

- `modifier`: An optional parameter that allows you to apply styling or positioning to the 
 calendar component.
- `initDate`: Defines the initial date displayed in the calendar. By default, it shows the 
 first day of the current month. You can specify any date from which the calendar should start.
- `events`: A list of `CalendarEvent` objects representing the events to display on
 the calendar. If no events are provided, the calendar initializes with an empty list.
- `onDayClick`: A callback function triggered when a day is clicked. It receives the 
 clicked `LocalDate` and a list of `CalendarEvent`s for that date. The list will be
 empty if there are no events on the selected day.
- `calendarColors`: Allows customization of the calendar’s color scheme. If not specified, a default 
color set is applied.
- `firstDayOfWeek`: Specifies the first day of the week (e.g., `DayOfWeek.MONDAY`). By default, it
uses the locale’s first day.
- `eventIndicator`: An optional composable lambda that renders a custom visual indicator for each
  event. It receives the event `CalendarEvent`, its position in the list, and the total count of
  indicators for the day.
- `maxIndicators`: The maximum number of indicators to display per day. Defaults to
  `CalendarDefaults.IndicatorLimit.Four`.
- `indicatorLayout`: Defines how indicators are laid out (e.g., `Row` or `Column`). Defaults to
  `CalendarDefaults.IndicatorLayout.Row`.
- `isContentClickable`: Determines whether the calendar days are clickable. Defaults to `true`.
- `onPreviousMonthClick`: Callback invoked when navigating to the previous month.
- `onNextMonthClick`: Callback invoked when navigating to the next month.


### CalendarEvents
The `CalendarEvent` class represents a calendar event.
It holds generic data and the event date.

```
data class CalendarEvent<T>(
    val data: T? = null,
    val date: LocalDate
)
```

**Parameters**

- `data`: Optional generic data of type `T` associated with the event. It can hold any relevant
 information related to the event.
- `date`: A `LocalDate` indicating the date on which the event occurs.


## Example

```
ComposeCalendar(
    events = state.events,
    onDayClick = { date, events -> 
        /* Do Something */ 
    },
    eventIndicator = { event, position, count ->
        if (position < 2) {
            IndicatorContent(Modifier.fillMaxWidth().height(8.dp))
        }
        if (position == 2) {
            Text(
                text = "+${count - 2}",
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            )
        }
    },
    maxIndicators = CalendarDefaults.IndicatorLimit.Three,
    indicatorLayout = CalendarDefaults.IndicatorLayout.Column,
    isContentClickable = false,
    onPreviousMonthClick = { 
        /* Do Something */ 
    },
    onNextMonthClick = { 
        /* Do Something */  
    }
)

@Composable
fun IndicatorContent(modifier: Modifier = Modifier, color: Color = Color.Black) {
    Box(modifier.clip(CircleShape).background(color))
}

private fun getListOfEvents() = listOf(
    CalendarEvent(
        data = MyData("Event 1"),
        date = java.time.LocalDate.now().plusDays(1)
    ),
    CalendarEvent(
        data = MyData("Event 2"),
        date = java.time.LocalDate.now().plusDays(1)
    ),
    CalendarEvent(
        data = MyData("Event 3"),
        date = java.time.LocalDate.now().plusDays(1)
    ),
    ...
)
```

## Contributing

Contributions are always welcome! To contribute, please follow these steps:

- Fork the repository.
- Create a new branch (git checkout -b feature/your-feature).
- Make your changes.
- Commit your changes (git commit -m 'Add some feature').
- Push to the branch (git push origin feature/your-feature).
- Open a pull request.

## License

This project is licensed under the terms of the [Apache License 2.0](./LICENSE).

```
   Copyright 2024 Jimmy Alejandro Plazas Lopez

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

## Library source

As you may know, the [library source](https://github.com/jimmyplazas/compose-calendar/tree/main) is
open-source. This means that you can fork it and do your own modifications, but it has some
conditions:

When using the [library source](https://github.com/jimmyplazas/compose-calendar/tree/main), anything
from it: errors, crashes, issues, etc. including successful builds, must be done completely by
yourself and under your own risk and responsibility. I **will not** provide any help/support when
using the [library source](https://github.com/jimmyplazas/compose-calendar/tree/main).

Finally, be sure your projects comply with
the [license previously mentioned](https://github.com/jimmyplazas/compose-calendar#license). Otherwise I
will be taking the required legal actions. I hope you understand.
