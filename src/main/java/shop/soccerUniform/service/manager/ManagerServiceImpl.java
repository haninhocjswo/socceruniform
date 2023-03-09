package shop.soccerUniform.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.dto.ManagerDTO;
import shop.soccerUniform.entity.dto.ManagerSearchForm;
import shop.soccerUniform.entity.enumtype.Role;
import shop.soccerUniform.entity.enumtype.UserState;
import shop.soccerUniform.repository.manager.ManagerRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    @Transactional
    @Override
    public void save(ManagerDTO managerDTO) {
        managerDTO.setRole(Role.ROLE_MANAGER);
        managerDTO.setState(UserState.WAIT);

        Manager manager = Manager.builder()
                .companyName(managerDTO.getCompanyName())
                .companyNum(managerDTO.getCompanyNum())
                .ceoName(managerDTO.getCeoName())
                .businessResistNum(managerDTO.getBusinessResistNum())
                .post(managerDTO.getPost())
                .address(managerDTO.getAddress())
                .detailAddress(managerDTO.getDetailAddress())
                .chargeName(managerDTO.getChargeName())
                .chargePosition(managerDTO.getChargePosition())
                .chargeMobile(managerDTO.getChargeMobile())
                .chargeNum(managerDTO.getChargeNum())
                .build();

        manager.addUser(managerDTO.getLoginId(), managerDTO.getPassword(), managerDTO.getUsername(),
                managerDTO.getEmail(), managerDTO.getRole(), managerDTO.getState());

        manager.addDate(LocalDateTime.now(), LocalDateTime.now());

        managerRepository.save(manager);
    }

    @Override
    public ManagerDTO findById(Long managerId) {
        Manager manager = managerRepository.findById(managerId).orElse(null);
        if(manager != null) {
            ManagerDTO managerDTO = new ManagerDTO(manager);
            return managerDTO;
        }
        return null;
    }

    @Transactional
    @Override
    public void updateManager(ManagerDTO managerDTO) {
        Manager manager = managerRepository.findById(managerDTO.getManagerId()).orElse(null);
        if(manager != null) {
            log.info("업데이트 폼={}", managerDTO);
            manager.editManager(managerDTO);
            manager.editUser(managerDTO.getPassword(), managerDTO.getUsername(), managerDTO.getEmail(), managerDTO.getState());
            manager.editDate(LocalDateTime.now());
        }
    }

    @Transactional
    @Override
    public void deleteManager(Long managerId) {
        Manager manager = managerRepository.findById(managerId).orElse(null);
        if(manager != null) {
            manager.delUser();
        }
    }

    @Override
    public Page<ManagerDTO> managers(ManagerSearchForm managerSearchForm, Pageable pageable) {
        return managerRepository.managers(managerSearchForm, pageable);
    }

    @Override
    public List<Manager> findManagersByState(UserState state) {
        return managerRepository.findManagersByState(state);
    }
}
