/**
 * @author Deniz Baran Aslan
 * @studentID 2021719183
 * @date 25.04.2022
 * 
 * This script reads coordinates from a file per user input, 
 * calculates the distance between each node, 
 * and determines the shortest path that visits each node, 
 * starting from and ending at the same "Migros" node determined in the input file.
 * Submission for Assignment 1, SWE 510
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.geom.Point2D;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// Determine filename per user input
		Scanner askFile = new Scanner(System.in);
	    System.out.println("Which input file should be read?");
	    String fileName = askFile.nextLine();
	    askFile.close();
	    System.out.println("File is: " + fileName);

		File file = new File(fileName); // Create file object
		
		// If the file is not found, print error message and quit
		if (!file.exists()) {
			System.out.printf("File cannot be found.", fileName);
			System.exit(1);
		}
		
		// Read all lines in file and store in ArrayList
		ArrayList<String> nodes = new ArrayList<String>();
		Scanner inputFile = new Scanner(file);
		while (inputFile.hasNextLine()) {
			String line = inputFile.nextLine();
			nodes.add(line);
		}
		
		inputFile.close();
		int nodeAmount = nodes.size(); // save number of nodes for later use 
		
		// Split data from previous array and place in new 2d array
		String[][] nodesArray = new String[nodeAmount][4];
		int index = 0;
		for (String node: nodes) {
			String[] nodeContents = node.split(",");
			nodesArray[index][0] = String.valueOf(index + 1); // add node number
			nodesArray[index][1] = nodeContents[0]; // add x coordinate
			nodesArray[index][2] = nodeContents[1]; // add y coordinate
			if (nodeContents.length == 3) {
				nodesArray[index][3] = nodeContents[2];
			}
			
			index++;
		}
		
		// Create matrix (2d array) of distance between each node
		double[][] distanceMatrix = new double[nodeAmount][nodeAmount];
		int row = 0;
		for (double[] node: distanceMatrix) {
			int column = 0;
			double x1 = Double.valueOf(nodesArray[row][1]);
			double y1 = Double.valueOf(nodesArray[row][2]);
			for (int i = 0; i < node.length; i++) {
				double x2 = Double.valueOf(nodesArray[column][1]);
				double y2 = Double.valueOf(nodesArray[column][2]);
				distanceMatrix[row][column] = Point2D.distance(x1, y1, x2, y2); // calculate Euclidean distance
				column++;
			}
			row++;
		}
		
		// Create arraylists for explored and unexplored nodes
		ArrayList<String> unexploredNodes = new ArrayList<String>();
		ArrayList<String> exploredNodes = new ArrayList<String>();
		
		// Add Migros as first explored node, add rest to unexplored nodes
		for (String[] node: nodesArray) {
			if (node[3] != null) {
				exploredNodes.add(node[0]);
			}
			else {
				unexploredNodes.add(node[0]);
			}
		}
		
		// Create variables used for iteration
		String firstNode = exploredNodes.get(0);
		String currentNode = firstNode;
		String closestNode = null;
		double distance = 2;
		double totalDistance = 0;
		
		// Iterate over nodes to find nearest neighbor, move until all nodes are visited
		for (int i = 0; i < nodeAmount - 1; i++) {
			for (String node: unexploredNodes) {
				if (distanceMatrix[Integer.parseInt(currentNode) - 1][Integer.parseInt(node) - 1] < distance 
				&& distanceMatrix[Integer.parseInt(currentNode) - 1][Integer.parseInt(node) - 1] > 0) {
					closestNode = node;
					distance = distanceMatrix[Integer.parseInt(currentNode) - 1][Integer.parseInt(node) - 1];
				}
			}
			totalDistance += distance; // add up distance traveled
			unexploredNodes.remove(currentNode); // remove node from unexplored
			exploredNodes.add(closestNode); // add node to explored
			currentNode = closestNode;	
			distance = 2;
		}
		
		// Account for final journey back to starting node
		unexploredNodes.remove(currentNode);
		totalDistance += distanceMatrix[Integer.parseInt(currentNode) - 1][Integer.parseInt(firstNode) - 1];
		exploredNodes.add(firstNode);
		
		// Print results
		System.out.println("Shortest Route: " + exploredNodes);
		System.out.println("Distance: " + totalDistance);
	}
}
