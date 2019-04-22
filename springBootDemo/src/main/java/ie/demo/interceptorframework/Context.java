package ie.demo.interceptorframework;

// Context interface
public class Context {
    protected Framework framework;

    public Context(Framework framework){
        this.framework = framework;
    }

    Context(){}

    @Override
    public String toString() {
        return super.toString();
    }
}
