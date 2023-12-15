package programa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entidades.Produto;

public class Main {

	public static void main(String[] args) {		
		Scanner sc =new Scanner (System.in);
		ArrayList<Produto> lista = new ArrayList<>();
		
		System.out.println("Digite o caminho do arquivo:");
		String path = "C:\\temp\\in.txt";
		
		try(BufferedReader br = new BufferedReader(new FileReader(path)))
		{
			String linha = br.readLine();
			
			while(linha != null && !linha.isBlank())
			{
				String[] campos = linha.split(",");
				String nome = campos[0];
				Double preco = Double.parseDouble(campos[1]);
				lista.add(new Produto(nome,preco));
				linha = br.readLine();
				
			}
			
			double media = lista.stream()
					.map(p -> p.getPreco())
					.reduce(0.0 , (x,y) -> x + y) /lista.size();
			
			System.out.print("Preco medio: " + String.format("%.2f", media) + "\n");
			
			Comparator<String> comp = (s1 , s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> nome = lista.stream()
					.filter(p -> p.getPreco() < media)
					.map(p -> p.getNome())
					.sorted(comp.reversed())
					.collect(Collectors.toList());
					
			nome.forEach(System.out::println);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		sc.close();
	}

}
