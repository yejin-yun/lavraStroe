
package com.example.lavrastore.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.lavrastore.dao.GroupItemDao;

@Component
public class Scheduler {
	
	@Autowired
	private GroupItemDao groupItemDao;
	
	@Autowired		// applicationContext.xml에 정의된 scheduler 객체를 주입 받음
	private ThreadPoolTaskScheduler scheduler;
	
	//0 0 14 * * * : cron 이렇게 할 경우 매일 14시에 한번 update함. 
	@Scheduled(fixedDelay = 1000 * 60 * 5)  // 원활한 test를 위해 5분 마다 진행중. 
	public void run() {   // 스케쥴러에 의해 미래의 특정 시점에 실행될 작업을 정의				
		Date curTime = new Date();
		groupItemDao.closeEvent(curTime);
		System.out.println("updateTableRunner is executed at " + curTime);
	}
	
}
