package org.ds.dsyouth.model;

import java.util.List;

public class AttendanceExcel {

	private Integer id;
	private List<Attendance> attendanceList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}
	public void setAttendanceList(List<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}
	
}
