package ie.demo.inventorymanagement.interceptor;

import ie.demo.inventorymanagement.Context;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

// Concrete interceptor
public class LoggingInterceptor implements Interceptor<Context> {
    private final String OUT_FILE;

    public LoggingInterceptor(){
        OUT_FILE = "output.txt";
    }

    @Override
    public void execute(Context context) {
        try {
            Files.write(Paths.get(OUT_FILE), context.toString().getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
