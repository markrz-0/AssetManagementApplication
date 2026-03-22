package dev.markrz0.assetmanagement.controller;

import dev.markrz0.assetmanagement.model.dto.CheckoutRequest;
import dev.markrz0.assetmanagement.model.dto.CheckoutResponse;
import dev.markrz0.assetmanagement.model.dto.EmployeeHistoryResponse;
import dev.markrz0.assetmanagement.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/checkouts")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<CheckoutResponse> checkoutDevice(@RequestBody @Valid CheckoutRequest request) {
        CheckoutResponse response = checkoutService.checkoutDevice(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/employee/{id}/history")
    public ResponseEntity<EmployeeHistoryResponse> getEmployeeHistory(@PathVariable Long id) {
        EmployeeHistoryResponse history = checkoutService.getEmployeeHistory(id);
        return ResponseEntity.ok(history);
    }
}