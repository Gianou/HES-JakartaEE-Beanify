
    alter table Album 
        drop constraint FK_bm5s46seevdhdwcsi2c06rtcu

    alter table Artist_Song 
        drop constraint FK_lmgs52t6hj5ngrd63l4qxoa9g

    alter table Artist_Song 
        drop constraint FK_o47kmig003cnn3m8xfc90prtp

    alter table Song 
        drop constraint FK_gdku75qsqamvm22yckhicp180

    alter table Subscriber_Song 
        drop constraint FK_lxiyg58thkdhqealm0g62e8d0

    alter table Subscriber_Song 
        drop constraint FK_k7y2ivl8fq4uf6dxpsohp00vb

    drop table Album if exists

    drop table Artist if exists

    drop table Artist_Song if exists

    drop table Person if exists

    drop table Song if exists

    drop table Subscriber if exists

    drop table Subscriber_Song if exists

    drop sequence hibernate_sequence

    create table Album (
        id bigint not null,
        album varchar(255),
        prenom date,
        artist_id bigint,
        primary key (id)
    )

    create table Artist (
        id bigint not null,
        firstName varchar(255),
        lastName varchar(255),
        artistName varchar(255),
        primary key (id)
    )

    create table Artist_Song (
        artists_id bigint not null,
        songs_id bigint not null,
        primary key (artists_id, songs_id)
    )

    create table Person (
        id bigint not null,
        firstName varchar(255),
        lastName varchar(255),
        primary key (id)
    )

    create table Song (
        id bigint not null,
        title varchar(255),
        url varchar(255),
        album_id bigint,
        primary key (id)
    )

    create table Subscriber (
        id bigint not null,
        firstName varchar(255),
        lastName varchar(255),
        email varchar(255),
        primary key (id)
    )

    create table Subscriber_Song (
        Subscriber_id bigint not null,
        likedSongs_id bigint not null,
        primary key (Subscriber_id, likedSongs_id)
    )

    alter table Album 
        add constraint FK_bm5s46seevdhdwcsi2c06rtcu 
        foreign key (artist_id) 
        references Artist

    alter table Artist_Song 
        add constraint FK_lmgs52t6hj5ngrd63l4qxoa9g 
        foreign key (songs_id) 
        references Song

    alter table Artist_Song 
        add constraint FK_o47kmig003cnn3m8xfc90prtp 
        foreign key (artists_id) 
        references Artist

    alter table Song 
        add constraint FK_gdku75qsqamvm22yckhicp180 
        foreign key (album_id) 
        references Album

    alter table Subscriber_Song 
        add constraint FK_lxiyg58thkdhqealm0g62e8d0 
        foreign key (likedSongs_id) 
        references Song

    alter table Subscriber_Song 
        add constraint FK_k7y2ivl8fq4uf6dxpsohp00vb 
        foreign key (Subscriber_id) 
        references Subscriber

    create sequence hibernate_sequence start with 1
