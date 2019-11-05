-- auth dump data
INSERT INTO auth (`no`, `name`, `description`) VALUES (9, '마스터', '모든권한');
INSERT INTO auth (`no`, `name`, `description`) VALUES (8, '목사', '최고관리자');
INSERT INTO auth (`no`, `name`, `description`) VALUES (7, '팀장', '관리자');
INSERT INTO auth (`no`, `name`, `description`) VALUES (6, '엘더', '준관리자');
INSERT INTO auth (`no`, `name`, `description`) VALUES (5, '순장', '리더');
INSERT INTO auth (`no`, `name`, `description`) VALUES (4, '리더', '리더');
INSERT INTO auth (`no`, `name`, `description`) VALUES (1, '순원', '일반');
INSERT INTO auth (`no`, `name`, `description`) VALUES (0, '국장', '임원');



-- user dump data
INSERT INTO user (login_id, login_pw, `name`, auth_id, auth_exec) VALUES ('admin','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '마스터', 1, 1);
INSERT INTO user (login_id, login_pw, `name`, auth_id, auth_exec) VALUES ('jaedal','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '김재달', 2, 0);
INSERT INTO user (login_id, login_pw, `name`, depart_id, team_id, date_of_birth, htel, gender, auth_id, auth_exec) VALUES ('kuny87','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '최건희', 2, 7, '870421','01087885690','남',4, 0);
INSERT INTO user (login_id, login_pw, `name`, depart_id, team_id, date_of_birth, htel, gender, auth_id, auth_exec) VALUES ('kuny1','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '오세희', 1, 2, '940421','01087885690','남',3, 0);
INSERT INTO user (login_id, login_pw, `name`, depart_id, team_id, date_of_birth, htel, gender, auth_id, auth_exec) VALUES ('kuny2','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '김다예', 2, 5, '870421','01087885690','여',3, 0);
INSERT INTO user (login_id, login_pw, `name`, depart_id, team_id, date_of_birth, htel, gender, auth_id, auth_exec) VALUES ('kuny3','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '황요한', 1, 1, '900421','01087885690','남',3, 0);
INSERT INTO user (login_id, login_pw, `name`, depart_id, team_id, date_of_birth, htel, gender, auth_id, auth_exec) VALUES ('kuny4','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '오현민', 2, 6, '870421','01087885690','남',3, 0);
INSERT INTO user (login_id, login_pw, `name`, depart_id, team_id, date_of_birth, htel, gender, auth_id, auth_exec) VALUES ('kuny5','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '김나영', 1, 4, '870421','01087885690','남',3, 0);
INSERT INTO user (login_id, login_pw, `name`, depart_id, team_id, date_of_birth, htel, gender, auth_id, auth_exec) VALUES ('kuny6','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '조민식', 1, 4, '870421','01087885690','남',5, 0);


-- member dump data
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('황요한', 1, 1, 1, '순장', '870421','01087885690','남',12,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('오세희', 1, 2, 3, '순장', '870421','01087885690','남',16,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('이성우', 1, 3, 5, '순장', '940421','01087885690','남',11,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('김나영', 1, 4, 7, '순장', '870421','01087885690','여',13,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('서유리', 1, 1, 2, '순원', '870421','01087885690','여',17,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('이신애', 1, 2, 4, '순원', '870421','01087885690','여',15,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('최은석', 1, 3, 6, '순원', '940421','01087885690','여',19,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('조민식', 1, 4, 8, '순원', '870421','01087885690','남',21,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('김다예', 2, 5, 9, '순장', '840421','01087885690','여',9,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('오현민', 2, 6, 11, '순장', '840421','01087885690','남',9,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('김은아', 2, 7, 13, '순장', '840421','01087885690','여',6,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('유지연', 2, 5, 10, '순원', '840421','01087885690','여',13,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('김보슬', 2, 6, 12, '순원', '840421','01087885690','여',10,1);
INSERT INTO member (`name`, depart_id, team_id, group_id, group_grade, date_of_birth, htel, gender, same_period_id, reg_user) VALUES ('최건희', 2, 7, 14, '순원', '840421','01087885690','남',9,1);


-- depart dump data
INSERT INTO depart (d_name, reg_user) VALUES ('제1청년부',1);
INSERT INTO depart (d_name, reg_user) VALUES ('제2청년부',1);


-- team dump data
INSERT INTO team (depart_id, t_short_name, t_full_name, reg_user) VALUES (1, '동산','소망품은 동산',1);
INSERT INTO team (depart_id, t_short_name, t_full_name, reg_user) VALUES (1, '등대','목자의 등대',1);
INSERT INTO team (depart_id, t_short_name, t_full_name, reg_user) VALUES (1, '언약','언약의 숲',1);
INSERT INTO team (depart_id, t_short_name, t_full_name, reg_user) VALUES (1, '기쁨','기쁨의 나라',1);
INSERT INTO team (depart_id, t_short_name, t_full_name, reg_user) VALUES (2, '열공','열린 공동체',1);
INSERT INTO team (depart_id, t_short_name, t_full_name, reg_user) VALUES (2, '은행','하나되어 은혜롭고 행복한 공동체',1);
INSERT INTO team (depart_id, t_short_name, t_full_name, reg_user) VALUES (2, '정감','정이 넘치고 감사가 넘치는 마을',1);
INSERT INTO team (depart_id, t_short_name, reg_user) VALUES (1, '제1새가족',1);
INSERT INTO team (depart_id, t_short_name, reg_user) VALUES (2, '제2새가족',1);


-- group dump data
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (1, 1, '기본진리', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (1, 1, '로마서', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (1, 2, '기본진리', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (1, 2, '로마서', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (1, 3, '기본진리', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (1, 3, '야고보서', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (1, 4, '로마서', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (1, 4, '야고보서', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (2, 5, '야고보서', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (2, 5, 'E100', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (2, 6, '야고보서', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (2, 6, 'E100', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (2, 7, 'E100-1', 1);
INSERT INTO `group` (depart_id, team_id, g_name, reg_user) VALUES (2, 7, 'E100-2', 1);


-- same_period dump data
INSERT INTO same_period (birth_year, reg_user) VALUES (1979,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1980,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1981,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1982,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1983,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1984,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1985,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1986,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1987,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1988,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1989,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1990,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1991,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1992,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1993,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1994,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1995,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1996,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1997,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1998,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (1999,1);
INSERT INTO same_period (birth_year, reg_user) VALUES (2000,1);


-- attendance dump data
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (1, 2019, 9,'Y','Y','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (2, 2019, 9,'Y','Y','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (3, 2019, 9,'Y','Y','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (4, 2019, 9,'Y','Y','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (5, 2019, 9,'Y','N','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (6, 2019, 9,'Y','Y','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (7, 2019, 9,'Y','Y','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (8, 2019, 9,'Y','N','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (9, 2019, 9,'Y','Y','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (10, 2019, 9,'Y','Y','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (11, 2019, 9,'Y','Y','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (12, 2019, 9,'Y','Y','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (13, 2019, 9,'Y','N','N','N','N');
INSERT INTO attendance (member_id, `year`, `month`, first_week, second_week, third_week, fourth_week, fifth_week) VALUES (14, 2019, 9,'Y','Y','N','N','N');


-- address dump data
INSERT INTO address (user_id, `name`, addr1, addr2, zipcode, memo) VALUES ('kuny87','집', '서울시 강남구 논현1동','아파트 12-3','12345', "자취방");
INSERT INTO address (user_id, `name`, addr1, addr2, zipcode, memo) VALUES ('kuny87','학교', '서울시 강남구 논현2동','아파트 12-4','67890', "용인");


-- board_opinion dump data
INSERT INTO board_opinion (content) VALUES ('이렇게 해줬으면 좋겠어요1');
INSERT INTO board_opinion (content) VALUES ('이렇게 해줬으면 좋겠어요2');
INSERT INTO board_opinion (content) VALUES ('이렇게 해줬으면 좋겠어요3');
INSERT INTO board_opinion (content) VALUES ('이렇게 해줬으면 좋겠어요4');
INSERT INTO board_opinion (content) VALUES ('이렇게 해줬으면 좋겠어요5');


-- board_free dump data
INSERT INTO board_free (content, reg_user) VALUES ('이렇게 해줬으면 좋겠어요1',1);
INSERT INTO board_free (content, reg_user) VALUES ('이렇게 해줬으면 좋겠어요2',1);
INSERT INTO board_free (content, reg_user) VALUES ('이렇게 해줬으면 좋겠어요3',1);
INSERT INTO board_free (content, reg_user) VALUES ('이렇게 해줬으면 좋겠어요4',1);
INSERT INTO board_free (content, reg_user) VALUES ('이렇게 해줬으면 좋겠어요5',1);