-- apc_payment 결제 테이블

create table apc_payment(

	order_no number(5) primary key, 				-- 주문번호
	cartno_fk number(5),							-- 카트번호
	order_id number(5) not null,					-- 주문아이디(비회원,회원통합관리) 
	category_fk varchar2(8) not null,				-- 카테고리 코드
	pname varchar2(50) not null,					-- 상품명	
	pqty number(5) not null,						-- 수량
	price number(10) not null,						-- 가격
	transcost number(10) default 3000,       		-- 배송비
	paytype number(1) not null check (paytype < 3),  -- 결제타입(0 계좌이체, 1 카드결제)
	orderdate date not null,						-- 주문날짜
	ordername varchar2(50) not null,				-- 주문자 이름
	orderaddr varchar2(1000) not null,				-- 주문자 주소
	orderphone varchar2(50) not null,				-- 주문자 연락처
	
	foreign key (category_fk) references apc_category(category_code),
	foreign key (cartno_fk) references apc_cart(cart_no)
	
);

-- 20220426 : memno외래키 삭제, order_id 추가 / category_no 삭제, category_fk(코드) 외래키참조  / cartno_fk(외래키)추가