package com.example.airhis_proyect;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.ejml.simple.SimpleMatrix;

public class Interpolation extends AppCompatActivity {

    ImageView iv;
    MatrixCartesian matrixx;

    SimpleMatrix matrix;
    SimpleMatrix values;
    SimpleMatrix matrixInterpolation;
    SimpleMatrix resultingMatrix;


    int wc = 100;
    int hc = 100;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolation);
        iv = findViewById(R.id.iv);
        matrixx = new MatrixCartesian();
        volverMatrizABit(makeImg(matrixx.resultingMatrix, 0, 1, 2), wc, hc);
    }


    /*******************************************************************************
     *Metodo que convierte una matriz en bitmap el cual puede ser asignado
     * a un ImageView corresponde a la matriz que se le pasa por parametro
     * @param mat matriz a la cual se le ha aplicado la funcion radial
     * @param w ancho de la matriz
     * @param h largo de la matriz
     * @return un bitmap de la matriz
     *********************************************************************************/

    public Bitmap matToBit(int mat[][][], int w, int h){
        int[] pix = new int[w * h];
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
            {
                int index = y * w + x;
                pix[index] = 0xff000000 | (mat[x][y][0] << 16) | (mat[x][y][1] << 8) | mat[x][y][2];
            }
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);
        bm.setPixels(pix, 0, w, 0, 0, w, h);
        return bm;
    }

    /**************************************************************************
     *Metodo que retorna una matriz de n*n*4 donde la n es
     * el tamaño de la lista recibida por parametro,lo que
     * hace es manejar intervalo de valores y si esta entre ellos
     * asignará un valor correspondiente entre 0-255
     * @param matrixFx matriz con valores promediados
     * @return retorna una matriz con los valores acordes a los intervalos
     *************************************************************************/
    public int[][][] makeImg(SimpleMatrix matrixFx, int r, int g, int b){
        int matriz[][][] = new int[wc][hc][3];
        double f = 0;
        for (int y = 0; y < wc; y++) {
            for (int x = 0; x < hc; x++) {
                //valorPos = (int)matrixFx[x][y];
                f = matrixFx.get(x,y);

                if(f < 12.0){
                    matriz[x][y][r] = 82;
                    matriz[x][y][g] = 145;
                    matriz[x][y][b] = 60;
                }else if(f >= 12.1 && f < 35.4){
                    matriz[x][y][r] = 241;
                    matriz[x][y][g] = 235;
                    matriz[x][y][b] = 106;
                }else if(f >= 35.5 && f < 55.4){
                    matriz[x][y][r] = 211;
                    matriz[x][y][g] = 126;
                    matriz[x][y][b] = 53;
                }else if(f >= 55.5 && f < 150.4){
                    matriz[x][y][r] = 192;
                    matriz[x][y][g] = 63;
                    matriz[x][y][b] = 54;
                }else if(f >= 150.5 && f < 250.4){
                    matriz[x][y][r] = 83;
                    matriz[x][y][g] = 65;
                    matriz[x][y][b] = 142;
                }else if(f >= 250.5){
                    matriz[x][y][r] = 86;
                    matriz[x][y][g] = 51;
                    matriz[x][y][b] = 41;

                }
            }
        }
        return matriz;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void volverMatrizABit(int scale[][][], int w, int h){
        Bitmap imgGray = matToBit(scale,w,h);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imgGray, 1000 /*Ancho*/, 1000 /*Alto*/, false /* filter*/);
        iv.setImageBitmap(resizedBitmap);
        iv.setImageAlpha(90);
    }
}
