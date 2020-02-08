package com.ffx11.qrms.controller;

import com.ffx11.qrms.entity.CodigoQR;
import com.ffx11.qrms.service.ICodigoQRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CodigoQRRestController {

    @Autowired
    private ICodigoQRService iCodigoQRService;

    @GetMapping("/codigoqr")
    public List<CodigoQR> index() {
        return iCodigoQRService.findAll();
    }

    @GetMapping("/codigoqr/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        CodigoQR codigoQR = null;
        Map<String, Object> response = new HashMap<>();

        try {
            codigoQR = iCodigoQRService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(" :  ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (codigoQR == null) {
            response.put("mensaje", "El CodigoQR con el id ".concat(id.toString().concat(" no existe ne la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CodigoQR>(codigoQR, HttpStatus.OK);
    }

    @PostMapping("/codigoqr")
    public ResponseEntity<?> create(@RequestBody CodigoQR codigoQR) {

        Map<String, Object> response = new HashMap<>();
        CodigoQR codigoQR1 = null;
        try {
            codigoQR1 = iCodigoQRService.save(codigoQR);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al persistir un nuevo CodigoQR");
            response.put("error", e.getMessage().concat(" :  ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El nuevo CodigoQR ha sido persistido satisfactoriamente");
        response.put("codigoqr", codigoQR1);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @PutMapping("/codigoqr/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CodigoQR codigoQR) {

        Map<String, Object> response = new HashMap<>();
        CodigoQR codigoQR1 = null;
        CodigoQR codigoQR2 = null;

        try {
            codigoQR1 = iCodigoQRService.findById(id);
            if (codigoQR == null) {
                response.put("mensaje", "No se pudo editar. El CodigoQR con el id ".concat(id.toString().concat(" no existe ne la base de datos")));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al acceder a la base de datos");
            response.put("error", e.getMessage().concat(" :  ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        codigoQR1.setApellido(codigoQR.getApellido());
        codigoQR1.setNombre(codigoQR.getNombre());
        codigoQR1.setEmail(codigoQR.getEmail());

        try {
            codigoQR2 = iCodigoQRService.save(codigoQR1);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el update a la base de datos");
            response.put("error", e.getMessage().concat(" :  ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("mensaje", "Actualizacion satisfactoria");
        response.put("codigoqr", codigoQR2);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/codigoqr/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            iCodigoQRService.delete(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar a la base de datos");
            response.put("error", e.getMessage().concat(" :  ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "CodigoQR eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
