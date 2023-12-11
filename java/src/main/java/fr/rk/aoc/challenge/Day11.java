package fr.rk.aoc.challenge;

import java.util.*;

public final class Day11 {

    public static long getSumOfShortestPath(List<String> input, long factor) {
        Node[][] parsedGraph = new Node[input.size()][input.get(0).length()];
        for (int i = 0; i < parsedGraph.length; i++) {
            for (int j = 0; j < parsedGraph[i].length; j++) {
                parsedGraph[i][j] = new Node(i, j,  input.get(i).charAt(j) == '#');
            }
        }

        //List Empty Lines
        List<Integer> emptyLines = new ArrayList<>();
        for(int i=0; i<parsedGraph.length; i++) {
            boolean allEmpty = true;
            for(int j=0; j<parsedGraph[i].length; j++) {
                if(parsedGraph[i][j].isGalaxy) {
                    allEmpty = false;
                    break;
                }
            }
            if(allEmpty) {
                emptyLines.add(i);
            }
        }

        //List empty columns
        List<Integer> emptyColumns = new ArrayList<>();
        for(int j=0; j<parsedGraph[0].length; j++) {
            boolean allEmpty = true;
            for(int i=0; i<parsedGraph.length; i++) {
                if(parsedGraph[i][j].isGalaxy) {
                    allEmpty = false;
                    break;
                }
            }
            if(allEmpty) {
                emptyColumns.add(j);
            }
        }
        //Galaxy list
        List<Node> galaxies = new ArrayList<>();
        for(int i =0; i<parsedGraph.length; i++) {
            for(int j=0; j<parsedGraph[i].length; j++) {
                if(parsedGraph[i][j].isGalaxy) {
                    galaxies.add(parsedGraph[i][j]);
                }
            }
        }

        //Calcul des distances
        long totalPathLength = 0L;
        for(int i=0; i<galaxies.size(); i++) {
            for(int j=i+1; j<galaxies.size(); j++) {
                int x1 = galaxies.get(j).x;
                int x2 = galaxies.get(i).x;
                int y1 = galaxies.get(j).y;
                int y2 = galaxies.get(i).y;
                int maxX = Math.max(x1, x2);
                int minX = Math.min(x1, x2);
                int maxY = Math.max(y1, y2);
                int minY = Math.min(y1, y2);
                int coeffX = 0;
                int coeffY = 0;
                for(int emptyX : emptyLines) {
                    if(minX < emptyX && emptyX < maxX) {
                        coeffX++;
                    }
                }
                for(int emptyY : emptyColumns) {
                    if(minY < emptyY && emptyY < maxY) {
                        coeffY++;
                    }
                }
                totalPathLength += (Math.abs(x1 - x2) + Math.abs(y1 - y2)) + (factor-1)*coeffY + (factor-1)*coeffX;
            }
        }
        return totalPathLength;
    }

    static class Node {
        public int x;
        public int y;
        boolean isGalaxy;

        public Node(int x, int y, boolean isGalaxy) {
            this.x = x;
            this.y = y;
            this.isGalaxy = isGalaxy;
        }
    }
}
