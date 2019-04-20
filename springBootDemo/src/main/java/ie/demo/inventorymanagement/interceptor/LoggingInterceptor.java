package ie.demo.inventorymanagement.interceptor;

import ie.demo.inventorymanagement.Context;

import java.io.*;

// Concrete interceptor
public class LoggingInterceptor implements Interceptor<Context> {
    private final String OUT_FILE;

    public LoggingInterceptor(){
        OUT_FILE = "output.log";
    }

    @Override
    public void execute(Context context) {
        String out = context.toString();

        OutputStream os = null;
        try {
            boolean append = new File(OUT_FILE).exists();
            os = new FileOutputStream(new File(OUT_FILE), append);
            os.write(out.getBytes(), 0, out.length());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                os.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
