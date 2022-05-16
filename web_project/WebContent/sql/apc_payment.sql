--------------------------------------------------------
--  파일이 생성됨 - 월요일-5월-16-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table APC_PAYMENT
--------------------------------------------------------

  CREATE TABLE "SEMI"."APC_PAYMENT" 
   (	"ORDER_NO" NUMBER(5,0), 
	"CARTNO_FK" NUMBER(5,0), 
	"ORDER_ID" VARCHAR2(100 BYTE), 
	"PNO_FK" NUMBER(5,0), 
	"PNAME" VARCHAR2(50 BYTE), 
	"PQTY" NUMBER(5,0), 
	"PRICE" NUMBER(10,0), 
	"TRANSCOST" NUMBER(10,0) DEFAULT 0, 
	"PAYTYPE" NUMBER(1,0), 
	"ORDERDATE" DATE, 
	"ORDERNAME" VARCHAR2(50 BYTE), 
	"ORDERADDR" VARCHAR2(1000 BYTE), 
	"ORDERPHONE" VARCHAR2(50 BYTE), 
	"PAY_PIMAGE" VARCHAR2(500 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into SEMI.APC_PAYMENT
SET DEFINE OFF;
Insert into SEMI.APC_PAYMENT (ORDER_NO,CARTNO_FK,ORDER_ID,PNO_FK,PNAME,PQTY,PRICE,TRANSCOST,PAYTYPE,ORDERDATE,ORDERNAME,ORDERADDR,ORDERPHONE,PAY_PIMAGE) values (8,7,'hong',20,'Elisa Cardigan (black)',1,319000,0,2,to_date('22/05/16','RR/MM/DD'),'홍길동','서울시 중구','010-1111-1111','/1WAN0000/Elisa_1b.jpg');
Insert into SEMI.APC_PAYMENT (ORDER_NO,CARTNO_FK,ORDER_ID,PNO_FK,PNAME,PQTY,PRICE,TRANSCOST,PAYTYPE,ORDERDATE,ORDERNAME,ORDERADDR,ORDERPHONE,PAY_PIMAGE) values (3,3,'funfun',16,'Nacy Skirt (green)',1,319000,0,2,to_date('22/05/08','RR/MM/DD'),'유재석','서울시 서초구','010-2111-1111','/1WAN0000/Nancy_1b.jpg');
Insert into SEMI.APC_PAYMENT (ORDER_NO,CARTNO_FK,ORDER_ID,PNO_FK,PNAME,PQTY,PRICE,TRANSCOST,PAYTYPE,ORDERDATE,ORDERNAME,ORDERADDR,ORDERPHONE,PAY_PIMAGE) values (9,6,'hong',1,'Quincy Knit Vest (indigo)',1,489000,0,2,to_date('22/05/16','RR/MM/DD'),'홍길동','서울시 중구','010-1111-1111','/3WBK0000/wTshirt1_1.jpg');
Insert into SEMI.APC_PAYMENT (ORDER_NO,CARTNO_FK,ORDER_ID,PNO_FK,PNAME,PQTY,PRICE,TRANSCOST,PAYTYPE,ORDERDATE,ORDERNAME,ORDERADDR,ORDERPHONE,PAY_PIMAGE) values (1,1,'non0',20,'Elisa Cardigan (black)',1,319000,0,1,to_date('22/05/03','RR/MM/DD'),'김경민','서울특별시 중구','010-1234-1234','/1WAN0000/Elisa_1b.jpg');
Insert into SEMI.APC_PAYMENT (ORDER_NO,CARTNO_FK,ORDER_ID,PNO_FK,PNAME,PQTY,PRICE,TRANSCOST,PAYTYPE,ORDERDATE,ORDERNAME,ORDERADDR,ORDERPHONE,PAY_PIMAGE) values (4,2,'funfun',18,'Elisa Cardigan (white)',1,319000,0,2,to_date('22/05/08','RR/MM/DD'),'유재석','서울시 서초구','010-2111-1111','/1WAN0000/Elisa_1.jpg');
Insert into SEMI.APC_PAYMENT (ORDER_NO,CARTNO_FK,ORDER_ID,PNO_FK,PNAME,PQTY,PRICE,TRANSCOST,PAYTYPE,ORDERDATE,ORDERNAME,ORDERADDR,ORDERPHONE,PAY_PIMAGE) values (5,6,'hong',1,'Quincy Knit Vest (indigo)',1,489000,0,2,to_date('22/05/10','RR/MM/DD'),'홍길동','서울시 중구','010-1111-1111','/3WBK0000/wTshirt1_1.jpg');
Insert into SEMI.APC_PAYMENT (ORDER_NO,CARTNO_FK,ORDER_ID,PNO_FK,PNAME,PQTY,PRICE,TRANSCOST,PAYTYPE,ORDERDATE,ORDERNAME,ORDERADDR,ORDERPHONE,PAY_PIMAGE) values (6,9,'hong',16,'Nacy Skirt (green)',1,319000,0,2,to_date('22/05/13','RR/MM/DD'),'홍길동','서울시 중구','010-1111-1111','/1WAN0000/Nancy_1b.jpg');
Insert into SEMI.APC_PAYMENT (ORDER_NO,CARTNO_FK,ORDER_ID,PNO_FK,PNAME,PQTY,PRICE,TRANSCOST,PAYTYPE,ORDERDATE,ORDERNAME,ORDERADDR,ORDERPHONE,PAY_PIMAGE) values (7,8,'hong',5,'Sermaise T-Shirts (white)',1,289000,0,2,to_date('22/05/16','RR/MM/DD'),'홍길동','서울시 중구','010-1111-1111','/3WAT0000/wTshirt3_1.jpg');
Insert into SEMI.APC_PAYMENT (ORDER_NO,CARTNO_FK,ORDER_ID,PNO_FK,PNAME,PQTY,PRICE,TRANSCOST,PAYTYPE,ORDERDATE,ORDERNAME,ORDERADDR,ORDERPHONE,PAY_PIMAGE) values (2,4,'funfun',11,'Marina Short-Sleeve Shirt (beige)',1,229000,0,2,to_date('22/05/08','RR/MM/DD'),'유재석','서울시 서초구','010-2111-1111','/1WAN0000/Marina1_1b.jpg');
--------------------------------------------------------
--  DDL for Index SYS_C009682
--------------------------------------------------------

  CREATE UNIQUE INDEX "SEMI"."SYS_C009682" ON "SEMI"."APC_PAYMENT" ("ORDER_NO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table APC_PAYMENT
--------------------------------------------------------

  ALTER TABLE "SEMI"."APC_PAYMENT" MODIFY ("ORDER_ID" NOT NULL ENABLE);
  ALTER TABLE "SEMI"."APC_PAYMENT" MODIFY ("PNO_FK" NOT NULL ENABLE);
  ALTER TABLE "SEMI"."APC_PAYMENT" MODIFY ("PNAME" NOT NULL ENABLE);
  ALTER TABLE "SEMI"."APC_PAYMENT" MODIFY ("PQTY" NOT NULL ENABLE);
  ALTER TABLE "SEMI"."APC_PAYMENT" MODIFY ("PRICE" NOT NULL ENABLE);
  ALTER TABLE "SEMI"."APC_PAYMENT" MODIFY ("PAYTYPE" NOT NULL ENABLE);
  ALTER TABLE "SEMI"."APC_PAYMENT" MODIFY ("ORDERDATE" NOT NULL ENABLE);
  ALTER TABLE "SEMI"."APC_PAYMENT" MODIFY ("ORDERNAME" NOT NULL ENABLE);
  ALTER TABLE "SEMI"."APC_PAYMENT" MODIFY ("ORDERADDR" NOT NULL ENABLE);
  ALTER TABLE "SEMI"."APC_PAYMENT" MODIFY ("ORDERPHONE" NOT NULL ENABLE);
  ALTER TABLE "SEMI"."APC_PAYMENT" ADD CHECK (paytype < 3) ENABLE;
  ALTER TABLE "SEMI"."APC_PAYMENT" ADD PRIMARY KEY ("ORDER_NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table APC_PAYMENT
--------------------------------------------------------

  ALTER TABLE "SEMI"."APC_PAYMENT" ADD FOREIGN KEY ("CARTNO_FK")
	  REFERENCES "SEMI"."APC_CART" ("CART_NO") ENABLE;
  ALTER TABLE "SEMI"."APC_PAYMENT" ADD FOREIGN KEY ("PNO_FK")
	  REFERENCES "SEMI"."APC_PRODUCTS" ("PNO") ENABLE;
