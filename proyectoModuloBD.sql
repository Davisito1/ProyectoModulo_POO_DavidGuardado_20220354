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

INSERT INTO Autores(nombre, apellido, nacionalidad, fecha_nacimiento) VALUES
('David', 'Guardado', 'hola', '20/05/2025');

SELECT * FROM Autores;

CREATE OR REPLACE TRIGGER trg_libro
BEFORE INSERT ON Libros FOR EACH ROW
BEGIN
    INSERT INTO Libros (id) VALUES ();
END;

CREATE OR REPLACE TRIGGER trg_autor 
BEFORE INSERT ON Autores FOR EACH ROW DECLARE 
BEGIN
    INSERT INTO Autores (id) VALUES (seq_autor.NEXTVAL)
END;