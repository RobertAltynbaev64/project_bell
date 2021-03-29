INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (1, 0, 'МТС', 'ПАО "МТС"', 7740000076, 770901001, '109147, г. Москва, Марксистская улица, 4', '8(800)250-08-90', true);
INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (2, 0, 'Билайн', 'ПАО "Вымпелком"', 7713076301, 771301001, '127083, г. Москва, улица 8 Марта, дом 10', '8(800)700-06-11', true);
INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (3, 0, 'Мегафон', 'ПАО "МЕГАФОН"', 7812014560, 770701001, '127006, г. Москва, Оружейный переулок, дом 41', '8(800)550-05-00', true);
INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (4, 0, 'Теле2', 'ООО "Т2 МОБАЙЛ"', 7743895280, 775101001, '108811, г. Москва, километр Киевское шоссе 22-й, дом 6', '8(800)555-06-11', false);
INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (5, 0, 'МТС', 'ПАО "МТС"', 8840000076, 770901001, '109147, г. Москва, Марксистская улица, 4', '8(800)250-08-90', false);

INSERT INTO Office (id, version, organization_id, name, address, phone, is_active) VALUES (1, 0, 1, 'Салон-магазин МТС на Шафиева', 'Уфа, ул. Шафиева, 39/2', '8917456-25-69', true);
INSERT INTO Office (id, version, organization_id, name, address, phone, is_active) VALUES (2, 0, 1, 'Салон-магазин МТС в ТРЦ Июнь', 'Уфа, ул. Комсомольская, 112', '8(917)578-62-17', true);
INSERT INTO Office (id, version, organization_id, name, address, phone, is_active) VALUES (3, 0, 2, 'Салон-магазин Билайн на Менделеева', 'Уфа, ул. Менделеева, 175Б', '8(962)276-17-89', true);
INSERT INTO Office (id, version, organization_id, name, address, phone, is_active) VALUES (4, 0, 2, 'Салон-магазин Билайн на Айской', 'Уфа, ул. Айская, 70', '8(962)796-58-88', false);
INSERT INTO Office (id, version, organization_id, name, address, phone, is_active) VALUES (5, 0, 3, 'Салон-магазин Мегафон в ТК Центральный', 'Уфа, ул. Цюрупа, 97', '8(927)478-25-11', false);
INSERT INTO Office (id, version, organization_id, name, address, phone, is_active) VALUES (6, 0, 3, 'Салон-магазин Мегафон на Ленина', 'Уфа, ул. Ленина, 26', '8(927)784-12-34', true);
INSERT INTO Office (id, version, organization_id, name, address, phone, is_active) VALUES (7, 0, 4, 'Салон-магазин Теле2', 'село Актаныш, просп. Ленина, 38', '8(900)427-87-11', false);

INSERT INTO Country (id, version, country_code, country_name) VALUES (1, 0, 643, 'Россия');
INSERT INTO Country (id, version, country_code, country_name) VALUES (2, 0, 112, 'Беларусь');
INSERT INTO Country (id, version, country_code, country_name) VALUES (3, 0, 804, 'Украина');
INSERT INTO Country (id, version, country_code, country_name) VALUES (4, 0, 398, 'Казахстан');

INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, position, phone, country_id, is_identified) VALUES (1, 0, 1, 'Регина', 'Антипина', 'Вячеславовна', 'Продавец-консультант', '89174587589', 2, true);
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, position, phone, country_id, is_identified) VALUES (2, 0, 1, 'Сергей', 'Смирнов', 'Андреевич', 'Менеджер', '8(917)6597588', 1, false);
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, position, phone, country_id, is_identified) VALUES (3, 0, 2, 'Артём', 'Антонов', 'Викторович', 'Продавец-консультант', '8(917)1235487', 1, true);
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, position, phone, country_id, is_identified) VALUES (4, 0, 2, 'Валерия', 'Иванова', 'Климовна', 'Менеджер', '8(962)1114723', 3, false);
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, position, phone, country_id, is_identified) VALUES (5, 0, 3, 'Айгуль', 'Хасанова', 'Вадимовна', 'Менеджер', '8(937)3336411', 1, true);
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, position, phone, country_id, is_identified) VALUES (6, 0, 3, 'Петр', 'Жуков', 'Денисович', 'Продавец-консультант', '8(927)1265800', 1, true);
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, position, phone, country_id, is_identified) VALUES (7, 0, 4, 'Булат', 'Анваров', 'Рамилевич', 'Продавец-консультант', '8(900)0002874', 1, true);
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, position, phone, country_id, is_identified) VALUES (8, 0, 4, 'Элина', 'Хабирова', 'Ураловна', 'Менеджер', '8(900)2356412', 4, false);

INSERT INTO Document_type (id, version, doc_code, doc_name) VALUES (1, 0, 21, 'Паспорт гражданина Российской Федерации');
INSERT INTO Document_type (id, version, doc_code, doc_name) VALUES (2, 0, 3, 'Свидетельство о рождении');
INSERT INTO Document_type (id, version, doc_code, doc_name) VALUES (3, 0, 7, 'Военный билет');

INSERT INTO Document (user_id, version, document_type_id, doc_number, doc_date) VALUES (1, 0, 1, 789632, '1995-03-02');
INSERT INTO Document (user_id, version, document_type_id, doc_number, doc_date) VALUES (2, 0, 2, 4789213, '1998-10-05');
INSERT INTO Document (user_id, version, document_type_id, doc_number, doc_date) VALUES (3, 0, 1, 124578, '1995-09-01');
INSERT INTO Document (user_id, version, document_type_id, doc_number, doc_date) VALUES (4, 0, 2, 859674, '1998-04-18');
INSERT INTO Document (user_id, version, document_type_id, doc_number, doc_date) VALUES (5, 0, 1, 475869, '2000-07-20');
INSERT INTO Document (user_id, version, document_type_id, doc_number, doc_date) VALUES (6, 0, 3, 8556855, '2011-03-14');
INSERT INTO Document (user_id, version, document_type_id, doc_number, doc_date) VALUES (7, 0, 3, 2563254, '2016-03-28');
INSERT INTO Document (user_id, version, document_type_id, doc_number, doc_date) VALUES (8, 0, 2, 214758, '1995-01-07');