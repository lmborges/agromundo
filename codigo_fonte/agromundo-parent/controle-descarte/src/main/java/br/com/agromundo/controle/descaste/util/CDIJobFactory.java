/**
 * 
 */
package br.com.agromundo.controle.descaste.util;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

/**
 * @author Leonardo Borges
 *
 */
public final class CDIJobFactory implements JobFactory {
	@Inject
	BeanManager beanManager;

	@Override
	public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) {
		Class<? extends Job> jobClazz = bundle.getJobDetail().getJobClass();
		Bean<?> bean = beanManager.getBeans(jobClazz).iterator().next();
		CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
		return (Job) beanManager.getReference(bean, jobClazz, ctx);
	}
}
