# Client SDK Android API Envision

Goal of the project is to experiment with various API types, to be up to date with modern aAndroid development.
- Java interface (we have it, but it's not ideal)
- Android LiveData
- Kotlin function callback
- Kotlin Coroutines
- Kotlin FLow
- RxJava

For now this project is merely a wrapper around existing client SDK API, but usage of the
Kotlin FLow is very promising.

It would be nice to have multiple APIs and extract them into separate dependencies.

```
implementation 'com.nexmo.android:client-sdk-core:x.y.z' // All below dependencies depend on core

implementation 'com.nexmo.android:client-sdk-java:x.y.z'
implementation 'com.nexmo.android:client-sdk-livedata:x.y.z'
implementation 'com.nexmo.android:client-sdk-kotlin-callback:x.y.z'
implementation 'com.nexmo.android:client-sdk-kotlin-coroutine:x.y.z'
implementation 'com.nexmo.android:client-sdk-kotlin-flow:x.y.z'
implementation 'com.nexmo.android:client-sdk-kotlin-rxJava:x.y.z'
```

## Example - Load conversation events

Current approach:
- [Java](app/src/main/java/com/vonage/client/current/LoadConversationEventsActivityJava.java)
- [Kotlin](app/src/main/java/com/vonage/client/current/LoadConversationEventsActivityKotlin.kt)

Envisioned approach:
- [Kotlin Coroutines](app/src/main/java/com/vonage/client/envision/app/LoadConversattionEventsCoroutines.kt)
- [Kotlin Fow](app/src/main/java/com/vonage/client/envision/app/LoadConversattionEventsFlow.kt)