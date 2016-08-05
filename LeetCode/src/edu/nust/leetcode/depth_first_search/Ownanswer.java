package edu.nust.leetcode.depth_first_search;

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
				seriesTreemap.get(from).add(to);
			}else{
				List<String> toList = new ArrayList<String>();
				toList.add(to);
				seriesTreemap.put(from,toList);
			}
		}
		return seriesTreemap;
	}
	
	private void toOneLine(Map<String,List<String>> seriesTreeMap,String curFrom) {
		for(String from : seriesTreeMap.keySet()){
			if(from.equals(curFrom)){
				List<String> toList = seriesTreeMap.get(from);
				if(toList.isEmpty()){
					return;
				}else{
					String smallestTo = compareSmallest(toList);
					toList.remove(smallestTo);
					oneList.add(smallestTo);
					toOneLine(seriesTreeMap,smallestTo);
				}
				
			}
		}
	}
	
	private String compareSmallest(List<String> toList) {
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
		String[][] testTickets = {{"JFK","KDA"},{"KDA","ZAA"},{"ZAA","KDA"},{"DAD","KDA"},{"KDA","DAD"},{"KDA","DAD"}};
		List<String> wholeItinerary = findItinerary(testTickets);
		System.out.println(wholeItinerary);
	}
}
