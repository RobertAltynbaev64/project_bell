CREATE TABLE IF NOT EXISTS Organization (
    id              LONG                     COMMENT 'Уникальный идентификатор организации' PRIMARY KEY AUTO_INCREMENT ,
    version         INTEGER NOT NULL         COMMENT 'Служебное поле hibernate',
    name            VARCHAR(50) NOT NULL     COMMENT 'Краткое название организации',
    full_name       VARCHAR(100) NOT NULL    COMMENT 'Полное название организации',
    inn             VARCHAR(11) NOT NULL     COMMENT 'ИНН организации',
    kpp             VARCHAR(12) NOT NULL     COMMENT 'КПП организации',
    address         VARCHAR(100) NOT NULL    COMMENT 'Адрес организации',
    phone           VARCHAR(18)              COMMENT 'Телефон организации',
    is_active       BOOLEAN                  COMMENT 'Активность организации'

);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
    id              LONG                     COMMENT 'Уникальный идентификатор офиса' PRIMARY KEY AUTO_INCREMENT ,
    version         INTEGER NOT NULL         COMMENT 'Служебное поле hibernate',
    organization_id LONG NOT NULL            COMMENT 'Уникальный идентификатор организации',
    name            VARCHAR(40)              COMMENT 'Название офиса',
    address         VARCHAR(100)             COMMENT 'Адрес офиса',
    phone           VARCHAR(18)              COMMENT 'Телефон офиса',
    is_active       BOOLEAN                  COMMENT 'Активность офиса'

);
COMMENT ON TABLE Office IS 'Офис';

CREATE INDEX IX_Office_Org_id ON Office (organization_id);
ALTER TABLE Office ADD FOREIGN KEY (organization_id) REFERENCES Organization(id);

CREATE TABLE IF NOT EXISTS User (
    id              LONG                     COMMENT 'Уникальный идентификатор пользователя' PRIMARY KEY AUTO_INCREMENT ,
    version         LONG NOT NULL            COMMENT 'Служебное поле hibernate',
    office_id       LONG NOT NULL            COMMENT 'Уникальный идентификатор офиса',
    first_name      VARCHAR(15) NOT NULL     COMMENT 'Имя пользователя',
    second_name     VARCHAR(15)              COMMENT 'Фамилия пользователя',
    middle_name     VARCHAR(15)              COMMENT 'Отчество пользователя',
    position        VARCHAR(20) NOT NULL     COMMENT 'Должность пользователя',
    phone           VARCHAR(18)              COMMENT 'Телефон организации',
    country_id      LONG                     COMMENT 'Служебное поле hibernate',
    is_identified   BOOLEAN                  COMMENT 'Определение пользователя'

);
COMMENT ON TABLE User IS 'Пользователь';

CREATE INDEX IX_User_Office_id ON User (office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);

CREATE TABLE IF NOT EXISTS Document (
     user_id         LONG                     COMMENT 'Уникальный идентификатор пользователя' PRIMARY KEY,
     version         INTEGER NOT NULL         COMMENT 'Служебное поле hibernate',
     document_type_id LONG                    COMMENT 'Идентификатор типа документа',
     doc_number      VARCHAR(20)              COMMENT 'Номер документа',
     doc_date        DATE                     COMMENT 'Дата выдачи документа'

);
COMMENT ON TABLE Document IS 'Документ пользователя';

CREATE INDEX IX_Document_User_id ON Document (user_id);
ALTER TABLE Document ADD FOREIGN KEY (user_id) REFERENCES User(id);

CREATE TABLE IF NOT EXISTS Document_type (
     id              LONG                     COMMENT 'Уникальный идентификатор (суррогатный ключ)' PRIMARY KEY AUTO_INCREMENT,
     version         INTEGER NOT NULL         COMMENT 'Служебное поле hibernate',
     doc_code        INTEGER                  COMMENT 'Код документа',
     doc_name        VARCHAR(100)             COMMENT 'Название документа'

);
COMMENT ON TABLE Document_type IS 'Справочник документов';

CREATE INDEX IX_Document_type_id ON Document_type (id);
ALTER TABLE Document ADD FOREIGN KEY (document_type_id) REFERENCES Document_type(id);

CREATE TABLE IF NOT EXISTS Country (
    id              LONG                     COMMENT 'Уникальный идентификатор (суррогатный ключ)' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER NOT NULL         COMMENT 'Служебное поле hibernate',
    country_code    INTEGER                  COMMENT 'Код страны',
    country_name    VARCHAR(50)              COMMENT 'Название страны'

);
COMMENT ON TABLE Country IS 'Справочник стран';

CREATE INDEX IX_User_Country_id ON User (country_id);
ALTER TABLE User ADD FOREIGN KEY (country_id) REFERENCES Country(id);