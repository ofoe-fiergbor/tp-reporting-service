package com.group19.reportingservice.service.firebase;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.group19.reportingservice.model.dto.MessageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MessagingService {

    public static final String COL_NAME="messages";

    public List<MessageDto> getMessages() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<MessageDto> messageDtoList = new ArrayList<>();

        for (QueryDocumentSnapshot doc: documents) {
            messageDtoList.add(doc.toObject(MessageDto.class));
        }

        return messageDtoList;

    }


//        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document();
//
//        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException e) {
//                if (e != null) {
//                    System.err.println("Listen failed: " + e);
//                    return;
//                }
//
//                if (snapshot != null && snapshot.exists()) {
//                    System.err.println("Current data: " + snapshot.getData());
//                    documentReference.delete();
//                } else {
//                    System.out.print("Current data: null");
//                }
//            }
//        });



}
