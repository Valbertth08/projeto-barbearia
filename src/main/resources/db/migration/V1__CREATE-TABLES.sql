CREATE TABLE tb_usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    login varchar(100) unique not null,
    senha varchar(9) not null,
    role varchar(100) not null
);

CREATE TABLE tb_cliente (
    id BIGINT PRIMARY KEY,
    nome varchar(200) not null,
    data_aniversario DATE not null,
    telefone  varchar(20) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    cidade varchar(100) not null,
    FOREIGN KEY (id) REFERENCES tb_usuario(id)
);

CREATE TABLE tb_funcionario (
    id BIGINT PRIMARY KEY,
    nome varchar(200) not null,
    telefone varchar(20) not null,
    especialidade varchar(50) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    cidade varchar(100) not null,
    FOREIGN KEY (id) REFERENCES tb_usuario(id)
);
CREATE TABLE tb_servico (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome varchar(100) not null,
    descricao varchar(100) not null,
    preco double not null
);
CREATE TABLE tb_agendamento (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_funcionario BIGINT NOT NULL,
    id_cliente BIGINT NOT NULL,
    id_servico BIGINT NOT NULL,
    data TIMESTAMP not null,
    FOREIGN KEY (id_funcionario) REFERENCES tb_funcionario(id),
    FOREIGN KEY (id_cliente) REFERENCES tb_cliente(id),
    FOREIGN KEY (id_servico) REFERENCES tb_servico(id)
);

CREATE TABLE tb_pagamento (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    agendamento_id BIGINT NOT NULL,
    valor double not null,
    data TIMESTAMP not null,
    forma_pagamento varchar(20),
    FOREIGN KEY (agendamento_id) REFERENCES tb_agendamento(id)
);


