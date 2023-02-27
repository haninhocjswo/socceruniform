package shop.soccerUniform.repository.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.dto.ManagerDTO;
import shop.soccerUniform.entity.dto.ManagerSearchForm;

public interface ManagerQueryRepository {
    public Page<ManagerDTO> managers(ManagerSearchForm managerSearchForm, Pageable pageable);
}
