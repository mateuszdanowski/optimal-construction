import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class Optimal extends Strategy {
    private Result[] result;

    Optimal(Rod[] rodsArr, int[] projectArr) {
        super(rodsArr, projectArr);

        result = new Result[1 << projectArr.length];
    }

    abstract boolean isMoreOptimal(Result firstResult, Result secondResult);

    protected class Result {
        private long cost, waste;
        private Map<Rod, ArrayList<ArrayList<Integer>>> division;

        Result(long cost, long waste) {
            this.cost = cost;
            this.waste = waste;
            division = new HashMap<>();

            for (int i = 0; i < rodsArr.length; i++) {
                division.put(rodsArr[i], new ArrayList<>());
            }
        }

        public long getCost() {
            return cost;
        }

        public long getWaste() {
            return waste;
        }

        public Map<Rod, ArrayList<ArrayList<Integer>>> getDivision() {
            return division;
        }

        public void add(Rod rod, ArrayList<Integer> list) {
            ArrayList<ArrayList<Integer>> listInMap = division.get(rod);
            listInMap.add(list);
        }

        public void print() {
            System.out.println(cost);
            System.out.println(waste);

            for (Map.Entry<Rod, ArrayList<ArrayList<Integer>>> entry : division.entrySet()) {
                Rod k = entry.getKey();
                ArrayList<ArrayList<Integer>> v = entry.getValue();

                for (ArrayList<Integer> list : v) {
                    System.out.print(k.getLength() + " ");

                    for (Object obj : list) {
                        System.out.print(obj + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    private Result mergeResults(Result firstResult, Result secondResult) {
        Result result = new Result(firstResult.getCost() + secondResult.getCost(), firstResult.getWaste() + secondResult.getWaste());

        Map<Rod, ArrayList<ArrayList<Integer>>> firstResultDivision = firstResult.getDivision();

        for (Map.Entry<Rod, ArrayList<ArrayList<Integer>>> entry : firstResultDivision.entrySet()) {
            Rod k = entry.getKey();
            ArrayList<ArrayList<Integer>> v = entry.getValue();

            for (ArrayList<Integer> list : v) {
                result.add(k, list);
            }
        }

        Map<Rod, ArrayList<ArrayList<Integer>>> secondResultDivision = secondResult.getDivision();

        for (Map.Entry<Rod, ArrayList<ArrayList<Integer>>> entry : secondResultDivision.entrySet()) {
            Rod k = entry.getKey();
            ArrayList<ArrayList<Integer>> v = entry.getValue();

            for (ArrayList<Integer> list : v) {
                result.add(k, list);
            }
        }

        return result;
    }

    @Override
    public void solve() {
        int lim = 1 << projectArr.length;

        for (int mask = 1; mask < lim; mask++) {

            result[mask] = new Result(Long.MAX_VALUE, Long.MAX_VALUE);

            for (int firstSubmask = mask; firstSubmask > 0; firstSubmask = ((firstSubmask - 1) & mask)) {

                int secondSubmask = mask ^ firstSubmask;
                if (secondSubmask > 0) {

                    Result candidateResult = mergeResults(result[firstSubmask], result[secondSubmask]);

                    if (isMoreOptimal(result[mask], candidateResult)) {
                        result[mask] = candidateResult;
                    }
                }
            }

            long sumOfRodLengths = 0;
            ArrayList<Integer> requiredLengths = new ArrayList<>();

            for (int i = 0; i < projectArr.length; i++) {

                if ((mask & (1 << i)) > 0) {
                    requiredLengths.add(projectArr[i]);
                    sumOfRodLengths += projectArr[i];
                }
            }

            for (Rod rod : rodsArr) {
                if (sumOfRodLengths <= rod.getLength()) {

                    Result candidateResult = new Result(rod.getPrice(), rod.getLength() - sumOfRodLengths);
                    candidateResult.add(rod, requiredLengths);

                    if (isMoreOptimal(result[mask], candidateResult)) {
                        result[mask] = candidateResult;
                    }
                }
            }
        }
    }

    @Override
    public void printResult() {
        result[(1 << (projectArr.length)) - 1].print();
    }
}