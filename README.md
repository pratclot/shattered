## Shattered Android Project

The project showcases an Android app that is totally split into Gradle subprojects - each feature
consists of the usual `Clean architecture / MVVM` layers, and **each** of these is represented by a
separate Gradle subproject. In the end this should improve build times due to subprojects'
decoupling (it already does for UI tests for example), and probably bring some other good to this
world.

The goal for me is to hone my skills and improve / reuse the code I produce over the years.

## Project structure

project|type|what does
-|-|-
app|com.android.application|keeps the App class... that's it!
screens-compose:entrypoint|com.android.library|hosts navigation logic and provides access to all other screens
========================|========================|========================
screens-compose:steamstore|com.android.library|shows items from store.steampowered.com
screens:news|com.android.library|shows news from currentsapi.services (requires auth)
usecase:steamstore|java-library|performs business transformations of data
usecase:news|java-library|--
repository:steamstore|java-library|governs access to external API's
repository:news|java-library|--
api-live|java-library|live implementation for the above
api-mock|java-library|mock one
api-common|java-library|stores interfaces for external APIs, maybe should be also split into multiple projects
domain|java-library|keeps data classes used by the app
dto|java-library|describes objects coming from the backend
common-android|com.android.library|reusable classes dependent on Android framework
common-compose|com.android.library|reusable classes related to Jetpack Compose only
common-kotlin|java-library|reusable classes that do not dependent on Android framework
common-test-android|com.android.library|reusable test classes dependent on Android framework

## Dev Tips

The project is organized in a way to simplify adding new features and reusing all available common
code, including Gradle configurations. This comes with a cost - the root `build.gradle` is not so
readable anymore, haha! On the other hand, newly added subprojects would **automatically** be linked to
common dependencies and take their place in the `Clean` architecture. This is intended to let
developer concentrate on the code rather than on project structure.

The project is completely split into Gradle subprojects, i.e. each UI feature occupies a separate
module and **NOT** a package. The root `build.gradle` script automatically configures subprojects
with these assumptions:

- if the path to subproject starts with `:usecase:` (i.e. it lies inside a `usecase` directory) make
  it depend on certain subprojects that a typical `usecase` would need (like `repository` and `domain`)
- the same for `:screens` - those are implied to be Android libraries, additionally
  `:screens-compose:` directory holds features with Jetpack Compose
- depending on the selected variant to be built (say, `live`), excludes it's alternative version (in
  this case `mock`) in `settings.gradle`, so that the final code will not have any mention of
  unneeded project. This is done per-feature, but currently only works if you pass **ONE** task to
  build (which is a normal use on a local dev machine, will sure not work well for CI systems).

Each `screen` defines their own internal UI data, which is derived from `domain` objects.

To generate compose compiler reports do this:

```bash
./gradlew -Pmyapp.enableComposeCompilerReports=true assembleRelease --rerun-tasks
```

To work with secrets (`common-android` is used):

```bash
./gradlew hideSecretFromPropertiesFile -PpropertiesFileName=secret.properties -Ppackage=com.pratclot
```

To solve R8 / minify / obfuscation issues:

- use `debugMinify` build variant to see the exact failure in the logs
- find the problematic class via `Analyze APK...` in AS
- the simplest way to fix is to right-click it, select `Generate Proguard keep rule`, take the first
  rule and apply it in `app/proguard-rules.pro` (I did not figure how to store these rules with the
  subproject that holds deleted classes)
- run the new version and see if it gives another error message (it should)

To figure out Gradle task dependencies (other tasks):

```bash
./gradlew testNewsMockSteamstoreMockDebugUnitTest --dry-run
```

To see Gradle configuration dependencies (jars or projects):

```bash
./gradlew :usecase:news:dependencies --configuration implementation
```

## TODO

- consider [this](https://github.com/raamcosta/compose-destinations) for navigation
- disable Jetifier
- add baseline profile
- invent something to not duplicate `Retrofit` init code in Dagger modules
- disable logging in OkHttpClient for non-debug builds (have to do some Gradle magic again)
- fix `hideSecretFromPropertiesFile` - it does not copy `.cpp` files properly, forgets the package
  name
- add paging support to `news`
- consider extracting build config to an opinionated Android clean arch Gradle plugin, that will
  organize the subprojects for any other app the same way, plus will add some extensibility
- add an obfuscated release to GitHub
- list used libs
- install Hilt modules in scopes smaller than `SingletonComponent`
- implement a common file resource reader for mock variants
