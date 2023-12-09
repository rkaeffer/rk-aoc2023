package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Day8 {

    public static long getNbStepToReachAllZ(List<String> input) {
        String instruction = input.get(0);
        Map<String, Node> nodes = new HashMap<>();
        for(int i=2; i<input.size(); i++) {
            Node node = new Node(input.get(i));
            nodes.put(node.value, node);
        }
        List<Long> nbStepsForEachNode = new ArrayList<>();
        nodes.forEach( (k, v) -> {
            if(k.endsWith("A")) {
                nbStepsForEachNode.add(getNbStepToReachOneZ(instruction, v, nodes));
            }
        });
        long res = ppcm(nbStepsForEachNode.get(0), nbStepsForEachNode.get(1));
        for(int i=2; i<nbStepsForEachNode.size(); i++) {
            res = ppcm(res, nbStepsForEachNode.get(i));
        }
        return res;
    }

    public static long getNbStepToReachOneZ(String instruction, Node startNode, Map<String, Node> nodes) {
        long nbStep =0L;
        boolean found = false;
        while(!found) {
            NodeResult nr = checkAllinstruction(instruction, nodes, startNode, true);
            found = nr.found;
            nbStep += nr.nbstep;
            startNode = nr.lastNode;
        }
        return nbStep;
    }


    public static long getNbStepToReachZZZ(List<String> input) {
        String instruction = input.get(0);
        Map<String, Node> nodes = new HashMap<>();
        for(int i=2; i<input.size(); i++) {
            Node node = new Node(input.get(i));
            nodes.put(node.value, node);
        }
        long nbStep = 0L;
        boolean found = false;
        Node startNode = nodes.get("AAA");
        while(!found) {
            NodeResult nr = checkAllinstruction(instruction, nodes, startNode, false);
            found = nr.found;
            nbStep += nr.nbstep;
            startNode = nr.lastNode;
        }

        return nbStep;
    }

    private static NodeResult checkAllinstruction(String instruction, Map<String, Node> nodes, Node startNode, boolean endWith) {
        long nbStep = 0L;
        boolean found = false;
        Node curNode = startNode;
        for(char c : instruction.toCharArray()) {
            if(c == 'L') {
                curNode = nodes.get(curNode.leftNode);
            } else {
                curNode = nodes.get(curNode.rightNode);
            }
            nbStep++;
            if(endWith ? curNode.value.endsWith("Z") : curNode.value.equals("ZZZ")) {
                found = true;
                break;
            }
        }
        return new NodeResult(found, nbStep, curNode);
    }

    static class NodeResult {
        boolean found;
        long nbstep;

        Node lastNode;

        NodeResult(boolean found, long nbStep, Node lastNode) {
            this.found = found;
            this.nbstep = nbStep;
            this.lastNode = lastNode;
        }
    }

    static class Node {
        String value;
        String leftNode;
        String rightNode;

        public Node(String input) {
            String[] nodeDesc = input.split("=");
            this.value = nodeDesc[0].trim();
            String[] nodeDestinations = nodeDesc[1].trim().substring(1, nodeDesc[1].trim().length()-1).split(",");
            this.leftNode = nodeDestinations[0].trim();
            this.rightNode = nodeDestinations[1].trim();
        }
    }


    static long pgcd(long a, long b)
    {
        if (a == 0)
            return b;
        return pgcd(b % a, a);
    }

    static long ppcm(long a, long b)
    {
        return (a / pgcd(a, b)) * b;
    }


}
