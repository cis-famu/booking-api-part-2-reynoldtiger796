package edu.famu.booking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.booking.model.Hotels;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class HotelsService {
    private Firestore firestore;

    public HotelsService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    private Hotels documentSnapshotToHotels(DocumentSnapshot document)
    {
        Hotels hotels = null;
        if(document.exists()){
            ArrayList<String> amenities = (ArrayList<String>) document.get("amenities");
            hotels = new Hotels(document.getId(),document.getString("name"),document.getString("description"),document.getDouble("rating"),document.getString("address"), document.getString("contactInformation"),amenities, document.getTimestamp("createdAt"));
        }
        return hotels;

    }
    public ArrayList<Hotels> getAllHotels() throws ExecutionException, InterruptedException {
        CollectionReference hotelsCollection = firestore.collection("Hotels");
        ApiFuture<QuerySnapshot> future = hotelsCollection.get();

        ArrayList<Hotels> hotelsList = new ArrayList<>();

        for(DocumentSnapshot document: future.get().getDocuments())
        {
            Hotels hotels = documentSnapshotToHotels(document);
            if(hotels != null)
                hotelsList.add(hotels);
        }
        return hotelsList;
    }

    public Hotels getHotelsById(String hotelID) throws ExecutionException, InterruptedException {
        CollectionReference hotelsCollection = firestore.collection("Hotels");
        ApiFuture<DocumentSnapshot> future = hotelsCollection.document(hotelID).get();
        DocumentSnapshot document = future.get();

        return documentSnapshotToHotels(document);
    }

    public String createHotel(Hotels hotel) throws ExecutionException, InterruptedException {
        String hotelId = null;
        ApiFuture<DocumentReference> future = firestore.collection("Hotels").add(hotel);
        DocumentReference postRef = future.get();
        hotelId = postRef.getId();

        return hotelId;
    }

    public void updateHotel(String id, Map<String, Object> updateValues)
    {
        String [] allowed = {"name", "description", "rating", "address", "contactInformation", "amenities", "createdAt"};
        List<String> list = Arrays.asList(allowed);
        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, Object> entry : updateValues.entrySet()){
            String key = entry.getKey();
            if(list.contains(key))
                formattedValues.put(key, entry.getValue());
        }
        DocumentReference HotelDoc = firestore.collection("Hotels").document(id);
        if(HotelDoc != null)
            HotelDoc.update(formattedValues);
    }

    public void deleteHotels(String hotelId) {
        CollectionReference hotelsCollection = firestore.collection("Hotels");
        DocumentReference hotelDoc = hotelsCollection.document(hotelId);

        if (hotelDoc != null) {
            hotelDoc.delete();
        }
    }

}
