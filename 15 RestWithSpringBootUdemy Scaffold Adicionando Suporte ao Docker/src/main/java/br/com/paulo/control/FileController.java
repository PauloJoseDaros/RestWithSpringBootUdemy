package br.com.paulo.control;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private static final  Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/uploadFile")
	public UploadFileResponseVO	uploadFile(@RequestParam("file") MultipartFile file) {
		
		String nomeArquivo = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().
				path("/api/arquivo/v1/downloadFile/").
				path(nomeArquivo).toUriString();
		return new UploadFileResponseVO(nomeArquivo, fileDownloadUri,file.getContentType(),file.getSize());
	}
	
	@PostMapping("/uploadMultiplosArquivos")
	public List<UploadFileResponseVO>	uploadMultiplosArquivos(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
	}
	
	@GetMapping("/downloadFile/{nomeArquivo:.+}")// PARA {nomeArquivo:.+} PARA ACEITAR EXTENCOES
	public ResponseEntity<Resource> downloadFile(@PathVariable String nomeArquivo, HttpServletRequest request) {
		
		Resource resource = fileStorageService.loadFileAsResource(nomeArquivo);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			logger.info("nao conseguimos determinar o tipo de arquivo");
		}
		
		if(contentType==null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).
				header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+ "\"")
				.body(resource);
	}

}
