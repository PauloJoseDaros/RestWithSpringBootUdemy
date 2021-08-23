package br.com.paulo.conversores;

public class ConverterNumeros {

	
	public static Double converterDouble(String pStrNumero) {
		
		
		if(pStrNumero==null) return 0d;
		
		String numero = pStrNumero.replaceAll(",",".");
		
		if(ehNumerico(pStrNumero)) return Double.parseDouble(pStrNumero);
		return 0d;
		
	}
	
	public static boolean ehNumerico(String pStrNumero) {
		
		if(pStrNumero==null) return false;
		
		String numero = pStrNumero.replaceAll(",",".");
		return numero.matches("[-+]?[0-9]*\\.?[0-9]+");
		
	}
}
