drop table if exists cases;
drop table if exists parties;
drop table if exists party_addresses;

create table cases(
    id int primary key auto_increment,
    number varchar(255),
    type varchar(255),
    state varchar(255),
    date_of_entry date
);

create table parties(
    id int primary key auto_increment,
    case_id int not null,
    party_type varchar(100),
    name varchar(255),
    active bit,
    foreign key (case_id) references cases (id)
);

create table party_addresses(
    id int primary key auto_increment,
    ordinal_id int,
    city varchar(255),
    postal_code varchar(255),
    street varchar(255),
    party_id int,
    foreign key (party_id) references parties (id)
);

