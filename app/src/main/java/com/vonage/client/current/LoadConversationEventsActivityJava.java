package com.vonage.client.current;

import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.nexmo.client.*;
import com.nexmo.client.request_listener.NexmoApiError;
import com.nexmo.client.request_listener.NexmoRequestListener;
import timber.log.Timber;

import java.util.Collection;

public class LoadConversationEventsActivityJava extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        NexmoClient client = NexmoClient.get();
        client.login("JWT token");

        client.getConversation("CONVERSATION_ID", new NexmoRequestListener<NexmoConversation>() {
            @Override
            public void onSuccess(@Nullable NexmoConversation conversation) {
                Timber.d("Conversation loaded");
                conversation.getEvents(100, NexmoPageOrder.NexmoMPageOrderAsc, new NexmoRequestListener<NexmoEventsPage>() {
                    @Override
                    public void onSuccess(@Nullable NexmoEventsPage eventsPage) {
                        Collection<NexmoEvent> pageData = eventsPage.getPageResponse().getData();
                    }

                    @Override
                    public void onError(@NonNull NexmoApiError apiError) {
                        Timber.d("Error: Unable to load conversation events %s", apiError.getMessage());
                    }
                });
            }

            @Override
            public void onError(@NonNull NexmoApiError apiError) {
                Timber.d("Error: Unable to load conversation %s", apiError.getMessage());
            }
        });

    }
}
