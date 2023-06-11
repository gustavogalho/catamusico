--AUTHENTICATION
INSERT into login(id, email, password) VALUES
(1001, 'musico@email.com', '$2a$12$jA3gBREfCkr9FSpW5fSIq.5DWN1dssAPaZyhKP7NmUGTqqIOYUrK6'),
(1002, 'banda@email.com', '$2a$12$gCU43oUrg4PoJd3IbEPmeOeAvWYMDLw3xiCb5asAxFzOTG2opJgbS');

-- USER_ROLES
--insert into login_roles (login_id, roles_id) values
--(1, 1),

-- MUSICIAN
INSERT INTO musician (id, city, contact, experience_level, experiences, instrument, music_genre, name, state, login_id)
VALUES
(1001, 'Acrelandia', 'joana@example.com', 'Intermediário', 'Tocou em uma banda local por 3 anos', 'Guitarra', 'Rock', 'Joana Smith', 'Acre', 1001);
-- (1002, 'Los Angeles', 'sarah@example.com', 'Avançado', 'Fez turnês com um artista famoso de pop', 'Piano', 'Pop', 'Sarah Johnson', 'Califórnia'),
-- (1003, 'Londres', 'mike@example.com', 'Iniciante', 'Apresentou em pequenos locais locais', 'Bateria', 'Indie', 'Mike Anderson', 'Inglaterra'),
-- (1004, 'Berlim', 'anna@example.com', 'Intermediário', 'Gravou em estúdio para um álbum de jazz', 'Saxofone', 'Jazz', 'Anna Müller', 'Alemanha'),
-- (1005, 'Tóquio', 'takashi@example.com', 'Avançado', 'Tocou em uma orquestra por 10 anos', 'Violino', 'Clássico', 'Takashi Yamamoto', 'Japão'),
-- (1006, 'Paris', 'emilie@example.com', 'Intermediário', 'Participou de vários festivais de jazz', 'Baixo', 'Jazz', 'Jovinalda Dupont', 'França'),
-- (1007, 'Sydney', 'liam@example.com', 'Iniciante', 'Apresentou em bares e pubs locais', 'Vocal', 'Pop/Rock', 'Liam Evans', 'Austrália'),
-- (1008, 'Toronto', 'emily@example.com', 'Avançado', 'Fez turnês com uma banda country', 'Banjo', 'Country', 'Emily Thompson', 'Canadá'),
-- (1009, 'Moscou', 'vladimir@example.com', 'Intermediário', 'Tocou em uma orquestra sinfônica', 'Violoncelo', 'Clássico', 'Vladimir Petrov', 'Rússia'),
-- (1010, 'São Paulo', 'maria@example.com', 'Iniciante', 'Participou de apresentações de talentos escolares', 'Flauta', 'Pop', 'Maria Silva', 'São Paulo');

--BAND
INSERT INTO band (id, city, contact, music_genre, name, state, login_id)
VALUES
(1001, 'Nova Iorque', 'info@banda1.com', 'Rock', 'Banda do Rock', 'Nova York', 1002);
-- (1002, 'Los Angeles', 'contato@banda2.com', 'Pop', 'Banda Popstar', 'Califórnia'),
-- (1003, 'Londres', 'banda3@gmail.com', 'Indie', 'Indie Rockers', 'Inglaterra'),
-- (1004, 'Berlim', 'banda4@gmail.com', 'Eletrônica', 'Electro Beats', 'Alemanha'),
-- (1005, 'Tóquio', 'banda5@banda5.com', 'J-Pop', 'Tokyo Stars', 'Japão'),
-- (1006, 'Paris', 'contato@banda6.com', 'Chanson', 'Les Amoureux', 'França'),
-- (1007, 'Sydney', 'banda7@hotmail.com', 'Rock Alternativo', 'The Sydney Sound', 'Austrália'),SELECT * FROM FILE 
-- (1008, 'Toronto', 'contato@banda8.com', 'Country', 'Country Roads', 'Canadá'),
-- (1009, 'Moscou', 'banda9@gmail.com', 'Rock Clássico', 'Moscow Legends', 'Rússia'),
-- (1010, 'São Paulo', 'banda10@gmail.com', 'Samba', 'Sambalanço', 'Brasil');

-- BAND_FAVORITES
-- INSERT INTO band_favorites (band_id, favorites_id)
-- VALUES(1001,1001);

--NOTIFICATION
--INSERT INTO notification (id, message, read, band_id, musician_id)
--VALUES(1001, 'abc', false, 1001, 1001);

--FILES 
insert into file(id,content,content_type) values
(1001,  FILE_READ('classpath:/static/assets/img/testimonials/testimonials-1.jpg'), 'image/jpg'),
(1002,  FILE_READ('classpath:/static/assets/img/testimonials/testimonials-2.jpg'), 'image/jpg'),;

INSERT INTO musician_media (musician_id, media_id)
VALUES(1001, 1001);

INSERT INTO band_media (band_id, media_id)
VALUES(1001, 1002);
