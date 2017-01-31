package com.frln.h66.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class RemoteService extends Service {

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();


    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }



    //Terrible Design
    static boolean justStarted = false;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ServiceStarted", "Started");
        this.dbRef.child("command").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.getValue() != null){
                    if(RemoteService.justStarted){
                        boolean action = Boolean.parseBoolean(dataSnapshot.child("action").getValue(String.class));
                        Command command = new Gson().fromJson(dataSnapshot.child("details").getValue(String.class), Command.class);
                        Commander.execute(action, command, RemoteService.this.getApplicationContext());
                    }
                    RemoteService.justStarted = true;
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("onCancelled", "Failed to read value.", error.toException());
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    private void registerD(){

    }

}
