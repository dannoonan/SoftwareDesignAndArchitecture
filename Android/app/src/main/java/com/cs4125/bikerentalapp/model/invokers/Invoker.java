package com.cs4125.bikerentalapp.model.invokers;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.model.commands.Command;
import com.cs4125.bikerentalapp.web.ResponseBody;

public class Invoker {

    private final Command command;

    public Invoker(Command aCommand){
        command = aCommand;
    }

    public LiveData<ResponseBody> executeCommand(){
        return command.execute();
    }
}
