CREATE TABLE IF NOT EXISTS USERS
(
    id uuid NOT NULL,
    family_name character varying(255),
    middle_name character varying(255),
    first_name character varying(255) NOT NULL,
    birthday timestamp NOT NULL,
    gender character varying(10) NOT NULL,
    age int NOT NULL,
    description character varying(1024),
    created timestamp,
    updated timestamp,
    is_blocked bool NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS COMPANY
(
    id uuid NOT NULL,
    company_name character varying(255) NOT NULL,
    description character varying(1024),
    created timestamp,
    updated timestamp,
    is_activity bool NOT NULL,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS USER_JOB_INFO
(
    id uuid NOT NULL,
    id_company uuid NOT NULL,
    user_id uuid NOT NULL,
    description character varying(1024),
    created timestamp,
    updated timestamp,
    is_activity bool,
    CONSTRAINT user_job_info_pkey PRIMARY KEY (id),
    CONSTRAINT user_job_info_users_fkey FOREIGN KEY (user_id) REFERENCES USERS(ID),
    CONSTRAINT user_job_info_company_fkey FOREIGN KEY (id_company) REFERENCES COMPANY(ID)
);