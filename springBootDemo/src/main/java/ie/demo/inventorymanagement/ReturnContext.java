package ie.demo.inventorymanagement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ReturnContext extends Context {
    private int userId;
    private Integer latitude;
    private Integer longitude;
    private int studentCardId;
    private Integer nodeId;
    private Date time;

    private ReturnContext(Framework framework) {
        super(framework);
    }

    ReturnContext(){}

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

    public static class Builder{
        private int userId;
        private Integer latitude;
        private Integer longitude;
        private int studentCardId;
        private Integer nodeId;

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setLatitude(Integer latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Integer longitude) {
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