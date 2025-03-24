package site.easy.to.build.crm.service.taux;

import site.easy.to.build.crm.entity.TauxAlerte;

public interface TauxAlerteService {
    TauxAlerte save(TauxAlerte tauxAlerte) throws Exception ;
    TauxAlerte getMostRecentTaux();
}
