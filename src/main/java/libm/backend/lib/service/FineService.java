package libm.backend.lib.service;

import libm.backend.lib.entity.Fine;

import java.util.List;

public interface FineService {
    Fine createFine(Fine fine);
    Fine getFineById(Long id);
    List<Fine> getAllFines();
    Fine markFineAsPaid(Long id);
}
