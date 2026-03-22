package dev.markrz0.assetmanagement.service;

import dev.markrz0.assetmanagement.model.dto.DeviceCreateRequest;
import dev.markrz0.assetmanagement.model.dto.DeviceResponse;
import dev.markrz0.assetmanagement.model.entity.Device;
import dev.markrz0.assetmanagement.model.entity.enums.DeviceStatus;
import dev.markrz0.assetmanagement.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<DeviceResponse> getAllDevices() {
        return deviceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<DeviceResponse> getAvailableDevices() {
        return deviceRepository.findAllByStatus(DeviceStatus.AVAILABLE)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public DeviceResponse addDevice(DeviceCreateRequest request) {
        Device newDevice = Device.builder()
                .name(request.name())
                .serialNumber(request.serialNumber())
                .status(DeviceStatus.AVAILABLE)
                .build();

        Device savedDevice = deviceRepository.save(newDevice);

        return mapToResponse(savedDevice);
    }

    private DeviceResponse mapToResponse(Device device) {
        return new DeviceResponse(
                device.getId(),
                device.getName(),
                device.getSerialNumber(),
                device.getStatus()
        );
    }
}