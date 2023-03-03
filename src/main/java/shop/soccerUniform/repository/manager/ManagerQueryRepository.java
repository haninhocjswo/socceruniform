package shop.soccerUniform.repository.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.dto.ManagerDTO;
import shop.soccerUniform.entity.dto.ManagerSearchForm;
import shop.soccerUniform.entity.enumtype.UserState;

import java.util.List;

public interface ManagerQueryRepository {
    public Page<ManagerDTO> managers(ManagerSearchForm managerSearchForm, Pageable pageable);

    List<Manager> findManagerState(UserState state);
}
