package shop.soccerUniform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.dto.ManagerDTO;
import shop.soccerUniform.entity.dto.ManagerSearchForm;
import shop.soccerUniform.repository.manager.ManagerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    @Override
    public void save(ManagerDTO manager) {

    }

    @Override
    public ManagerDTO findById(Long managerId) {
        return null;
    }

    @Override
    public void updateManager(ManagerDTO manager) {

    }

    @Override
    public void deleteManger(Long managerId) {

    }

    @Override
    public Page<ManagerDTO> managers(ManagerSearchForm managerSearchForm, Pageable pageable) {
        return managerRepository.managers(managerSearchForm, pageable);
    }
}
