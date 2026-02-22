import 'package:flutter/material.dart';
import '../models/inventory_item.dart';
import '../services/inventory_service.dart';

class DashboardScreen extends StatefulWidget {
  const DashboardScreen({super.key});

  @override
  State<DashboardScreen> createState() => _DashboardScreenState();
}

class _DashboardScreenState extends State<DashboardScreen> {
  final InventoryService _inventoryService = InventoryService();
  late Future<List<InventoryItem>> _inventoryFuture;

  @override
  void initState() {
    super.initState();
    _inventoryFuture = _inventoryService.fetchInventory();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFF1E1E2C),
      body: Row(
        children: [
          NavigationRail(
            backgroundColor: const Color(0xFF27293D),
            selectedIndex: 0,
            onDestinationSelected: (int index) {},
            labelType: NavigationRailLabelType.all,
            leading: const Padding(
              padding: EdgeInsets.all(8.0),
              child: Icon(Icons.dashboard, color: Colors.blueAccent, size: 30),
            ),
            destinations: const [
              NavigationRailDestination(
                icon: Icon(Icons.inventory_2_outlined, color: Colors.white70),
                selectedIcon: Icon(Icons.inventory_2, color: Colors.blueAccent),
                label: Text(
                  'Inventory',
                  style: TextStyle(color: Colors.white70),
                ),
              ),
              NavigationRailDestination(
                icon: Icon(Icons.analytics_outlined, color: Colors.white70),
                selectedIcon: Icon(Icons.analytics, color: Colors.blueAccent),
                label: Text('Reports', style: TextStyle(color: Colors.white70)),
              ),
              NavigationRailDestination(
                icon: Icon(Icons.settings_outlined, color: Colors.white70),
                selectedIcon: Icon(Icons.settings, color: Colors.blueAccent),
                label: Text(
                  'Settings',
                  style: TextStyle(color: Colors.white70),
                ),
              ),
            ],
          ),
          // Main Content
          Expanded(
            child: Padding(
              padding: const EdgeInsets.all(24.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  // Header
                  const Text(
                    "Dashboard",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 32,
                      fontWeight: FontWeight.bold,
                      letterSpacing: 1.2,
                    ),
                  ),
                  const SizedBox(height: 20),
                  // Search and Filter Bar
                  Container(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 16,
                      vertical: 8,
                    ),
                    decoration: BoxDecoration(
                      color: const Color(0xFF27293D),
                      borderRadius: BorderRadius.circular(12),
                    ),
                    child: Row(
                      children: [
                        const Icon(Icons.search, color: Colors.white54),
                        const SizedBox(width: 10),
                        const Expanded(
                          child: TextField(
                            style: TextStyle(color: Colors.white),
                            decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: "Search inventory...",
                              hintStyle: TextStyle(color: Colors.white38),
                            ),
                          ),
                        ),
                        IconButton(
                          icon: const Icon(
                            Icons.filter_list,
                            color: Colors.white54,
                          ),
                          onPressed: () {},
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 24),
                  // Inventory Table
                  Expanded(
                    child: FutureBuilder<List<InventoryItem>>(
                      future: _inventoryFuture,
                      builder: (context, snapshot) {
                        if (snapshot.connectionState ==
                            ConnectionState.waiting) {
                          return const Center(
                            child: CircularProgressIndicator(),
                          );
                        } else if (snapshot.hasError) {
                          return Center(
                            child: Text(
                              'Error: ${snapshot.error}',
                              style: const TextStyle(color: Colors.red),
                            ),
                          );
                        } else if (!snapshot.hasData ||
                            snapshot.data!.isEmpty) {
                          return const Center(
                            child: Text(
                              'No items found',
                              style: TextStyle(color: Colors.white),
                            ),
                          );
                        }

                        final items = snapshot.data!;
                        return Container(
                          decoration: BoxDecoration(
                            color: const Color(0xFF27293D),
                            borderRadius: BorderRadius.circular(16),
                            boxShadow: [
                              BoxShadow(
                                color: Colors.black.withOpacity(0.2),
                                blurRadius: 10,
                                offset: const Offset(0, 5),
                              ),
                            ],
                          ),
                          child: ClipRRect(
                            borderRadius: BorderRadius.circular(16),
                            child: SingleChildScrollView(
                              scrollDirection: Axis.vertical,
                              child: DataTable(
                                headingRowColor: MaterialStateProperty.all(
                                  const Color(0xFF32324A),
                                ),
                                dataRowColor: MaterialStateProperty.all(
                                  const Color(0xFF27293D),
                                ),
                                dividerThickness: 0.5,
                                horizontalMargin: 20,
                                columnSpacing: 30,
                                columns: const [
                                  DataColumn(
                                    label: Text(
                                      'ID',
                                      style: TextStyle(
                                        color: Colors.white70,
                                        fontWeight: FontWeight.bold,
                                      ),
                                    ),
                                  ),
                                  DataColumn(
                                    label: Text(
                                      'Category',
                                      style: TextStyle(
                                        color: Colors.white70,
                                        fontWeight: FontWeight.bold,
                                      ),
                                    ),
                                  ),
                                  DataColumn(
                                    label: Text(
                                      'Status',
                                      style: TextStyle(
                                        color: Colors.white70,
                                        fontWeight: FontWeight.bold,
                                      ),
                                    ),
                                  ),
                                  DataColumn(
                                    label: Text(
                                      'Name',
                                      style: TextStyle(
                                        color: Colors.white70,
                                        fontWeight: FontWeight.bold,
                                      ),
                                    ),
                                  ),
                                  DataColumn(
                                    label: Text(
                                      'Quantity',
                                      style: TextStyle(
                                        color: Colors.white70,
                                        fontWeight: FontWeight.bold,
                                      ),
                                    ),
                                  ),
                                  DataColumn(
                                    label: Text(
                                      'Actions',
                                      style: TextStyle(
                                        color: Colors.white70,
                                        fontWeight: FontWeight.bold,
                                      ),
                                    ),
                                  ),
                                ],
                                rows: items.map((item) {
                                  return DataRow(
                                    cells: [
                                      DataCell(
                                        Text(
                                          '#${item.id}',
                                          style: const TextStyle(
                                            color: Colors.white70,
                                          ),
                                        ),
                                      ),
                                      DataCell(
                                        Container(
                                          padding: const EdgeInsets.symmetric(
                                            horizontal: 10,
                                            vertical: 5,
                                          ),
                                          decoration: BoxDecoration(
                                            color: Colors.blueAccent
                                                .withOpacity(0.2),
                                            borderRadius: BorderRadius.circular(
                                              8,
                                            ),
                                          ),
                                          child: Text(
                                            item.category,
                                            style: const TextStyle(
                                              color: Colors.blueAccent,
                                            ),
                                          ),
                                        ),
                                      ),
                                      DataCell(
                                        Container(
                                          padding: const EdgeInsets.symmetric(
                                            horizontal: 10,
                                            vertical: 5,
                                          ),
                                          decoration: BoxDecoration(
                                            color: item.isLowStock
                                                ? Colors.redAccent.withOpacity(
                                                    0.2,
                                                  )
                                                : Colors.greenAccent
                                                      .withOpacity(0.2),
                                            borderRadius: BorderRadius.circular(
                                              8,
                                            ),
                                          ),
                                          child: Text(
                                            item.isLowStock
                                                ? 'Low Stock'
                                                : 'In Stock',
                                            style: TextStyle(
                                              color: item.isLowStock
                                                  ? Colors.redAccent
                                                  : Colors.greenAccent,
                                              fontWeight: FontWeight.w600,
                                            ),
                                          ),
                                        ),
                                      ),
                                      DataCell(
                                        Text(
                                          item.name,
                                          style: const TextStyle(
                                            color: Colors.white,
                                            fontWeight: FontWeight.w500,
                                          ),
                                        ),
                                      ),
                                      DataCell(
                                        Text(
                                          '${item.quantity}',
                                          style: const TextStyle(
                                            color: Colors.white70,
                                          ),
                                        ),
                                      ),
                                      DataCell(
                                        Row(
                                          children: [
                                            IconButton(
                                              icon: const Icon(
                                                Icons.edit,
                                                color: Colors.white54,
                                                size: 20,
                                              ),
                                              onPressed: () {},
                                            ),
                                            IconButton(
                                              icon: const Icon(
                                                Icons.delete,
                                                color: Colors.redAccent,
                                                size: 20,
                                              ),
                                              onPressed: () {},
                                            ),
                                          ],
                                        ),
                                      ),
                                    ],
                                  );
                                }).toList(),
                              ),
                            ),
                          ),
                        );
                      },
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
