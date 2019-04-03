package repository;

import Model.Product;

import java.util.Collection;

public interface ProductRepository {
    Product findById(Integer id);
    Collection<Product> findAll();
}
