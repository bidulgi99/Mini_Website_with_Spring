create table rd_member(
	rm_id varchar2(10 char) PRIMARY KEY,
	rm_pw varchar2(10 char) not null,
	rm_name varchar2(15 char) not null,
	rm_birthday date not null,
	rm_address varchar2(200 char) not null,
	rm_photo varchar2(100 char)not null
	
);


select * from rd_member;

------------------------------------------------------

create table rd_sns(
	rs_no number(4) PRIMARY KEY,
	rs_writer varchar2(10 char) not null,
	rs_txt varchar2(500 char) not null,
	rs_date date not null,
	rs_color char(6 char) not null,
	constraint sns2_writer
		foreign key(rs_writer) references rd_member(rm_id)
		on delete cascade

);

create sequence rd_sns_seq;

select rs_no, rs_writer, rs_txt, rs_date, rm_photo
from rd_sns, rd_member
where rs_writer = rm_id
order by rs_date desc;


select * from rd_sns;
select count(*) from rd_sns;

select * 
from (
 	select rownum as rn, rs_no,rs_writer,rs_txt,rs_date,rm_photo
 	from(
 		select rs_no,rs_writer,rs_txt,rs_date,rm_photo
 		from rd_member, rd_sns
 		where rs_writer = rm_id and rs_txt like '%새로고침%'
 		order by rs_date desc
 	)
 ) where rn >= 1 and rn <= 10;
 
 
 -- 날짜 역순, 2~5번까지 글
select * 
from (
 	select rownum as rn, rs_no,rs_writer,rs_txt,rs_date,rs_color
 	from(
 		select *
 		from rd_sns
 		order by rs_date desc
 	)
 ) where rn >= 2 and rn <= 5;
 
 
 
 -- 날짜 역순, 2~5번까지 글을 쓴 사람
select rm_id, rm_photo
from rd_member
where rm_id in(
	select rs_writer
	from(
		select rownum as rn, rs_writer
	 	from(
	 		select rs_writer
	 		from rd_sns
	 		where rs_writer like '%%' or rs_txt like '%%'
	 		order by rs_date desc
	 	)
	)
	where rn >= 2 and rn <= 5
);

 --위 둘을 조인
 select rs_no, rs_writer, rs_txt, rs_date, rs_color, rm_photo
 from (
 	select * 
	from (
	 	select rownum as rn, rs_no,rs_writer,rs_txt,rs_date,rs_color
	 	from(
	 		select *
	 		from rd_sns
	 		order by rs_date desc
	 	)
 ) where rn >= 2 and rn <= 5
 ),
 (
 select rm_id, rm_photo
	from rd_member
	where rm_id in(
		select rs_writer
		from(
			select rownum as rn, rs_writer
		 	from(
		 		select rs_writer
		 		from rd_sns
		 		where rs_writer like '%%' or rs_txt like '%새로고침%'
		 		order by rs_date desc
		 	)
		)
		where rn >= 2 and rn <= 5
	)
 )
 where rs_writer = rm_id
 order by rs_date desc;




drop table rd_sns;

----------------------------------------------

-- 현재 기온/습도/날씨
create table rd_weather_color(
	rwc_temp number(4,2) not null,
	rwc_humidity number(4,2) not null,
	rwc_description varchar2(20 char) not null,
	rwc_color char(6 char) not null

);
select * from rd_weather_color;


select * from rd_sns;


update rd_sns 
set rs_txt = '멍멍' 
where rs_no = '6';

--------------------------------------------------

create table rd_reply(
	rr_no number(4) PRIMARY KEY,
	rr_rs_no number(4) not null,
	rr_writer varchar2(10 char)not null,
	rr_txt varchar2(200 char)not null,
	rr_date date not null,
	constraint sns2_reply_writer
		foreign key (rr_writer) references rd_member(rm_id)
		on delete cascade,
	constraint sns2_reply
		foreign key (rr_rs_no) references rd_sns(rs_no)
		on delete cascade
	
);

create sequence rd_reply_seq;

select * from rd_reply;

----------------------------------------

create table rd_dataroom(
	rd_no number(3) PRIMARY KEY,
	rd_uploader varchar2(10 char) not null,
	rd_title varchar2(100 char) not null,
	rd_file varchar2(100 char) not null,
	rd_category varchar2(6 char) not null,
	rd_date date not null,
	constraint dataroom_uploader
		foreign key (rd_uploader) references rd_member(rm_id)


);
create sequence rd_dataroom_seq;

select * from rd_dataroom;







