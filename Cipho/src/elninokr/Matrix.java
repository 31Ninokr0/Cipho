package elninokr;

public class Matrix {
	public static double[][] subs(double[][] A, double[][] B) {
		if (A[0].length != B[0].length || A.length != B.length) 
			throw new IllegalArgumentException ("Invalid Matrices Size");
		double[][] temp = new double [A.length][A[0].length];
		for(int i = 0; i < A.length; i++) 
			for(int j = 0; j < A[0].length; j++)
				temp[i][j] = A[i][j] - A[i][j]; 
		return temp;
	}
	
	public static double[][] add(double[][] A, double[][] B) {
		if (A[0].length != B[0].length || A.length != B.length) 
			throw new IllegalArgumentException ("Invalid Matrices Size");
		double[][] temp = new double [A.length][A[0].length];
		for(int i = 0; i < A.length; i++) 
			for(int j = 0; j < A[0].length; j++)
				temp[i][j] = A[i][j] + A[i][j]; 
		return temp;
	}
	
	public static void fac(double[][] A, double x) {
		for(int i = 0; i < A.length; i++) {
			for(int j = 0; j < A[0].length; j++)
				A[i][j] = A[i][j] * x;
		}
	}
	
	public static double det(double[][] A){
		if (A.length == 1) {
			return A[0][0];
		}else if(A.length == 2) {
			return (A[0][0] * A[1][1]) - (A[0][1] * A[1][0]);
		}
		
		double temp[][];
		double det = 0;

		for (int i = 0; i < A[0].length; i++) {
			temp = new double[A.length - 1][A[0].length - 1];
			for (int j = 1; j < A.length; j++) 
				for (int k = 0; k < A[0].length; k++) 
					if (k < i) temp[j - 1][k] = A[j][k];
					else if (k > i) temp[j - 1][k - 1] = A[j][k];
			det += A[0][i] * Math.pow (-1, (double) i) * det(temp);
		}
		
		return det;
	}
	
	public static double[][] mul (double[][] A, double[][] B) {
//		A(B).length is # of rows
//		A(B)[0].length is # of columns
		if (A[0].length != B.length) 
			throw new IllegalArgumentException ("Invalid Matrices Size");
		
		double[][] result = new double [A.length][B[0].length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B[0].length; j++) 
				for (int x = 0; x < A[0].length; x++) 
					result[i][j] += (A[i][x] * B[x][j]);
		}
		return result;
	}
	
	public static double[][] inverse(double[][] A) {
		double[][] aux = new double[A.length][A.length];
		double[][] inverse = new double[A.length][A.length];
		int[] index = new int[A.length];
		for (int i = 0; i < A.length; ++i) aux[i][i] = 1;
		UpperTriangle (A, index);
		for (int i = 0; i < (A.length - 1); ++i) {
			for (int j = (i + 1); j < A.length; ++j) 
				for (int k = 0; k < A.length; ++k) 
					aux[index[j]][k] -= A[index[j]][i] * aux[index[i]][k];
		}
		for (int i = 0; i < A.length; ++i) {
			inverse[A.length - 1][i] = (aux[index[A.length - 1]][i] / A[index[A.length - 1]][A.length - 1]);
			for (int j = (A.length - 2); j >= 0; --j) {
				inverse[j][i] = aux[index[j]][i];
				for (int k = (j + 1); k < A.length; ++k) 
					inverse[j][i] -= (A[index[j]][k] * inverse[k][i]);
				inverse[j][i] /= A[index[j]][j];
			}
		}
		return inverse;
	}
	
	public static void UpperTriangle (double[][] matrix, int[] index) {
		double x;
		double y = 0;
		double m, n, v;
		int t;
		int k = 0;

		double[] temp = new double[matrix.length];
		for (int i = 0; i < matrix.length; ++i) index[i] = i;

		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix.length; ++j) {
				x = Math.abs (matrix[i][j]);
				if (x > y) y = x;
			}
			temp[i] = y;
		}

		for (int j = 0; j < (matrix.length - 1); ++j) {
			n = 0;
			for (int h = j; h < matrix.length; ++h) {
				m = Math.abs (matrix[index[h]][j]);
				m /= temp[index[h]];
				if (m > n) n = m; k = h;
			}
			t = index[j];
			index[j] = index[k];
			index[k] = t;
			for (int u = (j + 1); u < matrix.length; ++u) {
				v = matrix[index[u]][j] / matrix[index[j]][j];
				matrix[index[u]][j] = v;
				for (int l = (j + 1); l < matrix.length; ++l) 
					matrix[index[u]][l] -= v * matrix[index[j]][l];
			}
		}
	}
	
	public static void printMatrix(double[][] A) {
		for(int i = 0; i < A.length; i++) {
			if (i > 0) System.out.print("\n");
			for(int j = 0; j < A[0].length; j++)
				System.out.print(A[i][j] + " | ");
		}
		System.out.print("\n");
	}
}
	
