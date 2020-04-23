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

## Example - Load conversation events

Current approach:
- [Java](app/src/main/java/com/vonage/client/current/LoadConversationEventsActivityJava.java)
- [Kotlin](app/src/main/java/com/vonage/client/current/LoadConversationEventsActivityKotlin.kt)

Envisioned approach:
- [Kotlin Coroutines](app/src/main/java/com/vonage/client/envision/app/LoadConversattionEventsCoroutines.kt)
- [Kotlin FLow](app/src/main/java/com/vonage/client/envision/app/LoadConversattionEventsFlow.kt)
