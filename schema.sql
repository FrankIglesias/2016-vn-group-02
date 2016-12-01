CREATE DATABASE IF NOT EXISTS `db`;

USE db;


    create table Accion (
        discriminador varchar(31) not null,
        id_accion integer not null auto_increment,
        mailAdmin varchar(255),
        accionAquitar_id_accion integer,
        primary key (id_accion)
    );

    create table Acciones_manager (
        id bigint not null,
        accion_id_accion integer,
        primary key (id)
    );

    create table Banco (
        idPoi integer not null,
        primary key (idPoi)
    );

    create table Busqueda (
        id integer not null auto_increment,
        fecha date,
        frase varchar(255),
        tiempo double precision not null,
        terminal_name_terminal varchar(255),
        primary key (id)
    );

    create table CGP (
        idPoi integer not null,
        primary key (idPoi)
    );

    create table Colectivo (
        linea_colectivo varchar(255),
        idPoi integer not null,
        primary key (idPoi)
    );

    create table Domicilio (
        idDomicilio integer not null auto_increment,
        altura varchar(255),
        callePrincipal varchar(255),
        codigoPostal varchar(255),
        comuna integer not null,
        entreCalles varchar(255),
        piso varchar(255),
        unidad varchar(255),
        primary key (idDomicilio)
    );

    create table Feriado (
        idFeriado integer not null auto_increment,
        dia integer not null,
        mes integer not null,
        intervaloFeriado_idIntervalo integer,
        idServicio integer,
        idPoi integer,
        primary key (idFeriado)
    );

    create table Geolocalizacion (
        idGeo integer not null auto_increment,
        latitud double precision not null,
        ciudad varchar(255),
        pais varchar(255),
        provincia varchar(255),
        longitud double precision not null,
        domicilio_idDomicilio integer,
        primary key (idGeo)
    );

    create table GestorIntervalos (
        id_gestor integer not null,
        primary key (id_gestor)
    );

    create table HorarioYDia (
        idHorarioYDia integer not null auto_increment,
        primary key (idHorarioYDia)
    );

    create table IntervaloHorario (
        idIntervalo integer not null auto_increment,
        horaFin tinyblob,
        horaInicio tinyblob,
        medianoche tinyblob,
        primary key (idIntervalo)
    );

    create table Locales (
        idPoi integer not null,
        idRubro integer,
        primary key (idPoi)
    );

    create table Pois (
        idPoi integer not null auto_increment,
        nombrePoi varchar(255),
        horario_idHorarioYDia integer,
        geolocalizacion integer,
        primary key (idPoi)
    );

    create table PuntosPorBusqueda (
        Busqueda_id integer not null,
        puntosObtenidos_idPoi integer not null
    );

    create table Rubro (
        idRubro integer not null auto_increment,
        nombre varchar(255),
        radioDeCercania integer not null,
        primary key (idRubro)
    );

    create table Servicio (
        idServicio integer not null auto_increment,
        nombreDelServicio varchar(255),
        horario_idHorarioYDia integer,
        idPoi integer,
        primary key (idServicio)
    );

    create table Terminal (
        name_terminal varchar(255) not null,
        mailAdmin varchar(255),
        primary key (name_terminal)
    );

    create table Usuario (
        id bigint not null,
        primary key (id)
    );

    create table Usuario_Accion (
        Usuario_id bigint not null,
        listaDeAcciones_id_accion integer not null
    );

    create table palabrasClaves (
        id_poi integer not null,
        palabrasClave varchar(255)
    );

    create table reporteParcialPorTerminal (
        nombre_terminal varchar(255) not null,
        reporteParcialPorTerminal integer
    );

    alter table Usuario_Accion 
        add constraint UK_h936r49s2onvwsptys2g9quep  unique (listaDeAcciones_id_accion);

    alter table Accion 
        add constraint FK_crlxt6ahnyn0ty25b02idd9lt 
        foreign key (accionAquitar_id_accion) 
        references Accion (id_accion);

    alter table Acciones_manager 
        add constraint FK_3h58srxh66jl03af6th8odnxr 
        foreign key (accion_id_accion) 
        references Accion (id_accion);

    alter table Banco 
        add constraint FK_6fkgrvj0d0iaat7la27u49ybd 
        foreign key (idPoi) 
        references Pois (idPoi);

    alter table Busqueda 
        add constraint FK_ee3gi7ghwsngpebqisc0ygi69 
        foreign key (terminal_name_terminal) 
        references Terminal (name_terminal);

    alter table CGP 
        add constraint FK_owji7gswq0o41yt69oky6gv8 
        foreign key (idPoi) 
        references Pois (idPoi);

    alter table Colectivo 
        add constraint FK_1gdtjqixa8n1vc5wlrlp60q3n 
        foreign key (idPoi) 
        references Pois (idPoi);

    alter table Feriado 
        add constraint FK_cb5wvsxy2tgdqgw3nbbv1ouda 
        foreign key (intervaloFeriado_idIntervalo) 
        references IntervaloHorario (idIntervalo);

    alter table Feriado 
        add constraint FK_5mfu322231vyemk9gnfq77c6p 
        foreign key (idServicio) 
        references Servicio (idServicio);

    alter table Feriado 
        add constraint FK_86qip50sxyux2i5cni1f8gj11 
        foreign key (idPoi) 
        references Pois (idPoi);

    alter table Geolocalizacion 
        add constraint FK_c265idobkfl9r7uhhnysdjn8c 
        foreign key (domicilio_idDomicilio) 
        references Domicilio (idDomicilio);

    alter table Locales 
        add constraint FK_35y4as9qs6e93lw0xqlptil9v 
        foreign key (idRubro) 
        references Rubro (idRubro);

    alter table Locales 
        add constraint FK_rywmsr5xpfq5jtcu4dhyuwdlq 
        foreign key (idPoi) 
        references Pois (idPoi);

    alter table Pois 
        add constraint FK_25tno2ryamkxva6tufixbvcu6 
        foreign key (horario_idHorarioYDia) 
        references HorarioYDia (idHorarioYDia);

    alter table Pois 
        add constraint FK_bewuylvucrb424tmg95wkjqsq 
        foreign key (geolocalizacion) 
        references Geolocalizacion (idGeo);

    alter table PuntosPorBusqueda 
        add constraint FK_bvaif6fo2n4rvknrhdx3sn9g6 
        foreign key (puntosObtenidos_idPoi) 
        references Pois (idPoi);

    alter table PuntosPorBusqueda 
        add constraint FK_j9cw6odjrge7io2hv52f7l9d2 
        foreign key (Busqueda_id) 
        references Busqueda (id);

    alter table Servicio 
        add constraint FK_stvsow5e13vvn1kqjmmfm9c1m 
        foreign key (horario_idHorarioYDia) 
        references HorarioYDia (idHorarioYDia);

    alter table Servicio 
        add constraint FK_1erkttcdcmwlhfs7pw3b9k38h 
        foreign key (idPoi) 
        references CGP (idPoi);

    alter table Usuario_Accion 
        add constraint FK_h936r49s2onvwsptys2g9quep 
        foreign key (listaDeAcciones_id_accion) 
        references Accion (id_accion);

    alter table Usuario_Accion 
        add constraint FK_prw0fhcui757fhch5tekpsihp 
        foreign key (Usuario_id) 
        references Usuario (id);

    alter table palabrasClaves 
        add constraint FK_grk9gsdjgiud47d6be25v85xe 
        foreign key (id_poi) 
        references Pois (idPoi);

    alter table reporteParcialPorTerminal 
        add constraint FK_bynx5xf1vofsnp3l9801mnucl 
        foreign key (nombre_terminal) 
        references Terminal (name_terminal);
