package ie.demo.interceptorframework;



import ie.demo.domain.Node;
import ie.demo.service.BikeService;
import ie.demo.service.NodeService;

import java.util.*;

// Concrete framework
public class Framework {
    private static Framework instance = null;

    // When a bike is returned we will assign it to be collected and
    // sent to the nearest node. Here we store the destination nodes
    // as keys which are mapped to the list of bikeIds that must be sent to that node.
    private Map<Integer, List<Integer>> bikesDueForCollection;
    private Map<Integer, Pair<Double,Double>> nodeLocations;
    private BikeService bikeService;
    private NodeService nodeService;

    private Collection<Dispatcher<Context>> dispatchers;

    public Framework(){
        bikesDueForCollection = new HashMap<>();
    }

    public static Framework getInstance(){
        if(instance == null){
            instance = new Application().buildFramework();
            return instance;
        }
        return instance;
    }

    public void supplyBikeService(BikeService service){
        this.bikeService = service;
    }

    public void supplyNodeService(NodeService nodeService){
        this.nodeService = nodeService;
        if(nodeLocations == null || nodeLocations.size() == 0){
            generateNodeLocations();
        }
    }

    public void handleRequest(Context context){
        dispatchers.forEach(d -> d.dispatchInterceptor(context));
    }

    public BikeService getBikeService() {
        return bikeService;
    }

    public List<Integer> getBikesDueForCollection(int nodeId){
        return bikesDueForCollection.get(nodeId);
    }

    public Map<Integer, Pair<Double, Double>> getNodeLocations() {
        return nodeLocations;
    }

    public void addBikeDueForCollection(int nodeId, int bikeId){
        if(!bikesDueForCollection.keySet().contains(nodeId)){
            bikesDueForCollection.put(nodeId, new ArrayList<>());
        }
        if(!bikesDueForCollection.get(nodeId).contains(bikeId)){
            bikesDueForCollection.get(nodeId).add(bikeId);
        }
    }

    public void setNodeLocations(Map<Integer, Pair<Double, Double>> nodeLocations){
        this.nodeLocations = nodeLocations;
    }

    public void setDispatchers(Collection<Dispatcher<Context>> dispatchers){
        this.dispatchers = dispatchers;
    }

    private void generateNodeLocations(){
        List<Node> nodes = nodeService.findNode();
        Map<Integer, Pair<Double,Double>> nodeLocations = new HashMap<>();

        for(Node node: nodes){
            String[] latlong = node.getPosition().split(", ");
            double latitude = Double.parseDouble(latlong[0]);
            double longitude = Double.parseDouble(latlong[1]);

            nodeLocations.put(node.getNodeId(), new Pair<>(latitude, longitude));
        }
        this.nodeLocations = nodeLocations;
    }
}

