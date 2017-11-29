import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andi on 28.11.17.
 */
public class Input {
    private JButton btn_Translation;
    private JButton btn_RotationOrigin;
    private JButton btn_RotationPivot;
    private JButton btn_ScalingFijo;
    private JButton btn_ScalingGrad;
    private JButton btn_Reflection;
    private JComboBox cmb_Caso;
    private JButton btn_AddPoint;
    private JTextField txt_addPointX;
    private JTextField txt_addPointY;
    private JButton btn_ResetPolygon;
    private JButton btn_ChoosePolygon;
    private JComboBox cmb_Polygon;
    private JTextField txt_tx;
    private JTextField txt_ty;
    private JTextField txt_gradRotationOrigin;
    private JTextField txt_rotationPivotY;
    private JTextField txt_rotationPivotX;
    private JTextField txt_scalingFijoSx;
    private JTextField txt_scalingFijoSy;
    private JTextField txt_scalingFijoPuntoX;
    private JTextField txt_scalingGradS1;
    private JTextField txt_scalingGradS2;
    private JTextField txt_reflectionPuntoX;
    private JTextField txt_scalingFijoPuntoY;
    private JTextField txt_reflectionPuntoY;
    private JPanel main;
    private JTextArea txtA_startingPoints;
    private JTextArea txtA_endPoints;
    private JButton btn_transform;
    private JLabel lbl_tx;
    private JLabel lbl_ty;
    private JLabel lbl_gradRotationOrigin;
    private JLabel lbl_rotationPivotX;
    private JLabel lbl_rotationPivotY;
    private JLabel lbl_scalFijoSX;
    private JLabel lbl_scalFijoSY;
    private JLabel lbl_scalFijoPuntoX;
    private JLabel lbl_scalFijoPuntoY;
    private JLabel lbl_scalingGradS2;
    private JLabel lbl_scalingGradS1;
    private JLabel lbl_Caso4X;
    private JLabel lbl_Caso4Y;

    private Polygon polygonStart;
    private Polygon polygonEnd;

    private String typeOfTransformation;

    public static void main(String[] args) {
        JFrame frame = new JFrame("InputValues");
        frame.setContentPane(new Input().main);
        frame.setPreferredSize(new Dimension(1000, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Input() {

        polygonStart = new Polygon();
        polygonEnd = new Polygon();
        typeOfTransformation = "";

        btn_AddPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int x = Integer.parseInt(txt_addPointX.getText());
                    int y = Integer.parseInt(txt_addPointY.getText());

                    txt_addPointX.setText("");
                    txt_addPointY.setText("");

                    polygonStart.addPoint(x, y);

                    fillTextArea(txtA_startingPoints, polygonStart);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error Not a Number");
                }
            }
        });

        btn_ChoosePolygon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                polygonStart.reset();
                clearFields();

                String choosenPolygon = cmb_Polygon.getSelectedItem().toString();
                switch (choosenPolygon) {
                    case "Rectangle":
                        polygonStart.addPoint(5, 5);
                        polygonStart.addPoint(5, 10);
                        polygonStart.addPoint(10, 5);
                        polygonStart.addPoint(10, 10);
                        break;
                    case "Triangle":
                        polygonStart.addPoint(5, 5);
                        polygonStart.addPoint(7, 10);
                        polygonStart.addPoint(12, 6);
                        break;
                    case "Five-sided":
                        polygonStart.addPoint(5, 5);
                        polygonStart.addPoint(3, 10);
                        polygonStart.addPoint(7, 11);
                        polygonStart.addPoint(10, 2);
                        polygonStart.addPoint(7, 7);
                        break;
                    case "Tarea":
                        polygonStart.addPoint(30, 6);
                        polygonStart.addPoint(24, 21);
                        polygonStart.addPoint(11, 8);
                        polygonStart.addPoint(-26, 12);
                        polygonStart.addPoint(-10, 4);
                        polygonStart.addPoint(-29, -13);
                        polygonStart.addPoint(-18, -28);
                        polygonStart.addPoint(-8, -14);
                        polygonStart.addPoint(0, 25);
                        polygonStart.addPoint(29, -18);
                        polygonStart.addPoint(12, -4);
                        break;
                    default:
                        break;
                }
                fillTextArea(txtA_startingPoints, polygonStart);
            }
        });
        btn_ResetPolygon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                polygonStart.reset();
                clearFields();
            }
        });
        btn_transform.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                txtA_endPoints.setText("");
                polygonEnd.reset();

                switch (typeOfTransformation) {
                    case "t":
                        try {
                            int tx = Integer.parseInt(txt_tx.getText());
                            int ty = Integer.parseInt(txt_ty.getText());
                            translation(tx, ty);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error Not a Number");
                        }
                        break;
                    case "rp":
                        try {
                            double grad = Math.toRadians(Double.parseDouble(txt_gradRotationOrigin.getText()));
                            int pivotX = Integer.parseInt(txt_rotationPivotX.getText());
                            int pivotY = Integer.parseInt(txt_rotationPivotY.getText());
                            rotacionPivote(pivotX, pivotY, grad);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error Not a Number");
                        }
                        break;
                    case "ro":
                        try {
                            double grad = Math.toRadians(Double.parseDouble(txt_gradRotationOrigin.getText()));
                            rotacionOrigen(grad);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error Not a Number");
                        }
                        break;
                    case "sp":
                        try {
                            double sx = Double.parseDouble(txt_scalingFijoSx.getText());
                            double sy = Double.parseDouble(txt_scalingFijoSy.getText());
                            int pivotX = Integer.parseInt(txt_scalingFijoPuntoX.getText());
                            int pivotY = Integer.parseInt(txt_scalingFijoPuntoY.getText());
                            scalingPuntoFijo(sx, sy, pivotX, pivotY);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error Not a Number");
                        }
                        break;
                    case "sg":
                        try {
                            double s1 = Double.parseDouble(txt_scalingGradS1.getText());
                            double s2 = Double.parseDouble(txt_scalingGradS2.getText());
                            double grad = Math.toRadians(Double.parseDouble(txt_gradRotationOrigin.getText()));
                            scalingGrad(s1, s2, grad);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error Not a Number");
                        }
                        break;
                    case "r1":
                        caso1();
                        break;
                    case "r2":
                        caso2();
                        break;
                    case "r3":
                        caso3();
                        break;
                    case "r4":
                        try {
                            int x = Integer.parseInt(txt_reflectionPuntoX.getText());
                            int y = Integer.parseInt(txt_reflectionPuntoY.getText());
                            caso4(x, y);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error Not a Number");
                        }
                        break;
                    case "r5":
                        caso5();
                        break;
                    case "r6":
                        caso6();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error no method choosen");
                        break;
                }
            }
        });
        btn_Translation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                typeOfTransformation = "t";
                setAllInvisible();
                txt_tx.setVisible(true);
                txt_ty.setVisible(true);
                lbl_tx.setVisible(true);
                lbl_ty.setVisible(true);
                btn_transform.setText("Translation");
                btn_transform.setEnabled(true);
            }
        });
        btn_RotationOrigin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                typeOfTransformation = "ro";
                setAllInvisible();

                txt_gradRotationOrigin.setVisible(true);
                lbl_gradRotationOrigin.setVisible(true);
                btn_transform.setText("Rotation Origin");
                btn_transform.setEnabled(true);
            }
        });
        btn_RotationPivot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                typeOfTransformation = "rp";
                setAllInvisible();

                txt_gradRotationOrigin.setVisible(true);
                lbl_gradRotationOrigin.setVisible(true);
                txt_rotationPivotX.setVisible(true);
                lbl_rotationPivotX.setVisible(true);
                txt_rotationPivotY.setVisible(true);
                lbl_rotationPivotY.setVisible(true);
                btn_transform.setText("Rotation Pivot");
                btn_transform.setEnabled(true);
            }
        });
        btn_ScalingFijo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                typeOfTransformation = "sp";
                setAllInvisible();

                txt_scalingFijoPuntoX.setVisible(true);
                txt_scalingFijoPuntoY.setVisible(true);
                lbl_scalFijoSX.setVisible(true);
                lbl_scalFijoSY.setVisible(true);
                txt_scalingFijoSx.setVisible(true);
                txt_scalingFijoSy.setVisible(true);
                lbl_scalFijoPuntoX.setVisible(true);
                lbl_scalFijoPuntoY.setVisible(true);
                btn_transform.setText("Scaling Pivot");
                btn_transform.setEnabled(true);
            }
        });
        btn_ScalingGrad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                typeOfTransformation = "sg";
                setAllInvisible();

                txt_gradRotationOrigin.setVisible(true);
                lbl_gradRotationOrigin.setVisible(true);
                txt_scalingGradS1.setVisible(true);
                txt_scalingGradS2.setVisible(true);
                lbl_scalingGradS1.setVisible(true);
                lbl_scalingGradS2.setVisible(true);
                btn_transform.setText("Scaling Grad");
                btn_transform.setEnabled(true);
            }
        });
        btn_Reflection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                setAllInvisible();

                String caso = cmb_Caso.getSelectedItem().toString();
                switch (caso) {
                    case "Caso1":
                        typeOfTransformation = "r1";
                        btn_transform.setText("Rotacion Caso1");
                        break;
                    case "Caso2":
                        typeOfTransformation = "r2";
                        btn_transform.setText("Rotacion Caso2");
                        break;
                    case "Caso3":
                        typeOfTransformation = "r3";
                        btn_transform.setText("Rotacion Caso3");
                        break;
                    case "Caso4":
                        typeOfTransformation = "r4";
                        btn_transform.setText("Rotacion Caso4");
                        lbl_Caso4X.setVisible(true);
                        lbl_Caso4Y.setVisible(true);
                        txt_reflectionPuntoX.setVisible(true);
                        txt_reflectionPuntoY.setVisible(true);
                        break;
                    case "Caso5":
                        typeOfTransformation = "r5";
                        btn_transform.setText("Rotacion Caso5");
                        break;
                    case "Caso6":
                        typeOfTransformation = "r6";
                        btn_transform.setText("Rotacion Caso6");
                        break;
                    default:
                        break;
                }

                btn_transform.setEnabled(true);
            }
        });
    }

    private void translation(int tx, int ty) {

        for (int i = 0; i < polygonStart.npoints; i++) {
            int x = polygonStart.xpoints[i] + tx;
            int y = polygonStart.ypoints[i] + ty;
            polygonEnd.addPoint(x, y);
        }

        fillTextArea(txtA_endPoints, polygonEnd);
    }

    private void rotacionOrigen(double grad) {

        double[][] rotationMatrix = new double[][]{
                {Math.cos(grad), Math.sin(grad) * -1},
                {Math.sin(grad), Math.cos(grad)}
        };

        /*System.out.println("Rotation: \n-------");
        System.out.println(rotationMatrix[0][0] + " " + rotationMatrix[0][1] + "\n" + rotationMatrix[1][0] + " " + rotationMatrix[1][1]);
        System.out.println("\n--------");
*/
        double[][] pointToRotate = new double[2][1];
        for (int i = 0; i < polygonStart.npoints; i++) {
            pointToRotate[0][0] = polygonStart.xpoints[i];
            pointToRotate[1][0] = polygonStart.ypoints[i];

            double x = pointToRotate[0][0] * rotationMatrix[0][0] + pointToRotate[1][0] * rotationMatrix[0][1];
            double y = pointToRotate[1][0] * rotationMatrix[0][0] + pointToRotate[1][0] * rotationMatrix[0][0];

            polygonEnd.addPoint((int) Math.round(x), (int) Math.round(y));
        }

        fillTextArea(txtA_endPoints, polygonEnd);

    }

    private void rotacionPivote(int pivotX, int pivotY, double grad) {

        for (int i = 0; i < polygonStart.npoints; i++) {
            int x = pivotX + (int) Math.round((polygonStart.xpoints[i] - pivotX) * Math.cos(grad) - (polygonStart.ypoints[i] - pivotY) * Math.sin(grad));
            int y = pivotY + (int) Math.round((polygonStart.xpoints[i] - pivotX) * Math.sin(grad) + (polygonStart.ypoints[i] - pivotY) * Math.cos(grad));
            //System.out.println(pivotX + "+("+polygonStart.xpoints[i] +"-"+pivotX+")*"+Math.cos(grad)+"+("+polygonStart.ypoints[i]+"-"+pivotY+")*"+Math.sin(grad));
            polygonEnd.addPoint(x, y);
        }

        fillTextArea(txtA_endPoints, polygonEnd);
    }

    private void scalingPuntoFijo(double sx, double sy, int xPivot, int yPivot) {
        for (int i = 0; i < polygonStart.npoints; i++) {
            double x = polygonStart.xpoints[i] * sx + xPivot * (1 - sx);
            double y = polygonStart.ypoints[i] * sy + yPivot * (1 - sy);
            polygonEnd.addPoint((int) Math.round(x), (int) Math.round(y));
        }
        fillTextArea(txtA_endPoints, polygonEnd);

    }

    private void scalingGrad(double s1, double s2, double grad) {

        double[][] scalingMatrix = new double[][]{
                {s1 * Math.pow(Math.cos(grad), 2) + s2 * Math.pow(Math.sin(grad), 2), (s2 - s1) * Math.cos(grad) * Math.sin(grad), 0},
                {(s2 - s1) * Math.cos(grad) * Math.sin(grad), s1 * Math.pow(Math.sin(grad), 2) + s2 * Math.pow(Math.cos(grad), 2), 0},
                {0, 0, 1}
        };

        calculate(scalingMatrix);
        fillTextArea(txtA_endPoints, polygonEnd);
    }

    private void caso1() {

        double[][] reflexionMatrix = new double[][]{
                {1, 0, 0},
                {0, -1, 0},
                {0, 0, 1}
        };
        calculate(reflexionMatrix);
        fillTextArea(txtA_endPoints, polygonEnd);
    }

    private void caso2() {
        double[][] reflexionMatrix = new double[][]{
                {-1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        calculate(reflexionMatrix);
        fillTextArea(txtA_endPoints, polygonEnd);
    }

    private void caso3() {
        double[][] reflexionMatrix = new double[][]{
                {-1, 0, 0},
                {0, -1, 0},
                {0, 0, 1}
        };
        calculate(reflexionMatrix);
        fillTextArea(txtA_endPoints, polygonEnd);
    }

    private void caso4(int x, int y) {

        Polygon tempTrans = new Polygon();
        Polygon tempRota = new Polygon();

        System.out.printf("x: %d, y: %d", x, y);

        double[][] translacionMatrix = new double[][]{
                {1, 0, x * -1},
                {0, 1, y * -1},
                {0, 0, 1}
        };

        double[][] rotacion180 = new double[][]{
                {-1, 0, 0},
                {0, -1, 0},
                {0, 0, 1}
        };

        double[][] translacionMatrixRetour = new double[][]{
                {1, 0, x},
                {0, 1, y},
                {0, 0, 1}
        };

        for (int i = 0; i < polygonStart.npoints; i++) {
            double xt = translacionMatrix[0][0] * polygonStart.xpoints[i] + translacionMatrix[0][1] * polygonStart.ypoints[i] + translacionMatrix[0][2];
            double yt = translacionMatrix[1][0] * polygonStart.xpoints[i] + translacionMatrix[1][1] * polygonStart.ypoints[i] + translacionMatrix[1][2];
            tempTrans.addPoint((int) Math.round(xt), (int) Math.round(yt));
        }
        System.out.println("\n----");

        outputPolygon(tempTrans);
        for (int i = 0; i < tempTrans.npoints; i++) {
            double xr = rotacion180[0][0] * tempTrans.xpoints[i] + rotacion180[0][1] * tempTrans.ypoints[i];
            double yr = rotacion180[1][0] * tempTrans.xpoints[i] + rotacion180[1][1] * tempTrans.ypoints[i];
            tempRota.addPoint((int) Math.round(xr), (int) Math.round(yr));
        }

        System.out.println("----");
        outputPolygon(tempRota);

        for (int i = 0; i < tempRota.npoints; i++) {
            double xf = translacionMatrixRetour[0][0] * tempRota.xpoints[i] + translacionMatrixRetour[0][1] * tempRota.ypoints[i] + translacionMatrixRetour[0][2];
            double yf = translacionMatrixRetour[1][0] * tempRota.xpoints[i] + translacionMatrixRetour[1][1] * tempRota.ypoints[i] + translacionMatrixRetour[1][2];
            polygonEnd.addPoint((int) Math.round(xf), (int) Math.round(yf));
        }

        fillTextArea(txtA_endPoints, polygonEnd);

    }

    private void caso5() {
        double[][] reflexionMatrix = new double[][]{
                {0, 1, 0},
                {1, 0, 0},
                {0, 0, 1}
        };
        calculate(reflexionMatrix);
        fillTextArea(txtA_endPoints, polygonEnd);
    }

    private void caso6() {
        double[][] reflexionMatrix = new double[][]{
                {0, -1, 0},
                {-1, 0, 0},
                {0, 0, 1}
        };
        calculate(reflexionMatrix);
        fillTextArea(txtA_endPoints, polygonEnd);
    }

    private void calculate(double[][] matrix) {
        for (int i = 0; i < polygonStart.npoints; i++) {
            double x = matrix[0][0] * polygonStart.xpoints[i] + matrix[0][1] * polygonStart.ypoints[i];
            double y = matrix[1][0] * polygonStart.xpoints[i] + matrix[1][1] * polygonStart.ypoints[i];
            polygonEnd.addPoint((int) Math.round(x), (int) Math.round(y));
        }
    }

    private double[][] multiplyByMatrix(double[][] pointToRotate, double[][] rotationMatrix) {

        int pointToRotateColLength = pointToRotate[0].length; // m1 columns length
        int rotationMatrixLength = rotationMatrix.length;    // m2 rows length

        if (pointToRotateColLength != rotationMatrixLength) {
            JOptionPane.showMessageDialog(null, "Error matrix multiplication is not possible");
            return null; // matrix multiplication is not possible
        }

        int pointToRotateLength = pointToRotate.length;    // m result rows length
        int rotationMatrixColLength = rotationMatrix[0].length; // m result columns length

        double[][] mResult = new double[pointToRotateLength][rotationMatrixColLength];

        for (int i = 0; i < pointToRotateLength; i++) {         // rows from m1
            for (int j = 0; j < rotationMatrixColLength; j++) {     // columns from m2
                for (int k = 0; k < pointToRotateColLength; k++) { // columns from m1
                    mResult[i][j] += pointToRotate[i][k] * rotationMatrix[k][j];
                }
            }
        }

        return mResult;
    }

    private void outputPolygon(Polygon polygon) {
        for (int i = 0; i < polygon.npoints; i++) {
            System.out.printf("%d: x: %d y: %d\n", i, polygon.xpoints[i], polygon.ypoints[i]);
        }
    }

    private String outputMatrix(double matrix[][]) {
        String result = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result += String.format("%11.2f", matrix[i][j]);
            }
            result += "\n";
        }
        return result;
    }

    private void clearFields() {
        txtA_startingPoints.setText("");
        txtA_endPoints.setText("");
        txt_addPointX.setText("");
        txt_addPointY.setText("");
    }

    private void fillTextArea(JTextArea textArea, Polygon polygon) {
        textArea.setText("");
        for (int i = 0; i < polygon.npoints; i++) {
            textArea.append(i + 1 + ": x: " + polygon.xpoints[i] + " y: " + polygon.ypoints[i] + "\n");
        }
    }

    private void setAllInvisible() {
        txt_tx.setVisible(false);
        txt_ty.setVisible(false);
        lbl_tx.setVisible(false);
        lbl_ty.setVisible(false);
        txt_gradRotationOrigin.setVisible(false);
        lbl_gradRotationOrigin.setVisible(false);
        txt_rotationPivotX.setVisible(false);
        lbl_rotationPivotX.setVisible(false);
        txt_rotationPivotY.setVisible(false);
        lbl_rotationPivotY.setVisible(false);
        txt_scalingFijoPuntoX.setVisible(false);
        txt_scalingFijoPuntoY.setVisible(false);
        lbl_scalFijoSX.setVisible(false);
        lbl_scalFijoSY.setVisible(false);
        txt_scalingFijoSx.setVisible(false);
        txt_scalingFijoSy.setVisible(false);
        lbl_scalFijoPuntoX.setVisible(false);
        lbl_scalFijoPuntoY.setVisible(false);
        txt_scalingGradS1.setVisible(false);
        txt_scalingGradS2.setVisible(false);
        lbl_scalingGradS1.setVisible(false);
        lbl_scalingGradS2.setVisible(false);
        lbl_Caso4X.setVisible(false);
        lbl_Caso4Y.setVisible(false);
        txt_reflectionPuntoX.setVisible(false);
        txt_reflectionPuntoY.setVisible(false);
    }
}
