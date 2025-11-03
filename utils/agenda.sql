create database agenda;
use agenda;
create table enderecos(
	id INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(100),
    numero VARCHAR(10),
    cidade VARCHAR(50),
    estado VARCHAR(50)
);
CREATE TABLE contatos(
	id INT auto_increment PRIMARY KEY,
    nome VARCHAR(100),
    celular VARCHAR(20),
    email VARCHAR(100),
    id_endereco INT,
    FOREIGN KEY (id_endereco) REFERENCES enderecos(id)
);

INSERT INTO 
contatos(nome,celular,email) VALUES ("fulano","(45)99999-9898","fulano@gmail.com");