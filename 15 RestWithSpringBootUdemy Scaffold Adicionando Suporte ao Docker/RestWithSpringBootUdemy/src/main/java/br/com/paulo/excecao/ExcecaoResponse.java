package br.com.paulo.excecao;

import java.io.Serializable;
import java.util.Date;

public class ExcecaoResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date dataHora;
	private String mensagem;
	private String detalhes;
	
	public ExcecaoResponse(Date dataHora, String mensagem, String detalhes) {
		super();
		this.dataHora = dataHora;
		this.mensagem = mensagem;
		this.detalhes = detalhes;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getDetalhes() {
		return detalhes;
	}
	
}
