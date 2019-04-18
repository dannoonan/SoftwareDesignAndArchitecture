package ie.demo.inventorymanagement;

// Context interface
public abstract class Context {
    private Framework framework;

    Context(Framework framework){
        this.framework = framework;
    }
}
