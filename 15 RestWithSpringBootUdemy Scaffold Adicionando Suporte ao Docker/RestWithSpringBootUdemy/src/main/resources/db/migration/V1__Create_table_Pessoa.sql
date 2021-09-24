CREATE TABLE IF NOT EXISTS `pessoa` (
  `id_pessoa` bigint NOT NULL AUTO_INCREMENT,
  `endereco` varchar(100) NOT NULL,
  `genero` varchar(10) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `sobrenome` varchar(80) NOT NULL,
  PRIMARY KEY (`id_pessoa`)
)