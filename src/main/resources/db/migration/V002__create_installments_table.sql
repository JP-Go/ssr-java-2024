create table installment_plans (
    plan_id bigint not null auto_increment,
    client_id bigint not null,
    description varchar(255),
    end_value decimal(10,2) not null,
    installments tinyint not null,
    created_at datetime not null,

    primary key(plan_id)
);

alter table installment_plans 
add constraint fk_client_installment_plan foreign key (client_id) references clients(client_id);
