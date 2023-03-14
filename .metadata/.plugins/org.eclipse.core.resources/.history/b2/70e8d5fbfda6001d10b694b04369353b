package br.dio.exerciciosobrecarga;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int escolha = 0;
		
		Area area = new Area();
		Scanner opcao = new Scanner(System.in);
		
		
		System.out.println("=========== Cuso DIO: Entendendo Métodos Java ===========");
		System.out.println("Descrição: Bem vindo a minha aplicação java do exercício pratico da aula de Sobrecarga!");
		System.out.println("=========================================================");
		System.out.println("Exercício: Crie uma aplicação que calcula a área dos 3 quadriláteros notáveis: quadrado, retângulo e trapézio.");
		System.out.println("=========================================================");
		System.out.println("Escolha a opção que deseja acessar: ");
		System.out.println("Escolha 1 para Quadrado");
		System.out.println("Escolha 2 para Retângulo");
		System.out.println("Escolha 3 para Trapézio");
		System.out.print("Opção: ");
		escolha = opcao.nextInt();
		
		if (escolha == 1) { //Quadrado
			area.quadrado(3, 3);
			area.quadrado(5, 5);
			area.quadrado(10, 10);
		} else if (escolha == 2) { // Retângulo
			area.retangulo(3, 5);
			area.retangulo(5, 3);
			area.retangulo(10, 5);
		} else if (escolha == 3) { // Trapézio
			area.trapezio(10, 5, 4);
			area.trapezio(15, 10, 9);
			area.trapezio(20, 15, 14);
		} else {
			System.out.println("Erro.Você é burro? ou se faz? é opção 1, 2 ou 3...");
		}
		
	} 

}
