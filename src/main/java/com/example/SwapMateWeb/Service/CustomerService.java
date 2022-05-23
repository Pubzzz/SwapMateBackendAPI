package com.example.SwapMateWeb.Service;
import com.example.SwapMateWeb.Model.Customer;
import com.example.SwapMateWeb.Model.Order;
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
public class CustomerService {
    public static final String COL_NAME="Customer";

    public String createCustomer(Customer customer) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document().set(customer);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Customer getCustomer(String email) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        List<Customer> customers=new ArrayList<>();
        ApiFuture<QuerySnapshot> documentReference = dbFirestore.collection(COL_NAME).whereEqualTo("email",email).get();
        List<QueryDocumentSnapshot> documents=documentReference.get().getDocuments();

        List<Customer> all ;
        Customer [] a=new Customer[1];

        if(!documents.isEmpty()){
            for(DocumentSnapshot doc:documents){

                Customer customer=doc.toObject(Customer.class);
                customer.setId(doc.getReference().getId());
                customers.add(customer);
            }
        }

        if(customers.isEmpty()){
            return null;
        }
        else{
            return customers.get(0);
        }

    }
    public List<Customer> getAllCustomer() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFirestore.collection(COL_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Customer> customerList=new ArrayList<>();


        while(iterator.hasNext()) {
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();

            Customer cust=document.toObject(Customer.class);
            DocumentReference reference = document.getReference();

            cust.setId(reference.getId());
            customerList.add(cust);

        }
        return customerList;
    }
    public String updateCustomer(Customer customer) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(customer.getId()).set(customer);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteCustomer(Customer customer) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(customer.getId()).delete();
        return "Document with Customer ID "+customer.getId()+" has been deleted";
    }
}

