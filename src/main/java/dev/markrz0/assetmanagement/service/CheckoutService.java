package dev.markrz0.assetmanagement.service;

import dev.markrz0.assetmanagement.exception.DeviceUnavailableException;
import dev.markrz0.assetmanagement.model.dto.CheckoutRequest;
import dev.markrz0.assetmanagement.model.dto.CheckoutResponse;
import dev.markrz0.assetmanagement.model.dto.EmployeeHistoryResponse;
import dev.markrz0.assetmanagement.model.entity.CheckoutRecord;
import dev.markrz0.assetmanagement.model.entity.Device;
import dev.markrz0.assetmanagement.model.entity.Employee;
import dev.markrz0.assetmanagement.model.entity.enums.DeviceStatus;
import dev.markrz0.assetmanagement.repository.CheckoutRecordRepository;
import dev.markrz0.assetmanagement.repository.DeviceRepository;
import dev.markrz0.assetmanagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final EmployeeRepository employeeRepository;
    private final DeviceRepository deviceRepository;
    private final CheckoutRecordRepository checkoutRecordRepository;

    @Transactional
    public CheckoutResponse checkoutDevice(CheckoutRequest request) {

        Employee employee = employeeRepository.findById(request.employeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        Device device = deviceRepository.findById(request.deviceId())
                .orElseThrow(() -> new IllegalArgumentException("Device not found"));

        if (device.getStatus() != DeviceStatus.AVAILABLE) {
            throw new DeviceUnavailableException("Device " + device.getName() + " is currently " + device.getStatus());
        }

        device.setStatus(DeviceStatus.CHECKED_OUT);
        deviceRepository.save(device);

        CheckoutRecord record = CheckoutRecord.builder()
                .employee(employee)
                .device(device)
                .checkoutDate(LocalDateTime.now())
                .build();

        CheckoutRecord savedRecord = checkoutRecordRepository.save(record);

        return mapToResponse(savedRecord);
    }

    @Transactional(readOnly = true)
    public EmployeeHistoryResponse getEmployeeHistory(Long employeeId) {
        Employee employee = employeeRepository.findByIdWithHistory(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        // Map the list of entities to a list of DTOs
        List<CheckoutResponse> history = employee.getCheckoutRecords()
                .stream()
                .map(this::mapToResponse)
                .toList();

        return new EmployeeHistoryResponse(
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                history
        );
    }

    // --- PRIVATE HELPER METHOD ---

    private CheckoutResponse mapToResponse(CheckoutRecord record) {
        return new CheckoutResponse(
                record.getId(),
                record.getEmployee().getName(),
                record.getDevice().getName(),
                record.getDevice().getSerialNumber(),
                record.getCheckoutDate(),
                record.getReturnDate()
        );
    }
}