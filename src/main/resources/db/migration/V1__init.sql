-- 勉強会
CREATE TABLE STUDY_MEETINGS (
    ID      BIGINT         NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    TITLE   VARCHAR(100)   NOT NULL,
    SUMMARY VARCHAR(10000) NOT NULL,
    COMPLETED SMALLINT     NOT NULL,
    CONSTRAINT STUDY_MEETINGS_PK PRIMARY KEY (ID)
);

-- 参加希望
CREATE TABLE PARTICIPATE_WISHINGS (
    STUDY_MEETINGS_ID BIGINT      NOT NULL,
    USER_IP_ADDRESS   VARCHAR(15) NOT NULL,
    REGISTER_DATETIME TIMESTAMP   NOT NULL,
    CONSTRAINT PARTICIPATE_WISHINGS_PK PRIMARY KEY (STUDY_MEETINGS_ID, USER_IP_ADDRESS),
    CONSTRAINT PARTICIPATE_WISHINGS_FK1 FOREIGN KEY (STUDY_MEETINGS_ID) REFERENCES STUDY_MEETINGS (ID)
);
