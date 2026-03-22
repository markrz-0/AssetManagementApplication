package dev.markrz0.assetmanagement.controller;

import dev.markrz0.assetmanagement.model.dto.DeviceCreateRequest;
import dev.markrz0.assetmanagement.model.dto.DeviceResponse;
import dev.markrz0.assetmanagement.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devices")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping
    public ResponseEntity<List<DeviceResponse>> getAllDevices() {
        List<DeviceResponse> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/available")
    public ResponseEntity<List<DeviceResponse>> getAvailableDevices() {
        List<DeviceResponse> availableDevices = deviceService.getAvailableDevices();
        return ResponseEntity.ok(availableDevices);
    }

    @PostMapping
    public ResponseEntity<DeviceResponse> addDevice(@RequestBody DeviceCreateRequest device) {
        DeviceResponse savedDevice = deviceService.addDevice(device);
        return new ResponseEntity<>(savedDevice, HttpStatus.CREATED);
    }
}
