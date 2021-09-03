package org.ds.dsyouth.scheduler;

import org.ds.dsyouth.mapper.ScheduleMapper;
import org.ds.dsyouth.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SqlScheduler {
	
	@Autowired
	private ScheduleMapper scheduleMapper;
	
	protected String thisYear = DateHelper.getYear();
	protected Integer thisYearInt = Integer.parseInt(DateHelper.getYear());
	protected Integer nextYearInt = Integer.parseInt(DateHelper.getYear()) + 1;
	protected String nextYear = nextYearInt.toString();
	
	
	/**
	 * 매년 예비1학년 동기 추가 (매년 12월 10일 오전1시 1분 1초)
	 */
	@Scheduled(cron = "1 1 1 10 12 *")
	public void insertNewSamePeriod() {
		
		String value = "";
		
		Integer newSamePeriodBirth = thisYearInt - 18;
		
		value = "INSERT INTO same_period (`birth_year`, `reg_user`) VALUES ('" + newSamePeriodBirth + "', '1')";
		System.out.println(">>>>>>> " + value);
		
		scheduleMapper.sqlQuery(value);
		
	}
	
	/**
	 * 내년 시즌 추가 (매년 11월 30일 오전1시 1분 1초)
	 */
	@Scheduled(cron = "1 1 1 30 11 *")
	public void insertYearSeaon() {
		
		String value = "";
		
		value = "INSERT INTO year_season (`year`, `searson`, `season_flag`) VALUES ('" + nextYear + "', '상반기', '1')";
		System.out.println(">>>>>>> " + value);
		
		scheduleMapper.sqlQuery(value);
		
		value = "INSERT INTO year_season (`year`, `searson`, `season_flag`) VALUES ('" + nextYear + "', '하반기', '2')";
		System.out.println(">>>>>>> " + value);
		
		scheduleMapper.sqlQuery(value);
		
	}

	/**
	 * 내년 출석부 추가 (매년 12월 1일 오전1시 1분 1초)
	 */
	@Scheduled(cron = "1 1 1 1 12 *")
	public void createNextAttendance() {
		
		String value = "CREATE TABLE attendance_" + nextYear + " (" + 
				"	id int(50) not null AUTO_INCREMENT comment '고유번호'," + 
				"   member_id varchar(10) not null comment 'member 고유 id'," + 
				"   group_id varchar(10) null comment '순'," + 
				"   group_grade varchar(10) not null default '순원' comment '순장여부'," + 
				"   mem_state varchar(10) not null comment '회원상태'," + 
				"   att_yn varchar(10) not null default 'Y' comment '출석 카운트'," + 
				"   `year` varchar(10) not null comment '년'," + 
				"   `month` varchar(10) not null comment '월'," + 
				"   first_week varchar(10) not null default 'N' comment '1주'," + 
				"   sayu1 varchar(50) null comment '1주 예배 불참 사유'," + 
				"   second_week varchar(10) not null default 'N' comment '2주'," + 
				"   sayu2 varchar(50) null comment '2주 예배 불참 사유'," + 
				"   third_week varchar(10) not null default 'N' comment '3주'," + 
				"   sayu3 varchar(50) null comment '3주 예배 불참 사유'," + 
				"   fourth_week varchar(10) not null default 'N' comment '4주'," + 
				"   sayu4 varchar(50) null comment '4주 예배 불참 사유'," + 
				"   fifth_week varchar(10) not null default 'N' comment '5주'," + 
				"   sayu5 varchar(50) null comment '5주 예배 불참 사유'," + 
				"   att_ord int(10) not null default 99 comment '출석부 순서'," + 
				"   constraint pk_attendance_" + nextYear + " primary key (id)" + 
				")";
		
		System.out.println(">>>>>>> " + value);
		
		scheduleMapper.sqlQuery(value);
		
	}
	
	/**
	 * 출석부 데이터 복사 & 초기화 (매년 12월 1일 오전1시 1분 5초)
	 */
	@Scheduled(cron = "5 1 1 1 12 *")
	public void copyAttendance() {
		
		String value = "INSERT INTO attendance_" + nextYear + 
				" SELECT " + 
				"	a.id " + 
				"   , a.member_id" + 
				"   , null" + 
				"   , '순원'" + 
				"   , m.mem_state" + 
				"   , a.att_yn" + 
				"   , " + nextYear +  
				"   , a.month" + 
				"   , 'N'" + 
				"   , null" + 
				"   , 'N'" + 
				"   , null" + 
				"   , 'N'" + 
				"   , null" + 
				"   , 'N'" + 
				"   , null" + 
				"   , 'N'" + 
				"   , null" + 
				"   , '99'" + 
				" FROM attendance_" + thisYear + " a" + 
				" LEFT OUTER JOIN member m ON m.id = a.member_id" + 
				" WHERE m.del_yn = 'N'";
				
		System.out.println(">>>>>>> " + value);
		
		scheduleMapper.sqlQuery(value);
	}
	
	
	/**
	 * 삭제된 멤버의 출석부 데이터 정리 (상반기) (매년 1월 10일 오전1시 1분 1초)
	 */
	@Scheduled(cron = "1 1 1 10 1 *")
	public void attendanceClear1() {
		
		String value = "DELETE at FROM attendance_" + thisYear + " at" + 
				" INNER JOIN" + 
				"    member m" + 
				" ON " + 
				"    m.id = at.member_id" + 
				" WHERE " + 
				"    m.del_yn = 'Y'";
				
		System.out.println(">>>>>>> " + value);
		
		scheduleMapper.sqlQuery(value);
	}
	
	/**
	 * 삭제된 멤버의 출석부 데이터 정리 (하반기) (매년 7월 10일 오전1시 1분 1초)
	 */
	@Scheduled(cron = "1 1 1 10 7 *")
	public void attendanceClear2() {
		
		String value = "DELETE at FROM attendance_" + thisYear + " at" + 
				" INNER JOIN" + 
				"    member m" + 
				" ON " + 
				"    m.id = at.member_id" + 
				" WHERE " + 
				"    m.del_yn = 'Y'";
				
		System.out.println(">>>>>>> " + value);
		
		scheduleMapper.sqlQuery(value);
	}
	
}
