package GUI;

import java.awt.Point;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

/**
 *Clase que contiene el modelo de los datos de una tabla
 * @author Miguel González y Jaime Bárez
 */
public class ModeloTabla extends AbstractTableModel {

  private HashMap<Point,String> lookup;
  private final int rows;
  private final int columns;
  private final String headers[];
/**
   * Constructor
   * @param datos Array bidimensional que contiene todos los datos
   * @param columnHeaders Cabeceras de las columnas
   */
  public ModeloTabla(String datos[][], String columnHeaders[]) {
    lookup = new HashMap<Point,String>();

    //Rellenamos los datos
    for(int i=0; i<datos.length; i++)
    {
        for(int j=0; j<datos[i].length; j++)
        lookup.put(new Point(i, j), datos[i][j]);
    }

    this.rows = datos.length;
    this.columns = columnHeaders.length;

    headers = columnHeaders;
  }

    /**
   * Devuelve numero de columnas
   * @return numero de columnas
   */
    @Override
  public int getColumnCount() {
    return columns;
  }

    /**
     * Devuelve numero de filas
     * @return numero de filas
     */
    @Override
  public int getRowCount() {
    return rows;
  }

    /**
     * Devuelve el nombre de la columna
     * @param column numero de columna
     * @return nombre de la columna
     */
    @Override
  public String getColumnName(int column) {
    return headers[column];
  }

    /**
     * Devuelve el valor de una posicion
     * @param row numero de columna
     * @param column numero de fila
     * @return posicion
     */
    @Override
  public Object getValueAt(int row, int column) {
    return lookup.get(new Point(row, column));
  }

    /**
     * Introduce un dato en una posicion
     * @param value Valor a introducir
     * @param row Fila del dato
     * @param column Columna del dato
     */
  public void setValueAt(String value, int row, int column) {
    if ((rows < 0) || (columns < 0)) {
      throw new IllegalArgumentException("Invalid row/column setting");
    }
    if ((row < rows) && (column < columns)) {
      lookup.put(new Point(row, column), value.toString());
    }
  }
}
