import org.apache.commons.math3.linear.*;

public class MatrixSolver {
    public double[] Solve(double twoD[][], double answers[]) {
        //The matrix is set up with coefficients, constants and the solver.
        RealMatrix coefficients = new Array2DRowRealMatrix(twoD);
        DecompositionSolver solver = new QRDecomposition(coefficients).getSolver();
        RealVector constants = new ArrayRealVector(answers);

        //The matrix is solved.
        RealVector solution = solver.solve(constants);
        return solution.toArray();
    }

}
