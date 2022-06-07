Create Table tb_tipoconta(
id BIGINT AUTO_INCREMENT PRIMARY kEY,
tipoconta VARCHAR(20) NOT NULL
);

Create Table tb_tipotransacao(
id BIGINT AUTO_INCREMENT PRIMARY kEY,
tipotransacao VARCHAR(20) NOT NULL
);

Create Table tb_conta(
numconta BIGINT PRIMARY kEY,
agencia INT NOT NULL,
saldo  DECIMAL(19,2),
tipoconta_id BIGINT NOT NULL,
FOREIGN KEY(tipoconta_id) REFERENCES tb_tipoconta(id));


CREATE TABLE tb_usuario(
id BIGINT AUTO_INCREMENT PRIMARY kEY,
cpf VARCHAR(11) NOT NULL,
nome VARCHAR(80) NOT NULL,
contato VARCHAR(30),
numconta BIGINT,
FOREIGN KEY(numconta) REFERENCES tb_conta(numconta));


CREATE TABLE tb_historicotransacoes(
id BIGINT AUTO_INCREMENT PRIMARY kEY,
nconta BIGINT NOT NULL,
tipotransacao_id BIGINT NOT NULL,
valor DECIMAL(19,2) NOT NULL,
data DATETIME NOT NULL,
FOREIGN KEY(nconta) REFERENCES tb_conta(numconta),
FOREIGN KEY(tipotransacao_id) REFERENCES tb_tipotransacao(id));
);


