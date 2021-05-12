CREATE TABLE organization (
    id INT AUTO_INCREMENT PRIMARY KEY,
    version    INTEGER NOT NULL,
    name VARCHAR(40) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    inn VARCHAR(10) NOT NULL,
    kpp VARCHAR(9) NOT NULL,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(11),
    is_active BIT
);

CREATE INDEX IX_organization_name ON organization (name);

CREATE TABLE office (
    id INT AUTO_INCREMENT PRIMARY KEY,
    version INTEGER NOT NULL,
    org_id INT NOT NULL,
    name VARCHAR(30) NOT NULL,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(11),
    is_active BIT,
    FOREIGN KEY(org_id) REFERENCES organization(id)
);

CREATE INDEX IX_office_org_id ON office(org_id);


CREATE TABLE doc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    doc_name VARCHAR (50),
    doc_code VARCHAR (10)
);

CREATE INDEX IX_doc_doc_code ON doc(doc_code);

CREATE TABLE country (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_name VARCHAR (50),
    country_code VARCHAR (10)
);

CREATE INDEX IX_country_country_code ON country(country_code);




CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      version INTEGER NOT NULL,
                      first_name VARCHAR(30) NOT NULL,
                      second_name VARCHAR(30),
                      middle_name VARCHAR(30),
                      position VARCHAR(30) NOT NULL,
                      phone VARCHAR(11),
                      is_identified BIT,
                      office_id INT,
                      country_id INT,
                      FOREIGN KEY(office_id) REFERENCES office(id),
                      FOREIGN KEY(country_id) REFERENCES country(id)
);

CREATE INDEX IX_user_office_id ON user(office_id);


CREATE TABLE document (
                          id INT NOT NULL UNIQUE,
                          version INTEGER NOT NULL,
                          document_number VARCHAR (30),
                          document_date DATE,
                          doc_id INT,
                          FOREIGN KEY(doc_id) REFERENCES doc(id),
                          FOREIGN KEY(id) REFERENCES user(id)
);
