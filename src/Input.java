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
                fillTextArea(txtA_startingPoints,polygonStart);
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
                            translation(tx,ty);
                        }catch (NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "Error Not a Number");
                        }
                        break;
                    case "rp":
                        break;
                    case "ro":
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
    }

    private void translation(int tx, int ty) {
        for(int i=0; i<polygonStart.npoints;i++){
            int x = polygonStart.xpoints[i]+tx;
            int y = polygonStart.ypoints[i]+ty;
            polygonEnd.addPoint(x,y);
        }

        fillTextArea(txtA_endPoints, polygonEnd);
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
            textArea.append(i + 1 + " x: " + polygon.xpoints[i] + " y: " + polygon.ypoints[i] + "\n");
        }
    }

    private void setAllInvisible(){
        txt_tx.setVisible(false);
        txt_ty.setVisible(false);
    }
}
