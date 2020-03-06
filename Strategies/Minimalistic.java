public class Minimalistic extends Greedy {
    Minimalistic(Rod[] rodsArr, int[] projectArr) {
        super(rodsArr, projectArr);
    }

    @Override
    Rod getRod(int length) {
        int l = 0, r = rodsArr.length - 1;

        while (l != r) {
            int mid = (l + r) / 2;

            if (rodsArr[mid].getLength() < length) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return rodsArr[l];
    }
}