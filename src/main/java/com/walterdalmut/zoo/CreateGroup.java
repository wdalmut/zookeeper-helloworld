package com.walterdalmut.zoo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;

public class CreateGroup extends ConnectionWatcher {
	
	public void create(String groupName) throws KeeperException, InterruptedException
	{
		String path = "/" + groupName;
		String createdPath = zk.create(path, null/*data*/, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println("Created " + createdPath);
	}
	
	public static void main(String[] args) throws Exception 
	{
		CreateGroup createGroup = new CreateGroup();
		createGroup.connect(args[0]);
		createGroup.create(args[1]);
		createGroup.close();
	}

}
