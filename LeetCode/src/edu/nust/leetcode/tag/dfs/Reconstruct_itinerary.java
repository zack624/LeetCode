package edu.nust.leetcode.tag.dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;


public class Reconstruct_itinerary {
    HashMap<String,List<String>> maps;
    int count;
    
    public List<String> findItinerary(String[][] tickets) {
        maps = new HashMap();
        initHashMap(tickets);
        count = tickets.length+1;
        List<String> res = new LinkedList();
        res.add("JFK");
        dfs(res);
        return res;
    }
    private boolean dfs(List<String> res){
       if(res.size() == count)
            return true;
        String top = res.get(res.size()-1);
        List<String> lists = maps.get(top);
        if(lists == null)
            return false;
        for(int i = 0;i<lists.size();i++){
            String tmp = lists.get(i);
            res.add(tmp);
            lists.remove(i);
            if(dfs(res))//当找到合适点之后，直接返回true
                return true;
            lists.add(i,tmp);//恢复现场
            res.remove(res.size()-1);
        }
        return false;
    }
    private void initHashMap(String[][] tickets){
        for(String[] tmp:tickets){
            String from = tmp[0];
            String to = tmp[1];
            if(!maps.containsKey(from)){
                maps.put(from,new LinkedList());
            }
            List<String> list = maps.get(from);
            int index = 0;
            int size = list.size();
            while(index < size){
                String cur = list.get(index);
                if(cur.compareTo(to) >= 0){
                    break;
                }
                index++;
            }
            list.add(index,to);
        }
    }
    
    @Test
    public void testAnswer(){
    	String[][] tickets = {{"JFK","KDA"},{"KDA","AAA"},{"AAA","KDA"},{"DAD","ACK"},{"KDA","DAD"},{"ACK","CCK"}};
    	List result = findItinerary(tickets);
    	System.out.println(result);
    }
}