create table if not exists clients (
    client_id bigint not null auto_increment,
    client_name varchar(255) not null,
    client_email varchar(255) not null,
    client_phone varchar(255) not null,

    primary key (client_id)
);

alter table clients 
add constraint uk_email unique(client_email);
