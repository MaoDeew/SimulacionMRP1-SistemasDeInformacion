CREATE TABLE Producto(

	idProducto INTEGER(11) NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(45) NOT NULL,
	PRIMARY KEY(idProducto)

);

CREATE TABLE MateriaPrima(

	idMateriaPrima INTEGER(11) NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(45) NOT NULL,
	cantidad INTEGER(11) NOT NULL,
	idProducto INTEGER(11) NOT NULL,
	PRIMARY KEY(idMateriaPrima),
	FOREIGN KEY(idProducto) REFERENCES Producto(idProducto)

);

CREATE TABLE MesVenta(

	idMesVenta INTEGER(11) NOT NULL AUTO_INCREMENT,
	nombreMes VARCHAR(45) NOT NULL,
	PRIMARY KEY(idMesVenta)

);

CREATE TABLE Venta(

	idVenta INTEGER(11) NOT NULL AUTO_INCREMENT,
	cantidad INTEGER(11) NOT NULL,
	idProducto INTEGER(11) NOT NULL,
	idMesVenta INTEGER(11) NOT NULL,
	PRIMARY KEY(idVenta),
	FOREIGN KEY(idProducto) REFERENCES Producto(idProducto),
	FOREIGN KEY(idMesVenta) REFERENCES MesVenta(idMesVenta)

);

INSERT INTO MesVenta VALUES
(1,"Enero"),
(2,"Febrero"),
(3,"Marzo"),
(4,"Abril"),
(5,"Mayo"),
(6,"Junio"),
(7,"Julio"),
(8,"Agosto"),
(9,"Septiembre"),
(10,"Octubre"),
(11,"Noviembre"),
(12,"Diciembre");

select nombre, nombremes, cantidad
from Venta
inner join Producto on Producto.idProducto=Venta.idProducto
inner join MesVenta on MesVenta.idMesVenta=Venta.idMesVenta
where Venta.idProducto=1;

INSERT INTO Producto VALUES
(1,"A"),
(2,"B"),
(3,"C"),
(4,"D"),
(5,"E");

-------Producto A----------
INSERT INTO MateriaPrima VALUES
(1,"Papa sabanera",100,1),
(2,"Sal",24,1),
(3,"Saborizante Limón",125,1),
(4,"Aceite",100,1),
(5,"Inosinato Disódico",50,1);

-------Producto B----------
INSERT INTO MateriaPrima VALUES
(6,"Papa sabanera",150,2),
(7,"Sal",12,2),
(8,"Saborizante Pollo",125,2),
(9,"Aceite",100,2),
(10,"Inosinato Disódico",150,2);

-------Producto C----------
INSERT INTO MateriaPrima VALUES
(11,"Papa sabanera",110,3),
(12,"Sal",35,3),
(13,"Saborizante Natural",125,3),
(14,"Aceite",100,3),
(15,"Inosinato Disódico",30,3);

-------Producto D----------
INSERT INTO MateriaPrima VALUES
(16,"Papa sabanera",135,4),
(17,"Sal",44,4),
(18,"Saborizante BBQ",125,4),
(19,"Aceite",100,4),
(20,"Inosinato Disódico",250,4);

----Suma total cantidad por producto
select Producto.nombre, sum(cantidad) Total
from MateriaPrima
inner join Producto ON Producto.idProducto=MateriaPrima.idProducto 
group by Producto.idProducto;

----Suma total cantidad por materiaPrima
select MateriaPrima.nombre nombreMateriaPrima,sum(cantidad) Total
from MateriaPrima
inner join Producto ON Producto.idProducto=MateriaPrima.idProducto 
group by MateriaPrima.nombre;

select nombre, cantidad
from MateriaPrima
where idProducto=1;