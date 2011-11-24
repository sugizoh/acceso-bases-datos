/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.Point;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Miguel González y Jaime Bárez
 */
public class ModeloTabla extends AbstractTableModel {

  private HashMap<Point,String> lookup;
  private final int rows;
  private final int columns;
  private final String headers[];

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

    @Override
  public int getColumnCount() {
    return columns;
  }

    @Override
  public int getRowCount() {
    return rows;
  }

    @Override
  public String getColumnName(int column) {
    return headers[column];
  }

    @Override
  public Object getValueAt(int row, int column) {
    return lookup.get(new Point(row, column));
  }

  public void setValueAt(String value, int row, int column) {
    if ((rows < 0) || (columns < 0)) {
      throw new IllegalArgumentException("Invalid row/column setting");
    }
    if ((row < rows) && (column < columns)) {
      lookup.put(new Point(row, column), value.toString());
    }
  }
}
