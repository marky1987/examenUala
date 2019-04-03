package Model;

import Service.ProductService;
import Service.impl.ProductServiceImpl;
import Service.impl.UserServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import repository.ProductRepository;
import Enum.*;
import repository.UserRepository;
import util.DateUtil;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UserShould {

    ProductServiceImpl productService;
    UserServiceImpl userService;
    ProductRepository productRepository;
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        productRepository = Mockito.mock(ProductRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(productRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Product(1, 2015, "Batman Dark Nigh Rises", 200, 1 , false ),
                        new Product(2, 2004, "Lost", 43, 6 , false ),
                        new Product(3, 1972, "The Godfather", 172, 1 , true ),
                        new Product(4, 2008, "(Unnoficial) Bananaz", 92, 1 , false )

                )
        );

        Mockito.when(productRepository.findById(1)).thenReturn(
                new Product(1, 2015, "Batman Dark Nigh Rises", 200, 1 , false )
        );
        Mockito.when(productRepository.findById(2)).thenReturn(
                        new Product(2, 2004, "Lost", 43, 6 , false )
        );
        Mockito.when(productRepository.findById(3)).thenReturn(
                        new Product(3, 1972, "The Godfather", 172, 1 , false )
        );
        Mockito.when(productRepository.findById(4)).thenReturn(
                        new Product(4, 2008, "(Unnoficial) Bananaz", 92, 1 , false )
        );
        productService = new ProductServiceImpl(productRepository);

        Collection<Product> productToFirstUser = new ArrayList<>();
        productToFirstUser.add(productRepository.findById(1));
        productToFirstUser.add(productRepository.findById(4));
        Collection<Product> productToSecondUser = new ArrayList<>();
        productToFirstUser.add(productRepository.findById(1));
        productToFirstUser.add(productRepository.findById(4));


        Collection<String> notificationEnumsPrefeerFirstUser = new ArrayList<String>();
        notificationEnumsPrefeerFirstUser.add(NotificationChannelEnum.PHONE_NOTIFICATION.toString());
        Collection<String> notificationEnumsPrefeerSecondUser = new ArrayList<String>();
        notificationEnumsPrefeerSecondUser.add(NotificationChannelEnum.PHONE_NOTIFICATION.toString());


        Mockito.when(userRepository.findAll()).thenReturn(
                Arrays.asList(
                        new User(1, MoodEnum.HAPPY, 50.00, notificationEnumsPrefeerFirstUser, productToFirstUser),
                        new User(2, MoodEnum.SAD, 50.00, notificationEnumsPrefeerSecondUser, productToSecondUser)
                )
        );
    }

    @Test
    public void know_if_user_is_antique() {
        Collection<User> users = userRepository.findAll();
        Assert.assertTrue(users.stream().anyMatch(user -> user.getHistory().stream().anyMatch(p -> DateUtil.isOldProduct(p.getYearOfPremiere()))));
    }

    @Test
    public void fetch_recommend_movies_by_melancolic_mood() {
        Collection<Product> productsRecommendByMelancolicMood = productService.recommendByMood(MoodEnum.MELANCOLIC);
        List<Integer> productsId = productsRecommendByMelancolicMood.stream().map(p -> p.getId()).collect(Collectors.toList());
        assertThat(productsId, CoreMatchers.is(Arrays.asList(2,3,4)));
    }

    @Test
    public void fetch_recommend_movies_by_happy_mood() {
        Collection<Product> productsRecommendByHappyMood = productService.recommendByMood(MoodEnum.HAPPY);
        List<Integer> productsHappyId = productsRecommendByHappyMood.stream().map(p -> p.getId()).collect(Collectors.toList());
        assertThat(productsHappyId, CoreMatchers.is(Arrays.asList(1,2,3,4)));
    }

    @Test
    public void fetch_recommend_movies_by_sad_mood() {
        Collection<Product> productsRecommendBySadMood = productService.recommendByMood(MoodEnum.SAD);
        List<Integer> productsSadId = productsRecommendBySadMood.stream().map(p -> p.getId()).collect(Collectors.toList());
        assertThat(productsSadId, CoreMatchers.is(Arrays.asList(2,4)));
    }

    @Test
    public void suscription_debit_that_account_client() {
        Suscription suscription = new Suscription(9.99);
        userRepository.findAll().stream().forEach(user -> user.setCredit(user.getCredit() - suscription.getPriceSuscription()));
        List<Double> credits = userRepository.findAll().stream().map(u ->u.getCredit()).collect(Collectors.toList());
        assertThat(credits, CoreMatchers.is(Arrays.asList(40.01, 40.01)));
    }

    @Test
    public void know_if_user_watch_some_interesting() {
        assertTrue(userRepository.findAll().stream().anyMatch(user -> user.getHistory().stream().anyMatch(product -> product.getTitle().toLowerCase().contains("unnoficial")) ||
                userRepository.findAll().stream().anyMatch(user2 -> user2.getHistory().stream().anyMatch(product -> product.isHaveOscar()))));
    }
}