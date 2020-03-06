import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int rodsLength = in.nextInt();
        Rod[] rodsArr = new Rod[rodsLength];

        for (int i = 0; i < rodsLength; i++) {
            int length = in.nextInt();
            int price = in.nextInt();
            rodsArr[i] = new Rod(length, price, i);
        }

        int projectLength = in.nextInt();
        int[] projectArr = new int[projectLength];

        for (int i = 0; i < projectLength; i++) {
            projectArr[i] = in.nextInt();
        }

        in.nextLine();
        String strategyName = in.nextLine();

        switch (strategyName) {
            case "minimalistyczna": {
                Minimalistic solution = new Minimalistic(rodsArr, projectArr);
                solution.solve();
                solution.printResult();
                break;
            }
            case "maksymalistyczna": {
                Maximalistic solution = new Maximalistic(rodsArr, projectArr);
                solution.solve();
                solution.printResult();
                break;
            }
            case "ekonomiczna": {
                Economic solution = new Economic(rodsArr, projectArr);
                solution.solve();
                solution.printResult();
                break;
            }
            case "ekologiczna": {
                Ecologic solution = new Ecologic(rodsArr, projectArr);
                solution.solve();
                solution.printResult();
                break;
            }
            default: {
                System.out.println("No such strategy as " + strategyName + "!");
                break;
            }
        }
    }
}