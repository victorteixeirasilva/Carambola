 package br.fam.Carambola;

import javax.swing.JOptionPane;

public class FormaDePagamento {
	private int codigoDeSeguranca, dataVencimento;
	private String numeroDoCartao, nomeDoTitular, bandeiraCartao;
	
	private String checkBandeiraCartao(String numero1IdEmissor, String numero2IdEmissor) {
		if(numero2IdEmissor.equals("37")) {
			return "AMERICAN EXPRESS";
		} else if (numero2IdEmissor.equals("35")) {
			return "JCB";
		} else if (numero1IdEmissor.equals("4")) {
			return "VISA";
		} else if (numero1IdEmissor.equals("5")) {
			return "MASTER CARD";
		} else if (numero1IdEmissor.equals("6")) {
			return "DISCOVER";
		} else {
			return "Bandeira desconhecida";
		}
	}
	
	private boolean checkValidadeCartao(String numeroCartao) {
		int somaPar = 0;
		int somaImpar = 0;
		int aux;
		
		//PARES
		for(int j = numeroCartao.length()-2;j >= 0;j = j-2) {
			aux = Integer.parseInt(numeroCartao.charAt(j)+"");
			System.out.println("digito: "+aux+" dobro: "+(aux*2)+" soma digitos: "+somaDigitosCartao(aux*2));
			somaPar = somaPar + somaDigitosCartao(aux*2);
		}
		System.out.println("Soma par: "+somaPar);
		
		//IMPARES
		for(int i = numeroCartao.length()-1; i>=0; i=i-2) {
			aux = Integer.parseInt(numeroCartao.charAt(i)+"");
			System.out.println("digito: "+aux+" soma digitos: "+aux);
			somaImpar = somaImpar + aux;
		}
		System.out.println("Soma impar: "+somaImpar);
		
		System.out.println("Soma total pares e impares: "+(somaPar+somaImpar));
		
		if((somaPar+somaImpar)%10==0) {
			return true;
		} else {
			return false;
		}
	}
	
	private int somaDigitosCartao(int numeroCartao) {
		if(numeroCartao<9) {
			return numeroCartao;
		} else {
			return numeroCartao%10 + 1;
		}
		
	}
	
	private void validarCartao(String numeroCartao) {
		if((numeroCartao.length() >= 13)&&(numeroCartao.length() <= 16)) {
			boolean flag = checkValidadeCartao(numeroCartao);
			
			if(flag) {
				JOptionPane.showMessageDialog(null, "Cartão Válido!");
				this.bandeiraCartao = checkBandeiraCartao(numeroCartao.substring(0,1),numeroCartao.substring(0,2));
				JOptionPane.showMessageDialog(null, "Cartão Válido!"
						+ "\n\nNúmero do cartão: "+numeroCartao
						+ "\n\nBandeira do cartão:"+this.bandeiraCartao);
			} else {
				JOptionPane.showMessageDialog(null, "Cartão Inválido!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Número de cartão inválido!");
		}
	}
	
	public void excluirFormaDePagamento() {
		
	}
	
	public void editarFomraDePagamento() {
		
	}
	
	public void verFormaDePagamento() {
		
	}
	
	public void cadastrarFormaDePagamento() {
		String numeroCartao = JOptionPane.showInputDialog("Informe o número do seu cartão: ");
		validarCartao(numeroCartao);
		this.nomeDoTitular = JOptionPane.showInputDialog("Informe o nome completo do titular do cartão: ");
		String codigoSegurancaString = JOptionPane.showInputDialog("Informe o código de segurança: ");
		this.codigoDeSeguranca = Integer.parseInt(codigoSegurancaString);
		String dataVencimentoString = JOptionPane.showInputDialog("Informe a data de vencimento do cartão: \n\nLembrese de pasar a data como Ano-Mês, exemplo (2030-08");
		this.dataVencimento = Integer.parseInt(dataVencimentoString);
		
		
	}

	public String getNumeroDoCartao() {
		return numeroDoCartao;
	}

	public void setNumeroDoCartao(String numeroDoCartao) {
		this.numeroDoCartao = numeroDoCartao;
	}

	public int getCodigoDeSeguranca() {
		return codigoDeSeguranca;
	}

	public void setCodigoDeSeguranca(int codigoDeSeguranca) {
		this.codigoDeSeguranca = codigoDeSeguranca;
	}

	public String getNomeDoTitular() {
		return nomeDoTitular;
	}

	public void setNomeDoTitular(String nomeDoTitular) {
		this.nomeDoTitular = nomeDoTitular;
	}

	public String getBandeiraCartao() {
		return bandeiraCartao;
	}

	public void setBandeiraCartao(String bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}

	public int getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(int dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	

}
