-- Remplissage des bases de données "user" et "user_roles" avec des user exemples
-- fichier à placer dans src/main/resources/

-- Remplissage de la table "user" avec 5 utilisateurs
INSERT INTO user (username, discr, password, email, city) VALUES ('utilisateur1', 'USER', 'pass1', 'utilisateur1@email.fr', 'Paris');
INSERT INTO user (username, discr, password, email, city) VALUES ('utilisateur2', 'USER',  'pass2', 'utilisateur2@email.fr', 'Lyon');
INSERT INTO user (username, discr, password, email, city) VALUES ('utilisateur3', 'USER',  'pass3', 'utilisateur3@email.fr', 'Marseille');
INSERT INTO user (username, discr, password, email, city) VALUES ('utilisateur4', 'USER',  'pass4', 'utilisateur4@email.fr', 'Valence');
INSERT INTO user (username, discr, password, email, city) VALUES ('utilisateur5', 'USER',  'pass5', 'utilisateur5@email.fr', 'Dijon');

-- Remplissage de la table "user" avec 2 artistes
-- INSERT INTO user (username, discr, password, email, city, name, description) VALUES ('artisteA', 'ART',  'passArtA', 'artisteA@email.fr', 'Grenoble', 'MonNomDARTISTE', 'Je suis un chanteur');
-- INSERT INTO user (username, discr, password, email, city, name, description) VALUES ('artisteA', 'ART',  'passArtB', 'artisteB@email.fr', 'Lyon', 'Art', 'Je joue du saxo');


-- Remplissage de la table "user_roles" avec les roles des 7 utilisateurs et artistes précéduser_rolesents
INSERT INTO user_roles (userid, role) VALUES (1, 'USER_ADMIN');
INSERT INTO user_roles (userid, role) VALUES (2, 'USER_USER');
INSERT INTO user_roles (userid, role) VALUES (3, 'USER_USER');
INSERT INTO user_roles (userid, role) VALUES (4, 'USER_USER');
INSERT INTO user_roles (userid, role) VALUES (5, 'USER_USER');
-- INSERT INTO user_roles (role_id, userid, role) VALUES (6, 6, 'USER_USER');
-- INSERT INTO user_roles (role_id, userid, role) VALUES (7, 7, 'USER_USER');