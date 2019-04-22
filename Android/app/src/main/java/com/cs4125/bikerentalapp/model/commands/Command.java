package com.cs4125.bikerentalapp.model.commands;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.web.ResponseBody;

public interface Command {

    LiveData<ResponseBody> execute();
}
