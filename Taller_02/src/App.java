import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    private int[][] create_matrix(int n, int m) {
        Random rand = new Random();
        int[][] matrix = new int[n][m];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; i < matrix[0].length; i++) {
                matrix[i][j] = rand.nextInt(1000);
            }
        }

        return matrix;
    }
}
