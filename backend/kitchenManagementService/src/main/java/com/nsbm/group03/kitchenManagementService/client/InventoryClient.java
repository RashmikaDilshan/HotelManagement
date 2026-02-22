package com.nsbm.group03.kitchenManagementService.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.nsbm.group03.kitchenManagementService.dto.InventoryItemDTO;
import com.nsbm.group03.kitchenManagementService.dto.InventoryRequestDTO;

/**
 * REST client to communicate with the Inventory Management Service.
 * Kitchen does NOT store inventory — it sends requests to Inventory Service.
 */
@Component
public class InventoryClient {

    private static final Logger logger = LoggerFactory.getLogger(InventoryClient.class);

    private final RestTemplate restTemplate;

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Send inventory consumption request when a kitchen order is created.
     * Deducts ingredient quantities from the Inventory Service.
     */
    public boolean deductInventory(List<InventoryRequestDTO> items) {
        try {
            String url = inventoryServiceUrl + "/api/inventory/deduct";
            restTemplate.postForEntity(url, items, Void.class);
            logger.info("Inventory deduction request sent successfully for {} items", items.size());
            return true;
        } catch (RestClientException e) {
            logger.warn("Failed to connect to Inventory Service: {}. Order will proceed without inventory deduction.", e.getMessage());
            return false;
        }
    }

    /**
     * Get all inventory items from Inventory Service.
     */
    public List<InventoryItemDTO> getAllInventoryItems() {
        try {
            String url = inventoryServiceUrl + "/api/inventory";
            InventoryItemDTO[] items = restTemplate.getForObject(url, InventoryItemDTO[].class);
            if (items != null) {
                logger.info("Retrieved {} inventory items from Inventory Service", items.length);
                return Arrays.asList(items);
            }
            return Collections.emptyList();
        } catch (RestClientException e) {
            logger.warn("Failed to fetch inventory items: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Get a specific inventory item by ID from Inventory Service.
     */
    public InventoryItemDTO getInventoryItemById(Long itemId) {
        try {
            String url = inventoryServiceUrl + "/api/inventory/" + itemId;
            return restTemplate.getForObject(url, InventoryItemDTO.class);
        } catch (RestClientException e) {
            logger.warn("Failed to fetch inventory item {}: {}", itemId, e.getMessage());
            return null;
        }
    }

    /**
     * Check if a specific inventory item has enough stock.
     */
    public boolean checkStock(String itemName, int requiredQuantity) {
        try {
            String url = inventoryServiceUrl + "/api/inventory/check-stock?itemName=" + itemName + "&quantity=" + requiredQuantity;
            Boolean result = restTemplate.getForObject(url, Boolean.class);
            return result != null && result;
        } catch (RestClientException e) {
            logger.warn("Failed to check inventory stock for {}: {}", itemName, e.getMessage());
            return true; // Allow order to proceed if inventory service is unreachable
        }
    }
}
