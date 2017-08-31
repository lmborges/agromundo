-- insert fornecedores
INSERT INTO agromundo.fornecedor ( tp_fornecedor, ds_nome_fantasia, ds_razao_social, cd_cnpj, ds_email) VALUES ( '1', 'Coca cola', 'Charope de coca', '12345678911234', 'odranoel.df@gmail.com');


 -- insert tipo produtos

INSERT INTO agromundo.tipo_produto ( ds_nome, cd_unidade_medida) VALUES ( 'Litros de refrigerante para vacas', 1);
INSERT INTO agromundo.tipo_produto ( ds_nome, cd_unidade_medida) VALUES ( 'Quilos de charope para vacas', 2);

--insert produtos

INSERT INTO agromundo.produto (fk_id_tipo_produto, ds_nome, fk_id_fornecedor, qtd_medida, dt_validade) VALUES ( 1, 'Galão com coca-cola animal', 1, 14, now()); 
INSERT INTO agromundo.produto (fk_id_tipo_produto, ds_nome, fk_id_fornecedor, qtd_medida, dt_validade) VALUES ( 1, 'Galão com guaraná animal', 1, 28, now()); 
INSERT INTO agromundo.produto (fk_id_tipo_produto, ds_nome, fk_id_fornecedor, qtd_medida, dt_validade) VALUES ( 2, 'Caixa de charope de coca-cola animal', 1, 12, now()); 
INSERT INTO agromundo.produto (fk_id_tipo_produto, ds_nome, fk_id_fornecedor, qtd_medida, dt_validade) VALUES ( 2, 'Caixa de charope de guaraná animal', 1, 1, now()); 

--insert de embalagens

INSERT INTO agromundo.embalagem ( ds_nome, fk_id_fornecedor) VALUES ( 'Embalagem de caixa', 1);
INSERT INTO agromundo.embalagem ( ds_nome, fk_id_fornecedor) VALUES ( 'Embalagem de galão', 1);
INSERT INTO agromundo.embalagem ( ds_nome, fk_id_fornecedor) VALUES ( 'Plastico Bolha', 1);

