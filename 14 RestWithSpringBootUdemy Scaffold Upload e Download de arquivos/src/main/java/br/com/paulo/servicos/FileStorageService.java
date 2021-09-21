package br.com.paulo.servicos;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.paulo.config.FileStorageConfig;
import br.com.paulo.excecao.FileStorageExecao;

@Service
public class FileStorageService {

	
	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {
		
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {

			throw new FileStorageExecao("Não foi possivel criar o diretorio para armazenar os arquivos", e);
			
		}
	}
	
	public String storeFile(MultipartFile file) {
		String nomeArquivo = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(nomeArquivo.contains("..")) {
				throw new FileStorageExecao("Desculpa! caminho invalido");
			}
			Path targetLocation = this.fileStorageLocation.resolve(nomeArquivo);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return nomeArquivo;
		} catch (Exception e) {
			throw new FileStorageExecao("não foi possível armazenar o arquivo " +nomeArquivo+" por favor tente novamente", e);
		}
	}
	

}
