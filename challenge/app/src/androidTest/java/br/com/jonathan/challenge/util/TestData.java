package br.com.jonathan.challenge.util;

import java.util.Arrays;
import java.util.List;

import br.com.jonathan.challenge.model.Product;
import br.com.jonathan.challenge.model.User;

public class TestData {

    static final Product PRODUCT_1 = new Product( "brand", "Lipstick", "image url", "Baton", "Descrição desse produto");
    static final Product PRODUCT_2 = new Product( "brand 2", "Lipstick 2", "image url 2", "Marcador", "Outra descrição de produto");

    public static final List<Product> PRODUCTS = Arrays.asList(PRODUCT_1, PRODUCT_2);

    static final User USER_1 = new User("user fullname", "email@gmail.com", "myPassword");
    static final User USER_2 = new User("user2 fullname", "user2email@gmail.com", "myPassword");

    public static final List<User> USERS = Arrays.asList(USER_1, USER_2);
}
