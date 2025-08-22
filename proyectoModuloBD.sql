CREATE SEQUENCE seq_libro START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_autor START WITH 1 INCREMENT BY 1;

CREATE TABLE Libros
(
    id INT PRIMARY KEY ,
    titulo VARCHAR(200) NOT NULL,
    isbn VARCHAR(200) NOT NULL,
    año_publicacion INT,
    genero VARCHAR(50),
    autor_id INT,
    FOREIGN KEY (autor_id) REFERENCES Autores(id)
);

CREATE TABLE Autores
(
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(50),
    fecha_nacimiento DATE
);

INSERT INTO Autores(id, nombre, apellido, nacionalidad, fecha_nacimiento) VALUES
(seq_autor.nextval, 'David', 'Guardado', 'El Salvador', '26/04/2008');

SELECT * FROM Autores;

SELECT * FROM Libros;

DELETE FROM Autores;
