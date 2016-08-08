package edu.nust.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DFS {
	Map<String, List<String>> originalModelMap = new HashMap<String, List<String>>();
	List<String> targetModelList = new ArrayList<String>();
	
	public boolean dfs(String node){
		if(targetModelList.size() == 4){
			return true;
		}
		String newestNode = targetModelList.get(targetModelList.size()-1);
		List<String> newestList = originalModelMap.get(newestNode);
		if(newestList.isEmpty()){
			targetModelList.remove(newestNode);
			System.out.println("targetListRemove:"+newestNode);
			dfs(node);
		}else{
			String value = newestList.get(0);
			if(targetModelList.contains(value)){
				newestList.remove(0);
				System.out.println(newestNode+"ListRemove:"+value);
				dfs(node);
			}else{
				targetModelList.add(value);
				System.out.println("targetListAdd:"+value);
				newestList.remove(0);
				dfs(node);
			}
		}
		return false;
	}
	
	@org.junit.Test
	public void test(){
		initialMap();
		System.out.println(originalModelMap);
		System.out.println("-------------dfs-------------");
		
		targetModelList.add("v0");
		dfs("v0");
		System.out.println(targetModelList);
	}
	
	public void initialMap(){
		//先建立问题模型
		//{v1=[v2], v0=[v1, v3], v5=[v6], v6=[], v4=[], v3=[v4, v5], v2=[v0, v1]}
		String v0 = "v0";
		String v1 = "v1";
		String v2 = "v2";
		String v3 = "v3";
		String v4 = "v4";
		String v5 = "v5";
		String v6 = "v6";
		List<String> v0List = new ArrayList<String>();
		v0List.add(v1);
		v0List.add(v3);
		originalModelMap.put(v0,v0List);
		List<String> v1List = new ArrayList<String>();
		v1List.add(v2);
		originalModelMap.put(v1,v1List);
		List<String> v2List = new ArrayList<String>();
		v2List.add(v0);
		v2List.add(v1);
		originalModelMap.put(v2,v2List);
		List<String> v3List = new ArrayList<String>();
		v3List.add(v4);
		v3List.add(v5);
		originalModelMap.put(v3,v3List);
		List<String> v4List = new ArrayList<String>();

		originalModelMap.put(v4,v4List);
		List<String> v5List = new ArrayList<String>();
		v5List.add(v6);
		originalModelMap.put(v5,v5List);
		List<String> v6List = new ArrayList<String>();
		originalModelMap.put(v6,v6List);
	}
}
