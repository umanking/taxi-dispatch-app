package com.example.taxi.domain.dispatch;

import com.example.taxi.dto.DispatchDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DispatchService {

    @Autowired
    private DispatchRepository dispatchRepository;

    @Autowired
    private ModelMapper modelMapper;


    /**
     * 목록조회
     *
     * @return
     */
    public List<DispatchDto.DispatchResponse> findDispatchList() {
        List<Dispatch> dispatches = dispatchRepository.findAll();
        Type listType = new TypeToken<List<DispatchDto.DispatchResponse>>() {
        }.getType();
        return modelMapper.map(dispatches, listType);
    }

    /**
     * 택시 배차 요청
     *
     * @param currentAddress
     * @return
     */
    @Transactional
    public DispatchDto.DispatchResponse saveDispatch(String currentAddress) {
        // TODO user가 승객인지 확인
        Dispatch saveDispatch = dispatchRepository.save(
                Dispatch.builder()
                        .address(currentAddress)
                        .build());

        return modelMapper.map(saveDispatch, DispatchDto.DispatchResponse.class);
    }


    /**
     * 기사 배차
     *
     * @param id
     * @return
     */
    @Transactional
    public DispatchDto.DispatchResponse takeTheDispatch(Long id) {
        // TODO SessionUser가 택시기사인지 확인
        Optional<Dispatch> dispatchOptional = dispatchRepository.findByIdAndDriverStatus(id, DriverStatus.WAITING);
        if (!dispatchOptional.isPresent()) {
            throw new RuntimeException("누군가가 이미 차지했습니다");
        }
        Dispatch dispatch = dispatchOptional.get();
        dispatch.setDriverStatus(DriverStatus.COMPLETED);
        dispatch.setCompletedAt(LocalDateTime.now());
        // TODO SessionUser를 할당한다.
        //dispatch.setAssignDriver(SessionUser.getEmail);

        return modelMapper.map(dispatch, DispatchDto.DispatchResponse.class);
    }
}
