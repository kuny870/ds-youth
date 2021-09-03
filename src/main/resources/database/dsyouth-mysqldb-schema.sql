-- safe 모드 해제
SET SQL_SAFE_UPDATES = 0;

drop table auth;
drop table depart;
drop table team;
drop table `group`;
drop table same_period;
drop table user;
drop table member;
drop table attendance;
drop table leader_info;
drop table qt;
drop table year_season;

drop table board_opinion;
drop table board_free;
drop table address;

ALTER TABLE attendance_2021
ADD (sayu1 VARCHAR(50));

ALTER TABLE `group`
ADD (ord int(10));

ALTER TABLE attendance_2021
ADD (att_ord Integer(10) DEFAULT 0);

update `group` set ord = 0;
update attendance_2021 set att_ord = 99 where att_ord = 0;


-- TODO 전체 연락처 가져오기
SELECT t.t_short_name, m.name, m.htel FROM member m
left outer join team t on t.id = m.team_id
where m.mem_state != 5 and m.mem_state != 6 and m.del_yn = 'N'
ORDER BY 
m.depart_id ASC,
FIELD(t.t_short_name, '새가족') ASC,
t.id ASC
;

-- 출석부 순서 초기화 쿼리
update attendance_2021 set  att_ord = 0;

-- 매년 한두번씩 삭제된 멤버의 출석부 데이터 정리 쿼리
delete at from attendance_2021 at
inner JOIN
    member m
ON 
    m.id = at.member_id
WHERE 
    m.del_yn = 'Y';
    
    
-- 등급
create table auth (
	id int(50) not null AUTO_INCREMENT,	-- 고유번호
    `no` int(10) not null,	-- 권한 번호
    `name` varchar(50) not null,	-- 권한명
    `description` varchar(50) null,	-- 설명
	-- 1:일반(순원) 3:임원(국장) 5:준관리자(순장(리더)) 6:중간 관리자(엘더) 
    -- 7:관리자(팀장) 8:최고관리자(목사) 9:마스터 관리자(admin 계정)
	constraint pk_auth primary key (id)
);

-- 사용자 (일반, 마스터)
create table user (
    id int(50) not null AUTO_INCREMENT,	-- 고유번호
    login_id varchar(50) not null,		-- 아이디
    login_pw varchar(100) not null,		-- 비밀번호
    name varchar(50) not null,	-- 이름
    depart_id int(10) not null default '3',	-- 1,2청년부
    team_id int(10) not null default '10',	-- 팀
    date_of_birth varchar(50) null,		-- 생년월일
    htel varchar(50) null,	-- 핸드폰번호
    gender varchar(10) null,	-- 성별 / male or female
    session_id varchar(100) null,	-- 세션 id
    use_cookie varchar(10) not null default 'N', -- 로그인 유지 여부
    auth_id int(10) not null default 7,	-- 회원 등급 default : 일반 순원 7
    auth_exec int(10) not null default 0, -- 임원 여부 / 0:일반 or 1:국장
	reg_date datetime DEFAULT CURRENT_TIMESTAMP,	-- 가입 시간
    mod_date datetime null,		-- 수정 시간
	del_yn varchar(10) not null default 'N',	-- 탈퇴 여부
    
    constraint pk_user primary key (id)
);

-- 사용자 로그인 유지
create table user_keep_login (
    login_id varchar(50) not null,		-- 아이디
    session_id varchar(100) not null,	-- 세션 id
	reg_date datetime DEFAULT CURRENT_TIMESTAMP	-- 등록 시간
);


-- 청년부 멤버
create table member (
    id int(50) not null AUTO_INCREMENT,	-- 고유번호
    name varchar(50) not null,	-- 이름
    memo varchar(5000) null,	-- 지체상황
    guider varchar(50) null,	-- 인도자
    depart_id int(10) null,	-- 1,2청년부
    team_id int(10) null,	-- 팀
    date_of_birth varchar(50) null,		-- 생년월일
    htel varchar(50) null,	-- 핸드폰번호
    gender varchar(10) null,	-- 성별 / male or female
    member_reg_date varchar(20) null,		-- 멤버 등록 날짜
    member_grad_date varchar(20) null,		-- 멤버 수정 날짜
    same_period_id int(10) null,	-- 동기
    profile_img varchar(255) null,	-- 프로필 이미지
    reg_user int(10) not null,	-- 등록한 사용자
	reg_date datetime DEFAULT CURRENT_TIMESTAMP,	-- 가입 시간
    mod_date datetime null,		-- 수정 시간
    mem_state varchar(10) not null default '1',	-- 회원상태값  1:회원, 2:기타, 3:군인, 4:해외, 5:장기결석, 6:새가족수료, 7:졸업
	del_yn varchar(10) not null default 'N',	-- 삭제 여부

	constraint pk_member primary key (id)
);

-- 부서
create table depart (
	id int(50) not null AUTO_INCREMENT,	-- 고유번호
	d_name varchar(50) not null,	-- 부서 이름
    reg_user int(10) not null,	-- 등록자
	del_yn varchar(10) not null default 'N',	-- 삭제 여부
    
    constraint pk_depart primary key (id)
);

-- 팀
create table team (
    id int(50) not null AUTO_INCREMENT,	-- 고유번호
    depart_id varchar(10) null,	-- 팀 소속 청년부
    t_short_name varchar(50) not null,	-- 팀 짧은 이름
    t_full_name varchar(50) null,	-- 팀 전체 이름
    t_theme varchar(50) null,	-- 팀 주제 말씀
    reg_user int(10) not null,	-- 작성자
	del_yn varchar(10) not null default 'N',	-- 삭제 여부
    
    constraint pk_team primary key (id)
);


-- 순관리
create table year_season (
	id int(50) not null AUTO_INCREMENT comment '고유번호',	-- 고유번호
    `year` varchar(10) not null comment '년도',	-- 년도
    season varchar(20) not null comment '시즌',	-- 시즌
    season_flag varchar(10) null comment '시즌구분자',	-- 시즌구분자
    del_yn varchar(10) not null default 'N' comment '삭제여부',	-- 삭제 여부
    
	constraint pk_year_season primary key (id)
);


-- 순명
create table `group` (
	id int(50) not null AUTO_INCREMENT,	-- 고유번호
	`year` varchar(10) not null,			-- 년
	season varchar(10) not null,		-- 상반기 / 하반기
	season_flag varchar(10) null,		-- 상하반기 구분코드
    depart_id varchar(10) not null,	-- 부서
	team_id varchar(10) not null,	-- 팀
    g_name varchar(50) not null,	-- 순명 
    reg_user int(10) not null,	-- 등록자
	del_yn varchar(10) not null default 'N',	-- 삭제 여부
    
    constraint pk_group primary key (id)
);

-- 팀별 출석부
create table attendance_${nextYear} (
	id int(50) not null AUTO_INCREMENT comment '고유번호',	-- 고유번호
    member_id varchar(10) not null comment 'member 고유 id',	-- member 고유 id
    group_id varchar(10) null comment '순',	-- 순
    group_grade varchar(10) not null default '순원' comment '순장여부',	-- 순장여부
    mem_state varchar(10) not null comment '회원상태',		-- 회원상태 값
    att_yn varchar(10) not null default 'Y' comment '출석 카운트',	-- 출석 카운트
    `year` varchar(10) not null comment '년',	-- 년
    `month` varchar(10) not null comment '월',	-- 월
    
    first_week varchar(10) not null default 'N' comment '1주',	-- 1주
    sayu1 varchar(50) null comment '1주 예배 불참 사유',	-- 1주 예배 불참 사유
    second_week varchar(10) not null default 'N' comment '2주',	-- 2주
    sayu2 varchar(50) null comment '2주 예배 불참 사유',	-- 2주 예배 불참 사유
    third_week varchar(10) not null default 'N' comment '3주',	-- 3주
    sayu3 varchar(50) null comment '3주 예배 불참 사유',	-- 3주 예배 불참 사유
    fourth_week varchar(10) not null default 'N' comment '4주',	-- 4주
    sayu4 varchar(50) null comment '4주 예배 불참 사유',	-- 4주 예배 불참 사유
    fifth_week varchar(10) not null default 'N' comment '5주',	-- 5주
    sayu5 varchar(50) null comment '5주 예배 불참 사유',	-- 5주 예배 불참 사유
    
    att_ord int(10) not null default 99 comment '출석부 순서',       -- 출석부 순서
    
    constraint pk_attendance_${nextYear} primary key (id)
);


-- 출석부 테이블 데이터 복사
INSERT INTO attendance_${nextYear} SELECT * FROM attendance_${thisYear}

-- 출석부 테이블 데이터 초기화
update attendance_${nextYear} set 
group_id = null, 
group_grade = '순원', 
year = #{nextYear},
first_week = 'N',
second_week = 'N',
third_week = 'N',
fourth_week = 'N',
fifth_week = 'N';

-- 멤버 상태 값
create table member_state (
	id int(50) not null AUTO_INCREMENT,	-- 고유번호
	m_state varchar(20) not null,		-- 멤버 상태
	ord varchar(10) null,				-- 상태값에 따른 순서
	del_yn varchar(10) not null default 'N',	-- 삭제 여부
	
	constraint pk_member_state primary key (id)
);


-- 동기
create table same_period (
	id int(50) not null AUTO_INCREMENT,	-- 고유번호
    birth_year varchar(20) not null,	-- 탄생년
    reg_user int(10) not null,	-- 등록자
    del_yn varchar(10) not null default 'N',	-- 삭제 여부
    
	constraint pk_same_period primary key (id)
);

-- 수련회명
create table retreat (
	id int(50) not null AUTO_INCREMENT, -- 고유번호
	year varchar(10) not null,			-- 수련회 연도
	season varchar(20) not null,		-- 여름 / 겨울
	retreat_name varchar(30) not null,	-- 수련회명
	header_first varchar(20) null,	-- 대가족장
	header_second varchar(20) null,	-- 시간청지기
	reg_user int(20) not null,			-- 등록자
	del_yn varchar(10) not null default 'N',		-- 삭제 여부
	
	constraint pk_retreat primary key (id)
);


-- 가족명
create table family (
	id int(50) not null AUTO_INCREMENT, -- 고유번호
	retreat_id varchar(10) not null,		-- 수련회 id
	fam_name varchar(50) not null,		-- 가족명
	del_yn varchar(10) not null default 'N',		-- 삭제 여부
	
	constraint pk_family primary key (id)
);


-- 가족원
create table family_member (
	id int(50) not null AUTO_INCREMENT, -- 고유번호
	team_id varchar(10) not null,		-- 팀
	member_id varchar(10) not null,			-- 가족원 id
	group_id varchar(10) null,			-- 순
	retreat_id varchar(10) not null,		-- 수련회 id
	family_id varchar(10) not null,			-- 가족명 id
	group_grade varchar(10) not null default '0',		-- 가족 레벨 / 0:가족원, 1:리더, 9:가족장
	attend_state varchar(20) null,			-- 참석 / 부분 / 불참 / 미정
	
	constraint pk_family_member primary key (id)
);


-- 리더배포자료 게시판
create table leader_info (
	id int(50) not null AUTO_INCREMENT, -- 고유번호
    title varchar(100) null,	-- 제목
    origin_img varchar(100) not null,	-- 원본 이미지 이름
    replace_img varchar(100) not null,	-- 대체 이미지 이름
    reg_user int(10) not null,	-- 등록자
    reg_date datetime DEFAULT CURRENT_TIMESTAMP,	-- 등록일
    del_yn varchar(10) not null default 'N',	-- 삭제 여부
    
	constraint pk_leader_info primary key(id)
);


-- QT
create table qt (
	id int(50) not null AUTO_INCREMENT, -- 고유번호
    `year` varchar(20) not null,	-- 년
    `month` varchar(20) not null,	-- 월
    `day` varchar(20) not null,		-- 일
    title varchar(50) not null,		-- 제목
    bible varchar(50) not null,		-- 성경 본문
    content text(2000) not null,	-- 본문 내용

	constraint pk_qt primary key(id)
);


-- 의견 게시판
create table board_opinion (
	id int(50) not null AUTO_INCREMENT, -- 고유번호
    content varchar(255) not null,	-- 내용
    reg_date datetime DEFAULT CURRENT_TIMESTAMP,	-- 등록일

	constraint pk_board_opinion primary key(id)
);


-- 자유 게시판
create table board_free (
	id int(50) not null AUTO_INCREMENT, -- 고유번호
    content varchar(255) not null,	-- 내용 
    reg_user int(10) not null,	-- 등록자
    reg_date datetime DEFAULT CURRENT_TIMESTAMP,	-- 등록일
    mod_date datetime null,		-- 수정 시간
    del_yn varchar(10) not null default 'N',	-- 삭제 여부

	constraint pk_board_free primary key(id)
);


-- 주소
create table address (
	id int(50) not null AUTO_INCREMENT,	-- 고유번호
	user_id varchar(50) not null,	-- 회원 ID
	name varchar(50),	-- 주소 이름
    addr1 varchar(255),	-- 주소
    addr2 varchar(255),	-- 상세주소
	zipcode varchar(50),	-- 우편번호
    memo varchar(255),	-- 메모
    reg_date datetime DEFAULT CURRENT_TIMESTAMP,	-- 등록 시간
    del_yn varchar(10) not null default 'N', -- 삭제 여부
    
    constraint pk_address primary key (id)
);

