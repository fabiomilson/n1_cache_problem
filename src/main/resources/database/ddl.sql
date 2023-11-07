create table N1_CACHE.FATHER
(
    ID          NUMBER not null
        constraint ID
            primary key,
    DESC_FATHER VARCHAR2(50)
)
/

create table N1_CACHE.CHILDREN
(
    ID            NUMBER not null
        constraint PK
            primary key,
    FK_FATHER     NUMBER
        constraint FK_FATHER
            references N1_CACHE.FATHER,
    DESC_CHILDREN VARCHAR2(100)
)
/

create sequence N1_CACHE.SEQ_CHILDREN
    maxvalue 1000
/

create sequence N1_CACHE.SEQ_FATHER
    maxvalue 1000
/

