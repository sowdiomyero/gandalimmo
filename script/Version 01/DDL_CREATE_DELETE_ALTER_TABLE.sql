CREATE TABLE IF NOT EXISTS `activite` (
  `TYPE_ACTIVITE` varchar(15) NOT NULL,
  `id_activite` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `budget_prevu` double DEFAULT NULL,
  `budget_reel` double DEFAULT NULL,
  `date_deb_prevu` date DEFAULT NULL,
  `date_deb_reel` date DEFAULT NULL,
  `date_fin_prevu` date DEFAULT NULL,
  `date_fin_reel` date DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `etat` int(2) DEFAULT '1',
  `nbre_emploi_prevu` int(11) DEFAULT NULL,
  `nbre_emploi_rel` int(11) DEFAULT NULL,
  `activite_nom` varchar(60) NOT NULL,
  `ponderation` float DEFAULT NULL,
  `taux_realisation` float DEFAULT NULL,
  `type` varchar(40) NOT NULL DEFAULT 'PROJET',
  `version` int(11) DEFAULT '0',
  `id_parent` bigint(20) DEFAULT NULL,
  `etat_actuel` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_activite`),
  KEY `FK9D4BF2FB1FB84AF7` (`id_parent`),
  KEY `FK9D4BF2FB45B2C797` (`etat_actuel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `activite_etat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `id_activite` bigint(20) DEFAULT NULL,
  `id_etat` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA01613E676C4DC48` (`id_activite`),
  KEY `FKA01613E6AC184196` (`id_etat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `activite_localisation` (
  `ID_ACTIVITE` bigint(20) NOT NULL,
  `ID_LOCALISATION` bigint(20) NOT NULL,
  KEY `FKF7E941045C9531D2` (`ID_LOCALISATION`),
  KEY `FKF7E9410476C4DC48` (`ID_ACTIVITE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE IF NOT EXISTS `compte` (
  `ID_COMPTE` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `LOGIN` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_COMPTE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=116 ;


CREATE TABLE IF NOT EXISTS `demande` (
  `id_demande` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `code_demande` varchar(255) DEFAULT NULL,
  `date_echeance` date DEFAULT NULL,
  `resume` varchar(255) DEFAULT NULL,
  `type_demande` varchar(255) DEFAULT NULL,
  `id_responsable` bigint(20) DEFAULT NULL,
  `id_createur` bigint(20) DEFAULT NULL,
  `id_prestataire` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_demande`),
  KEY `FK5C765F7A20EAD157` (`id_prestataire`),
  KEY `FK5C765F7A75B660AE` (`id_responsable`),
  KEY `FK5C765F7A4DEA3511` (`id_createur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `etat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  `nom` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `indicateur` (
  `id_indicateur` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `etat` int(2) DEFAULT '1',
  `nom_indicateur` varchar(255) DEFAULT NULL,
  `unite_indicateur` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_indicateur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

CREATE TABLE IF NOT EXISTS `job_execution_hist` (
  `ID_JOB` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `RAPPORT_GENERATION_PARAMS` varchar(255) DEFAULT NULL,
  `RAPPORT_NAME` varchar(255) DEFAULT NULL,
  `RAPPORT_PATH` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_JOB`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `journal` (
  `id_journal` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `etat` int(2) DEFAULT '1',
  `libelle` varchar(255) DEFAULT NULL,
  `id_activite` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_journal`),
  KEY `FKAB64AF3776C4DC48` (`id_activite`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `localisation` (
  `id_localisation` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `etat` varchar(20) DEFAULT '1',
  `json_form_view` varchar(255) DEFAULT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `local_nom` varchar(60) NOT NULL,
  `type` varchar(60) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `local_nom_google` varchar(80) NOT NULL,
  `type_localisation` varchar(15) NOT NULL,
  `id_parent` bigint(20) DEFAULT NULL,
  `ID_USER` bigint(20) DEFAULT NULL,
  `type_incident` varchar(255) DEFAULT NULL,
  `gravite` varchar(255) DEFAULT NULL,
  `type_site` varchar(255) DEFAULT NULL,
  `nb_objets` int(11) DEFAULT NULL,
  `nb_niveaux` int(11) DEFAULT NULL,
  `id_responsable` bigint(20) DEFAULT NULL,
  `id_createur` bigint(20) DEFAULT NULL,
  `clef` varchar(50) DEFAULT NULL,
  `id_zone` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_localisation`),
  KEY `FKC3A7DA40CACEAB3C` (`id_parent`),
  KEY `FKC3A7DA40AC26C6E8` (`ID_USER`),
  KEY `FKC3A7DA4075B660AE` (`id_responsable`),
  KEY `FKC3A7DA404DEA3511` (`id_createur`),
  KEY `FKC3A7DA40C00F2205` (`id_zone`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;


CREATE TABLE IF NOT EXISTS `localisation_indicateur` (
  `id_localisation_indicateur` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `valeur` varchar(255) DEFAULT NULL,
  `id_indicateur` bigint(20) DEFAULT NULL,
  `id_localisation` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_localisation_indicateur`),
  KEY `FK8E7B72755C9531D2` (`id_localisation`),
  KEY `FK8E7B72759D9867FE` (`id_indicateur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;


CREATE TABLE IF NOT EXISTS `messagerie` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARAM_SMTP_EMAIL` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_HOST` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PASSWORD` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PORT` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_SSL` bit(1) DEFAULT NULL,
  `PARAM_SMTP_TRANSPORT` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `messagerie_param` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `PARAM_SMTP_EMAIL` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_HOST` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PASSWORD` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PORT` int(11) DEFAULT NULL,
  `PARAM_SMTP_SSL` bit(1) DEFAULT NULL,
  `PARAM_SMTP_TRANSPORT` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `niveau` (
  `id_niveau` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `camera` bit(1) DEFAULT NULL,
  `etage` varchar(255) DEFAULT NULL,
  `extincteur` bit(1) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `superficie` varchar(255) DEFAULT NULL,
  `wifi` bit(1) DEFAULT NULL,
  `id_batiment` bigint(20) DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `ascenseur` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_niveau`),
  KEY `FKC1B3E35E36D51740` (`id_batiment`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;


CREATE TABLE IF NOT EXISTS `parametremail` (
  `ID_PARAMETREMAIL` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `ADRESSE_MESSAGERIE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NOM_UTILISATEUR` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PORT_POP3` varchar(255) DEFAULT NULL,
  `PORT_SMTP` varchar(255) DEFAULT NULL,
  `SERVEUR_MAIL_ENTRANT` varchar(255) DEFAULT NULL,
  `SERVEUR_MAIL_SORTANT` varchar(255) DEFAULT NULL,
  `typeCompte` varchar(255) DEFAULT NULL,
  `SERVEUR_MAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAMETREMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `prestataire` (
  `id_user` bigint(20) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `id_compte` bigint(20) DEFAULT NULL,
  `user_logged` int(11) DEFAULT NULL,
  `user_mail` varchar(255) DEFAULT NULL,
  `user_nom` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_prenom` varchar(255) DEFAULT NULL,
  `code_prestataire` varchar(255) DEFAULT NULL,
  `raison_sociale` varchar(255) DEFAULT NULL,
  `type_prestataire` varchar(255) DEFAULT NULL,
  `adr_code_postal` varchar(5) DEFAULT NULL,
  `adr_commune` varchar(64) DEFAULT NULL,
  `adr_escalier` varchar(2) DEFAULT NULL,
  `adr_num_etage` int(11) DEFAULT NULL,
  `adr_libelle_voie` varchar(128) DEFAULT NULL,
  `adr_num_voie` varchar(28) DEFAULT NULL,
  `adr_porte` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `FK1E448BECAA4F4EEDba52cc52` (`id_compte`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE IF NOT EXISTS `programme_indicateur` (
  `id_programme_indicateur` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `operateur_post` varchar(255) DEFAULT NULL,
  `operateur_pre` varchar(255) DEFAULT NULL,
  `valeur_post` bigint(20) DEFAULT NULL,
  `valeur_pre` bigint(20) DEFAULT NULL,
  `id_indicateur` bigint(20) DEFAULT NULL,
  `id_programme` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_programme_indicateur`),
  KEY `FKFAF3D0199D9867FE` (`id_indicateur`),
  KEY `FKFAF3D019D5F50A26` (`id_programme`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `rapport_param` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `PARAM_RAPPORT_BENEFICIAIRE` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_DONNES` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_FORMAT` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_FREQUENCE` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_REPERTOIRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `role` (
  `ID_ROLE` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `ROLE_NAME` varchar(255) DEFAULT NULL,
  `ROLE_DESC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_ROLE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

CREATE TABLE IF NOT EXISTS `user` (
  `ID_USER` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` datetime DEFAULT NULL,
  `DATE_UPDATED` datetime DEFAULT NULL,
  `ID_COMPTE` bigint(20) DEFAULT NULL,
  `USER_LOGGED` int(11) DEFAULT NULL,
  `USER_MAIL` varchar(255) DEFAULT NULL,
  `USER_NOM` varchar(255) DEFAULT NULL,
  `USER_PHONE` varchar(255) DEFAULT NULL,
  `USER_PRENOM` varchar(255) DEFAULT NULL,
  `adr_code_postal` varchar(5) DEFAULT NULL,
  `adr_commune` varchar(64) DEFAULT NULL,
  `adr_escalier` varchar(2) DEFAULT NULL,
  `adr_num_etage` int(11) DEFAULT NULL,
  `adr_libelle_voie` varchar(128) DEFAULT NULL,
  `adr_num_voie` varchar(28) DEFAULT NULL,
  `adr_porte` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`ID_USER`),
  KEY `FK36EBCBFFB8CE52` (`ID_COMPTE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=121 ;


CREATE TABLE IF NOT EXISTS `user_activite` (
  `id_user_activite` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `fonction` varchar(255) DEFAULT NULL,
  `id_activite` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_user_activite`),
  KEY `FKA04F4CEF76C4DC48` (`id_activite`),
  KEY `FKA04F4CEFAC26C6E8` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `user_role` (
  `ID_USER` bigint(20) NOT NULL,
  `ID_ROLE` bigint(20) NOT NULL,
  KEY `FK143BF46AAC23F03E` (`ID_ROLE`),
  KEY `FK143BF46AAC26C6E8` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `zone` (
  `id_zone` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `code` varchar(60) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `nom` varchar(80) NOT NULL,
  `polygone` varchar(1500) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `couleur` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id_zone`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;



--
-- Contraintes pour la table `activite`
--
ALTER TABLE `activite`
  ADD CONSTRAINT `FK9D4BF2FB1FB84AF7` FOREIGN KEY (`id_parent`) REFERENCES `activite` (`id_activite`),
  ADD CONSTRAINT `FK9D4BF2FB45B2C797` FOREIGN KEY (`etat_actuel`) REFERENCES `etat` (`id`);

--
-- Contraintes pour la table `activite_etat`
--
ALTER TABLE `activite_etat`
  ADD CONSTRAINT `FKA01613E676C4DC48` FOREIGN KEY (`id_activite`) REFERENCES `activite` (`id_activite`),
  ADD CONSTRAINT `FKA01613E6AC184196` FOREIGN KEY (`id_etat`) REFERENCES `etat` (`id`);

--
-- Contraintes pour la table `activite_localisation`
--
ALTER TABLE `activite_localisation`
  ADD CONSTRAINT `FKF7E941045C9531D2` FOREIGN KEY (`ID_LOCALISATION`) REFERENCES `localisation` (`id_localisation`),
  ADD CONSTRAINT `FKF7E9410476C4DC48` FOREIGN KEY (`ID_ACTIVITE`) REFERENCES `activite` (`id_activite`);

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `FK5C765F7A20EAD157` FOREIGN KEY (`id_prestataire`) REFERENCES `prestataire` (`id_user`),
  ADD CONSTRAINT `FK5C765F7A4DEA3511` FOREIGN KEY (`id_createur`) REFERENCES `user` (`ID_USER`),
  ADD CONSTRAINT `FK5C765F7A75B660AE` FOREIGN KEY (`id_responsable`) REFERENCES `user` (`ID_USER`);

--
-- Contraintes pour la table `journal`
--
ALTER TABLE `journal`
  ADD CONSTRAINT `FKAB64AF3776C4DC48` FOREIGN KEY (`id_activite`) REFERENCES `activite` (`id_activite`);

--
-- Contraintes pour la table `localisation`
--
ALTER TABLE `localisation`
  ADD CONSTRAINT `FKC3A7DA404DEA3511` FOREIGN KEY (`id_createur`) REFERENCES `user` (`ID_USER`),
  ADD CONSTRAINT `FKC3A7DA4075B660AE` FOREIGN KEY (`id_responsable`) REFERENCES `user` (`ID_USER`),
  ADD CONSTRAINT `FKC3A7DA40AC26C6E8` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID_USER`),
  ADD CONSTRAINT `FKC3A7DA40C00F2205` FOREIGN KEY (`id_zone`) REFERENCES `zone` (`id_zone`),
  ADD CONSTRAINT `FKC3A7DA40CACEAB3C` FOREIGN KEY (`id_parent`) REFERENCES `localisation` (`id_localisation`);

--
-- Contraintes pour la table `localisation_indicateur`
--
ALTER TABLE `localisation_indicateur`
  ADD CONSTRAINT `FK8E7B72755C9531D2` FOREIGN KEY (`id_localisation`) REFERENCES `localisation` (`id_localisation`),
  ADD CONSTRAINT `FK8E7B72759D9867FE` FOREIGN KEY (`id_indicateur`) REFERENCES `indicateur` (`id_indicateur`);

--
-- Contraintes pour la table `niveau`
--
ALTER TABLE `niveau`
  ADD CONSTRAINT `FKC1B3E35E36D51740` FOREIGN KEY (`id_batiment`) REFERENCES `localisation` (`id_localisation`);

--
-- Contraintes pour la table `prestataire`
--
ALTER TABLE `prestataire`
  ADD CONSTRAINT `FK1E448BECAA4F4EEDba52cc52` FOREIGN KEY (`id_compte`) REFERENCES `compte` (`ID_COMPTE`);

--
-- Contraintes pour la table `programme_indicateur`
--
ALTER TABLE `programme_indicateur`
  ADD CONSTRAINT `FKFAF3D0199D9867FE` FOREIGN KEY (`id_indicateur`) REFERENCES `indicateur` (`id_indicateur`),
  ADD CONSTRAINT `FKFAF3D019D5F50A26` FOREIGN KEY (`id_programme`) REFERENCES `activite` (`id_activite`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK36EBCBFFB8CE52` FOREIGN KEY (`ID_COMPTE`) REFERENCES `compte` (`ID_COMPTE`);

--
-- Contraintes pour la table `user_activite`
--
ALTER TABLE `user_activite`
  ADD CONSTRAINT `FKA04F4CEF76C4DC48` FOREIGN KEY (`id_activite`) REFERENCES `activite` (`id_activite`),
  ADD CONSTRAINT `FKA04F4CEFAC26C6E8` FOREIGN KEY (`id_user`) REFERENCES `user` (`ID_USER`);

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK143BF46AAC23F03E` FOREIGN KEY (`ID_ROLE`) REFERENCES `role` (`ID_ROLE`),
  ADD CONSTRAINT `FK143BF46AAC26C6E8` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID_USER`);
SET FOREIGN_KEY_CHECKS=1;