package com.ybigta.project.pubg_small;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;
import java.util.Random;

public class PUBGReducer extends Reducer<IntWritable, PUBG_Info_CustomWritable, Text, PUBG_LC_CustomWritable> {

    private Text outputkey = new Text();
    private PUBG_LC_CustomWritable outputvalue = new PUBG_LC_CustomWritable();
    private float[][] matrix = new float[500000][5];
    private float[][] target_matrix = new float[500000][1];
    private float[][] coefficient;
    private float[][] temp1;
    private float[][] temp2;
    private int num_data = 0;

    @Override
    protected void reduce(IntWritable key, Iterable<PUBG_Info_CustomWritable> values, Context context) throws IOException, InterruptedException {

        for (PUBG_Info_CustomWritable value : values){
            matrix[num_data][0]  =  Float.parseFloat(value.getPlayer_dist_ride().toString());
            matrix[num_data][1]  =  Float.parseFloat(value.getPlayer_dist_walk().toString());
            matrix[num_data][2]  =  Float.parseFloat(value.getPlayer_dmg().toString());
            matrix[num_data][3]  =  Float.parseFloat(value.getPlayer_kills().toString());
            matrix[num_data][4]  =  1;
            target_matrix[num_data][0]  =  Float.parseFloat(value.getPlayer_survive_time().toString());

            num_data ++;
            if (num_data == 500000){break;}
        }

        //회귀 계수 = (X^T * X)^(-1) * X^T * y
        temp1 = inverse(multiplication(transpose(matrix), matrix));
        temp2 = multiplication(transpose(matrix), target_matrix);
        coefficient = multiplication(temp1, temp2);

        outputkey.set("Linear Regression Coeffcient :");
        outputvalue.set(coefficient[0][0], coefficient[1][0], coefficient[2][0], coefficient[3][0], coefficient[4][0]);
        context.write(outputkey, outputvalue);

    }

    private float[][] transpose(float[][] A){
        int row = A.length;
        int col = A[0].length;
        float[][] result = new float[col][row];

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                result[j][i] = A[i][j];
            }
        }

        return result;
    }

    private float[][] multiplication(float[][] A, float[][] B){
        int row = A.length;
        int col = B[0].length;
        int mid = A[0].length;
        int total = 0;
        float[][] result = new float[row][col];

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                for(int k=0;k<mid;k++){
                    total += A[i][k] * B[k][j];
                }

                result[i][j] = total;
                total = 0;
            }
        }
        return result;
    }

    private float[][] inverse(float[][] A) {
        int n = A.length;
        float[][] x = new float[n][n];
        float[][] b = new float[n][n];
        int index[] = new int[n];

        for (int i=0; i<n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        A = gaussian(A, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k] -= A[index[j]][i]*b[index[i]][k];

        // Perform backward substitutions
        for (int i=0; i<n; ++i) {
            x[n-1][i] = b[index[n-1]][i]/A[index[n-1]][n-1];

            for (int j=n-2; j>=0; --j) {
                x[j][i] = b[index[j]][i];

                for (int k=j+1; k<n; ++k) {
                    x[j][i] -= A[index[j]][k]*x[k][i];
                }

                x[j][i] /= A[index[j]][j];
            }
        }
        return x;
    }

    private   float[][] gaussian(float[][] A, int[] index) {
        int n = index.length;
        float c[] = new float[n];

        // Initialize the index
        for (int i=0; i<n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) {
            float c1 = 0;

            for (int j=0; j<n; ++j) {
                float c0 = Math.abs(A[i][j]);
                if (c0 > c1) {c1 = c0;}
            }

            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) {
            float pi1 = 0;

            for (int i=j; i<n; ++i) {
                float pi0 = Math.abs(A[index[i]][j]);
                pi0 /= c[index[i]];

                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) {
                float pj = A[index[i]][j]/A[index[j]][j];

                // Record pivoting ratios below the diagonal
                A[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    A[index[i]][l] -= pj*A[index[j]][l];
            }

        }

        return A;
    }
}
