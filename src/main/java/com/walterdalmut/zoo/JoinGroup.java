package com.walterdalmut.zoo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;

public class JoinGroup extends ConnectionWatcher {
	
	public void join(String groupName, String memberName, String data) throws KeeperException, InterruptedException
	{
		String path = "/" + groupName + "/" + memberName;
		if (zk.exists(path, false) != null) {
			zk.setData(path, data.getBytes(), -1);
			System.out.printf("Updated node %s with data %s\n", path, data);
		} else {
			String createdPath = zk.create(path, data.getBytes() /* data */, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.printf("Create node %s with data %s\n", createdPath, data);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		JoinGroup joinGroup = new JoinGroup();
		joinGroup.connect(args[0]);
		joinGroup.join(args[1], args[2], args[3]);
	}

}
