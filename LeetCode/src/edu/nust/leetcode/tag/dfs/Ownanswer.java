package edu.nust.leetcode.tag.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Ownanswer {
	List<String> oneList = new ArrayList<String>();
	
	public List<String> findItinerary(String[][] tickets){
		Map<String, List<String>> seriesTreeMap = toTwoDepthTree(tickets);
		oneList.add("JFK");
		toOneLine(seriesTreeMap,"JFK");
		return oneList;
	}

	private Map<String, List<String>> toTwoDepthTree(String[][] tickets) {
		Map<String,List<String>> seriesTreemap = new HashMap<String, List<String>>();
		for(String[] row : tickets){
			String from = row[0];
			String to = row[1];
			if(seriesTreemap.containsKey(from)){
				List<String> toList = seriesTreemap.get(from);
				if(to.compareTo(getSmallest(toList)) < 0){
					toList.add(0,to);
				}else{
					toList.add(to);
				}
			}else{
				List<String> toList = new ArrayList<String>();
				toList.add(to);
				seriesTreemap.put(from,toList);
			}
		}
		return seriesTreemap;
	}
	
	private void toOneLine(Map<String,List<String>> seriesTreeMap,String curFrom) {
		List<String> toList = seriesTreeMap.get(curFrom);
		if(!toList.isEmpty()){
			String smallestTo = toList.get(0);
			toList.remove(smallestTo);
			oneList.add(smallestTo);
			toOneLine(seriesTreeMap,smallestTo);
		}
	}
	
	private String getSmallest(List<String> toList) {
		String smallestTo = toList.get(0);
		for(int i=1 ; i<toList.size() ; i++){
			if(smallestTo.compareTo(toList.get(i)) > 0 ){
				smallestTo = toList.get(i);
			}
		}
		return smallestTo;
	}

	@Test
	public void test(){
		String[][] testTickets = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		List<String> wholeItinerary = findItinerary(testTickets);
		System.out.println(wholeItinerary);
	}
}
