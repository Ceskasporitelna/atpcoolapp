CREATE SCHEMA HPLATFORM;

CREATE TABLE HPLATFORM.TRANSACTIONS
(
  TRNID       VARCHAR(256) NOT NULL,
  AMOUNT        VARCHAR(2048) NOT NULL,
  CURRENCY   VARCHAR(20),
  EXPDATE    VARCHAR(10),
  MPAN     VARCHAR(20),
  AUTHDATE DATE NOT NULL,
  STATUS VARCHAR(20),
  PUBKEY VARCHAR(2048)
);

CREATE TABLE HPLATFORM.PRICES
(
	ID  		VARCHAR(256) NOT NULL,
	TRNID		VARCHAR(256) NOT NULL,
	TYPE		VARCHAR(256),
	REGION  VARCHAR(256),
	PRICE		VARCHAR(2048) NOT NULL,
);

CREATE TABLE HPLATFORM.BILITEMS
(
	ID      VARCHAR(256) NOT NULL,
	TRNID		VARCHAR(256) NOT NULL,
	TYPE		VARCHAR(256),
	PRICE		VARCHAR(2048) NOT NULL,
	NAME    VARCHAR(256)
);

CREATE TABLE HPLATFORM.STATPRICES
(
	ID  		VARCHAR(256) NOT NULL,
	VALUE		NUMERIC(8,2),
	CENKOD	VARCHAR(50),
	FROMDATE   DATE NOT NULL,
	TODATE     DATE NOT NULL,
	REGIONKODE		VARCHAR(20),
	REGION		VARCHAR(256),
	NAME    VARCHAR(256)
);

ALTER TABLE HPLATFORM.TRANSACTIONS
  ADD CONSTRAINT PK_TRANSACTIONS
PRIMARY KEY (TRNID);

ALTER TABLE HPLATFORM.PRICES
  ADD CONSTRAINT PK_PRICES
PRIMARY KEY (ID);

ALTER TABLE HPLATFORM.BILITEMS
  ADD CONSTRAINT PK_BILITEMS
PRIMARY KEY (ID);

ALTER TABLE HPLATFORM.STATPRICES
  ADD CONSTRAINT PK_STATPRICES
PRIMARY KEY (ID);

GRANT SELECT ON HPLATFORM.TRANSACTIONS TO bigsix;
GRANT INSERT ON HPLATFORM.TRANSACTIONS TO bigsix;
GRANT UPDATE ON HPLATFORM.TRANSACTIONS TO bigsix;

GRANT SELECT ON HPLATFORM.PRICES TO bigsix;
GRANT INSERT ON HPLATFORM.PRICES TO bigsix;
GRANT UPDATE ON HPLATFORM.PRICES TO bigsix;

GRANT SELECT ON HPLATFORM.BILITEMS TO bigsix;
GRANT INSERT ON HPLATFORM.BILITEMS TO bigsix;
GRANT UPDATE ON HPLATFORM.BILITEMS TO bigsix;

GRANT SELECT ON HPLATFORM.STATPRICES TO bigsix;
GRANT INSERT ON HPLATFORM.STATPRICES TO bigsix;
GRANT UPDATE ON HPLATFORM.STATPRICES TO bigsix;

/*
INSERT INTO MORFINA.MORFINA_CONF_BDT VALUES (
  '123456789',
  'PAILLIER',
  '{
   "method": "PAILLIER",
   "preCalculatedDenominator": "12106853997386792569330058721713456303240354594631911447132191952767560013759",
   "lambda": "8730610850576098108475732642812548398736937109943727651665665679037686442986"
}',
  '{
   "method": "PAILLIER",
   "nSquared": "2744048369671095574561583007959980901355918163690500471631736129549114696724932097880235179184429799730201850640419024858965622819166137651676944593730281",
   "g": "9819266694848734599019384375903625362875798967138880476824318058493575873131",
   "bits": 256,
   "n": "52383665103456588650854395856875290392884554625553083201198459773567331028909"
}',
  now()
);

INSERT INTO MORFINA.MORFINA_CONF_BDT VALUES (
  '123456789',
  'AES',
  '{
   "method": "AES",
   "password": "0123456789ABCDEF",
   "salt": "0123456789ABCDEF",
   "keyLength": 128
}',
  '',
  now()
);*/
