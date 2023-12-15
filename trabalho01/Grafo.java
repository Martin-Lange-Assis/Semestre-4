//Aluno: Martin Lange de Assis

package trabalho01;

import java.util.ArrayList;

public class Grafo {

	public static void main(String[] args) {
		//int[][] matriz = { { 0, 1, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 1, 0, 0, 0 },
		//		{ 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 1 },
		//		{ 0, 0, 0, 0, 0, 1, 0, 1 }, { 0, 0, 0, 0, 0, 1, 1, 0 } };

		// int[][] matriz = { { 0, 0, 0, 0, 0, }, { 0, 0, 0, 0, 0, }, { 0, 0, 0, 0, 0,
		// }, { 0, 0, 0, 0, 0, },
		// { 0, 0, 0, 0, 0, } };

		// int[][] matriz = { { 0, 1, 0, 2, 0, 0, 0 }, { 1, 0, 1, 0, 1, 0, 0 }, { 0, 1,
		// 0, 0, 1, 0, 0 },
		// { 2, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, {
		// 0, 0, 0, 0, 0, 0, 0 } };

		// int[][] matriz = { { 0, 0, 1, 0, 1 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, {
		// 0, 0, 1, 0, 0 },
		// { 0, 1, 0, 0, 0 } };

		// int[][] matriz = { { 2, 0, 0, 0, 1, 0 }, { 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0,
		// 0, 0 }, { 0, 0, 1, 0, 0, 0 },
		// { 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
		
		int matriz[][] = {{0,1,0,1,1},{1,0,1,0,0},{0,1,0,1,0},{1,0,1,1,0},{1,0,0,0,0}};

		System.out.println("__________________________________");
		System.out.println();
		System.out.println("________MATRIZ DE ADJACÊNCIA______");
		System.out.println();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}

		String tipo = tipoDoGrafo(matriz);
		System.out.println();
		System.out.println("__________________________________");
		System.out.println();
		System.out.println("______________TIPOS_______________");
		System.out.println();
		System.out.println(tipo);
		System.out.println("__________________________________");
		System.out.println();
		System.out.println("________GRAUS DOS VÉRTICES________");
		System.out.println(grausDoVertice(matriz));
		System.out.println("__________________________________");
		System.out.println();
		System.out.println("________ARESTAS DO GRAFO__________");
		System.out.println();
		System.out.println(arestasDoGrafo(matriz));
		System.out.println("__________________________________");
	}

	public static String tipoDoGrafo(int matriz[][]) {
		ArrayList<String> tipos = new ArrayList<>();
		// --DIRIGIDO OU NÃO DIRIGIDO--

		boolean isDirected = false;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] != matriz[j][i]) {
					isDirected = true;
					break;
				}
			}
			if (isDirected) {
				break;
			}
		}

		if (isDirected) {
			tipos.add("Dirigido");
		} else {
			tipos.add("Não Dirigido");
		}

		// --SIMPLES OU MULTIGRAFO--

		boolean multigrafo = false;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (i != j && matriz[i][j] > 1) {
					multigrafo = true;
					break;
				}
			}
			if (matriz[i][i] != 0) {
				multigrafo = true;
				break;
			}
		}
		if (multigrafo) {
			tipos.add("Multigrafo");
		} else {
			tipos.add("Simples");
		}

		// --REGULAR--

		ArrayList<Integer> graus = new ArrayList<>();
		for (int i = 0; i < matriz.length; i++) {
			int grau = 0;
			for (int j = 0; j < matriz.length; j++) {
				grau += matriz[i][j];
			}
			graus.add(grau);
		}
		boolean regular = true;
		for (Integer integer : graus) {
			if (!integer.equals(graus.get(0))) {
				regular = false;
				break;
			}
		}
		if (regular) {
			tipos.add("Regular");
		} else {
			tipos.add("Não Regular");
		}

		// --COMPLETO--

		boolean completo = true;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (j != i && matriz[i][j] == 0) {
					completo = false;
					break;
				}
			}
			if (!completo) {
				break;
			}
		}
		if (completo) {
			tipos.add("Completo");
		} else {
			tipos.add("Não Completo");
		}

		// --NULO--

		boolean nulo = true;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] != 0) {
					nulo = false;
					break;
				}
			}
			if (!nulo) {
				break;
			}
		}
		if (nulo) {
			tipos.add("Nulo");
		} else {
			tipos.add("Não Nulo");
		}

		// --BIPARTIDO--

		boolean bipartido = true;
		int n = matriz.length;
		if (nulo) {
			bipartido = false;
		} else if (n % 2 == 0) {
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					for (int k = j + 1; k < n; k++) {
						if (matriz[i][j] >= 0 && matriz[j][k] >= 0 && matriz[k][i] >= 0) {
							bipartido = false;
							break;
						}
					}
				}
			}
		} else {
			bipartido = false;
		}

		if (bipartido) {
			tipos.add("Bipartido");
		} else {
			tipos.add("Não Bipartido");
		}

		String resposta = String.join(", ", tipos);
		return resposta;
	}

	// MÉTODO QUE VERIFICA A QUANTIDADE E O CONJUNTO DE ARESTAS
	public static String arestasDoGrafo(int matriz[][]) {
		int contador = 0;
		String arestas = "";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] != 0) {
					contador++;
					arestas += "Aresta: " + "(" + (i + 1) + "," + (j + 1) + ")" + "\n";
				}
			}
		}
		String tipo = tipoDoGrafo(matriz);
		if (tipo.contains("Não Dirigido")) {
			contador = contador / 2;
		}
		String numArestas = "Número de Arestas: " + contador + "\n";
		return numArestas + arestas;
	}

	// MÉTODO QUE VERIFICA O GRAU DE CADA VÉRTICE E LISTA
	public static String grausDoVertice(int matriz[][]) {
		String tipo = tipoDoGrafo(matriz);
		String resposta = "";

		if (tipo.contains("Não Dirigido")) {
			int[] graus = new int[matriz.length];
			int somaTotal = 0;

			for (int i = 0; i < matriz.length; i++) {
				int grau = 0;
				for (int j = 0; j < matriz.length; j++) {
					if (i == j && matriz[i][j] != 0) {
						grau += 2 * matriz[i][j];
					} else {
						grau += matriz[i][j];
					}
				}
				graus[i] = grau;
				somaTotal += grau;
			}

			resposta += "(";
			for (int i = 0; i < graus.length; i++) {
				resposta += graus[i];
				if (i < graus.length - 1) {
					resposta += ", ";
				}
			}
			resposta += ")" + "\n" + "Soma Total: " + somaTotal;
		} else if (tipo.contains("Dirigido")) {
			int[] grausEntrada = new int[matriz.length];
			int[] grausSaida = new int[matriz.length];
			int somaTotalEntrada = 0;
			int somaTotalSaida = 0;

			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					grausSaida[i] += matriz[i][j];
					grausEntrada[i] += matriz[j][i];
				}
				somaTotalSaida += grausSaida[i];
				somaTotalEntrada += grausEntrada[i];
			}

			resposta += "Entrada (";
			for (int i = 0; i < matriz.length; i++) {
				resposta += grausEntrada[i];
				if (i < matriz.length - 1) {
					resposta += ", ";
				}
			}
			resposta += ")" + "\n" + "Saída (";
			for (int i = 0; i < matriz.length; i++) {
				resposta += grausSaida[i];
				if (i < matriz.length - 1) {
					resposta += ", ";
				}
			}
			resposta += ")" + "\n" + "Soma Total Entrada: " + somaTotalEntrada + "\n" + "Soma Total Saída: "
					+ somaTotalSaida + "\n" + "Total de Vértices:" + matriz.length;
		}

		return resposta;
	}

}
