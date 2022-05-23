package com.example.SwapMateWeb.Service;

import com.example.SwapMateWeb.Model.Donation;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class DonationService {
    public static final String COL_NAME="Donation";

    public String createDonation(Donation donation) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(donation.getDid()).set(donation);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Donation getDonation(String document_id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(document_id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Donation donation = null;

        if(document.exists()) {
            donation = document.toObject(Donation.class);
            return donation;
        }else {
            return null;
        }
    }
    public List<Donation> getAllDonation() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFirestore.collection(COL_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Donation> donationList=new ArrayList<>();
        Donation donation=null;

        while(iterator.hasNext()) {
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();

            donation = document.toObject(Donation.class);
            donationList.add(donation);
        }
        return donationList;
    }
    public String updateDonation(Donation donation) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(donation.getDid()).set(donation);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteDonation(String document_id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(document_id).delete();
        return "Document with Stock ID "+document_id+" has been deleted";
    }

}


