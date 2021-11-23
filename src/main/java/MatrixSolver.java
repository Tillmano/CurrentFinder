import org.apache.commons.math3.linear.*;

public class MatrixSolver {
    public double[] Solve(double twoD[][], double answers[]) {
        RealMatrix coefficients =
                new Array2DRowRealMatrix(twoD);
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        RealVector constants = new ArrayRealVector(answers);
        RealVector solution = solver.solve(constants);
        return solution.toArray();
    }

}
