class InventoryItem {
  final int id;
  final String category;
  final bool isLowStock;
  final String name;
  final int quantity;

  InventoryItem({
    required this.id,
    required this.category,
    required this.isLowStock,
    required this.name,
    required this.quantity,
  });

  factory InventoryItem.fromJson(Map<String, dynamic> json) {
    return InventoryItem(
      id: json['id'] as int,
      category: json['category'] as String,
      isLowStock:
          json['low_stock'] ==
          1, // Assuming 1 means true based on standard INT boolean representation
      name: json['name'] as String,
      quantity: json['quantity'] as int,
    );
  }
}
