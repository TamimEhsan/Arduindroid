package com.tamimehsan.boticabot;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class Bluetooth {
    private static Bluetooth instance = new Bluetooth();

    private String address = null;
    private BluetoothAdapter bluetoothAdapter = null;
    private BluetoothSocket bluetoothSocket = null;
    private boolean connected = false;
    private ProgressDialog progress;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Context context;
    private Bluetooth(){}
    public static Bluetooth getInstance(){
        return instance;
    }

    public void setAddress(String address) {
        this.address = address;
        new ConnectBT().execute();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isConnected() {
        return connected;
    }

    private void msg(String s) {
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    public boolean sendSignal(String s){
        if (bluetoothSocket!=null) {
            try {
                bluetoothSocket.getOutputStream().write(s.getBytes());
                return true;
            } catch (IOException e) {
                msg("Error");
                return false;
            }
        }
        return false;
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute() {
//            progress = ProgressDialog.show(context, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices){//while the progress dialog is shown, the connection is done in background
            try {
                if (bluetoothSocket == null || !connected) {
                    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = bluetoothAdapter.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    bluetoothSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    bluetoothSocket.connect();//start connection
                }
            }
            catch (IOException e) {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){//after the doInBackground, it checks if everything went fine

            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
            } else {
                msg("Connected.");
                connected = true;
            }
            // progress.dismiss();
        }
    }

}
