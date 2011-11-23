package main;
import Utilidades.Traductor;
import BD.Consultor;
import GUI.ModeloTabla;
import Utilidades.LecturaTeclado;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
    JTable tablaResultados;
    JScrollPane pane;

    public Main(String nombrePrograma) {
        super(nombrePrograma);

        this.setLayout(null);

        JLabel lblEsquema = new JLabel("Esquea de la base de datos:");
        lblEsquema.setBounds(20,20,400,15);
        lblEsquema.setVisible(true);
        this.add(lblEsquema);

        JLabel lblImagenEsquema = new JLabel();
        lblImagenEsquema.setBounds(10, 40, 980, 80);
        lblImagenEsquema.setIcon(new ImageIcon("esquemaBD.png"));
        this.add(lblImagenEsquema);


        JLabel lblConsulta = new JLabel("Consulta sql");
        lblConsulta.setBounds(20,120,980,40);
        lblConsulta.setVisible(true);
        this.add(lblConsulta);

        txtConsulta = new JTextArea("SELECT substr(titulo,1,10), fechaEdicion, precio, nombreAutor FROM"
                + " Libro, Autor WHERE Libro.idAutor = Autor.idAutor");
        txtConsulta.setBounds(5, 160, 980, 60);
        txtConsulta.setAlignmentX(TOP_ALIGNMENT);
        txtConsulta.setVisible(true);
        this.add(txtConsulta);

        JLabel lblResultados = new JLabel("Resultados consulta");
        lblResultados.setBounds(20,230,980,40);
        lblResultados.setVisible(true);
        this.add(lblResultados);

        btnEjecutarConsulta = new JButton("Ejecutar SQL");
        btnEjecutarConsulta.setBounds(750, 125, 150, 30);
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
        pane.setBounds(10,270, 970, 200);

        this.add(pane);
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         JFrame principal = new Main("Consulta base de datos");
         principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         principal.setBounds(100, 50, 1000, 500);
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
        try {
            String consultaSQL = main.txtConsulta.getText();
            Traductor traductor = new Traductor();
            HashMap<String, String> traducciones = traductor.getConsultasTraducidas(consultaSQL);
            Consultor consultor = new Consultor();
            ModeloTabla tableModel = consultor.lanzarConsulta(traducciones);
            main.tablaResultados.setModel(tableModel);
        } catch (Exception ex) {
            Logger.getLogger(eventosBotones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
