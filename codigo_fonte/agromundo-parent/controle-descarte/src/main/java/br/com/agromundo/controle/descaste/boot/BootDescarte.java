/**
 * 
 */
package br.com.agromundo.controle.descaste.boot;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import br.com.agromundo.controle.descaste.services.JobVerificaProdutosDescarte;
import br.com.agromundo.controle.descaste.util.CDIJobFactory;

/**
 * @author Leonardo Borges
 *
 */
public class BootDescarte extends HttpServlet {

  Logger log = Logger.getLogger(BootDescarte.class);

  
	private static final int MINUTOS_NOTIFICACAO = 57;
	private static final int HORARIO_NOTIFICACAO = 22;
	private static final String GRUPO_TRIGGER = "Notifica as 8 a.m";
	private static final String NOME_TRIGGER = "trigger1";
	private static final String GRUPO_JOB = "Notificar fabricantes sobre descarte";
	private static final String NOME_JOB = "Job1";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Scheduler scheduler;
	
	@Inject
	CDIJobFactory proveInjecao;
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.setJobFactory(proveInjecao);
			JobDetail job = newJob(JobVerificaProdutosDescarte.class)
					.withIdentity(NOME_JOB, GRUPO_JOB).build();
			Trigger trigger = newTrigger().withIdentity(NOME_TRIGGER, GRUPO_TRIGGER).startNow()
					.withSchedule(CronScheduleBuilder.atHourAndMinuteOnGivenDaysOfWeek(HORARIO_NOTIFICACAO, MINUTOS_NOTIFICACAO, DateBuilder.MONDAY,
							DateBuilder.THURSDAY, DateBuilder.WEDNESDAY, DateBuilder.TUESDAY, DateBuilder.FRIDAY))
					.build();
			scheduler.scheduleJob(job, trigger);
			scheduler.start();
			
			log.info("Foi configurado a rotina de envio de notificações para rodar nos dias da semanas às "+HORARIO_NOTIFICACAO+" horas e "+MINUTOS_NOTIFICACAO+" minutos");
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}
