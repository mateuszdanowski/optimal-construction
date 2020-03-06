import java.util.ArrayList;

abstract class Greedy extends Strategy {
    private long finalCost, finalWaste;
    private ArrayList[] finalResult;

    Greedy(Rod[] rodsArr, int[] projectArr) {
        super(rodsArr, projectArr);

        finalCost = 0;
        finalWaste = 0;

        finalResult = new ArrayList[rodsArr.length];

        for (int i = 0; i < finalResult.length; i++) {
            finalResult[i] = new ArrayList<>();
        }
    }

    abstract Rod getRod(int length);

    @Override
    public void printResult() {
        System.out.println(finalCost);
        System.out.println(finalWaste);

        for (int i = 0; i < finalResult.length; i++) {

            if (finalResult[i].size() > 0) {
                Rod rod = rodsArr[i];
                long rodLength = rod.getLength();
                long sum = 0;

                System.out.print(rodLength + " ");

                for (Object id : finalResult[i]) {

                    long length = projectArr[(int) id];

                    if (sum + length > rodLength) {
                        System.out.print("\n" + rodLength + " ");
                        sum = 0;
                    }
                    sum += length;

                    System.out.print(length + " ");
                }
                System.out.println();
            }
        }
    }

    @Override
    public void solve() {
        boolean[] used = new boolean[projectArr.length];

        for (int i = projectArr.length - 1; i >= 0; i--) {

            int requiredRodLength = projectArr[i];

            if (!used[i]) {
                Rod rod = getRod(requiredRodLength);

                finalCost += rod.getPrice();
                int remaining = rod.getLength();
                int rodId = rod.getId();

                for (int j = i; j >= 0; j--) {

                    if (!used[j] && projectArr[j] <= remaining) {
                        used[j] = true;
                        finalResult[rodId].add(j);
                        remaining -= projectArr[j];
                    }
                }
                finalWaste += remaining;
            }
        }
    }
}