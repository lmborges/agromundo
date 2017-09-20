-- insert fornecedores
INSERT INTO agromundo.fornecedor ( tp_fornecedor, cd_id_externo_fornecedor) VALUES ( '1', '1');


 -- insert tipo produtos

INSERT INTO agromundo.tipo_produto ( ds_nome, cd_unidade_medida) VALUES ( 'Litros de refrigerante para vacas', 1);
INSERT INTO agromundo.tipo_produto ( ds_nome, cd_unidade_medida) VALUES ( 'Quilos de charope para vacas', 2);

--insert produtos

INSERT INTO agromundo.produto (fk_id_tipo_produto, ds_nome, fk_id_fornecedor, qtd_medida, dt_validade, tp_toxico, vr_valor_custo, vr_valor_venda) VALUES ( 1, 'Galão com coca-cola animal', 1, 14, now(), '1', 12, 15); 
INSERT INTO agromundo.produto (fk_id_tipo_produto, ds_nome, fk_id_fornecedor, qtd_medida, dt_validade,tp_toxico, vr_valor_custo, vr_valor_venda) VALUES ( 1, 'Galão com guaraná animal', 1, 28, now(), '1', 22, 35); 
INSERT INTO agromundo.produto (fk_id_tipo_produto, ds_nome, fk_id_fornecedor, qtd_medida, dt_validade,tp_toxico, vr_valor_custo, vr_valor_venda) VALUES ( 2, 'Caixa de charope de coca-cola animal', 1, 12, now(), '1', 32, 45); 
INSERT INTO agromundo.produto (fk_id_tipo_produto, ds_nome, fk_id_fornecedor, qtd_medida, dt_validade,tp_toxico, vr_valor_custo, vr_valor_venda) VALUES ( 2, 'Caixa de charope de guaraná animal', 1, 1, now(), '1', 42, 55); 

--insert de embalagens

INSERT INTO agromundo.embalagem ( ds_nome, fk_id_fornecedor) VALUES ( 'Embalagem de caixa', 1);
INSERT INTO agromundo.embalagem ( ds_nome, fk_id_fornecedor) VALUES ( 'Embalagem de galão', 1);
INSERT INTO agromundo.embalagem ( ds_nome, fk_id_fornecedor) VALUES ( 'Plastico Bolha', 1);

-- usuario admin
INSERT INTO agromundo.usuario ( ds_login, cd_senha, ds_nome, cd_tipo_usuario) VALUES ('admin', 'c55eaf3f86f645c8916217b4a2f175ba6086abf2d797ea5f7b3bd067cafe9a32c2b7ccaf09ea2e7d62fe87b0762b0c279c4ec1ff35346f9bd1dd01ac1e49cdea', 'Jaum', '1');
