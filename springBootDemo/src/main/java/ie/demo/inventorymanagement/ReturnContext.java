package ie.demo.inventorymanagement;

import javafx.util.Pair;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReturnContext extends Context {
    // TODO: modify return so that bike id is also passed
    private int userId;
    private int bikeId;
    private Double latitude;
    private Double longitude;
    private int studentCardId;
    private Integer nodeId;
    private Date time;

    private ReturnContext(Framework framework) {
        super(framework);
    }

    public ReturnContext(){}

    @Override
    public String toString(){
        return "RETURN:"
                + userId + ","
                + latitude + ","
                + longitude + ","
                + studentCardId + ","
                + nodeId + ","
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(time)
                + "\n";
    }

    public List<Integer> getBikesDueForCollections(Integer nodeId){
        return framework.getBikesDueForCollection(nodeId);
    }

    public Map<Integer,Pair<Double,Double>> getNodeLocations(){
        return framework.getNodeLocations();
    }


    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void addBikeDueForCollection(int nodeId){
        framework.addBikeDueForCollection(nodeId, bikeId);
    }

    public static class Builder{
        private int userId;
        private Double latitude;
        private Double longitude;
        private int studentCardId;
        private Integer nodeId;

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setNodeId(Integer nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public Builder setStudentCardId(int studentCardId) {
            this.studentCardId = studentCardId;
            return this;
        }

        public ReturnContext build(Framework framework){
            ReturnContext context = new ReturnContext(framework);

            context.userId = this.userId;
            context.latitude = this.latitude;
            context.longitude = this.longitude;
            context.nodeId = this.nodeId;
            context.studentCardId = this.studentCardId;
            context.time = new Date();

            return context;
        }
    }

}
