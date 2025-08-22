package ProyectoModulo.DavidGuardado_20220354.Services;

import ProyectoModulo.DavidGuardado_20220354.Entities.LibroEntity;
import ProyectoModulo.DavidGuardado_20220354.Exceptions.ExceptionLibroNoEncontrado;
import ProyectoModulo.DavidGuardado_20220354.Models.DTO.LibroDTO;
import ProyectoModulo.DavidGuardado_20220354.Repositories.LibroRepository;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LibroService {
    @Autowired
    private LibroRepository repo;

    //Consultar los datos
    public List<LibroDTO> obtenerLibros() {
        List<LibroEntity> lista = repo.findAll();
        return lista.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    //Insertar datos
    public LibroDTO insertarLibro(LibroDTO data) {
        if (data == null){
            throw new IllegalArgumentException("Datos nulos");
        }

        try{
            LibroEntity entity = convertirAEntity(data);
            LibroEntity guardado = repo.save(entity);
            return convertirADTO(guardado);

        } catch (Exception e){
            log.error("Error al registrar libro: " + e.getMessage());
            throw new RuntimeException("No se pudo registrar el libro");
        }
    }

    //Actualizar datos
    public LibroDTO actualizarLibro(Long id, @Valid LibroDTO data) {
        if (data == null){
            throw new IllegalArgumentException("Datos nulos");
        }

        try{
            LibroEntity existente = repo.findById(id).orElseThrow(() -> new ExceptionLibroNoEncontrado("No se encontro libro con ID: " + id));

            existente.setAutor_id(data.getAutor_id());
            existente.setTitulo(data.getTitulo());
            existente.setIsbn(data.getIsbn());
            existente.setAnio(data.getAnio());

            LibroEntity actualizado = repo.save(existente);
            return convertirADTO(actualizado);
        } catch (Exception e){
            log.error("Error al actualizar libro: " + e.getMessage());
            throw new RuntimeException("No se pudo actualizar el libro");
        }
    }

    //Eliminar datos
    public boolean eliminarLibro(Long id) {
        try{
            LibroEntity existente = repo.findById(id).orElse(null);
            if (existente != null){
                repo.deleteById(id);
                return true;
            }
            else{
                log.warn("Libro con ID " + id + " no encontrado para eliminar");
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("No se encontro libro con ID " + id + " para eliminar");
        }
    }

    //Metodo para convertir los datos de DTO a Entity
    private LibroEntity convertirAEntity(LibroDTO dto) {
        LibroEntity entity = new LibroEntity();
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setIsbn(dto.getIsbn());
        entity.setAnio(dto.getAnio());
        entity.setGenero(dto.getGenero());
        entity.setAutor_id(dto.getAutor_id());
        return entity;
    }

    //Metodo para convertir los datos de Entity a DTO
    private LibroDTO convertirADTO(LibroEntity entity) {
        LibroDTO dto = new LibroDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setIsbn(entity.getIsbn());
        dto.setAnio(entity.getAnio());
        dto.setGenero(entity.getGenero());
        dto.setAutor_id(entity.getAutor_id());
        return dto;
    }
}
