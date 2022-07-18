CREATE TABLE case_managers
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(100)          NOT NULL,
    department VARCHAR(150)          NOT NULL,
    CONSTRAINT pk_case_managers PRIMARY KEY (id)
);

CREATE TABLE dossiers
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    arrival_date    date                  NOT NULL,
    deadline        INT                   NOT NULL,
    subject         VARCHAR(255)          NOT NULL,
    `description`   VARCHAR(255)          NULL,
    department      VARCHAR(150)          NOT NULL,
    status          VARCHAR(255)          NULL,
    case_manager_id BIGINT                NULL,
    CONSTRAINT pk_dossiers PRIMARY KEY (id)
);

ALTER TABLE dossiers
    ADD CONSTRAINT FK_DOSSIERS_ON_CASE_MANAGER FOREIGN KEY (case_manager_id) REFERENCES case_managers (id);