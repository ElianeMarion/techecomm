INSERT INTO TB_ROLES(ROLE_ID, ROLE_NAME) VALUES('a894f9dc-0024-4556-996d-793df5b4ef46', 'ROLE_ADMIN');
INSERT INTO TB_ROLES(ROLE_ID, ROLE_NAME) VALUES('276481f6-edc1-4941-bea2-4d0ce91a09d5', 'ROLE_MERCHANT');
INSERT INTO TB_ROLES(ROLE_ID, ROLE_NAME) VALUES('16d375de-6e51-4eaa-8c37-d215e515e3a6', 'ROLE_CUSTOMER');
INSERT INTO TB_USERS(USER_ID, EMAIL, LOGIN, NAME, PASSWORD)
	VALUES ('44ba9760-97ed-4415-88cc-a308a592d503', 'admin@host.com', 'admin', 'admin',
	  '$2a$10$zxuGrCN.xA34I/7mF99LJOTWTqtE5IqAL.hV1gTANMhKCzy2JI2Pu');
INSERT INTO TB_USERS_ROLES(ROLE_ID, USER_ID)
    VALUES ('a894f9dc-0024-4556-996d-793df5b4ef46', '44ba9760-97ed-4415-88cc-a308a592d503');
