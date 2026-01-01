package libm.backend.lib.controller;

import libm.backend.lib.entity.Fine;
import libm.backend.lib.service.FineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @PostMapping
    public ResponseEntity<Fine> createFine(@RequestBody Fine fine) {
        return ResponseEntity.ok(fineService.createFine(fine));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fine> getFine(@PathVariable Long id) {
        Fine fine = fineService.getFineById(id);
        if (fine == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(fine);
    }

    @GetMapping
    public ResponseEntity<List<Fine>> getAllFines() {
        return ResponseEntity.ok(fineService.getAllFines());
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<Fine> payFine(@PathVariable Long id) {
        Fine fine = fineService.markFineAsPaid(id);
        return ResponseEntity.ok(fine);
    }
}
