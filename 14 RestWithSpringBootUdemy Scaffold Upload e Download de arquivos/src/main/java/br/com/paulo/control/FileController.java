package br.com.paulo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.paulo.data.vo.v1.UploadFileResponseVO;
import br.com.paulo.servicos.FileStorageService;
import io.swagger.annotations.Api;

@Api(tags = "FileEndpoint")
@RestController
@RequestMapping("/api/arquivo/v1")
public class FileController {
	
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/uploadFile")
	public UploadFileResponseVO	uploadFile(@RequestParam("file") MultipartFile file) {
		
		String nomeArquivo = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().
				path("/api/arquivo/v1/downloadFile").
				path(nomeArquivo).toString();
		return new UploadFileResponseVO(nomeArquivo, fileDownloadUri,file.getContentType(),file.getSize());
	}

}
