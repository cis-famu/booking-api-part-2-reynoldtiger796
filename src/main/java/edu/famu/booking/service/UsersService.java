package edu.famu.booking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.booking.model.Hotels;
import edu.famu.booking.model.PaymentInformation;
import edu.famu.booking.model.Users;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UsersService {
    private Firestore firestore;

    public UsersService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    private Users documentSnapshotToUsers(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        Users users = null;
        if (document.exists()) {
            PaymentInformationService paymentInformationService = new PaymentInformationService();
            PaymentInformation paymentInformation = paymentInformationService.getPaymentInformation((DocumentReference) document.get("paymentInformation"));
            users = new Users(document.getId(), document.getString("name"), document.getString("email"), document.getString("phone"), paymentInformation, document.getTimestamp("createdAt"));
        }
        return users;

    }

    public ArrayList<Users> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = usersCollection.get();

        ArrayList<Users> usersList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            Users users = documentSnapshotToUsers(document);
            if (users != null)
                usersList.add(users);
        }
        return usersList;
    }

    public Users getUsersById(String userID) throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = usersCollection.document(userID).get();
        DocumentSnapshot document = future.get();

        return documentSnapshotToUsers(document);
    }
    public String createUsers(Users users) throws ExecutionException, InterruptedException {
        String userId = null;
        ApiFuture<DocumentReference> future = firestore.collection("Users").add(users);
        DocumentReference postRef = future.get();
        userId = postRef.getId();

        return userId;
    }

    public void updateUsers(String id, Map<String, String> updateValues)
    {
        String [] allowed = {"name", "email", "phone", "paymentInformation", "createdAt"};
        List<String> list = Arrays.asList(allowed);
        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, String> entry : updateValues.entrySet()){
            String key = entry.getKey();
            if(list.contains(key))
                formattedValues.put(key, entry.getValue());
        }
        DocumentReference UserDoc = firestore.collection("Users").document(id);
        if(UserDoc != null)
            UserDoc.update(formattedValues);
    }

    public void deleteUsers(String userId) {
        CollectionReference usersCollection = firestore.collection("Users");
        DocumentReference userDoc = usersCollection.document(userId);

        if (userDoc != null) {
            userDoc.delete();
        }
    }

}