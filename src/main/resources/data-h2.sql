--AUTHENTICATION
--INSERT into login(id, email, password, active) VALUES
--(1, 'user@email.com', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', TRUE);

-- USER_ROLES
--insert into login_roles (login_id, roles_id) values
--(1, 1),

-- MUSICIAN
INSERT INTO musician (id, city, contact, experience_level, experiences, instrument, music_genre, name, state)
VALUES
(1, 'Nova Iorque', 'john@example.com', 'Intermediário', 'Tocou em uma banda local por 3 anos', 'Guitarra', 'Rock', 'John Smith', 'Nova York'),
(2, 'Los Angeles', 'sarah@example.com', 'Avançado', 'Fez turnês com um artista famoso de pop', 'Piano', 'Pop', 'Sarah Johnson', 'Califórnia'),
(3, 'Londres', 'mike@example.com', 'Iniciante', 'Apresentou em pequenos locais locais', 'Bateria', 'Indie', 'Mike Anderson', 'Inglaterra'),
(4, 'Berlim', 'anna@example.com', 'Intermediário', 'Gravou em estúdio para um álbum de jazz', 'Saxofone', 'Jazz', 'Anna Müller', 'Alemanha'),
(5, 'Tóquio', 'takashi@example.com', 'Avançado', 'Tocou em uma orquestra por 10 anos', 'Violino', 'Clássico', 'Takashi Yamamoto', 'Japão'),
(6, 'Paris', 'emilie@example.com', 'Intermediário', 'Participou de vários festivais de jazz', 'Baixo', 'Jazz', 'Jovinalda Dupont', 'França'),
(7, 'Sydney', 'liam@example.com', 'Iniciante', 'Apresentou em bares e pubs locais', 'Vocal', 'Pop/Rock', 'Liam Evans', 'Austrália'),
(8, 'Toronto', 'emily@example.com', 'Avançado', 'Fez turnês com uma banda country', 'Banjo', 'Country', 'Emily Thompson', 'Canadá'),
(9, 'Moscou', 'vladimir@example.com', 'Intermediário', 'Tocou em uma orquestra sinfônica', 'Violoncelo', 'Clássico', 'Vladimir Petrov', 'Rússia'),
(10, 'São Paulo', 'maria@example.com', 'Iniciante', 'Participou de apresentações de talentos escolares', 'Flauta', 'Pop', 'Maria Silva', 'Brasil');

--BAND
INSERT INTO band (id, city, contact, music_genre, name, state)
VALUES
(1, 'Nova Iorque', 'info@banda1.com', 'Rock', 'Banda do Rock', 'Nova York'),
(2, 'Los Angeles', 'contato@banda2.com', 'Pop', 'Banda Popstar', 'Califórnia'),
(3, 'Londres', 'banda3@gmail.com', 'Indie', 'Indie Rockers', 'Inglaterra'),
(4, 'Berlim', 'banda4@gmail.com', 'Eletrônica', 'Electro Beats', 'Alemanha'),
(5, 'Tóquio', 'banda5@banda5.com', 'J-Pop', 'Tokyo Stars', 'Japão'),
(6, 'Paris', 'contato@banda6.com', 'Chanson', 'Les Amoureux', 'França'),
(7, 'Sydney', 'banda7@hotmail.com', 'Rock Alternativo', 'The Sydney Sound', 'Austrália'),
(8, 'Toronto', 'contato@banda8.com', 'Country', 'Country Roads', 'Canadá'),
(9, 'Moscou', 'banda9@gmail.com', 'Rock Clássico', 'Moscow Legends', 'Rússia'),
(10, 'São Paulo', 'banda10@gmail.com', 'Samba', 'Sambalanço', 'Brasil');

-- BAND_FAVORITES
INSERT INTO band_favorites (band_id, favorites_id)
VALUES(1,1);

--NOTIFICATION
INSERT INTO notification (id, message, read, band_id, musician_id)
VALUES(1, 'abc', false, 1, 1);

--FILE
