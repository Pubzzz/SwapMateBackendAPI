package com.example.SwapMateWeb.Service;

import com.example.SwapMateWeb.Model.Order;
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
public class OrderService {
    public static final String COL_NAME="Order";

    public String createOrder(Order order) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(order.getOid()).set(order);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Order getOrder(String document_id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(document_id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Order order = null;

        if(document.exists()) {
            order = document.toObject(Order.class);
            return order;
        }else {
            return null;
        }
    }
    public List<Order> getAllOrder() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFirestore.collection(COL_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Order> orderList=new ArrayList<>();
        Order order=null;

        while(iterator.hasNext()) {
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();

            order = document.toObject(Order.class);
            orderList.add(order);
        }
        return orderList;
    }
    public String updateOrder(Order order) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(order.getOid()).set(order);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteOrder(String document_id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(document_id).delete();
        return "Document with Stock ID "+document_id+" has been deleted";
    }

}
