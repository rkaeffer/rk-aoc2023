package fr.rk.aoc.challenge;

import java.util.List;

public final class Day6 {

    public static long getMultiplicationRecordsPossibility(List<String> input) {
        String[] times = input.get(0).split(",");
        String[] distances = input.get(1).split(",");
        long records = 1L;
        for(int i=0; i<times.length; i++) {
            long nbBeating = new Race(Long.parseLong(times[i]), Long.parseLong(distances[i])).getNbBeatingInterval();
            records*=nbBeating;
        }
        return records;
    }

    static class Race {
        long time;
        long distance;


        Race(long time, long distance) {
            this.time = time;
            this.distance = distance;
        }

        //x = temps d'appui
        //L = Temps accordé
        //f(x)= x*(L-x) = xL - x²
        //on cherche f(x) = D avec D le record actuel
        // -1x² + Lx + - D = 0
        //a=-1
        //b=L
        //c=-D
        //Delta = b²-4ac
        //Delta = L² -4*-1*-D = L² - 4D
        //MIN = -b - sqtr(delta)/2a = -L -sqrt(delta)/-2
        //MAX = -L + sqrt(delta)/-2
        public long getNbBeatingInterval() {
            double max = (-this.time - Math.sqrt(Math.pow(this.time, 2) - 4*this.distance))/-2;
            double min = (-this.time + Math.sqrt(Math.pow(this.time, 2) - 4*this.distance))/-2;
            //Gestion des cas où on tombe pile sur le record
            if(max % 1 == 0) max--;
            if(min % 1 == 0) min++;
            return (long) (Math.floor(max) - Math.ceil(min)) + 1;
        }
    }
}
