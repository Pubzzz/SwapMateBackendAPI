package com.example.SwapMateWeb.Service;

import com.example.SwapMateWeb.Model.Image;
import com.example.SwapMateWeb.Model.Stock;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class StockService {
    public static final String COL_NAME="Stock";

    public String createStock(Stock stock) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(stock.getSid()).set(stock);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Stock getStock(String document_id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(document_id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Stock stock = null;

        if(document.exists()) {
            stock = document.toObject(Stock.class);
            return stock;
        }else {
            return null;
        }
    }
    public List<Stock> getAllStock() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFirestore.collection(COL_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Stock> stockList=new ArrayList<>();
        Stock stock=null;

        while(iterator.hasNext()) {
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();

            stock = document.toObject(Stock.class);
            stockList.add(stock);
        }
        return stockList;
    }
    public String updateStock(Stock stock) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(stock.getSid()).set(stock);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteStock(String document_id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(document_id).delete();
        return "Document with Stock ID "+document_id+" has been deleted";
    }


    public String updateImages(String document_id, Image image)  throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(document_id).update("pictures", FieldValue.arrayUnion(image.getImage()));
        return "Document with Showroom ID "+document_id+" has been updated";
    }


}
