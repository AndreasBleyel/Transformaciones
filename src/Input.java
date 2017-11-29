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
    private JTextArea txtA_allPoints;
    private JTextArea textArea1;
    private JButton btn_transform;

    private Polygon polygon;

    public static void main(String[] args) {
        JFrame frame = new JFrame("InputValues");
        frame.setContentPane(new Input().main);
        frame.setPreferredSize(new Dimension(800, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Input() {

        polygon = new Polygon();

        btn_AddPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int x = Integer.parseInt(txt_addPointX.getText());
                    int y = Integer.parseInt(txt_addPointY.getText());

                    txt_addPointX.setText("");
                    txt_addPointY.setText("");

                    polygon.addPoint(x, y);

                    txtA_allPoints.append(polygon.npoints + " x: " + x + " y: " + y + "\n");

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error Not a Number");
                }
            }
        });

        btn_ChoosePolygon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                polygon.reset();
                clearFields();

                String choosenPolygon = cmb_Polygon.getSelectedItem().toString();
                switch (choosenPolygon) {
                    case "Rectangle":
                        polygon.addPoint(5, 5);
                        txtA_allPoints.append(polygon.npoints + " x: 5  y: 5\n");

                        polygon.addPoint(5, 10);
                        txtA_allPoints.append(polygon.npoints + " x: 5  y: 10\n");

                        polygon.addPoint(10, 5);
                        txtA_allPoints.append(polygon.npoints + " x: 10  y: 5\n");

                        polygon.addPoint(10, 10);
                        txtA_allPoints.append(polygon.npoints + " x: 10  y: 10\n");

                        break;
                    case "Triangle":
                        polygon.addPoint(5, 5);
                        txtA_allPoints.append(polygon.npoints + " x: 5  y: 5\n");

                        polygon.addPoint(7, 10);
                        txtA_allPoints.append(polygon.npoints + " x: 7  y: 10\n");

                        polygon.addPoint(12, 6);
                        txtA_allPoints.append(polygon.npoints + " x: 12  y: 6\n");
                        break;
                    case "Five-sided":
                        polygon.addPoint(5, 5);
                        txtA_allPoints.append(polygon.npoints + " x: 5  y: 5\n");

                        polygon.addPoint(3, 10);
                        txtA_allPoints.append(polygon.npoints + " x: 3  y: 10\n");

                        polygon.addPoint(7, 11);
                        txtA_allPoints.append(polygon.npoints + " x: 7  y: 11\n");

                        polygon.addPoint(10, 2);
                        txtA_allPoints.append(polygon.npoints + " x: 10  y: 2\n");

                        polygon.addPoint(7, 7);
                        txtA_allPoints.append(polygon.npoints + " x: 7  y: 7\n");
                        break;
                    default:
                        break;
                }
            }
        });
        btn_ResetPolygon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                polygon.reset();
                clearFields();
            }
        });
    }

    private void clearFields() {
        txtA_allPoints.setText("");
        txt_addPointX.setText("");
        txt_addPointY.setText("");
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }
}
