# Client SDK Android API Envision

Goal of the project is to experiment with various API types
- Java interface (we have it, but it's not ideal)
- Android LiveData
- Kotlin function callback
- Kotlin Coroutines
- Kotlin FLow
- RxJava

## Example - Load conversation events

Current approach:
- [Java](app/src/main/java/com/vonage/client/current/LoadConversationEventsActivityJava.java)
- [Kotlin](app/src/main/java/com/vonage/client/current/LoadConversationEventsActivityKotlin.kt)

Envisioned approach:
- [Kotlin Coroutines](app/src/main/java/com/vonage/client/envision/app/LoadConversattionEventsCoroutines.kt)
- [Kotlin FLow](app/src/main/java/com/vonage/client/envision/app/LoadConversattionEventsFlow.kt)
