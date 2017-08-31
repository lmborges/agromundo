package br.com.agromundo.controle.descaste.services;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.agromundo.controle.descaste.gerenciador.GerenciadorDescarte;

public class JobVerificaProdutosDescarte implements org.quartz.Job {

	Logger log = Logger.getLogger(JobVerificaProdutosDescarte.class);

	@Inject
	private GerenciadorDescarte gerenciadorDescarte;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("Iniciando notificação de fornecedores");
		int qtdFornecedoresNotificados = gerenciadorDescarte.notificalFornecedoresComPendencia();
		log.info(qtdFornecedoresNotificados+" fornecedores notificados");

	}

}
