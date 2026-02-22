import 'package:dio/dio.dart';
import '../models/inventory_item.dart';

class InventoryService {
  final Dio _dio = Dio();

  static const String _baseUrl = 'http://localhost:8087';
  static const String _inventoryEndpoint = '/api/inventory';

  Future<List<InventoryItem>> fetchInventory() async {
    try {
      final response = await _dio.get('$_baseUrl$_inventoryEndpoint');

      if (response.statusCode == 200) {
        final List<dynamic> data = response.data;
        return data.map((e) => InventoryItem.fromJson(e)).toList();
      } else {
        throw Exception('Failed to load inventory: ${response.statusCode}');
      }
    } on DioException catch (e) {
      if (e.response != null) {
        throw Exception(
          'Failed to load inventory: ${e.response?.statusCode} - ${e.response?.statusMessage}',
        );
      } else {
        throw Exception('Failed to load inventory: ${e.message}');
      }
    } catch (e) {
      throw Exception('Failed to load inventory: $e');
    }
  }
}
