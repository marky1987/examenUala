package Service.impl;

import Model.Product;
import Service.ProductService;
import Enum.*;
import repository.ProductRepository;
import util.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Collection<Product> recommendByMood(MoodEnum mood) {
            Collection<Product> productRecommended = new ArrayList<Product>();
            if(mood.equals(MoodEnum.HAPPY))
                productRecommended = productRepository.findAll();
            if(mood.equals(MoodEnum.SAD))
                productRecommended = productRepository.findAll().stream().filter(m -> m.getTime() < 120).collect(Collectors.toList());
            if(mood.equals(MoodEnum.MELANCOLIC))
                productRecommended = productRepository.findAll().stream().filter(m -> LocalDateTime.now().getYear() - m.getYearOfPremiere() > 10).collect(Collectors.toList());
        return productRecommended;
        }

        public boolean isInteresting(){
            return (productRepository.findAll().stream().anyMatch(m -> m.getSeason() > 4)) ||
                    (productRepository.findAll().stream().anyMatch(m -> m.isHaveOscar()) || (productRepository.findAll().stream().anyMatch(m -> m.getTitle().toLowerCase().contains("unnoficial"))));
        }

        public boolean isOld(int year){
            return DateUtil.isOldProduct(year);
        }

}
