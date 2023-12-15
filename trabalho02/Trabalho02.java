/*
 * Alunos: Maria Júlia Testoni e Martin Lange de Assis;
 * Professor: Aurélio Faustino Hoppe;
 * Ciência da Computação - FURB
 */
package trabalho02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Trabalho02 {

    public static void main(String[] args) {
        System.out.println("_____Algoritmo de Dijkstra_____");
        System.out.println();
        try {
            BufferedReader leitor = new BufferedReader(new FileReader("src/trabalho02/entradas.txt"));
            String linha;

            // Define o tamanho da matriz com a primeira linha do documento
            linha = leitor.readLine();
            int linhaN = Integer.parseInt(linha);
            String[][] matriz = new String[linhaN][linhaN];

            // Define a origem e destino, segunda linha do documento
            linha = leitor.readLine();
            String[] origemDestino = linha.split(" ");

            // matriz dijkstra
            String[][] matrizD = new String[3][linhaN];
            matrizD = initialize(matrizD);

            // calcular o indice da letra no alfabeto
            int indiceOrigem = origemDestino[0].charAt(0) - 'A';
            int indiceDestino = origemDestino[1].charAt(0) - 'A';
            String pivoAtual = origemDestino[0];
            int pivoAtualN = pivoAtual.charAt(0) - 'A';

            matrizD[0][indiceOrigem] = "0";
            matrizD[2][indiceOrigem] = "x";

            int contador = 0;

            // ele preenche a matriz de custo
            while ((linha = leitor.readLine()) != null) {
                linha = linha.trim();
                String[] vetor = linha.split(" ");

                for (int i = 0; i < vetor.length; i++) {
                    matriz[contador][i] = vetor[i];
                }
                contador++;
            }

            leitor.close();

            // for para preencher dj
            for (int i = 0; i < matrizD[0].length - 1; i++) {
                matrizD = relax(matrizD, pivoAtual, matriz);
                pivoAtualN = proxPivo(matrizD, pivoAtual);
                char letra = (char) ('A' + pivoAtualN);
                pivoAtual = letra + "";

            }
            imprimirMatrizD(matrizD);
            imprimirCaminho(matrizD, indiceOrigem, indiceDestino);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[][] initialize(String[][] matrizD) {

        for (int i = 0; i < matrizD[0].length; i++) {
            matrizD[0][i] = "1000";
            matrizD[1][i] = "nil";
            matrizD[2][i] = "-";
        }
        return matrizD;
    }

    private static String[][] relax(String[][] matrizD, String pivoAtual, String[][] matriz) {
        int pivoAtualN = pivoAtual.charAt(0) - 'A';
        for (int i = 0; i < matrizD[0].length; i++) {
            if (Integer.parseInt(
                    matrizD[0][i]) > (Integer.parseInt(matrizD[0][pivoAtualN]) + caminho(pivoAtualN, i, matriz))) {
                matrizD[0][i] = "" + (Integer.parseInt(matrizD[0][pivoAtualN]) + caminho(pivoAtualN, i, matriz));
                matrizD[1][i] = pivoAtual;
            }
        }
        return matrizD;
    }

    public static int caminho(int ponto1, int ponto2, String[][] matriz) {
        // Verifica se os índices são válidos
        if (ponto1 < 0 || ponto1 >= matriz.length || ponto2 < 0 || ponto2 >= matriz.length) {
            throw new IllegalArgumentException("Índices de pontos inválidos.");
        }

        // Verifica se a matriz contém um valor válido
        if (matriz[ponto1][ponto2] == null || matriz[ponto1][ponto2].isEmpty()) {
            throw new IllegalArgumentException("A matriz contém um valor inválido.");
        }

        if (matriz[ponto1][ponto2].equals("I")) {
            return 1000;
        } else {
            try {
                return Integer.parseInt(matriz[ponto1][ponto2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "A matriz contém um valor que não pode ser convertido em um inteiro.", e);
            }
        }
    }

    private static int proxPivo(String[][] matrizD, String pivoAtual) {
        int indice = -1;
        int minValor = Integer.MAX_VALUE; // Traz o maior valor possivel inteiro. Aqui é armazenada a menor distancia

        for (int i = 0; i < matrizD[0].length; i++) {
            // Verifica se o vértice atual é o mesmo do pivoAtual. Se for, pula pro próximo.
            if (String.valueOf((char) ('A' + i)).equals(pivoAtual)) {
                continue;
            }
            // obtém a distancia do vértice de origem ao atual e guarda na variável
            // verticeAtual
            int verticeAtual = Integer.parseInt(matrizD[0][i]);

            // verifica se o vértice atual ainda não foi visitado e se a distncia da origem
            // ao vertice atual é menor valor.
            if (matrizD[2][i].equals("-") && verticeAtual < minValor) {
                indice = i;
                minValor = verticeAtual;
            }
        }
        if (indice != -1) {
            matrizD[2][indice] = "x";
        }
        return indice;
    }

    private static void imprimirCaminho(String[][] matrizD, int origem, int destino) {
        String caminho = "";

        int atual = destino;
        while (atual != origem) {
            caminho = " -> " + (char) ('A' + atual) + caminho;
            atual = matrizD[1][atual].charAt(0) - 'A';
        }
        caminho = (char) ('A' + origem) + caminho;

        System.out.print("Caminho mínimo:");
        System.out.println(caminho);
        System.out.println("Custo: " + matrizD[0][destino]);
    }

    private static void imprimirMatrizD(String[][] matrizD) {
        for (int i = 0; i < matrizD.length; i++) {
            for (int j = 0; j < matrizD[i].length; j++) {
                System.out.printf("%-6s", matrizD[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}