directorio: %PATH%/scanner-port/db

jdbc:hsqldb:file:[PATH/NOMBRE_ARCHIVOS]
usuario:SA

url de conexion
jdbc:hsqldb:file:D:/jose/uni/cursos/scd/trabajo/scanner-port/db/scannerport-db




create table request(
id integer,
protocolo varchar(20),
comando   varchar(500)
)

insert into request values(1,'http','HEAD / HTTP/1.1
Host:%SERVER%

');
insert into request values(2,'http','DELETE / HTTP/1.1
Host:%SERVER%

');


SELECT COMANDO FROM REQUEST RE WHERE RE.PROTOCOLO='http'