package ie.demo.inventorymanagement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ReturnContext extends Context {
    private int userId;
    private int latitude;
    private int longitude;
    private int studentCardId;
    private int nodeId;
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
        private int latitude;
        private int longitude;
        private int studentCardId;
        private int nodeId;

        public Builder setuserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setLatitude(int latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(int logitude) {
            this.longitude = logitude;
            return this;
        }

        public Builder setnodeId(int nodeId) {
            this.longitude = nodeId;
            return this;
        }

        public Builder setStudentCardId(int studentCardId) {
            this.latitude = studentCardId;
            return this;
        }

        public ReturnContext build(Framework framework){
            ReturnContext context = new ReturnContext(framework);

            context.userId = this.userId;
            context.latitude = this.latitude;
            context.longitude = this.longitude;
            context.nodeId = this.nodeId;
            context.studentCardId = this.nodeId;
            context.time = new Date();

            return context;
        }
    }

}
