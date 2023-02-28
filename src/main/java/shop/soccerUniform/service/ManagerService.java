package shop.soccerUniform.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.dto.ManagerDTO;
import shop.soccerUniform.entity.dto.ManagerSearchForm;

public interface ManagerService {

    public void save(ManagerDTO managerDTO);

    public ManagerDTO findById(Long managerId);

    public void updateManager(ManagerDTO managerDTO);

    public void deleteManager(Long managerId);

    public Page<ManagerDTO> managers(ManagerSearchForm managerSearchForm, Pageable pageable);
}
