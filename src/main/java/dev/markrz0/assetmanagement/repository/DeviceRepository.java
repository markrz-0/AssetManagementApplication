package dev.markrz0.assetmanagement.repository;

import dev.markrz0.assetmanagement.model.entity.Device;
import dev.markrz0.assetmanagement.model.entity.enums.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findAllByStatus(DeviceStatus status);
}