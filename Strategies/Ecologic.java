public class Ecologic extends Optimal {
    Ecologic(Rod[] rodsArr, int[] projectArr) {
        super(rodsArr, projectArr);
    }

    @Override
    boolean isMoreOptimal(Result firstResult, Result secondResult) {
        return secondResult.getWaste() < firstResult.getWaste();
    }
}