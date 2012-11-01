package com.walterdalmut.zoo;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;

public class MyDataWatcher implements Watcher {
	
	MyKeyValueStore store;
	
	public MyDataWatcher() throws IOException, InterruptedException
	{
		store = new MyKeyValueStore();
		store.connect("localhost");
		
	}
	
	public void displayValue() throws KeeperException, InterruptedException
	{
		String value = store.read("/zoo/walter", this);
		System.out.printf("Read %s as %s\n", "/zoo/walter", value);
	}
	
	@Override
	public void process(WatchedEvent event) {
		if (event.getType() == EventType.NodeDataChanged) {
			try {
				displayValue();
			} catch (KeeperException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception
	{
		MyDataWatcher watcher = new MyDataWatcher();
		watcher.displayValue();
		
		Thread.sleep(Long.MAX_VALUE);
	}
}
