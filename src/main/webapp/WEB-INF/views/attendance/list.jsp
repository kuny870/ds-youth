<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
  <head>

    <jsp:include page="/WEB-INF/views/layouts/header.jsp" flush="false" />

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="resourcesPath" value="${contextPath}/resources" />
	<script>
		var contextPath = '${contextPath}';
		var resourcesPath = '${resourcesPath}';
	</script>
    <link href="${resourcesPath}/assets/css/reset.css?${nowTime}" rel="stylesheet">
	<link href="${resourcesPath}/assets/css/common.css?${nowTime}" rel="stylesheet">
	<link href="${resourcesPath}/assets/css/general.css?${nowTime}" rel="stylesheet">
  </head>
  <body>
    <div class="container">
    
            <div class="header-product">
   				<span class="shop-link-login" onclick="mypage()">
   					<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
   				</span>
			    <p class="shop_name text-center">
		            	출석 관리       
		        </p>
            
				<div class="div-container">
                    
					<div class="customer-select2 selectbox" style="width: 37.3%; margin-left:5%; float: left;">
						 <select class="select-fix" id="team" name="team" style="width:90%; height:27px; font-size: 13px;">
	                       	<c:forEach var="team" items="${teamList }">
	                       		<c:set var="selected" value="" />
	                       		<c:choose>
								    <c:when test="${attendanceSearch.getTeam() eq null}">
								        <c:if test="${team.tShortName eq login.team.tShortName }">
											<c:set var="selected" value="selected" />
										</c:if>
								    </c:when>
								    <c:otherwise>
								        <c:if test="${team.tShortName eq attendanceSearch.getTeam() }">
											<c:set var="selected" value="selected" />
										</c:if>
								    </c:otherwise>
								</c:choose>
								<option value="${team.tShortName}" ${selected} >${team.tShortName}</option>
							</c:forEach>
	                    </select> 
                    </div>
                    
                    <div class="customer-select2 selectbox" style="width: 25%; margin-left: 3%; float: left;">
						<!-- 월 선택 -->
                   		<select class="select-fix" id="month" name="month" style="height:27px; font-size: 13px;">
	                       	<c:forEach var="month" items="${SMonthSearchType }">
	                       		<c:set var="selected" value="" />
								<c:if test="${month.getVName() eq attendanceSearch.getMonth() }">
									<c:set var="selected" value="selected" />
								</c:if>
								<option value="${month.getVName()}" ${selected} >${month.getVName()}월</option>
							</c:forEach>
	                    </select>  
                    </div>        
                    
		            <div>
		            	<button class="basic-btn attendance-list-btn" onclick="attSearch()">조회</button>
		            </div>

                    
					<div class="sales-table">
			            <div class="table-wrap">

			                <table>
			                    <thead>
			                        <tr>
			                            <th class="th-5p6">No</th>
			                            <th class="th-20p0">
			                            	 <c:choose>
							                	<c:when test="${attendanceSearch.getTeam() == '제1새가족' || attendanceSearch.getTeam() == '제2새가족'}">
							                		인도자
							                	</c:when>
							                	<c:otherwise>
							                		순
							                	</c:otherwise>
							                </c:choose>
			                            </th>
			                            <th class="th-20p0">이름(동기)</th>
			                            <c:forEach var="s" items="${sunday}" varStatus="i">
			                            	<th class="th-7p6">${s}</th>
			                            </c:forEach>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="att" items="${attendanceList}" varStatus="i">
			                    		<input type="hidden" id="${att.id}" name="aId"/>
			                    		<tr>
				                            <td>${i.index + 1}</td>
				                            <td>
				                            	<c:choose>
			                            			<c:when test="${att.attYn == 'Y'}">
			                            				 <c:choose>
										                	<c:when test="${attendanceSearch.getTeam() == '제1새가족' || attendanceSearch.getTeam() == '제2새가족'}">
										                		${att.member.guider}
										                	</c:when>
										                	<c:otherwise>
										                		${att.group.gName}
										                	</c:otherwise>
										                </c:choose>
			                            			</c:when>
			                            			<c:otherwise>
			                            			</c:otherwise>
			                            		</c:choose>
				                            </td>
				                            <c:set var="bold" value=""/>
				                            <c:set var="italic" value=""/>
				                            <c:if test="${att.member.groupGrade == '순장' }">
				                            	<c:set var="bold" value="700"/>
				                            </c:if>
				                            <c:if test="${att.member.longAbsent == 'Y' }">
				                            	<c:set var="italic" value="italic"/>
				                            </c:if>
				                            <td style="font-weight: ${bold}; font-style: ${italic};">
				                            	<c:choose>
			                            			<c:when test="${att.attYn == 'Y'}">
			                            				${att.member.name }
			                            				<!-- 동기 표시 -->
			                            				<c:if test="${att.member.samePeriodId != null }">
						                            		<c:choose>
						                            			<c:when test="${year - att.samePeriod.birthYear < 19}">
						                            				(${fn:substring(att.samePeriod.birthYear,2,4)})
						                            			</c:when>
						                            			<c:when test="${year - att.samePeriod.birthYear == 19}">
						                            				(1)
						                            			</c:when>
						                            			<c:when test="${year - att.samePeriod.birthYear == 20}">
						                            				(2)
						                            			</c:when>
						                            			<c:when test="${year - att.samePeriod.birthYear == 21}">
						                            				(3)
						                            			</c:when>
						                            			<c:when test="${year - att.samePeriod.birthYear == 22}">
						                            				(4)
						                            			</c:when>
						                            			<c:when test="${year - att.samePeriod.birthYear == 23}">
						                            				(5)
						                            			</c:when>
						                            			<c:when test="${year - att.samePeriod.birthYear == 24}">
						                            				(6)
						                            			</c:when>
						                            			<c:when test="${year - att.samePeriod.birthYear == 25}">
						                            				(7)
						                            			</c:when>
						                            			<c:when test="${year - att.samePeriod.birthYear == 26}">
						                            				(8)
						                            			</c:when>
						                            			<c:otherwise>
						                            				(${fn:substring(att.samePeriod.birthYear,2,4)})
						                            			</c:otherwise>
						                            		</c:choose>
						                            	</c:if>
			                            			</c:when>
			                            			<c:otherwise>
			                            				2부
			                            			</c:otherwise>
			                            		</c:choose>
				                            </td>
				                            <c:forEach var="sun" items="${sunday}" varStatus="i">
				                            	<c:set var="checked" value=""/>
				                            	<c:choose>
			                            			<c:when test="${i.index == 0}">
			                            				<c:if test="${att.firstWeek == 'Y' }" >
						                            		<c:set var="checked" value="checked"/>
						                            	</c:if>
						                            	<td>
						                            		<input type="checkbox" id="firstWeek" name="firstWeek" ${checked} >
						                            	</td>
			                            			</c:when>
			                            			<c:when test="${i.index == 1}">
			                            				<c:if test="${att.secondWeek == 'Y' }" >
						                            		<c:set var="checked" value="checked"/>
						                            	</c:if>
						                            	<td>
						                            		<input type="checkbox" id="secondWeek" name="secondWeek" ${checked} >
						                            	</td>
			                            			</c:when>
			                            			<c:when test="${i.index == 2}">
			                            				<c:if test="${att.thirdWeek == 'Y' }" >
						                            		<c:set var="checked" value="checked"/>
						                            	</c:if>
						                            	<td>
						                            		<input type="checkbox" id="thirdWeek" name="thirdWeek" ${checked} >
						                            	</td>
			                            			</c:when>
			                            			<c:when test="${i.index == 3}">
			                            				<c:if test="${att.fourthWeek == 'Y' }" >
						                            		<c:set var="checked" value="checked"/>
						                            	</c:if>
						                            	<td>
						                            		<input type="checkbox" id="fourthWeek" name="fourthWeek" ${checked} >
						                            	</td>
			                            			</c:when>
			                            			<c:otherwise>
			                            				<c:if test="${att.fifthWeek == 'Y' }" >
						                            		<c:set var="checked" value="checked"/>
						                            	</c:if>
						                            	<td>
						                            		<input type="checkbox" id="fifthWeek" name="fifthWeek" ${checked} >
						                            	</td>
			                            			</c:otherwise>
			                            		</c:choose>
				                            </c:forEach>
				                        </tr>
									</c:forEach>
                                    
			                    </tbody>
			                    <tfoot>
			                    	<tr>
			                    		<td></td>
			                    		<td colspan="2">소계</td>
			                    		<c:set var="firstWeekCnt" value="0"/>
			                    		<c:set var="secondWeekCnt" value="0"/>
			                    		<c:set var="thirdWeekCnt" value="0"/>
			                    		<c:set var="fourthWeekCnt" value="0"/>
			                    		<c:set var="fifthWeekCnt" value="0"/>
			                    		<c:forEach var="att" items="${attendanceList}" varStatus="i">
			                    			<c:if test="${att.attYn == 'Y' }">
				                    			<c:if test="${att.firstWeek == 'Y'}">
				                    				<c:set var="firstWeekCnt" value="${firstWeekCnt + 1}"/>
				                    			</c:if>
				                    			<c:if test="${att.secondWeek == 'Y'}">
				                    				<c:set var="secondWeekCnt" value="${secondWeekCnt + 1}"/>
				                    			</c:if>
				                    			<c:if test="${att.thirdWeek == 'Y'}">
				                    				<c:set var="thirdWeekCnt" value="${thirdWeekCnt + 1}"/>
				                    			</c:if>
				                    			<c:if test="${att.fourthWeek == 'Y'}">
				                    				<c:set var="fourthWeekCnt" value="${fourthWeekCnt + 1}"/>
				                    			</c:if>
				                    			<c:if test="${att.fifthWeek == 'Y'}">
				                    				<c:set var="fifthWeekCnt" value="${fifthWeekCnt + 1}"/>
				                    			</c:if>
			                    			</c:if>
			                    		</c:forEach>
			                    		<td>${firstWeekCnt }</td>
			                    		<td>${secondWeekCnt }</td>
			                    		<td>${thirdWeekCnt }</td>
			                    		<td>${fourthWeekCnt }</td>
			                    		<c:if test="${sunday.size() > 4 }" >
			                    			<td>${fifthWeekCnt }</td>
			                    		</c:if>
			                    	</tr>
			                    </tfoot>
			                </table>
			                
			            </div>
			        </div>
	
				</div>
				
				<div class="div-temp1"></div>
		    	<!-- 팀장 이상만 적용 가능 -->
		    	<c:if test="${login.authId <= 4}">
			        <div class="layer_fixed3">
			        	<form>
					        <div class="form-bottom" style="text-align:center;">
				            	<button class="btn md-button2" type="button" onclick="attendanceRegist()">적용</button>
				          	</div>
			          	</form>
					</div>
				</c:if>
			
	    	</div>
	    </div>    

  </body>
  
  <script src="${resourcesPath}/assets/js/attendance/list.js?${nowTime}"></script>
  
</html>
