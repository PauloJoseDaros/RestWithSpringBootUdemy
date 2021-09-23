package br.com.paulo.data.vo.v1;

import java.io.Serializable;

public class UploadFileResponseVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nomeArquivo;
	private String uriDownloadArquivo;
	private String tipoArquivo;
	private long size;
	
	public UploadFileResponseVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UploadFileResponseVO(String nomeArquivo, String uriDownloadArquivo, String tipoArquivo, long size) {
		super();
		this.nomeArquivo = nomeArquivo;
		this.uriDownloadArquivo = uriDownloadArquivo;
		this.tipoArquivo = tipoArquivo;
		this.size = size;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getUriDownloadArquivo() {
		return uriDownloadArquivo;
	}

	public void setUriDownloadArquivo(String uriDownloadArquivo) {
		this.uriDownloadArquivo = uriDownloadArquivo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
