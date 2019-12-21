package org.ds.dsyouth.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestJob extends QuartzJobBean{

	public static void main(String[] args) {
		System.out.print("test main!!!!!!!!!!!!!!");
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.print("test main override!!!!!!!!!!!!!!");
	}

}
