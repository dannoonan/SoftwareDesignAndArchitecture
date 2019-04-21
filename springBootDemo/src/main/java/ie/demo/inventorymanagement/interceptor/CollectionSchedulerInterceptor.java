package ie.demo.inventorymanagement.interceptor;

import ie.demo.inventorymanagement.Pair;
import ie.demo.inventorymanagement.ReturnContext;

import java.util.Map;

// Concrete interceptor
// Due to the fact there is specific functionality
// associated with this interceptor it can only work with a ReturnContext object
public class CollectionSchedulerInterceptor implements Interceptor<ReturnContext> {

    @Override
    public void execute(ReturnContext context) {
        if (context.getNodeId() != null) return;
        if (context.getLatitude() == null || context.getLongitude() == null) return;

        int nearestNode = getNearestNode(context);

        context.addBikeDueForCollection(nearestNode);
        if (context.getBikesDueForCollections(nearestNode).size() > 10){
            // TODO: schedule collection.
        }
    }

    private int getNearestNode(ReturnContext context){
        Map<Integer, Pair<Double,Double>> locations = context.getNodeLocations();
        int nearestNode = -1;
        double nearestDistance = Integer.MAX_VALUE;
        double latitude = context.getLatitude();
        double longitude = context.getLongitude();

        for (Integer nodeId : locations.keySet()) {
            double lat = locations.get(nodeId).getFirst();
            double lon = locations.get(nodeId).getSecond();
            double distance = distance(latitude, longitude, lat, lon, 0, 0);
            if (distance < nearestDistance){
                nearestDistance = distance;
                nearestNode = nodeId;
            }
        }
        return nearestNode;
    }

    private double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
