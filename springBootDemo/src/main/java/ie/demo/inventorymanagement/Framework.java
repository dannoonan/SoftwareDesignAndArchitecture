package ie.demo.inventorymanagement;


import javafx.util.Pair;

import java.util.Collection;
import java.util.List;
import java.util.Map;

// Concrete framework
public class Framework {
    private static Framework instance = null;

    // When a bike is returned we will assign it to be collected and
    // sent in the nearest node. Here we store the destination nodes
    // as keys which are mapped to the list of bikeIds that must be sent to that node.
    private Map<Integer, List<Integer>> bikesDueForCollection;
    private Map<Integer, Pair<Double,Double>> nodeLocations;

    private Collection<Dispatcher<Context>> dispatchers;

    public Framework(){

    }

    public static Framework getInstance(){
        if(instance == null){
            instance = new Application().buildFramework();
            return instance;
        }
        return instance;
    }

    public List<Integer> getBikesDueForCollection(int nodeId){
        return bikesDueForCollection.get(nodeId);
    }

    public Map<Integer, Pair<Double, Double>> getNodeLocations() {
        return nodeLocations;
    }

    public void addBikeDueForCollection(int nodeId, int bikeId){
        bikesDueForCollection.get(nodeId).add(bikeId);
    }

    public void setNodeLocations(Map<Integer, Pair<Double, Double>> nodeLocations){
        this.nodeLocations = nodeLocations;
    }

    public void handleRequest(Context context){
        dispatchers.forEach(d -> d.dispatchInterceptor(context));
    }

    public void setDispatchers(Collection<Dispatcher<Context>> dispatchers){
        this.dispatchers = dispatchers;
    }
}

