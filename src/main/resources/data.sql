drop table if exists player;
create table player (number integer primary key, name varchar(255), lastname varchar(255), position varchar(255), captain boolean);

drop table if exists team;
create table team (id integer primary key, coach varchar(255), year integer);

drop table if exists team_players;
create table team_players (team_id integer not null, players_number integer not null);

ALTER TABLE team_players ADD FOREIGN KEY (team_id) REFERENCES team (id);
ALTER TABLE team_players ADD FOREIGN KEY (players_number) REFERENCES player (number);


insert into player (number, name, lastname, position, captain) values (31, 'Dominique', 'Price', 'goaltender', false);
insert into player (number, name, lastname, position, captain) values (14, 'Nick', 'Suzuki', 'forward', false);
insert into player (number, name, lastname, position, captain) values (15, 'Jesperi', 'Kotkaniemi', 'forward', false);
insert into player (number, name, lastname, position, captain) values (71, 'Jake', 'Evans', 'forward', false);
insert into player (number, name, lastname, position, captain) values (27, 'Alexander', 'Romanov', 'defenseman', false);
insert into player (number, name, lastname, position, captain) values (6, 'Shea', 'Weber', 'defenseman', true);

insert into team (id, coach, year) values (1, 'Dominique Ducharme', 2020);
insert into team (id, coach, year) values (2, 'Dominique Ducharme', 2019);

insert into team_players (team_id, players_number) values (1, 31);
insert into team_players (team_id, players_number) values (1, 14);
insert into team_players (team_id, players_number) values (1, 15);
insert into team_players (team_id, players_number) values (1, 71);
insert into team_players (team_id, players_number) values (1, 27);
insert into team_players (team_id, players_number) values (1, 6);

insert into team_players (team_id, players_number) values (2, 31);
insert into team_players (team_id, players_number) values (2, 14);
insert into team_players (team_id, players_number) values (2, 15);
insert into team_players (team_id, players_number) values (2, 71);
insert into team_players (team_id, players_number) values (2, 27);
insert into team_players (team_id, players_number) values (2, 6);
