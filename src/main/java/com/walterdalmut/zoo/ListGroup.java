package com.walterdalmut.zoo;

import java.util.List;

import org.apache.zookeeper.KeeperException;

public class ListGroup extends ConnectionWatcher {
	public void list(String groupName) throws InterruptedException
	{
		String path = "/" + groupName;
		
		try {
			List<String> children = zk.getChildren(path, false);
			
			if (children.isEmpty()) {
				System.out.printf("No members in group %s", groupName);
				System.exit(1);
			}
			
			for (String child : children) {
				System.out.println(child);
			}
		} catch (KeeperException e) {
			System.out.printf("Group %s does not exists\n", groupName);
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		ListGroup listGroup = new ListGroup();
		listGroup.connect(args[0]);
		listGroup.list(args[1]);
		listGroup.close();
	}
}
