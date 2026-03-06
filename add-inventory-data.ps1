# Sample Hotel Inventory Data
$baseUrl = "https://inventorymanagementservice-inventory.onrender.com/api/inventory"

Write-Host "Adding Hotel Inventory Items..." -ForegroundColor Cyan
Write-Host "===========================" -ForegroundColor Cyan

$items = @(
    @{ name = "Luxury Bath Towels"; category = "Linens"; quantity = 250; lowStock = 50 },
    @{ name = "Standard Hand Towels"; category = "Linens"; quantity = 400; lowStock = 100 },
    @{ name = "Queen Size Bed Sheets"; category = "Linens"; quantity = 150; lowStock = 30 },
    @{ name = "Shampoo - 50ml bottles"; category = "Toiletries"; quantity = 500; lowStock = 100 },
    @{ name = "Conditioner - 50ml bottles"; category = "Toiletries"; quantity = 480; lowStock = 100 },
    @{ name = "Body Wash - 50ml bottles"; category = "Toiletries"; quantity = 520; lowStock = 100 },
    @{ name = "Bar Soap"; category = "Toiletries"; quantity = 1000; lowStock = 200 },
    @{ name = "Toothbrush Kits"; category = "Amenities"; quantity = 300; lowStock = 50 },
    @{ name = "Shaving Kits"; category = "Amenities"; quantity = 200; lowStock = 40 },
    @{ name = "Sewing Kits"; category = "Amenities"; quantity = 150; lowStock = 30 },
    @{ name = "Coffee Pods - Espresso"; category = "Food & Beverage"; quantity = 600; lowStock = 150 },
    @{ name = "Coffee Pods - Decaf"; category = "Food & Beverage"; quantity = 400; lowStock = 100 },
    @{ name = "English Breakfast Tea Bags"; category = "Food & Beverage"; quantity = 1000; lowStock = 200 },
    @{ name = "Bottled Water - 500ml"; category = "Food & Beverage"; quantity = 1200; lowStock = 300 },
    @{ name = "Mini Fridge Snacks - Chips"; category = "Food & Beverage"; quantity = 150; lowStock = 40 },
    @{ name = "Room Keys (RFID Cards)"; category = "Equipment"; quantity = 800; lowStock = 200 },
    @{ name = "Ironing Boards"; category = "Equipment"; quantity = 40; lowStock = 10 },
    @{ name = "Hair Dryers"; category = "Equipment"; quantity = 35; lowStock = 8 },
    @{ name = "All-Purpose Cleaner - 5L"; category = "Housekeeping"; quantity = 25; lowStock = 5 },
    @{ name = "Glass Cleaner - 5L"; category = "Housekeeping"; quantity = 20; lowStock = 5 },
    @{ name = "Vacuum Bags"; category = "Housekeeping"; quantity = 50; lowStock = 15 }
)

foreach ($item in $items) {
    $jsonBody = $item | ConvertTo-Json
    try {
        $response = Invoke-RestMethod -Uri $baseUrl -Method Post -Body $jsonBody -ContentType "application/json"
        Write-Host "[OK] Added: $($item.name) (Category: $($item.category), Qty: $($item.quantity))" -ForegroundColor Green
    }
    catch {
        Write-Host "[ERROR] Failed to add: $($item.name)" -ForegroundColor Red
        Write-Host $_.Exception.Message -ForegroundColor Gray
    }
}

Write-Host ""
Write-Host "===========================" -ForegroundColor Cyan
Write-Host "Data Import Complete!" -ForegroundColor Green

# Verify items
$finalItems = Invoke-RestMethod -Uri $baseUrl -Method Get
Write-Host "Total Inventory Items: $($finalItems.Count)" -ForegroundColor Yellow
