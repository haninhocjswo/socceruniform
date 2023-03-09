package shop.soccerUniform.service.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.dto.ManagerDTO;
import shop.soccerUniform.entity.dto.ManagerSearchForm;
import shop.soccerUniform.entity.enumtype.UserState;

import java.util.List;

public interface ManagerService {

    public void save(ManagerDTO managerDTO);

    public ManagerDTO findById(Long managerId);

    public void updateManager(ManagerDTO managerDTO);

    public void deleteManager(Long managerId);

    public Page<ManagerDTO> managers(ManagerSearchForm managerSearchForm, Pageable pageable);

    public List<Manager> findManagersByState(UserState state);
}
