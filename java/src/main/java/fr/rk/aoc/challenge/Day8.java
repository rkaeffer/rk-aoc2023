package fr.rk.aoc.challenge;

import java.math.BigInteger;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Day8 {

    public static BigInteger getNbStepToReachAllZ(List<String> input) {
        String instruction = input.get(0);
        Map<String, Node> nodes = new HashMap<>();
        for(int i=2; i<input.size(); i++) {
            Node node = new Node(input.get(i));
            nodes.put(node.value, node);
        }
        long nbStep = 0L;
        Map<Node, Long> aNodes = new HashMap<>();
        nodes.forEach( (k, v) -> {
            if(k.endsWith("A")) {
                aNodes.put(v, getNbStepToReachOneZ(instruction, v, nodes));
            }
        });
        // Il faut calculer le PPCM des 6 steps
        // A coder plus tard, calcul via un site ==> 13663968099527
        BigInteger res = BigInteger.ONE;
        for(Map.Entry<Node, Long> entry : aNodes.entrySet()) {
            res = res.multiply(new BigInteger(String.valueOf(entry.getValue())));
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


}
