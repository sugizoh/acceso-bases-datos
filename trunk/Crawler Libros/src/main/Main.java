package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.*;


/**
 *
 * @author Miguel González Gómez & Jaime Báred Lobato
 */
public class Main {

	final int CUANTOS = 5;
	public Main() throws IOException {
		String sFichero = "libros.txt";
		File fichero = new File(sFichero);
	    BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));

		for(int i=0; i<CUANTOS; i++)
		{
			System.out.println("Crawling por la página: " + (int)(i + 1) + " de " + CUANTOS);

			String htmlWeb = "";
			boolean conexionValida = false;
			while(!conexionValida)
			{
				try{
		            URL my_url = new URL("http://www.casadellibro.com/busquedas/resultados?orden=1&sl1=7&totalres=50694&page=" + i);

		            BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream()));
		            String strTemp = "";

		            while(null != (strTemp = br.readLine())) {
		            	htmlWeb += strTemp;
		            }
		            conexionValida = true;
		        } catch (Exception ex) {
		            System.out.println("Reintentando conexión... (Pagina: " + (i + 1) + " de " + CUANTOS + ")");
		        }
			}

			ArrayList <String>titulos = new ArrayList<String>();
			ArrayList <String>autores = new ArrayList<String>();
			ArrayList <String>precios = new ArrayList<String>();
			ArrayList <String>editoriales = new ArrayList<String>();
			//Sacar imagen
			//Ver que mas datos sacar [coger la url para acceder a la desc.?]
			titulos = cargarTitulos(htmlWeb);
			autores = cargarAutores(htmlWeb);
			precios = cargarPrecios(htmlWeb);
			editoriales = cargarEditoriales(htmlWeb);

			for(int j=0; j<titulos.size(); j++) {
				bw.write("Título: " + titulos.get(j) + "\n");
				bw.write("Autor:_" + autores.get(j) + "\n");
				bw.write("Editorial: " + editoriales.get(j) + "\n");
				bw.write("Precio: " + precios.get(j) + "\n");
				bw.write("-----------------" + "\n");
			}
		}
		bw.close();
		System.out.println("<< FIN DE LA CARGA DE LIBROS >>");
	}

	public static void main(String[] args) throws IOException {
		Main main = new Main();
	}

	public ArrayList<String> cargarTitulos(String htmlWeb) {
		ArrayList <String>titulos = new ArrayList<String>();

		//Ahora parseamos los datos de la web para obtener los nombres de los libros
        String regexTitulos = "<p class=\"tit\">(.*?)</p>";
        String regexEnlace = "<a (.*?)>(.*?)</a>";
        titulos = obtenerArrayRegex(obtenerArrayRegex(htmlWeb, regexTitulos, 1), regexEnlace, 2);

        return titulos;
	}

	public ArrayList<String> cargarAutores(String htmlWeb) {
		ArrayList <String>autores = new ArrayList<String>();
		String regexAutores = "<p class=\"pAutores\">(.*?)</p>";
		String regexEnlace = "<a (.*?)>(.*?)</a>";
		String regexSpan = "<span class=\"autor\">(.*?)</span>";
		autores = obtenerArrayRegex(obtenerArrayRegex(obtenerArrayRegex(htmlWeb, regexAutores, 1), regexEnlace, 2), regexSpan, 1);
		for(int i=0; i<autores.size(); i++) {
			if(autores.get(i).startsWith("de")) autores.set(i, autores.get(i).substring(3));
		}

		return autores;
	}

	public ArrayList<String> cargarPrecios(String htmlWeb) {
		ArrayList <String>precios = new ArrayList<String>();
		String regexPrecios = "<p class=\"eurosG\">(.*?)</p>";
		precios = obtenerArrayRegex(htmlWeb, regexPrecios, 1);

		return precios;
	}

	public ArrayList<String> cargarEditoriales(String htmlWeb) {
		ArrayList <String>editoriales = new ArrayList<String>();
		String regexEditoriales = "<p class=\"pEditorial\">(.*?)</p>";
		editoriales = obtenerArrayRegex(htmlWeb, regexEditoriales, 1);

		return editoriales;
	}

	public ArrayList<String> obtenerArrayRegex(String texto, String regex, int indice) {
		ArrayList <String>arrayRegex = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(texto);

        while(matcher.find()) {
        	arrayRegex.add(matcher.group(indice));
        }

        return arrayRegex;
	}

	public ArrayList<String> obtenerArrayRegex(ArrayList<String> array, String regex, int indice) {
		ArrayList <String>arrayRegex = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

		for(int i=0; i<array.size();i++) {
			boolean encontrado = false;
			Matcher matcher = pattern.matcher(array.get(i));

	        while(matcher.find()) {
	        	encontrado = true;
	        	arrayRegex.add(matcher.group(indice));
	        }
	        if(!encontrado) arrayRegex.add(array.get(i));
		}
        return arrayRegex;
	}

}
