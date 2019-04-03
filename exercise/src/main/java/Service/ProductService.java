package Service;

import Model.Product;
import Enum.MoodEnum;

import java.util.Collection;

public interface ProductService {
    Collection<Product> recommendByMood(MoodEnum mood);
}
