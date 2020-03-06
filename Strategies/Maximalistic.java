public class Maximalistic extends Greedy {
    Maximalistic(Rod[] rodsArr, int[] projectArr) {
        super(rodsArr, projectArr);
    }

    @Override
    Rod getRod(int length) {
        return rodsArr[rodsArr.length - 1];
    }
}