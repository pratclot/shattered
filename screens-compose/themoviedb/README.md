## TheMovieDB

How to run:

- create `secret.properties` in the root directory with this content:

```
API_MOVIES=actual-key-goes-here
API_SECRET_NEWS=this-one-is-for-another-screen
```

then run this:

```
./gradlew hideSecretFromPropertiesFile -PpropertiesFileName=secret.properties -Ppackage=com.pratclot
```

Features:

- runs `ktlint` and Unit tests thru GitHub Actions CI
- natively supports configuration changes via `ViewModel` use
- ViewModel unit tests are
  in `screens-compose/themoviedb/src/test/java/com/pratclot/themoviedb/MoviesViewModelTest.kt`
- done 100% in Compose (although UI tests do not even start, hehe)
- (for an example of UI tests with DI overrides, idling resource usage and a robot pattern please
  take a look at another screen
  here: `screens/news/src/androidTest/java/com/pratclot/MyNewsActivityTest.kt`)
- has a tested error animation implemented via `AnimatedVisibility`
- has a tested swipe-to-refresh feature
- there are `mock` and `live` variants available; the former reads a `json` file after switching to
  an `IO` dispatcher; also it is the one to be used for UI tests
- repo uses a DTO to Domain converter which is tested here:
  `common-kotlin/src/test/java/com/pratclot/themoviedb/StringToYearConverterTest.kt`
- `api-key` is added globally by an interceptor
