REM   Script: CARAMBOLA FIM
REM   P.I

CREATE TABLE TB_CATALOGO (  
    CAT_IDCAT NUMERIC(10) NOT NULL, 
    CAT_NOMECAT VARCHAR2(60) NOT NULL,  
    CAT_DESCCAT VARCHAR2(100) NOT NULL  
);

CREATE TABLE TB_USUARIOS ( 
    USU_IDUSU NUMERIC(10) NOT NULL, 
    USU_IDLOG NUMERIC(10), 
    USU_NOME VARCHAR2(60) NOT NULL, 
    USU_DATANASC DATE NOT NULL, 
    USU_CPF NUMERIC(11) UNIQUE NOT NULL, 
    USU_TEL NUMERIC(11) UNIQUE NOT NULL, 
    USU_EMAIL VARCHAR2(60) UNIQUE NOT NULL, 
    USU_SENHA VARCHAR2(60) NOT NULL, 
    USU_CEP NUMERIC(8) NOT NULL, 
    USU_NUMRESI NUMERIC(4) NOT NULL 
);

CREATE TABLE TB_LOGIN ( 
    LOG_IDLOG NUMERIC(10) NOT NULL, 
    LOG_IDUSU NUMERIC(10), 
    LOG_EMAIL VARCHAR2(60) NOT NULL, 
    LOG_SENHA VARCHAR2(60) NOT NULL 
);

CREATE TABLE TB_COMANDA ( 
    COM_IDCOM NUMERIC(10) NOT NULL, 
    COM_IDUSU NUMERIC(10), 
    COM_DATA DATE 
);

CREATE TABLE TB_ITENSCOMANDA ( 
    ITE_IDCOM NUMERIC(10) NOT NULL, 
    ITE_CODPROD NUMERIC(10) NOT NULL, 
    ITE_QTDITENS NUMERIC(5) NOT NULL 
);

CREATE TABLE TB_PRODUTOS (  
    PRO_IDPROD NUMERIC(10) NOT NULL,  
    PRO_IDCATE NUMERIC(10),  
    PRO_DESC VARCHAR2(50) NOT NULL,  
    PRO_VALOR NUMERIC(10,2) NOT NULL  
);

CREATE TABLE TB_CATEGORIAS (   
    CATE_IDCATE NUMERIC(10) NOT NULL,   
    CATE_IDCAT NUMERIC(10),   
    CATE_DESCCATE VARCHAR2(60) NOT NULL   
);

ALTER TABLE TB_USUARIOS ADD CONSTRAINT TB_USUARIOS_USU_IDUSU_PK PRIMARY KEY(USU_IDUSU) 
;

ALTER TABLE TB_LOGIN ADD CONSTRAINT TB_LOGIN_LOG_IDLOG_PK PRIMARY KEY(LOG_IDLOG) 
;

ALTER TABLE TB_COMANDA ADD CONSTRAINT TB_COMANDA_COM_IDCOM_PK PRIMARY KEY(COM_IDCOM) 
;

ALTER TABLE TB_PRODUTOS ADD CONSTRAINT TB_PRODUTOS_PRO_IDPROD_PK PRIMARY KEY(PRO_IDPROD) 
;

ALTER TABLE TB_CATALOGO ADD CONSTRAINT TB_CATALOGO_CAT_IDCAT_PK PRIMARY KEY(CAT_IDCAT) 
;

ALTER TABLE TB_CATEGORIAS ADD CONSTRAINT TB_CATEGORIAS_CATE_IDCATE_PK PRIMARY KEY(CATE_IDCATE);

ALTER TABLE TB_USUARIOS ADD CONSTRAINT TB_USUARIOS_USU_IDLOG_FK FOREIGN KEY(USU_IDLOG) REFERENCES TB_LOGIN(LOG_IDLOG) 
;

ALTER TABLE TB_LOGIN ADD CONSTRAINT TB_LOGIN_LOG_IDUSU_FK FOREIGN KEY(LOG_IDUSU) REFERENCES TB_USUARIOS(USU_IDUSU)  
;

ALTER TABLE TB_COMANDA ADD CONSTRAINT TB_COMANDA_COM_IDUSU_FK FOREIGN KEY(COM_IDUSU) REFERENCES TB_USUARIOS(USU_IDUSU)  
;

ALTER TABLE TB_ITENSCOMANDA ADD CONSTRAINT TB_ITENSCOMANDA_ITE_IDCOM_FK FOREIGN KEY(ITE_IDCOM) REFERENCES TB_COMANDA(COM_IDCOM) ;

ALTER TABLE TB_ITENSCOMANDA ADD CONSTRAINT TB_ITENSCOMANDA_ITE_CODPROD_FK FOREIGN KEY(ITE_CODPROD) REFERENCES TB_PRODUTOS(PRO_IDPROD)  
;

ALTER TABLE TB_PRODUTOS ADD CONSTRAINT TB_PRODUTOS_PRO_IDCATE_FK FOREIGN KEY(PRO_IDCATE) REFERENCES TB_CATEGORIAS(CATE_IDCATE) ;

ALTER TABLE TB_CATEGORIAS ADD CONSTRAINT TB_CATEGORIAS_CATE_IDCAT_FK FOREIGN KEY(CATE_IDCAT) REFERENCES TB_CATALOGO(CAT_IDCAT) ;

CREATE SEQUENCE SQ_USU_IDUSU START WITH 1000 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE SEQUENCE SQ_LOG_IDLOG START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE SEQUENCE SQ_COM_IDCOM START WITH 100 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE SEQUENCE SQ_PRO_IDPROD START WITH 500 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE SEQUENCE SQ_CAT_IDCAT START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE SEQUENCE SQ_CATE_IDCATE START WITH 10 INCREMENT BY 1 NOCACHE NOCYCLE;

INSERT INTO TB_USUARIOS( 
    USU_IDUSU,  
    USU_NOME,  
    USU_DATANASC, 
    USU_CPF, 
    USU_TEL, 
    USU_EMAIL, 
    USU_SENHA, 
    USU_CEP, 
    USU_NUMRESI 
) VALUES ( 
    SQ_USU_IDUSU.NEXTVAL, 
    'Victor Teixeira Silva', 
    TO_DATE('23/08/2003', 'DD/MM/YYYY/'), 
    45275798948, 
    11981709247, 
    'victor@carambola.com', 
    'v1i2c3t4o5r6', 
    04917140, 
    374 
);

INSERT INTO TB_USUARIOS( 
    USU_IDUSU,  
    USU_NOME,  
    USU_DATANASC, 
    USU_CPF, 
    USU_TEL, 
    USU_EMAIL, 
    USU_SENHA, 
    USU_CEP, 
    USU_NUMRESI 
) VALUES ( 
    SQ_USU_IDUSU.NEXTVAL, 
    'Marcos Paulo Jesus Conceição', 
    TO_DATE('29/07/1994', 'DD/MM/YYYY/'), 
    4633080846, 
    11959808697, 
    'marcos@carambola.com', 
    '29071994', 
    06823590, 
    424 
);

INSERT INTO TB_USUARIOS( 
    USU_IDUSU,  
    USU_NOME,  
    USU_DATANASC, 
    USU_CPF, 
    USU_TEL, 
    USU_EMAIL, 
    USU_SENHA, 
    USU_CEP, 
    USU_NUMRESI 
) VALUES ( 
    SQ_USU_IDUSU.NEXTVAL, 
    'Arthur Teixeira Silva', 
    TO_DATE('27/05/2007', 'DD/MM/YYYY/'), 
    4633080850, 
    11959808698, 
    'arthurt@carambola.com', 
    '270507', 
    04917140, 
    374 
);

INSERT INTO TB_USUARIOS( 
    USU_IDUSU,  
    USU_NOME,  
    USU_DATANASC, 
    USU_CPF, 
    USU_TEL, 
    USU_EMAIL, 
    USU_SENHA, 
    USU_CEP, 
    USU_NUMRESI 
) VALUES ( 
    SQ_USU_IDUSU.NEXTVAL, 
    'Leandro Luiz da Silva', 
    TO_DATE('16/05/1981', 'DD/MM/YYYY/'), 
    4633080851, 
    11947101638, 
    'leandrotop@carambola.com', 
    '232715', 
    04917140, 
    374 
);

INSERT INTO TB_CATALOGO(   
    CAT_IDCAT,  
    CAT_NOMECAT,  
    CAT_DESCCAT   
) VALUES (  
    SQ_CAT_IDCAT.NEXTVAL,  
    'VARANDA DO PEP',  
    'BAR RESTAURANTE'  
);

INSERT INTO TB_CATEGORIAS (  
    CATE_IDCATE, 
    CATE_IDCAT, 
    CATE_DESCCATE 
) VALUES ( 
    SQ_CATE_IDCATE.NEXTVAL, 
    1, 
    'BEBIDAS' 
);

INSERT INTO TB_CATEGORIAS (  
    CATE_IDCATE, 
    CATE_IDCAT, 
    CATE_DESCCATE 
) VALUES ( 
    SQ_CATE_IDCATE.NEXTVAL, 
    1, 
    'PRATOS' 
);

INSERT INTO TB_PRODUTOS (   
    PRO_IDPROD, 
    PRO_IDCATE, 
    PRO_DESC, 
    PRO_VALOR 
) VALUES (  
    SQ_PRO_IDPROD.NEXTVAL, 
    10, 
    'COCA COLA LATA', 
    5 
) 
;

INSERT INTO TB_PRODUTOS (   
    PRO_IDPROD, 
    PRO_IDCATE, 
    PRO_DESC, 
    PRO_VALOR 
) VALUES (  
    SQ_PRO_IDPROD.NEXTVAL, 
    11, 
    'X-BURGUER', 
    20 
);

INSERT INTO TB_COMANDA (    
    COM_IDCOM,  
    COM_IDUSU,  
    COM_DATA 
) VALUES (   
    SQ_COM_IDCOM.NEXTVAL, 
    1000, 
    TO_DATE('16/10/2022','DD/MM/YYYY') 
) 
;

INSERT INTO TB_COMANDA (    
    COM_IDCOM,  
    COM_IDUSU,  
    COM_DATA 
) VALUES (   
    SQ_COM_IDCOM.NEXTVAL, 
    1001, 
    TO_DATE('16/10/2022','DD/MM/YYYY') 
);

INSERT INTO TB_COMANDA (    
    COM_IDCOM,  
    COM_IDUSU,  
    COM_DATA 
) VALUES (   
    SQ_COM_IDCOM.NEXTVAL, 
    1002, 
    TO_DATE('16/10/2022','DD/MM/YYYY') 
);

INSERT INTO TB_COMANDA (    
    COM_IDCOM,  
    COM_IDUSU,  
    COM_DATA 
) VALUES (   
    SQ_COM_IDCOM.NEXTVAL, 
    1003, 
    TO_DATE('16/10/2022','DD/MM/YYYY') 
);

INSERT INTO TB_ITENSCOMANDA (     
    ITE_IDCOM,   
    ITE_CODPROD, 
    ITE_QTDITENS 
) VALUES (     
    100, 
    500, 
    2 
) ;

INSERT INTO TB_ITENSCOMANDA (     
    ITE_IDCOM,   
    ITE_CODPROD, 
    ITE_QTDITENS 
) VALUES (     
    100, 
    501, 
    3 
)  
;

