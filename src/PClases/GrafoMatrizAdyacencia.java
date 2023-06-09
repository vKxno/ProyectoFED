//Carballo Cano Christian Noe
package PClases;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.UnsupportedOperationException;
import java.lang.RuntimeException;

public class GrafoMatrizAdyacencia {
    private final int MAXIMO_VERTICES;
    private final int MAXIMO_ARISTAS;
    private int aristas;
    private int matrix[][];
    
    /**
     * Crea un grafo por matrices de adyacencia
     *
     * @param nroVertices numero de vertices
     * @param nroAristas numero de aristas
     */
    
    public GrafoMatrizAdyacencia(int nroVertices, int nroAristas) {
        MAXIMO_VERTICES = nroVertices;
        MAXIMO_ARISTAS = nroAristas;
        this.aristas = 0;

        matrix = new int[MAXIMO_VERTICES][MAXIMO_VERTICES];
    
        for (int i = 0; i < getMax_VERTICES(); i++){
            for (int j = 0; j < getMax_VERTICES(); j++){
                matrix[i][j] = 0;
            }
        }
    }
    
    /**
     * crea un grafo. Metodo general
     *
     * @param nroVertices
     */
    public GrafoMatrizAdyacencia(int nroVertices){
        this(nroVertices, nroVertices);
    }
    
    public int getMax_VERTICES(){
        return MAXIMO_VERTICES;
    }
    
    public int getMAX_ARISTAS(){
        return MAXIMO_ARISTAS;
    }
    
    /**
     * Inserta una arista dada la relacion entre vertice1 y vertice2 y la
     * distancia entre los vertices dados
     *
     * @param v1 vertice1
     * @param v2 vertice2
     * @param dist distancia entre los dos vertices
     * @throws ArrayIndexOutOfBoundsException
     * @throws UnsupportedOperationException
     */
    
    public void insertaArista(int v1, int v2, int dist)
            throws ArrayIndexOutOfBoundsException, UnsupportedOperationException {
        if (v1 >= MAXIMO_VERTICES || v2 >= MAXIMO_VERTICES) {
            throw new ArrayIndexOutOfBoundsException(
                    "Vertices inválidos, fuera de rango" + "nRango de vertices: 0 - " + (getMax_VERTICES() - 1));
        } else if (aristas == MAXIMO_ARISTAS) {
            throw new UnsupportedOperationException("No se puede añadir más aristas");
        } else {
            matrix[v1][v2] = dist;
        }
    }
    
    /**
     * Este metodo nos indica si existe una arista entre un par de vertice
     * dados.
     *
     * @param v1 vertice1
     * @param v2 vertice2
     * @return verdadero o falso.
     */
    
    public boolean existeArista(int v1, int v2) {
        if (v1 >= MAXIMO_VERTICES || v2 >= MAXIMO_VERTICES) {
            throw new ArrayIndexOutOfBoundsException(
                    "Vertices inválidos, fuera de rango" + "nRango de vertices: 0 - " + (getMax_VERTICES() - 1));
        } else if (matrix[v1][v2] != 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Eliminar arista
     *
     * @param v1 vertice1
     * @param v2 vertice2
     */
    
    public void eliminaArista(int v1, int v2) {
        if (v1 >= MAXIMO_VERTICES || v2 >= MAXIMO_VERTICES) {
            throw new ArrayIndexOutOfBoundsException(
                    "Vertices inválidos, fuera de rango" + "nRango de vertices: 0 - " + (getMax_VERTICES() - 1));
        } else if (matrix[v1][v2] == 0) {
            System.err.println("La arista NO existe");
        } else {
            matrix[v1][v2] = 0;
        }
    }
    
    public void borrarGrafo() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }
    
    public void impMatrix() {
        System.out.print(" ");
        for (int i = 0; i < MAXIMO_VERTICES; i++) {
            System.out.printf("  %3d", i);
        }
        System.out.println();
        for (int i = 0; i < MAXIMO_VERTICES; i++) {
            System.out.printf(" %3d", i);
            for (int j = 0; j < MAXIMO_VERTICES; j++) {
                System.out.printf(" %3d", matrix[i][j]);
            }
            System.out.println();
        }
    }
    
    // ----- Operaciones para obtener Lista de Adyacencia ----- //
    public boolean grafoVerticesAdyacentes(int v) {
        int auxiliar = 0;
        boolean estavacia = true;

        while (auxiliar < MAXIMO_VERTICES && estavacia) {
            if (matrix[v][auxiliar] == 1) {
                estavacia = false;
            } else {
                auxiliar = auxiliar + 1;
            }
        }

        return estavacia;
    }
    
    public int primeroListaAdy(int v) throws RuntimeException {
        int auxiliar = 0;
        boolean estavacia = true;

        while (auxiliar < MAXIMO_VERTICES && estavacia) {
            if (matrix[v][auxiliar] == 1) {
                estavacia = false;
            } else {
                auxiliar = auxiliar + 1;
            }
        }

        if (auxiliar == MAXIMO_VERTICES) {
            throw new RuntimeException("La lista de Adyacencia esta vacía");
        }
        return auxiliar;
    }
    
    public int proxAdy(int v, int ady) {
        int proximo = ady + 1;
        while (proximo < MAXIMO_VERTICES && matrix[v][proximo] == 0) {
            proximo = proximo + 1;
        }
        if (proximo == MAXIMO_VERTICES) {
            return -1;
        }
        return proximo;
    }
    
    public static void main(String[] args) {
        GrafoMatrizAdyacencia eGrafo2 = new GrafoMatrizAdyacencia(5, 10); // Crear un grafo con 5 vértices y capacidad para 10 aristas

        eGrafo2.insertaArista(0, 1, 10);
        eGrafo2.insertaArista(0, 2, 15);
        eGrafo2.insertaArista(1, 2, 20);

        eGrafo2.impMatrix();

        boolean existeArista = eGrafo2.existeArista(0, 1);
        System.out.println("¿Existe arista entre 0 y 1? " + existeArista);

        int primerAdyacente = eGrafo2.primeroListaAdy(0);
        System.out.println("Primer adyacente a 0: " + primerAdyacente);

        int siguienteAdyacente = eGrafo2.proxAdy(0, primerAdyacente);
        System.out.println("Siguiente adyacente a 0 después de " + primerAdyacente + ": " + siguienteAdyacente);

        eGrafo2.eliminaArista(0, 1);

        eGrafo2.impMatrix();
    }
}
