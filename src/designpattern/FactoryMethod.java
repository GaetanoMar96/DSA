package designpattern;

public class FactoryMethod {

    /*
    * Product is the interface for the products created by the factory.
    * ConcreteProductA and ConcreteProductB are concrete implementations of the Product interface.
    * Creator is the interface for the factory method.
    * ConcreteCreatorA and ConcreteCreatorB are concrete implementations of the Creator interface,
    * each providing a different implementation of the factory method
    * */

    interface Product {
        void create();
    }

    static class ConcreteProductA implements Product {
        @Override
        public void create() {
            System.out.println("Product A created.");
        }
    }

    static class ConcreteProductB implements Product {
        @Override
        public void create() {
            System.out.println("Product B created.");
        }
    }

    interface Creator {
        Product createProduct();
    }

    static class ConcreteCreatorA implements Creator {
        @Override
        public Product createProduct() {
            return new ConcreteProductA();
        }
    }

    static class ConcreteCreatorB implements Creator {
        @Override
        public Product createProduct() {
            return new ConcreteProductB();
        }
    }

    public static void main(String[] args) {
        Creator creatorA = new ConcreteCreatorA();
        Product productA = creatorA.createProduct();
        productA.create();

        Creator creatorB = new ConcreteCreatorB();
        Product productB = creatorB.createProduct();
        productB.create();
    }

}
