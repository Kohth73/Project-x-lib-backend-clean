package libm.backend.lib.service.service.impl;

import libm.backend.lib.entity.Fine;
import libm.backend.lib.repository.FineRepository;
import libm.backend.lib.service.FineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FineServiceImpl implements FineService {

    private final FineRepository fineRepository;

    public FineServiceImpl(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    @Override
    public Fine createFine(Fine fine) {
        return fineRepository.save(fine);
    }

    @Override
    public Fine getFineById(Long id) {
        return fineRepository.findById(id).orElse(null);
    }

    @Override
    public List<Fine> getAllFines() {
        return fineRepository.findAll();
    }

    @Override
    public Fine markFineAsPaid(Long id) {
        Fine fine = fineRepository.findById(id).orElseThrow(() -> new RuntimeException("Fine not found"));
        fine.setPaid(true);
        return fineRepository.save(fine);
    }
}
