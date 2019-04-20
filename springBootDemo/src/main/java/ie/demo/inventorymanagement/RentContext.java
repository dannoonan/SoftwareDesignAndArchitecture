package ie.demo.inventorymanagement;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RentContext extends Context {
    private int bikeId;
    private int userId;
    private Date time;

    private RentContext(Framework framework) {
        super(framework);
    }

    RentContext(){super();}

    @Override
    public String toString(){
        return "RENT:"
                + bikeId + ","
                + userId + ","
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(time)
                + "\n";
    }

    public static class Builder{
        private int bikeId;
        private int userId;
        private Date time;

        public Builder setBikeId(int bikeId) {
            this.bikeId = bikeId;
            return this;
        }

        public Builder setTime(Date time) {
            this.time = time;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public RentContext build(Framework framework){
            RentContext context = new RentContext(framework);

            context.bikeId = this.bikeId;
            context.userId = this.userId;
            context.time = this.time;

            return context;
        }
    }
}
