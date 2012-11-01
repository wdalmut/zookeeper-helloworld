package com.walterdalmut.zoo;

import java.nio.charset.Charset;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;

public class MyKeyValueStore extends ConnectionWatcher {
	private static final Charset CHARSET = Charset.forName("UTF-8");
	
	public String read(String path, Watcher watcher) throws KeeperException, InterruptedException
	{
		byte[] data = zk.getData(path, watcher, null);
		return new String(data, CHARSET);
	}
}
