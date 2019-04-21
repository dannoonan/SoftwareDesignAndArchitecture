package ie.demo.inventorymanagement;

import ie.demo.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ie.demo.domain.Node;
import ie.demo.inventorymanagement.interceptor.Interceptor;
import ie.demo.inventorymanagement.interceptor.LoggingInterceptor;

import javax.annotation.PostConstruct;
import java.util.*;

// Interceptors are created and registered to dispatchers.
// If more interceptors are implemented, we must also register
// these interceptors here
@Service
public class Application {

    @Autowired
    private NodeService nodeService;


    public Framework buildFramework(){
        Framework framework = new Framework();
        DispatcherFactory factory = new DispatcherFactory();
        Collection<Dispatcher<Context>> dispatchers = new ArrayList<>();

        Dispatcher rentDispatcher = factory.getDispatcher(new RentContext());
        Dispatcher returnDispatcher = factory.getDispatcher(new ReturnContext());
        Dispatcher reportDispatcher = factory.getDispatcher(new ReportContext());

        Interceptor<Context> i = new LoggingInterceptor();

        rentDispatcher.register(i);
        reportDispatcher.register(i);
        returnDispatcher.register(i);

        dispatchers.add(rentDispatcher);
        dispatchers.add(returnDispatcher);
        dispatchers.add(reportDispatcher);

        framework.setDispatchers(dispatchers);
        framework.setNodeLocations(new HashMap<>());

        return framework;
    }


    // TODO: Causing nullpointer because nodeService is not being autowired.
    @PostConstruct
    private Map<Integer, Pair<Double,Double>> getNodeLocations(){
        List<Node> nodes = nodeService.findNode();
        Map<Integer, Pair<Double,Double>> nodeLocations = new HashMap<>();

        for(Node node: nodes){
            String[] latlong = node.getPosition().split(", ");
            double latitude = Double.parseDouble(latlong[0]);
            double longitude = Double.parseDouble(latlong[1]);

            nodeLocations.put(node.getNodeId(), new Pair<>(latitude, longitude));
        }
        return nodeLocations;
    }

}
