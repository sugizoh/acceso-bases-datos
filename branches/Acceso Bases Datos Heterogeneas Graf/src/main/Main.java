package main;
import BD.Consultor;
import GUI.ModeloTabla;
import Utilidades.LecturaTeclado;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/**
 *
 * @author Jaime Bárez y Miguel González
 * Clase principal del programa
 */
public class Main extends JFrame {
    //
    LecturaTeclado lectura;
    Traductor traductor;
    JTextArea txtConsulta;
    JButton btnEjecutarConsulta;
    JTable tablaResultados;
    JScrollPane pane;

    public Main(String nombrePrograma) {
        super(nombrePrograma);

        this.setLayout(null);

        JLabel lblConsulta = new JLabel("Consulta sql");
        lblConsulta.setBounds(20,0,980,40);
        lblConsulta.setVisible(true);
        this.add(lblConsulta);

        txtConsulta = new JTextArea("SELECT * FROM LIBRO;");
        txtConsulta.setBounds(5, 30, 980, 60);
        txtConsulta.setAlignmentX(TOP_ALIGNMENT);
        txtConsulta.setVisible(true);
        this.add(txtConsulta);

        JLabel lblResultados = new JLabel("Resultados consulta");
        lblResultados.setBounds(20,110,980,40);
        lblResultados.setVisible(true);
        this.add(lblResultados);

        btnEjecutarConsulta = new JButton("Ejecutar SQL");
        btnEjecutarConsulta.setBounds(300, 95, 150, 30);
        btnEjecutarConsulta.setVisible(true);
        this.add(btnEjecutarConsulta);

        btnEjecutarConsulta.addActionListener(new eventosBotones(this));

        String datos[][] = {{""}};
        String col[] = {"Resultados..."};

        ModeloTabla tableModel = new ModeloTabla(datos,col);

        tablaResultados = new JTable(datos,col);
        tablaResultados.setVisible(true);

        JTableHeader header = tablaResultados.getTableHeader();
        header.setBackground(Color.yellow);
        header.setVisible(true);

        pane = new JScrollPane(tablaResultados);
        pane.setVisible(true);
        pane.setBounds(10,150, 970, 200);

        this.add(pane);
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         JFrame principal = new Main("Consulta base de datos");
         principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         principal.setBounds(100, 50, 1000, 400);
         principal.setVisible(true);
    }
}

class eventosBotones implements ActionListener
{
    Main main;

    public eventosBotones(Main main) {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String consultaSQL = main.txtConsulta.getText();

        Traductor traductor = new Traductor();
        HashMap<String, String> traducciones = traductor.getConsultasTraducidas(consultaSQL);
        
        Consultor consultor = new Consultor();

        ModeloTabla tableModel = consultor.lanzarConsulta(traducciones);

        main.tablaResultados.setModel(tableModel);
    }

}
