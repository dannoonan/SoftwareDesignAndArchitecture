package ie.demo.interceptorframework;

public class DispatcherFactory {

    public Dispatcher getDispatcher(Context context){
        if(context instanceof RentContext){
            System.out.println("generated rent dispatcher " + context.getClass());
            return new Dispatcher<RentContext>(context);
        }
        else if (context instanceof ReturnContext){
            System.out.println("generated return dispatcher " + context.getClass());
            return new Dispatcher<ReturnContext>(context);
        }
        else if(context instanceof ReportContext){
            System.out.println("generated report dispatcher " + context.getClass());
            return new Dispatcher<ReportContext>(context);
        }
        return null;
    }

}
