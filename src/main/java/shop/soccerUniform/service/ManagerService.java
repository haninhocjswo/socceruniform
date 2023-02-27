package shop.soccerUniform.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.dto.ManagerDTO;
import shop.soccerUniform.entity.dto.ManagerSearchForm;

import java.util.List;

public interface ManagerService {

    public void save(ManagerDTO manager);

    public ManagerDTO findById(Long managerId);

    public void updateManager(ManagerDTO manager);

    public void deleteManger(Long managerId);
    public Page<ManagerDTO> managers(ManagerSearchForm managerSearchForm, Pageable pageable);
}
