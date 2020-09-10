create table home
(
    id     int unsigned auto_increment
        primary key,
    name   varchar(100)   null,
    image  varchar(255)   null,
    number int            null,
    type   varchar(30)    null,
    link   varchar(255)   null,
    price  decimal(10, 2) null
);

create table safeguard(
    uuid char(32) primary key,
    name varchar(36) not null
);


create table shopkeeper(
    uuid char(32) primary key,
    name varchar(36) not null,
    total_sales int not null default 0,
    total_goods int not null default 0
);

create table detail
(
    id     int unsigned auto_increment
        primary key,
    title   varchar(255)   not null,
    sales_vol int          not null,
    origin_price numeric(10,2)  not null,
    describe_score numeric(3,2),
    price_score numeric(3,2),
    quantity_score numeric(3,2),
    description text null
);

alter table detail add column home_id int unsigned not null;
alter table detail add constraint fk_home foreign key (home_id) references home(id);

create table detail_safeguard(
    safeguard_id char(32) not null,
    detail_id int unsigned not null,
    constraint fk_safeguard_id foreign key (safeguard_id) references safeguard(uuid),
    constraint fk_detail_id foreign key (detail_id) references detail(id)
);

create table detail_pic
(
    id     int unsigned auto_increment
        primary key,
    image varchar(255),
    detail_id int unsigned not null,
    constraint fk_detail_pic_id foreign key (detail_id) references detail(id)
);

alter table shopkeeper add column head_sculpture varchar(255);
alter table detail add column shop_keeper_uuid char(32);
alter table detail add constraint fk_shop_keeper foreign key (shop_keeper_uuid) references shopkeeper(uuid)

create table description_pic
  (
      id     int unsigned auto_increment
          primary key,
      image varchar(255),
      detail_id int unsigned not null,
      constraint fk_description_pic_id foreign key (detail_id) references detail(id)
  );

create table param(
   id     int unsigned auto_increment
     primary key,
   size varchar(50),
   length varchar(50),
   season varchar(50),
   address varchar(50),
   material varchar(50),
   type varchar(50),
   style varchar(50),
   fashion varchar(50),
   detail_id int unsigned not null,
   constraint fk_param_id foreign key (detail_id) references detail(id)
)

create table comment(
  id  int unsigned auto_increment primary key,
  username varchar(20),
  head_sculpture varchar(255),
  detail text,
  date datetime,
  color varchar(20),
  detail_id int unsigned not null,
  constraint fk_comment_id foreign key (detail_id) references detail(id)
);

create table comment_pic(
  id  int unsigned auto_increment primary key,
  image varchar(255),
  comment_id int unsigned not null,
  constraint fk_comment_pic_id foreign key (comment_id) references comment(id)
)