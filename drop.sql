set client_min_messages = WARNING;
alter table if exists equipment drop constraint if exists FKd58fdrndxbdfxdbhya451j8rv;
alter table if exists gym_equipment drop constraint if exists FKb13axkyadtyffci5t9dxjaa23;
alter table if exists gym_equipment drop constraint if exists FKf9hi0fo971xm30ydv24lcd181;
alter table if exists user_exercise drop constraint if exists FK4dsfvd3ee924pwq4078equ1tu;
alter table if exists user_exercise drop constraint if exists FKn1dbwyk7c0cjnk34ykfvxn355;
alter table if exists user_details drop constraint if exists FKmu3qqjuftewuov0m5mwe6wgid;
drop table if exists equipment cascade;
drop table if exists exercise cascade;
drop table if exists gym cascade;
drop table if exists gym_equipment cascade;
drop table if exists pass cascade;
drop table if exists user_exercise cascade;
drop table if exists user_details cascade;
set client_min_messages = WARNING;
alter table if exists equipment drop constraint if exists FKd58fdrndxbdfxdbhya451j8rv;
alter table if exists gym_equipment drop constraint if exists FKb13axkyadtyffci5t9dxjaa23;
alter table if exists gym_equipment drop constraint if exists FKf9hi0fo971xm30ydv24lcd181;
alter table if exists user_exercise drop constraint if exists FK4dsfvd3ee924pwq4078equ1tu;
alter table if exists user_exercise drop constraint if exists FKn1dbwyk7c0cjnk34ykfvxn355;
alter table if exists user_details drop constraint if exists FKmu3qqjuftewuov0m5mwe6wgid;
drop table if exists equipment cascade;
drop table if exists exercise cascade;
drop table if exists gym cascade;
drop table if exists gym_equipment cascade;
drop table if exists pass cascade;
drop table if exists user_exercise cascade;
drop table if exists user_details cascade;
