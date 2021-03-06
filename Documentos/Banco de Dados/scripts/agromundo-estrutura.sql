
DROP SCHEMA AGROMUNDO CASCADE;

CREATE SCHEMA AGROMUNDO;


CREATE SEQUENCE agromundo.seq_usuario;

CREATE TABLE agromundo.Usuario (
                id_usuario BIGINT NOT NULL DEFAULT nextval('agromundo.seq_usuario'),
                ds_login VARCHAR(80) NOT NULL,
                cd_senha VARCHAR(128) NOT NULL,
                ds_nome VARCHAR(200) NOT NULL,
                cd_tipo_usuario VARCHAR(2) NOT NULL,
                CONSTRAINT id_usuario PRIMARY KEY (id_usuario)
);
COMMENT ON TABLE agromundo.Usuario IS 'Responsável por armazenar os usuários';
COMMENT ON COLUMN agromundo.Usuario.id_usuario IS 'identificador do usuario';
COMMENT ON COLUMN agromundo.Usuario.ds_login IS 'login do usuário';
COMMENT ON COLUMN agromundo.Usuario.cd_senha IS 'Senha do usuário';
COMMENT ON COLUMN agromundo.Usuario.ds_nome IS 'Nome do usuario';
COMMENT ON COLUMN agromundo.Usuario.cd_tipo_usuario IS 'Código do tipo de usuário: 1- Administrador, 2 - Operador, 3 - cliente para acesso via web-service, 4 cliente';


ALTER SEQUENCE agromundo.seq_usuario OWNED BY agromundo.Usuario.id_usuario;

CREATE UNIQUE INDEX cliente_especial_idx
 ON agromundo.Usuario
 ( ds_login );

CREATE SEQUENCE agromundo.seq_tipo_produto;

CREATE TABLE agromundo.Tipo_Produto (
                id_tipo_produto BIGINT NOT NULL DEFAULT nextval('agromundo.seq_tipo_produto'),
                ds_nome VARCHAR(300) NOT NULL,
                cd_unidade_medida VARCHAR(2) NOT NULL,
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
                cd_id_externo_fornecedor BIGINT NOT NULL,
                CONSTRAINT id_fornecedor PRIMARY KEY (id_fornecedor)
);
COMMENT ON TABLE agromundo.Fornecedor IS 'Tabela responsável por armazenar o fornecedor';
COMMENT ON COLUMN agromundo.Fornecedor.id_fornecedor IS 'identificador do fornecedor';
COMMENT ON COLUMN agromundo.Fornecedor.tp_fornecedor IS 'Tipo do fornecedor, 1 - Fabricante; 2- Revendedor';
COMMENT ON COLUMN agromundo.Fornecedor.cd_id_externo_fornecedor IS 'id externo fornecedor';


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

CREATE SEQUENCE agromundo.seq_produto;

CREATE TABLE agromundo.Produto (
                id_produto BIGINT NOT NULL DEFAULT nextval('agromundo.seq_produto'),
                fk_id_tipo_produto BIGINT NOT NULL,
                ds_nome VARCHAR(250) NOT NULL,
                fk_id_fornecedor BIGINT NOT NULL,
                qtd_medida NUMERIC(8,2) NOT NULL,
                dt_validade DATE NOT NULL,
                tp_toxico INTEGER NOT NULL,
                vr_valor_custo NUMERIC(8,2) NOT NULL,
                vr_valor_venda NUMERIC(8,2) NOT NULL,
                CONSTRAINT id_produto PRIMARY KEY (id_produto)
);
COMMENT ON TABLE agromundo.Produto IS 'Tabela responsável por armazenar produtos';
COMMENT ON COLUMN agromundo.Produto.id_produto IS 'Identificação do produto';
COMMENT ON COLUMN agromundo.Produto.fk_id_tipo_produto IS 'Tipo do produto';
COMMENT ON COLUMN agromundo.Produto.ds_nome IS 'Nome do produto';
COMMENT ON COLUMN agromundo.Produto.fk_id_fornecedor IS 'Fornecedor';
COMMENT ON COLUMN agromundo.Produto.qtd_medida IS 'Representação em quilos ou litros do produto';
COMMENT ON COLUMN agromundo.Produto.dt_validade IS 'Data de validade do produto';
COMMENT ON COLUMN agromundo.Produto.tp_toxico IS '1 - Agrotoxico, 2 - Não agrotoxico';
COMMENT ON COLUMN agromundo.Produto.vr_valor_custo IS 'Valor de custo do produto';
COMMENT ON COLUMN agromundo.Produto.vr_valor_venda IS 'Valor de venda do produto';


ALTER SEQUENCE agromundo.seq_produto OWNED BY agromundo.Produto.id_produto;

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
