
--DROP SCHEMA AGROMUNDO CASCADE;

CREATE SCHEMA AGROMUNDO;

CREATE SEQUENCE agromundo.seq_cliente_especial;

CREATE TABLE agromundo.Cliente_Especial (
                id_cliente_especial BIGINT NOT NULL DEFAULT nextval('agromundo.seq_cliente_especial'),
                ds_login VARCHAR(80) NOT NULL,
                cd_senha VARCHAR(128) NOT NULL,
                ds_nome VARCHAR(200) NOT NULL,
                CONSTRAINT id_cliente_especial PRIMARY KEY (id_cliente_especial)
);
COMMENT ON TABLE agromundo.Cliente_Especial IS 'Responsável por armazenar clientes que utilizam o web-service';
COMMENT ON COLUMN agromundo.Cliente_Especial.id_cliente_especial IS 'identificador do cliente';
COMMENT ON COLUMN agromundo.Cliente_Especial.ds_login IS 'login do usuário';
COMMENT ON COLUMN agromundo.Cliente_Especial.cd_senha IS 'Senha do usuário';
COMMENT ON COLUMN agromundo.Cliente_Especial.ds_nome IS 'Nome do cliente';


ALTER SEQUENCE agromundo.seq_cliente_especial OWNED BY agromundo.Cliente_Especial.id_cliente_especial;

CREATE UNIQUE INDEX cliente_especial_idx
 ON agromundo.Cliente_Especial
 ( ds_login );

CREATE SEQUENCE agromundo.seq_tipo_produto;

CREATE TABLE agromundo.Tipo_Produto (
                id_tipo_produto BIGINT NOT NULL DEFAULT nextval('agromundo.seq_tipo_produto'),
                ds_nome VARCHAR(300) NOT NULL,
                cd_unidade_medida DOUBLE PRECISION,
                CONSTRAINT id_tipo_produto PRIMARY KEY (id_tipo_produto)
);
COMMENT ON TABLE agromundo.Tipo_Produto IS 'responsável por armazenar tipo de produtos agropecuarios';
COMMENT ON COLUMN agromundo.Tipo_Produto.id_tipo_produto IS 'Identificador do tipo do produto';
COMMENT ON COLUMN agromundo.Tipo_Produto.ds_nome IS 'Nome do tipo do produto';
COMMENT ON COLUMN agromundo.Tipo_Produto.cd_unidade_medida IS 'Unidade de medida: 1- Litro, 2 - Quilograma';


ALTER SEQUENCE agromundo.seq_tipo_produto OWNED BY agromundo.Tipo_Produto.id_tipo_produto;

CREATE SEQUENCE agromundo.seq_fornecedor;

CREATE TABLE agromundo.Fornecedor (
                id_fornecedor BIGINT NOT NULL DEFAULT nextval('agromundo.seq_fornecedor'),
                tp_fornecedor VARCHAR NOT NULL,
                ds_nome_fantasia VARCHAR(250),
                ds_razao_social VARCHAR(250) NOT NULL,
                cd_cnpj VARCHAR(14) NOT NULL,
                ds_email VARCHAR(250) NOT NULL,
                CONSTRAINT id_fornecedor PRIMARY KEY (id_fornecedor)
);
COMMENT ON TABLE agromundo.Fornecedor IS 'Tabela responsável por armazenar o fornecedor';
COMMENT ON COLUMN agromundo.Fornecedor.id_fornecedor IS 'identificador do fornecedor';
COMMENT ON COLUMN agromundo.Fornecedor.tp_fornecedor IS 'Tipo do fornecedor, 1 - Fabricante; 2- Revendedor';
COMMENT ON COLUMN agromundo.Fornecedor.ds_nome_fantasia IS 'Nome do Fornecedor';
COMMENT ON COLUMN agromundo.Fornecedor.ds_razao_social IS 'Razão social do fornecedor';
COMMENT ON COLUMN agromundo.Fornecedor.cd_cnpj IS 'Cadastro Nacional da Pessoa Jurídica';
COMMENT ON COLUMN agromundo.Fornecedor.ds_email IS 'Email do fornecedor';


ALTER SEQUENCE agromundo.seq_fornecedor OWNED BY agromundo.Fornecedor.id_fornecedor;

CREATE SEQUENCE agromundo.seq_embalagem;

CREATE TABLE agromundo.Embalagem (
                id_embalagem BIGINT NOT NULL DEFAULT nextval('agromundo.seq_embalagem'),
                ds_nome VARCHAR(250) NOT NULL,
                fk_id_fornecedor BIGINT NOT NULL,
                CONSTRAINT id_embalagem PRIMARY KEY (id_embalagem)
);
COMMENT ON TABLE agromundo.Embalagem IS 'Responsável por armazenar a embalagem';
COMMENT ON COLUMN agromundo.Embalagem.id_embalagem IS 'identificador da embalagem';
COMMENT ON COLUMN agromundo.Embalagem.ds_nome IS 'Nome da embalagem';
COMMENT ON COLUMN agromundo.Embalagem.fk_id_fornecedor IS 'Fornecedor';


ALTER SEQUENCE agromundo.seq_embalagem OWNED BY agromundo.Embalagem.id_embalagem;

CREATE TABLE agromundo.Produto (
                id_produto BIGINT NOT NULL,
                fk_id_tipo_produto BIGINT NOT NULL,
                ds_nome VARCHAR(250) NOT NULL,
                fk_id_fornecedor BIGINT NOT NULL,
                qtd_medida DOUBLE PRECISION,
                dt_validade DATE NOT NULL,
                CONSTRAINT id_produto PRIMARY KEY (id_produto)
);
COMMENT ON TABLE agromundo.Produto IS 'Tabela responsável por armazenar produtos';
COMMENT ON COLUMN agromundo.Produto.id_produto IS 'Identificação do produto';
COMMENT ON COLUMN agromundo.Produto.fk_id_tipo_produto IS 'Tipo do produto';
COMMENT ON COLUMN agromundo.Produto.ds_nome IS 'Nome do produto';
COMMENT ON COLUMN agromundo.Produto.fk_id_fornecedor IS 'Fornecedor';
COMMENT ON COLUMN agromundo.Produto.qtd_medida IS 'Representação em quilos ou litros do produto';
COMMENT ON COLUMN agromundo.Produto.dt_validade IS 'Data de validade do produto';


ALTER TABLE agromundo.Produto ADD CONSTRAINT tipo_produto_produto_fk
FOREIGN KEY (fk_id_tipo_produto)
REFERENCES agromundo.Tipo_Produto (id_tipo_produto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE agromundo.Produto ADD CONSTRAINT fornecedor_produto_fk
FOREIGN KEY (fk_id_fornecedor)
REFERENCES agromundo.Fornecedor (id_fornecedor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE agromundo.Embalagem ADD CONSTRAINT fornecedor_embalagem_fk
FOREIGN KEY (fk_id_fornecedor)
REFERENCES agromundo.Fornecedor (id_fornecedor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
