package com.csewannabe.selection;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DataCollector {


		Map<String, List<String>> homeworkDataset;
		Map<String, Integer[]> scoreMap;
	
		public DataCollector() {
			homeworkDataset = new TreeMap<String, List<String>>();
			scoreMap = new TreeMap<String, Integer[]>();
			add("assignment 1A", "problem 1");
			add("assignment 1A", "problem 2");
			add("assignment 1A", "problem 3");
			add("assignment 1A", "problem 4");
			add("assignment 1A", "problem 5");
			add("assignment 2", "problem 1");
			add("assignment 2", "problem 2a");
			add("assignment 2", "problem 3");
			add("assignment 2", "problem 4");
			add("assignment 2", "problem 5");
			add("assignment 2", "problem 6");
			add("assignment 2", "problem 6a");
			add("assignment 2", "problem 7");
			add("assignment 2B", "problem 1");
			add("assignment 2B", "problem 2");
			add("assignment 2B", "problem 3");
			add("assignment 2B", "problem 4");
			add("assignment 3", "problem 1");
			add("assignment 3", "problem 2");
			add("assignment 3", "problem 3");
			add("assignment 3", "problem 4");
			add("assignment 3", "problem 5");
			add("assignment 3", "problem 6");
			add("assignment 3", "problem 7");
			add("assignment 3", "problem 8");
			add("assignment 3", "problem 9");
			add("assignment 3", "problem 10");
			add("bonus 1", "problem 1");
			add("bonus 2", "problem 2");
			addScore("assignment 1A", 9, 9);
			addScore("assignment 2", 3, 9);
			addScore("assignment 2B", 5, 8);
			addScore("assignment 3", 5, 9);
			addScore("bonus 1", 1, 1);
			addScore("bonus 2", 0, 1);
		}
		
		public void add(String assignment, String problem) {
			if(!homeworkDataset.containsKey(assignment)) {
				homeworkDataset.put(assignment, new LinkedList<String>());
			}
			homeworkDataset.get(assignment).add(problem);
		}
		
		public void addScore(String assignment, int score, int total) {
			Integer[] myScore = new Integer[2];
			myScore[0] = score;
			myScore[1] = total;
			
				scoreMap.put(assignment, myScore);
	
		}
		
		public Integer[] getScore(String assignment) {
			return scoreMap.get(assignment);
		}
		
		public String[] getAssignments() {
			return homeworkDataset.keySet().toArray(new String[] {});
		}
		
		public String[] getProblems(String assignment) {
			return (String[]) homeworkDataset.get(assignment).toArray(new String[] {});
		}
}
