package br.com.paulo.conversor;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	//<O,D> Origem e destino
	
	public static <O,D> D parserObjeto(O origen, Class<D> destino){
		return mapper.map(origen, destino);
	}

	public static <O,D> List<D> parserListaObjeto(List<O> origemList, Class<D> destino){
		
		List<D> objetosDestino = new ArrayList<D>();
		for (Object o : origemList) {
			objetosDestino.add(mapper.map(o, destino));
		}
		return objetosDestino;
	}
}
