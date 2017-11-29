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
    private JTextField txt_pivoteRotationPivot;
    private JTextField txt_gradRotationPivot;
    private JTextField txt_scalingFijoSx;
    private JTextField txt_scalingFijoSy;
    private JTextField txt_scalingFijoPuntoX;
    private JTextField txt_scalingGradS1;
    private JTextField txt_scalingGradS2;
    private JTextField txt_scalingGradGrad;
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

    private Polygon polygonStart;
    private Polygon polygonEnd;

    private String typeOfTransformation;

    public static void main(String[] args) {
        JFrame frame = new JFrame("InputValues");
        frame.setContentPane(new Input().main);
        frame.setPreferredSize(new Dimension(800, 800));
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
                        break;
                    case "ro":
                        try {
                            int grad = Integer.parseInt(txt_gradRotationOrigin.getText());
                            rotacionOrigen(grad);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error Not a Number");
                        }
                        break;
                    case "sp":
                        break;
                    case "sg":
                        break;
                    case "r1":
                        break;
                    case "r2":
                        break;
                    case "r3":
                        break;
                    case "r4":
                        break;
                    case "r5":
                        break;
                    case "r6":
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
                txt_gradRotationOrigin.setVisible(true);
                lbl_gradRotationOrigin.setVisible(true);
                btn_transform.setText("Rotation Origin");
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

    private void rotacionOrigen(int grad) {

        double[][] rotationMatrix = new double[][]{
                { Math.cos(grad), Math.sin(grad)*-1 },
                { Math.sin(grad), Math.cos(grad) }
        };


        System.out.println("Rotation: \n-------");
        System.out.println(rotationMatrix[0][0]+" "+rotationMatrix[0][1]+"\n"+rotationMatrix[1][0]+" "+rotationMatrix[1][1]);
        System.out.println("\n--------");
        double[][] pointToRotate = new double[1][2];
        for (int i = 0; i < polygonStart.npoints; i++) {
            pointToRotate[0][0]=polygonStart.xpoints[i];
            pointToRotate[0][1]=polygonStart.ypoints[i];
            double[][] result = multiplyByMatrix(pointToRotate, rotationMatrix);
            polygonEnd.addPoint((int)Math.round(result[0][0]),(int)Math.round(result[0][1]));
        }

        fillTextArea(txtA_endPoints, polygonEnd);

    }

    private void rotacionPivote() {

    }

    private double[][] multiplyByMatrix(double[][] pointToRotate, double[][] rotationMatrix) {

        int m1ColLength = pointToRotate[0].length; // m1 columns length
        int m2RowLength = rotationMatrix.length;    // m2 rows length

        if (m1ColLength != m2RowLength) return null; // matrix multiplication is not possible

        int mRRowLength = pointToRotate.length;    // m result rows length
        int mRColLength = rotationMatrix[0].length; // m result columns length

        double[][] mResult = new double[mRRowLength][mRColLength];

        for (int i = 0; i < mRRowLength; i++) {         // rows from m1
            for (int j = 0; j < mRColLength; j++) {     // columns from m2
                for (int k = 0; k < m1ColLength; k++) { // columns from m1
                    mResult[i][j] += pointToRotate[i][k] * rotationMatrix[k][j];
                }
            }
        }


        System.out.println(outputMatrix(mResult));

        return mResult;
    }

    private String outputMatrix(double matrix[][]){
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
    }
}
