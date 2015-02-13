package com.SG.link;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Intent;

public class LinkHandler {
	BluetoothAdapter ba;
	String name;
	int UID;
	LinkService parent;
	BluetoothDevice link = null;
	public LinkHandler(String name, int UID,BluetoothAdapter ba, LinkService context) {
		this.name=name;
		this.UID=UID;
		this.ba=ba;
		parent=context;
		if(!ba.isEnabled()){
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			parent.startActivity(enableBtIntent);
		}
		
		BluetoothLeScanner bles = ba.getBluetoothLeScanner();
		
		ScanCallback sc = new ScanCallback(){
			public void onScanResult(int callbackType, ScanResult result) {
				super.onScanResult(callbackType, result);
				BluetoothDevice d = result.getDevice();
				
				if(LinkService.getInstance().canConnect(d.getName())){
					d.createBond();
					link=d;
				}
			}
		};
		bles.startScan(sc);
		bles.stopScan(sc);
	}
	public boolean sendCode(byte code){
		
	}
	private final BluetoothGattCallback btleGattCallback = new BluetoothGattCallback() {
		public void onCharacteristicChanged(BluetoothGatt gatt, final BluetoothGattCharacteristic characteristic) {
			// this will get called anytime you perform a read or write characteristic operation
		}
		public void onConnectionStateChange(final BluetoothGatt gatt, final int status, final int newState) { 
			// this will get called when a device connects or disconnects
		}
		public void onServicesDiscovered(final BluetoothGatt gatt, final int status) { 
			// this will get called after the client initiates a            BluetoothGatt.discoverServices() call
		}
	};
	public static final byte COMMAND_CONNECTION = 'C';
	public static final byte COMMAND_REQUEST_UPDATE = 'Q';
	public static final byte STATUS_NORMAL = 'N';
	public static final byte STATUS_ALERT = '!';
	public static final byte STATUS_LOW_BATTERY = 'B';
}
