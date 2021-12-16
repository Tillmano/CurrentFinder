import org.apache.commons.math3.linear.*;

import javax.swing.*;

public class MatrixSolver {
    public double[] Solve(double twoD[][], double answers[]) {
        //The matrix is set up with coefficients, constants and the solver.
        RealMatrix coefficients = new Array2DRowRealMatrix(twoD);
        DecompositionSolver solver = new QRDecomposition(coefficients).getSolver();
        RealVector constants = new ArrayRealVector(answers);
        RealVector solution = null;
        //The matrix is solved.
        try {
            solution = solver.solve(constants);
        } catch (SingularMatrixException e){
            JOptionPane.showMessageDialog(null,
                    "Error: Please enter a proper circuit with 2 or more components between different nodes." +
                            " See the guide for examples.");
        }
        return solution.toArray();
    }

}
