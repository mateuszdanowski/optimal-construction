abstract class Strategy {
    protected Rod[] rodsArr;
    protected int[] projectArr;

    Strategy(Rod[] rodsArr, int[] projectArr) {
        this.rodsArr = rodsArr;
        this.projectArr = projectArr;
    }

    abstract void solve();

    abstract void printResult();
}