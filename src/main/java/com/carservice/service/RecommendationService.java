package com.carservice.service;

import com.carservice.entity.Car;
import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.AddDetailView;
import com.recombee.api_client.api_requests.Batch;
import com.recombee.api_client.api_requests.RecommendItemsToUser;
import com.recombee.api_client.api_requests.Request;
import com.recombee.api_client.bindings.Recommendation;
import com.recombee.api_client.bindings.RecommendationResponse;
import com.recombee.api_client.exceptions.ApiException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationService {

    public Set<Long> recommendation(List<Car> cars) {
        RecombeeClient client = new RecombeeClient("teb-dev", "mHSVzMswqCcNQ5ZGa9au2bk7PcqVcggMQtBAAfdtHzv1vg5bekuQSolxI6fLz6Qc");

        ArrayList<Request> interactions = new ArrayList<>();

        for (Car car : cars) {
            Map<String, Object> additionalData = new HashMap<>();
            additionalData.put("model", car.getModel());
            additionalData.put("brand", car.getBrand());
            additionalData.put("sold", car.isSold());

            Request r = new AddDetailView("1", car.getId().toString())
                    .setTimestamp(new Date())
                    .setCascadeCreate(true)
                    .setAdditionalData(additionalData);

            interactions.add(r);
        }

        try {
            client.send(new Batch(interactions));

            System.out.println("Items related for \"user 1\":");

            RecommendationResponse recommended = client.send(new RecommendItemsToUser("1", 5));
            Set<Long> recIds = new HashSet<>();
            for (Recommendation rec : recommended) {
                String id = rec.getId();
                System.out.println(id);
                recIds.add(Long.valueOf(id));
            }

            return recIds;
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
