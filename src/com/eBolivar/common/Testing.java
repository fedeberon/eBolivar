package com.eBolivar.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
		FileReader fr = new FileReader("C:\\CLOACAS.21.DIF");
		BufferedReader bf = new BufferedReader(fr);
//		BufferedReader bf = new BufferedReader(new FileReader("CLOACAS.21.DIF"));
		String sCadena;
		int cont =0;
			while ((sCadena = bf.readLine())!=null) {
				
				if(cont > 5){
				 
					System.out.println(sCadena.subSequence(25, sCadena.length()));
				   
				}
				cont++;
			}
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
 
	}

}
