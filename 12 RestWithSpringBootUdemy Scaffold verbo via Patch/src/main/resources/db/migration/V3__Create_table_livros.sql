CREATE TABLE `livros` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY,
  `autor` longtext,
  `data_lancamento` datetime(6) NOT NULL,
  `preco` decimal(65,2) NOT NULL,
  `titulo` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
