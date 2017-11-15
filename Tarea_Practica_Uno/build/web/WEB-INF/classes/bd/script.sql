create database bd_tareaUno;
use bd_tareaUno;

drop database bd_tareaUno;

create table pais(
    id int auto_increment,
    nombre varchar(100),
    primary key(id)
);

create table posicion(
    id int auto_increment,
    nombre varchar(100),
    primary key(id)
);

create table jugador(
    id int auto_increment,
    nombre varchar(100),
    pais int,
    posicion int,
    dorsal int,
    primary key(id),
    foreign key(pais) references pais(id),
    foreign key(posicion) references posicion(id)
);
/*Insert tabla pais*/
insert into pais values(null,"Alemania");
insert into pais values(null,"Argentina");
insert into pais values(null,"Brasil");
insert into pais values(null,"Belgica");
insert into pais values(null,"Chile");
insert into pais values(null,"Colombia");
insert into pais values(null,"España");
insert into pais values(null,"Francia");
insert into pais values(null,"Uruguay");
insert into pais values(null,"Holanda");
insert into pais values(null,"Inglaterra");
insert into pais values(null,"Italia");
insert into pais values(null,"Perú");


/*Insert tabla posicion*/
insert into posicion values(null,"Arquero");
insert into posicion values(null,"Defensa Central Derecho");
insert into posicion values(null,"Defensa Central Izquerdo");
insert into posicion values(null,"Lateral Derecho");
insert into posicion values(null,"Lateral Izquerdo");
insert into posicion values(null,"Volante Central");
insert into posicion values(null,"Volante Derecho");
insert into posicion values(null,"Volante Izquerdo");
insert into posicion values(null,"Volante Ofensivo");
insert into posicion values(null,"Extremo Derecho");
insert into posicion values(null,"Extremo Izquerdo");
insert into posicion values(null,"Delantero Centro");

/*Insert de jugadores*/
insert into jugador values(null,"Kylian Mbappé",8,12,20);

select * from jugador;
select * from pais;
select * from posicion;

SELECT jugador.id, jugador.nombre, pais.nombre AS 'País', posicion.nombre AS 'Posición', jugador.dorsal 
FROM jugador 
INNER JOIN pais ON jugador.pais = pais.id 
INNER JOIN posicion ON jugador.posicion = posicion.id;

SELECT jugador.id, jugador.nombre, pais.nombre AS 'País', posicion.nombre AS 'Posición', jugador.dorsal 
FROM jugador 
INNER JOIN pais ON jugador.pais = pais.id 
INNER JOIN posicion ON jugador.posicion = posicion.id 
WHERE 
jugador.nombre LIKE '%%' OR
pais.nombre LIKE '%%' OR
posicion.nombre LIKE '%%' OR
jugador.dorsal LIKE '%%';
        
