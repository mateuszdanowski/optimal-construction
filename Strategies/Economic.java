public class Economic extends Optimal {
    Economic(Rod[] rodsArr, int[] projectArr) {
        super(rodsArr, projectArr);
    }

    @Override
    boolean isMoreOptimal(Result firstResult, Result secondResult) {
        return secondResult.getCost() < firstResult.getCost();
    }
}