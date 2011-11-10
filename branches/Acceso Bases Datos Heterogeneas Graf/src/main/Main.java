package main;
import BD.Consultor;
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

    public Main(String nombrePrograma) {
        super(nombrePrograma);

        this.setLayout(null);

        JLabel lblConsulta = new JLabel("Consulta sql");
        lblConsulta.setBounds(20,0,580,40);
        lblConsulta.setVisible(true);
        this.add(lblConsulta);

        txtConsulta = new JTextArea("SELECT * FROM LIBRO;");
        txtConsulta.setBounds(5, 30, 580, 60);
        txtConsulta.setAlignmentX(TOP_ALIGNMENT);
        txtConsulta.setVisible(true);
        this.add(txtConsulta);

        JLabel lblResultados = new JLabel("Resultados consulta");
        lblResultados.setBounds(20,110,580,40);
        lblResultados.setVisible(true);
        this.add(lblResultados);

        btnEjecutarConsulta = new JButton("Ejecutar SQL");
        btnEjecutarConsulta.setBounds(300, 95, 150, 30);
        btnEjecutarConsulta.setVisible(true);
        this.add(btnEjecutarConsulta);

        

        btnEjecutarConsulta.addActionListener(new eventosBotones(this));

        /*
        traductor = new Traductor();
        lectura = new LecturaTeclado();
        
        System.out.println("Escribe la consulta SQL:");
        //SELECT DE PRUEBA, por ej.
        //SELECT idLibro, titulo, ISBN, paginas, stock, fechaEdicion FROM Libro;
        String consultaSQL = lectura.leerTexto();

        HashMap<String, String> traducciones = traductor.getConsultasTraducidas(consultaSQL);
        Consultor consultor = new Consultor();
        ArrayList<String> resultados = consultor.lanzarConsulta(traducciones);

        for(int i=0; i<resultados.size(); i++)
            System.out.println(resultados.get(i));


         */
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         JFrame principal = new Main("Consulta base de datos");
         principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         principal.setBounds(50, 50, 600, 400);
         principal.setVisible(true);
    }
}

class eventosBotones implements ActionListener
{
    Traductor traductor;
    LecturaTeclado lectura;
    JTextArea txtConsulta;
    Main main;

    public eventosBotones(Main main) {
        this.main = main;
        this.traductor = main.traductor;
        this.txtConsulta = main.txtConsulta;
        this.lectura = main.lectura;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String consultaSQL = txtConsulta.getText();

        traductor = new Traductor();

        lectura = new LecturaTeclado();

        HashMap<String, String> traducciones = traductor.getConsultasTraducidas(consultaSQL);
        Consultor consultor = new Consultor();
        ArrayList<String> resultados = consultor.lanzarConsulta(traducciones);

        String[] columnNames = {"Consultas"};
        String[][] datos = new String[resultados.size()][1];

        //Rellenamos los datos
        for(int i=0; i<resultados.size(); i++)
        {
            datos[i][0] = resultados.get(i);;
        }

        JTable tabla = new JTable(datos, columnNames);
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(Color.yellow);

        JScrollPane pane = new JScrollPane(tabla);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        pane.setVisible(true);
        tabla.setVisible(true);

        JPanel panel = new JPanel();

        JButton btn = new JButton("Gas");
        btn.setVisible(true);
        panel.add(btn);

        panel.setBounds(0, 0, 580, 260);

        panel.add(pane);
        panel.setVisible(true);

        main.add(pane);
         

        main.repaint();
    }

}
