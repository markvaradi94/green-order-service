//package ro.asis.green.order.service.bootstrap;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import ro.asis.green.order.service.model.entity.GreenBag;
//import ro.asis.green.order.service.model.entity.OrderEntity;
//import ro.asis.green.order.service.model.enums.OrderStatus;
//import ro.asis.green.order.service.repository.OrderRepository;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class DataLoader implements CommandLineRunner {
//
//    private final OrderRepository repository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        List<OrderEntity> orderEntities = repository.saveAll(List.of(
//            OrderEntity.builder()
//                .id("0")
//                .clientId("0")
//                .providerId("0")
//                .status(OrderStatus.PLACED)
//                .bags(List.of(
//                        GreenBag.builder()
//                                .id("0")
//                                .description("Goood bag")
//                                .price(420.0)
//                                .imageUrl("asd")
//                                .build(),
//                        GreenBag.builder()
//                                .id("1")
//                                .description("Best bag")
//                                .price(69.0)
//                                .imageUrl("asd")
//                                .build()
//                ))
//                .build(),
//                OrderEntity.builder()
//                        .id("1")
//                        .clientId("1")
//                        .providerId("1")
//                        .status(OrderStatus.PREPARING)
//                        .bags(List.of(
//                                GreenBag.builder()
//                                        .id("2")
//                                        .description("Goood bag")
//                                        .price(420.0)
//                                        .imageUrl("asd")
//                                        .build(),
//                                GreenBag.builder()
//                                        .id("3")
//                                        .description("Magic bag")
//                                        .price(69.0)
//                                        .imageUrl("asd")
//                                        .build()
//                        ))
//                        .build(),
//                OrderEntity.builder()
//                        .id("3")
//                        .clientId("3")
//                        .providerId("3")
//                        .status(OrderStatus.READY)
//                        .bags(List.of(
//                                GreenBag.builder()
//                                        .id("4")
//                                        .description("Very good bag")
//                                        .price(420.0)
//                                        .imageUrl("asd")
//                                        .build(),
//                                GreenBag.builder()
//                                        .id("5")
//                                        .description("Bag bag")
//                                        .price(69.0)
//                                        .imageUrl("asd")
//                                        .build()
//                        ))
//                        .build()
//                ));
//    }
//}
