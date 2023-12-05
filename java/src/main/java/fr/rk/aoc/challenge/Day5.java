package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Day5 {

    public static long getLowestSeedLocation(List<String> input, boolean withRange) {
        return new Almanac(input).getMinDestination(withRange);
    }

    static class Almanac {
        List<Long> seeds;
        List<List<Range>> almanacInstruction = new ArrayList<>();

        Almanac(List<String> input) {
            seeds = Arrays.stream(input.get(0).split(":")[1].trim().split(" "))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            int j=0;

            for(int i=3; i<input.size(); i++) {
                almanacInstruction.add(new ArrayList<>());
                while(!input.get(i).equals("")) {
                    almanacInstruction.get(j).add(new Range(input.get(i).split(" ")));
                    i++;
                    if(i == input.size()) {
                        break;
                    }
                }
                i++;
                j++;
            }
        }

        public long getMinDestination(boolean withRange) {
            long min = Long.MAX_VALUE;
            if(!withRange) {
                for(Long seed : this.seeds) {
                    long destination = this.getSeedDestination(seed);
                    if(destination < min) {
                        min = destination;
                    }
                }
            } else {
                for(int i=0; i<this.seeds.size(); i+=2) {
                    for(long j=this.seeds.get(i); j<this.seeds.get(i) + this.seeds.get(i+1); j++) {
                        long destination = this.getSeedDestination(j);
                        if(destination < min) {
                            min = destination;
                        }
                    }
                }
            }

            return min;
        }

        public long getSeedDestination(long seed) {
            long destination = seed;
            for(List<Range> ranges : this.almanacInstruction) {
                for(Range range : ranges) {
                    if(range.isInRange(destination)) {
                        destination = range.getDestination(destination);
                        break;
                    }
                }
            }
            return destination;
        }
    }

    static class Range {
        long destinationRange;
        long sourceRange;
        long rangeLength;

        Range(String[] data) {
            this(Long.parseLong(data[0]), Long.parseLong(data[1]), Long.parseLong(data[2]));
        }

        Range(long destinationRange, long sourceRange, long rangeLength) {
            this.destinationRange = destinationRange;
            this.sourceRange = sourceRange;
            this.rangeLength = rangeLength;
        }

        public boolean isInRange(long seed) {
            return seed >= sourceRange && seed <= sourceRange + rangeLength - 1;
        }

        public long getDestination(long seed) {
            long delta = seed - sourceRange;
            return destinationRange + delta;
        }
    }
}
