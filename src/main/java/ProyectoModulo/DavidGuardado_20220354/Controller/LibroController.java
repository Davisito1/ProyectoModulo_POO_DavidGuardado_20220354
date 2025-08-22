package ProyectoModulo.DavidGuardado_20220354.Controller;

import ProyectoModulo.DavidGuardado_20220354.Exceptions.ExceptionLibroNoEncontrado;
import ProyectoModulo.DavidGuardado_20220354.Models.DTO.LibroDTO;
import ProyectoModulo.DavidGuardado_20220354.Services.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiLibro")
public class LibroController {
    @Autowired
    private LibroService service;

    //Consultar los datos
    @GetMapping("/consultar")
    public List<LibroDTO> obtenerLibros() { return service.obtenerLibros(); }

    //Insertar datos
    @PostMapping("/insertar")
    public ResponseEntity<?> insertarLibro(@Valid @RequestBody LibroDTO dto, BindingResult result) {
        if (result.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        try {
            LibroDTO creado = service.insertarLibro(dto);
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Libro registrado"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    //Actualizar datos
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarLibro(@PathVariable Long id,@Valid @RequestBody LibroDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        try{
            LibroDTO actualizado = service.actualizarLibro(id, dto);
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Libro actualizado"
            ));
        } catch (ExceptionLibroNoEncontrado e){
            return ResponseEntity.status(404).body(Map.of(
                   "status", "error",
                   "message", e.getMessage()
            ));
        }
    }

    //Eliminar datos
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable Long id){
        try{
            //Si la funcion del service devuelve se mostrara un mensaje de exito, caso contrario mostrara un mensaje de error
            if (service.eliminarLibro(id)){
                return ResponseEntity.ok(Map.of(
                        "status", "success",
                        "message", "Libro eliminado"
                ));
            }
            else{
                return ResponseEntity.status(404).body(Map.of(
                        "status", "error",
                        "message", "Libro no encontrado"
                ));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "error",
                    "message", "Error al eliminar libro",
                    "timestamp", Instant.now().toString()
            ));
        }
    }
}
