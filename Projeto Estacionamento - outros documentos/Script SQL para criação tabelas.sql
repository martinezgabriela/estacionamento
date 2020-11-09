CREATE TABLE tb_cliente (id bigint(20) NOT NULL, email varchar(255) DEFAULT NULL, nome varchar(255) DEFAULT NULL,  telefone varchar(255) DEFAULT NULL,  PRIMARY KEY (id));

CREATE TABLE tb_estacionamento (id bigint(20) NOT NULL, total_vagas_estacionamento int(11) NOT NULL, valor_tarifa double NOT NULL, PRIMARY KEY (id));

CREATE TABLE tb_veiculo (id bigint(20) NOT NULL, cor varchar(255) DEFAULT NULL, marca varchar(255) DEFAULT NULL, placa varchar(255) DEFAULT NULL,
 tipo_veiculo varchar(255) DEFAULT NULL, fk_id_cliente bigint(20) DEFAULT NULL,  PRIMARY KEY (id), FOREIGN KEY (fk_id_cliente) REFERENCES tb_cliente (id));

CREATE TABLE tb_ticket (id bigint(20) NOT NULL, entrada datetime(6) DEFAULT NULL, saida datetime(6) DEFAULT NULL, valor_estadia double NOT NULL,
 fk_id_estacionamento bigint(20) DEFAULT NULL, fk_id_veiculo bigint(20) DEFAULT NULL,  PRIMARY KEY (id),  FOREIGN KEY (fk_id_veiculo) REFERENCES tb_veiculo (id),
 FOREIGN KEY (fk_id_estacionamento) REFERENCES tb_estacionamento (id));

CREATE TABLE tb_usuario (id bigint(20) NOT NULL AUTO_INCREMENT, email varchar(255) DEFAULT NULL, nome varchar(255) DEFAULT NULL,
senha varchar(255) DEFAULT NULL,  PRIMARY KEY (id));

CREATE TABLE tb_perfil (id bigint(20) NOT NULL AUTO_INCREMENT, nome varchar(255) DEFAULT NULL, PRIMARY KEY (id));

CREATE TABLE tb_usuario_perfis (usuario_id bigint(20) NOT NULL, perfis_id bigint(20) NOT NULL, FOREIGN KEY (perfis_id) REFERENCES tb_perfil (id),
 FOREIGN KEY (usuario_id) REFERENCES tb_usuario (id));