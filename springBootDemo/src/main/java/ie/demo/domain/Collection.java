package ie.demo.domain;

public class Collection {

    private int collectionId;
    private int driverId;
    private int nodeId;

    public int getCollectionId() {
        return this.collectionId;
    }

    public  Collection(int driverId, int nodeId) {
        this.driverId = driverId;
        this.nodeId = nodeId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getDriverId() {
        return this.driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }
}

