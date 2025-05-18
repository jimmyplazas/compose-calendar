# Compose Calendar

![ComposeCalendar](https://github.com/jimmyplazas/compose-calendar/blob/main/img/banner.png)

A simple, customizable and easy-to-use calendar component built entirely with Jetpack Compose.
Designed to fit seamlessly into modern Android applications, this library offers a flexible
interface for displaying calendar events focused on simplicity and material design principles.

- **Easy to Use**: Simple integration into your application.
- **Customizable**: Modify the style and functionality to suit your needs.

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
kotlinxDatetime = "0.6.0"
composeCalendar = "{latest_version}"

[libraries]
kotlinx-datetime = { module = "org.jetbrains.kotlinx:kotlinx-datetime", version.ref = "kotlinxDatetime" }
compose-calendar = { module = "com.github.jimmyale3102:compose-calendar", version.ref = "composeCalendar" }
```

And then add the library to your `build.gradle.kts` file

```
implementation(libs.compose.calendar)
```

Replace the `{latest_version}`
with.  [![GitHub Release](https://img.shields.io/github/v/release/jimmyplazas/compose-calendar?label=ComposeCalendar&sort=semver)](https://github.com/jimmyplazas/compose-calendar/releases/latest)

## Documentation

```
@Composable
fun ComposeCalendar(
    modifier: Modifier = Modifier,
    initDate: LocalDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date.toInitDate(),
    events: List<CalendarEvent> = emptyList(),
    onDayClick: (date: LocalDate, events: List<CalendarEvent>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors = CalendarDefaults.calendarColors(),
    animatedBody: Boolean = true,
    onPreviousMonthClick: () -> Unit = {},
    onNextMonthClick: () -> Unit = {}
)
```

**Parameters**

- `modifier`: An optional parameter that allows you to apply styling or positioning to the calendar
  component.
- `LocalDate`: This parameter defines the initial date displayed in the calendar. By default, it
  sets the calendar to show the current date. You can specify any date you want the calendar to
  start from.
- `events`: A list of CalendarEvent objects that represent the events to be displayed in the
  calendar. If no events are provided, the calendar will initialize with an empty list.
- `onDayClick`: A callback function that is triggered when a day is clicked in the calendar.
  The function receives a LocalDate representing the clicked day and a list of CalendarEvents
  associated with that date. The list will be empty if there are no events for the selected day.
- `calendarColors`: This allows you to customize the color scheme of the calendar. If not specified,
  it will use a default color set defined by the calendarColors() function.
- `animatedBody`: Whether the body of the calendar (days grid) should animate during month transitions.
- `onPreviousMonthClick`: Callback invoked when the user navigates to the previous month.
- `onNextMonthClick:` Callback invoked when the user navigates to the next month.

**CalendarEvents**

The `CalendarEvent` class represents an event in the calendar. Each event includes information like
the title, optional description, date, and an associated icon.

```
data class CalendarEvent(
    val title: String? = null,
    val description: String? = null,
    val date: LocalDate,
    val icon: ImageVector? = null
)
```

## Example

```
ComposeCalendar(
    events = listOf(
        CalendarEvent(
            title = "Event 1",
            date = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault()).date,
            description = "Description 1",
            icon = Icons.Default.Star
        )
    ),
    calendarColors = calendarColors(
        eventBackgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
        eventContentColor = MaterialTheme.colorScheme.onSurface
    ),
    onDayClick = { date, events -> 
        /* Do Something */ 
    },
    onPreviousMonthClick = { 
        /* Do Something */
    },
    onNextMonthClick = {  
        /* Do Something */
    }
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

As you may know, the [library source](https://github.com/jahirfiquitiva/Blueprint/tree/master) is open-source. This means that you can fork it and do your own modifications, but it has some conditions:

When using the [library source](https://github.com/jahirfiquitiva/Blueprint/tree/master), anything from it: errors, crashes, issues, etc. including successful builds, must be done completely by yourself and under your own risk and responsibility. I **will not** provide any help/support when using the [library source](https://github.com/jahirfiquitiva/Blueprint/tree/master).

Finally, be sure your projects comply with the [license previously mentioned](https://github.com/jahirfiquitiva/Blueprint#license). Otherwise I will be taking the required legal actions. I hope you understand.
