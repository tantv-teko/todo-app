CREATE TABLE ADDRESSES
(
/
CREATE TABLE USERS
(
	ID INT NOT NULL PRIMARY KEY,
    NAME VARCHAR(50) NOT
        ID INT NOT NULL PRIMARY KEY,
    STREET VARCHAR(100) NOT NULL,
    CITY VARCHAR(100),
    PIN INT
) NULL,
    EMAIL VARCHAR(100),
    PHONE INT,
    ADDRESS INT NOT NULL,
    CONSTRAINT USERS_FK FOREIGN KEY(ADDRESS) REFERENCES ADDRESSES(ID)
)
/