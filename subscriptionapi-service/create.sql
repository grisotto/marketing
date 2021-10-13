create sequence hibernate_sequence start with 1 increment by 1
create table newsletter
(
    id             bigint not null,
    email_template varchar(255),
    primary key (id)
)
create table subscription
(
    id            bigint not null,
    newsletter_id bigint not null,
    user_id       bigint not null,
    primary key (id)
)
create table user
(
    id               bigint       not null,
    consent          boolean,
    create_date_time timestamp,
    date_of_birth    date,
    email            varchar(320) not null,
    first_name       varchar(255),
    gender           integer,
    update_date_time timestamp,
    primary key (id)
)
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table subscription
    add constraint FKcktix21e2isa0814v2m1s08tt foreign key (newsletter_id) references newsletter
alter table subscription
    add constraint FK8l1goo02px4ye49xd7wgogxg6 foreign key (user_id) references user
