ALTER TABLE `pessoa`
	ADD COLUMN `ativo` BIT(1) NOT NULL DEFAULT b'1' AFTER `sobrenome`;