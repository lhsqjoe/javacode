package com.primemobi.iaas.other;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.primemobi.iaas.util.SSHUtil;

public class ResourcesMonitor {
public static void main(String[] args) {
	List list=new ArrayList();
	list.add("ping -qc 1  10.10.203.163 |awk '$0~/received/ && $4>0 {print$4}' ");
	try {
		String strMonitor = SSHUtil.remoteSshExecutor("10.10.203.162", "root", "vicloud.1", list);
		String listMonitor = strMonitor.trim();
		//list.clear();
		System.out.println("listMonitor"+listMonitor.equals("1"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
