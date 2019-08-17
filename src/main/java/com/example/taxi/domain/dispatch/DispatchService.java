package com.example.taxi.domain.dispatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DispatchService {

    @Autowired
    private DispatchRepository dispatchRepository;


    public List<Dispatch> findDispatchList() {
        return dispatchRepository.findAll();
//        return dispatchRepository.findByDriverStatusNot(DriverStatus.DRIVING);
    }

    @Transactional
    public Dispatch saveDispatch(String currentAddress) {
        // TODO user가 승객인지 검사

        return dispatchRepository.save(
                Dispatch.builder()
                        .address(currentAddress)
                        .build());
    }


    @Transactional
    public Dispatch takeTheDispatch(Long id) {
        // TODO SessionUser가 택시기사인지 확인

        Optional<Dispatch> dispatchOptional = dispatchRepository.findByIdAndDriverStatus(id, DriverStatus.WAITING);
        if (!dispatchOptional.isPresent()) {
            throw new RuntimeException("누군가가 이미 차지했습니다");
        }
        Dispatch dispatch = dispatchOptional.get();
        dispatch.setDriverStatus(DriverStatus.COMPLETED);
        dispatch.setCompletedAt(LocalDateTime.now());
        // TODO SessionUser를 할당한다.
//        dispatch.setAssignDriver(SessionUser.getEmail);

        return dispatch;
    }
}
