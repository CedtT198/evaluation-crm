package site.easy.to.build.crm.service.taux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.TauxAlerte;
import site.easy.to.build.crm.repository.TauxAlerteRepository;

@Service
public class TauxAlerteServiceImpl implements TauxAlerteService {

    @Autowired
    private TauxAlerteRepository tauxAlerteRepository;

    @Override
    public TauxAlerte save(TauxAlerte tauxAlerte) throws Exception {
        if (tauxAlerte.getAmount().intValue() < 0) throw new Exception("Amount cannot be under 0.");
        return tauxAlerteRepository.save(tauxAlerte);
    }

    @Override
    public TauxAlerte getMostRecentTaux() {
        return tauxAlerteRepository.getMostRecentTaux();
    }
}
