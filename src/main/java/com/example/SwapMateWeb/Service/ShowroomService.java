package com.example.SwapMateWeb.Service;

import com.example.SwapMateWeb.Model.Image;
import com.example.SwapMateWeb.Model.Showroom;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class ShowroomService {
    public static final String COL_NAME="Showroom";

    public String createShowroom(Showroom showroom) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(showroom.getSrid()).set(showroom);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Showroom getShowroom(String document_id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(document_id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Showroom showroom = null;

        if(document.exists()) {
            showroom = document.toObject(Showroom.class);
            return showroom;
        }else {
            return null;
        }
    }
    public List<Showroom> getAllShowroom() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFirestore.collection(COL_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Showroom> showroomList=new ArrayList<>();
        Showroom showroom=null;

        while(iterator.hasNext()) {
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();

            showroom = document.toObject(Showroom.class);
            showroomList.add(showroom);
        }
        return showroomList;
    }
    public String updateShowroom(Showroom showroom) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(showroom.getSrid()).set(showroom);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteShowroom(String document_id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(document_id).delete();
        return "Document with Customer ID "+document_id+" has been deleted";
    }


    public String updateImages(String document_id, Image image)  throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(document_id).update("pictures", FieldValue.arrayUnion(image.getImage()));
        return "Document with Showroom ID "+document_id+" has been updated";
    }


}

