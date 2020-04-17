package com.example.airhis_proyect;

import android.graphics.Bitmap;
import android.os.StrictMode;

import org.ejml.simple.SimpleMatrix;

import java.io.IOException;

public class Matrix {

    public static SimpleMatrix matrix;
    public static SimpleMatrix values;
    public static SimpleMatrix matrixInterpolation;
    public static SimpleMatrix resultingMatrix;
    private static PointCartesian data[];
    static  int nStations = 19;
    Float[] array;

    /*   private static double n[];
    private static double lat[];
    private static double lon[];
    private static double dat[];
*/
    public static int wc = 100;
    public static int hc = 100;

    public Matrix(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Collector2 data = new Collector2();
            array = data.getPromedio();
        } catch (IOException e) {
            e.printStackTrace();
        }

        data = new PointCartesian[nStations];
        matrix = new SimpleMatrix(nStations, nStations);
        resultingMatrix = new SimpleMatrix(wc, hc);
        values = new SimpleMatrix(nStations, 1);
        matrixInterpolation = new SimpleMatrix(nStations, 1);
        fill();
        fillValues();
        fillMatrix();
        matrixInterpolation();
        //fuctionMatrix();

    }

    public void fillValues(){
        for(int i = 0; i < data.length; i++){
            data[i].setDat(array[i]);
        }
    }


    private static void fill() {
        data[0] = new PointCartesian("81", 463464, 711519, 5.0);
        data[1] = new PointCartesian("3", 450107, 705059, 13.0);
        data[1] = new PointCartesian("3", 450107, 705059, 13.0);
        data[2] = new PointCartesian("82", 444174, 701408, 17.0);
        data[3] = new PointCartesian("87", 437200, 700552, 25.0);
        data[4] = new PointCartesian("86", 438553, 695347, 51.0);
        data[5] = new PointCartesian("85", 429601, 693961, 10.0);
        data[6] = new PointCartesian("25", 436173, 692353, 14.0);
        data[7] = new PointCartesian("80", 439352, 691856, 19.0);
        data[8] = new PointCartesian("94", 444857, 689358, 6.0);
        data[9] = new PointCartesian("83", 432468, 689468, 21.0);
        data[10] = new PointCartesian("79", 432451, 687772, 13.0);
        data[11] = new PointCartesian("84", 437941, 685331, 11.0);
        data[12] = new PointCartesian("44", 439081, 683414, 9.0);
        data[13] = new PointCartesian("28", 433929, 683765, 14.0);
        data[14] = new PointCartesian("88", 435612, 681886, 18.0);
        data[15] = new PointCartesian("38", 428710, 681873, 17.0);
        data[16] = new PointCartesian("78", 428728, 680440, 19.0);
        data[17] = new PointCartesian("90", 431371, 679088, 7.0);
        data[18] = new PointCartesian("69", 429429, 673535, 5.0);
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

    private static void fillMatrix(){
        for(int i = 0 ; i < nStations; i++){
            for(int j = 0 ; j < nStations; j++){
                values.set(i,0, data[i].getDat());
                matrix.set(i, j, functionFundamental(distance(data[i].getX() - data[j].getX(), data[i].getY() - data[j].getY())));
            }
        }
    }

    private static void matrixInterpolation(){
        matrixInterpolation = matrix.invert().mult(values);
    }

    public static double function(double lat1, double lon1){
        double n = 0.0;
        Coordinates coordinates = new Coordinates(lat1, lon1);
        for(int i = 0; i < nStations; i++){
            n += matrixInterpolation.get(i) * distance(coordinates.getX() - data[i].getX(), coordinates.getY() - data[i].getY());
        }
        return n;
    }

    public static double distance(double x, double y){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static double functionFundamental(double r){
        return Math.sqrt(1 + Math.pow(r,2));
    }

    public double functionPlateSpline(double r){
        return Math.pow(r,2) * Math.log(r);
    }

    public double functionTriarmonica(double r){
        return Math.pow(r,3);
    }

    public double functionGaussiana(double r){
        return Math.pow(Math.E, - Math.pow(r, 2));
    }

    public double functionBiarmonica(double r){
        return r;
    }

    private static void fuctionMatrix(){
        for(int i = 0 ; i < wc; i++){
            for(int j = 0 ; j < hc; j++) {
                resultingMatrix.set(i, j, function(i, j));
            }
        }
    }


}
